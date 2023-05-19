package bootcamps.turkcell.rentalservice.business.managers;


import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.formats.Conversion;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.common.utilities.operations.Mathematics;
import bootcamps.turkcell.common.utilities.rules.CrudRules;
import bootcamps.turkcell.rentalservice.api.clients.car.CarClient;
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
    public GetRentalResponse finishRental(UUID carId) {
        businessRules.carCannotBeFinishedWhenNotRented(carId);
        Rental rental = repository.findByCarId(carId);
        rental.setEndDate(LocalDate.now());
        repository.save(rental);
        carClient.changeState(carId, CarState.AVAILABLE);
        return mapper.forResponse().map(rental, GetRentalResponse.class);
    }

    @Override
    public CreateRentalResponse create(CreateRentalRequest rentalRequest) {
        var car = carClient.getById(rentalRequest.getCarId());

        businessRules.carCannotBeRentedWhenRented(car.getCarState());
        businessRules.carCannotBeRentedWhenMaintenance(car.getCarState());
        businessRules.carCannotBeRentedWhenNotAvailable(car.getCarState());

        Rental rental = mapper.forRequest().map(rentalRequest, Rental.class);
        String datePattern = "d/M/yyyy";
        LocalDate startDate = Conversion.date(rentalRequest.getStartDate(), datePattern);
        LocalDate endDate = Conversion.date(rentalRequest.getEndDate(), datePattern);

        rental.setStartDate(startDate);
        rental.setEndDate(endDate);

        carClient.changeState(rentalRequest.getCarId(), CarState.RENTED);

        //createInvoiceRequest(rentalRequest, rental);

        repository.save(rental);

        return mapper.forResponse().map(rental, CreateRentalResponse.class);
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
