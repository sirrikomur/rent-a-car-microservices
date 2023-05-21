package bootcamps.turkcell.rentalservice.business.managers;


import bootcamps.turkcell.common.events.inventory.CarStateUpdatedEvent;
import bootcamps.turkcell.common.utilities.brokers.kafka.producers.KafkaProducer;
import bootcamps.turkcell.common.utilities.constants.Topics;
import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.formats.Conversion;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.common.utilities.operations.Mathematics;
import bootcamps.turkcell.common.utilities.rules.CrudRules;
import bootcamps.turkcell.rentalservice.api.clients.inventory.car.CarClient;
import bootcamps.turkcell.rentalservice.business.dtos.requests.rental.create.CreateRentalRequest;
import bootcamps.turkcell.rentalservice.business.dtos.requests.rental.update.UpdateRentalRequest;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.create.CreateRentalResponse;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.get.GetAllRentalsResponse;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.get.GetRentalResponse;
import bootcamps.turkcell.rentalservice.business.dtos.responses.rental.update.UpdateRentalResponse;
import bootcamps.turkcell.rentalservice.business.rules.RentalBusinessRules;
import bootcamps.turkcell.rentalservice.business.services.RentalService;
import bootcamps.turkcell.rentalservice.domain.entities.Rental;
import bootcamps.turkcell.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final RentalBusinessRules businessRules;
    private final CrudRules crudRules;
    private final KafkaProducer producer;
    private final ModelMapperService mapper;
    private final CarClient carClient;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        return rentals.stream().map(rental -> mapper.forResponse().map(rental, GetAllRentalsResponse.class)).toList();
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        Rental rental = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(rental, GetRentalResponse.class);
    }


    @Override
    public CreateRentalResponse create(CreateRentalRequest request) {
        businessRules.carCannotBeRentedWhenRented(request.getCarId());
        businessRules.carCannotBeRentedWhenMaintenance(request.getCarId());
        businessRules.carCannotBeRentedWhenNotAvailable(request.getCarId());

        Rental rental = mapper.forRequest().map(request, Rental.class);
        String datePattern = "d/M/yyyy";
        LocalDate startDate = Conversion.date(request.getStartDate(), datePattern);
        LocalDate endDate = Conversion.date(request.getEndDate(), datePattern);

        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setCompleted(false);
        carClient.changeState(request.getCarId(), CarState.RENTED);

        var createdRental = repository.save(rental);

        producer.sendMessage(new CarStateUpdatedEvent(request.getCarId(), CarState.RENTED), Topics.Inventory.CAR_STATE_UPDATED);
        return mapper.forResponse().map(createdRental, CreateRentalResponse.class);
    }

    @Override
    public GetRentalResponse finishRental(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        var carId = repository.findById(id).orElseThrow().getCarId();

        businessRules.carCannotBeFinishedWhenNotRented(carId);

        Rental rental = repository.findById(id).orElseThrow();
        rental.setEndDate(LocalDate.now());
        rental.setCompleted(true);
        repository.save(rental);

        carClient.changeState(carId, CarState.AVAILABLE);

        //createInvoiceRequest(rentalRequest, rental);

        return mapper.forResponse().map(rental, GetRentalResponse.class);
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest rentalRequest) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);

        Rental rental = mapper.forRequest().map(rentalRequest, Rental.class);
        rental.setId(id);
        repository.save(rental);
        return mapper.forResponse().map(rental, UpdateRentalResponse.class);
    }

    @Override
    public void delete(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        carClient.changeState(repository.findById(id).orElseThrow().getCarId(), CarState.AVAILABLE);
        repository.deleteById(id);
    }

    /*private void createInvoiceRequest(CreateRentalRequest rentalRequest, Rental rental) {
        GetCarResponse carResponse = carService.getById(rentalRequest.getCarId());
        double dailyRental = carService.getById(rentalRequest.getCarId()).getDailyRental();
        double rentalPrice = calculateRentalPrice(Calculation.daysBetween(rental.getStartDate(), rental.getEndDate()), dailyRental, Values.TaxRate.VAT);

        //pay(rentalPrice);

        invoiceService.create(new CreateInvoiceRequest(
                carResponse.getBrandName(),
                carResponse.getModelName(),
                carResponse.getModelYear(),
                carResponse.getLicensePlate(),
                rental.getStartDate(),
                rental.getEndDate(),
                carResponse.getDailyRental(),
                rentalPrice));
    }*/

    /*private void pay(double rentalPrice) {
        paymentService.pay(new Card(), rentalPrice);
    }*/

    private double calculateRentalPrice(int numberOfDaysRented, double dailyPrice, double taxRate) {
        return Mathematics.addPercentOf(dailyPrice, taxRate) * numberOfDaysRented;
    }
}
