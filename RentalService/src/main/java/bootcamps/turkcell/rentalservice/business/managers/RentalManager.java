package bootcamps.turkcell.rentalservice.business.managers;


import bootcamps.turkcell.common.models.events.inventory.CarStateUpdatedEvent;
import bootcamps.turkcell.common.utilities.brokers.kafka.producers.KafkaProducer;
import bootcamps.turkcell.common.utilities.constants.Topics;
import bootcamps.turkcell.common.utilities.constants.Values;
import bootcamps.turkcell.common.models.dtos.invoice.requests.create.CreateInvoiceRequest;
import bootcamps.turkcell.common.models.dtos.payment.requests.PaymentRequest;
import bootcamps.turkcell.common.models.dtos.rental.requests.rental.create.CreateRentalRequest;
import bootcamps.turkcell.common.models.dtos.rental.requests.rental.update.UpdateRentalRequest;
import bootcamps.turkcell.common.models.dtos.rental.responses.rental.create.CreateRentalResponse;
import bootcamps.turkcell.common.models.dtos.rental.responses.rental.get.GetAllRentalsResponse;
import bootcamps.turkcell.common.models.dtos.rental.responses.rental.get.GetRentalResponse;
import bootcamps.turkcell.common.models.dtos.rental.responses.rental.update.UpdateRentalResponse;
import bootcamps.turkcell.common.models.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.formats.Conversion;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.common.utilities.operations.Calculation;
import bootcamps.turkcell.common.utilities.operations.Mathematics;
import bootcamps.turkcell.common.utilities.rules.CrudRules;
import bootcamps.turkcell.rentalservice.api.clients.InventoryServiceClient;
import bootcamps.turkcell.rentalservice.api.clients.InvoiceServiceClient;
import bootcamps.turkcell.rentalservice.api.clients.PaymentServiceClient;
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
    private final InventoryServiceClient inventoryServiceClient;
    private final InvoiceServiceClient invoiceServiceClient;
    private final PaymentServiceClient paymentServiceClient;

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
        inventoryServiceClient.changeState(request.getCarId(), CarState.RENTED);

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

        createInvoice(carId, rental);

        inventoryServiceClient.changeState(carId, CarState.AVAILABLE);

        return mapper.forResponse().map(rental, GetRentalResponse.class);
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);

        Rental rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        repository.save(rental);
        return mapper.forResponse().map(rental, UpdateRentalResponse.class);
    }

    @Override
    public void delete(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        inventoryServiceClient.changeState(repository.findById(id).orElseThrow().getCarId(), CarState.AVAILABLE);
        repository.deleteById(id);
    }

    private void createInvoice(UUID carId, Rental rental) {
        var car = inventoryServiceClient.getById(carId);
        double dailyRental = car.getDailyRental();
        double rentalPrice = calculateRentalPrice(Calculation.daysBetween(rental.getStartDate(), rental.getEndDate()), dailyRental, Values.TaxRate.VAT);

        pay(rentalPrice);

        invoiceServiceClient.create(new CreateInvoiceRequest(
                car.getBrandName(),
                car.getModelName(),
                car.getModelYear(),
                car.getLicensePlate(),
                rental.getStartDate(),
                rental.getEndDate(),
                car.getDailyRental(),
                rentalPrice));
    }

    private void pay(double amount) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(amount);
        paymentServiceClient.create(paymentRequest);
    }

    private double calculateRentalPrice(int numberOfDaysRented, double dailyPrice, double taxRate) {
        return Mathematics.addPercentOf(dailyPrice, taxRate) * numberOfDaysRented;
    }
}
