package bootcamps.turkcell.rentalservice.business.rules;

import bootcamps.turkcell.common.utilities.constants.ExceptionDetail;
import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.exceptions.business.BusinessException;
import bootcamps.turkcell.rentalservice.api.clients.inventory.car.CarClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    //private final RentalRepository rentalRepository;
    private final CarClient carClient;

    public void carCannotBeRentedWhenRented(UUID carId) {
        var car = carClient.getById(carId);
        if (car.getCarState().equals(CarState.RENTED)) {
            throw new BusinessException(ExceptionDetail.Messages.Rental.CAR_RENTED);
        }
    }

    public void carCannotBeFinishedWhenNotRented(UUID carId) {
        var car = carClient.getById(carId);
        if (!car.getCarState().equals(CarState.RENTED)) {
            throw new BusinessException(ExceptionDetail.Messages.Rental.NOT_EXISTS);
        }
    }

    public void carCannotBeRentedWhenMaintenance(UUID carId) {
        var car = carClient.getById(carId);
        if (car.getCarState().equals(CarState.MAINTENANCE)) {
            throw new BusinessException(ExceptionDetail.Messages.Maintenance.CAR_UNDER_MAINTENANCE);
        }
    }

    public void carCannotBeRentedWhenNotAvailable(UUID carId) {
        var car = carClient.getById(carId);
        if (!car.getCarState().equals(CarState.AVAILABLE)) {
            throw new BusinessException(ExceptionDetail.Messages.Car.NOT_AVAILABLE);
        }
    }



    /*public void carCannotBeRentedWhenRented(CarState carState) {
        if (carState.equals(CarState.RENTED)) {
            throw new BusinessException(ExceptionDetail.Messages.Rental.CAR_RENTED);
        }
    }

    public void carCannotBeFinishedWhenNotRented(UUID carId) {
        if (!rentalRepository.existsByCarId(carId)) {
            throw new BusinessException(ExceptionDetail.Messages.Rental.NOT_EXISTS);
        }
    }

    public void carCannotBeRentedWhenMaintenance(CarState carState) {
        if (carState.equals(CarState.MAINTENANCE)) {
            throw new BusinessException(ExceptionDetail.Messages.Maintenance.CAR_UNDER_MAINTENANCE);
        }
    }

    public void carCannotBeRentedWhenNotAvailable(CarState carState) {
        if (!carState.equals(CarState.AVAILABLE)) {
            throw new BusinessException(ExceptionDetail.Messages.Car.NOT_AVAILABLE);
        }
    }*/

    /*public void ensureCarIsAvailable(UUID carId) {
        var response = client.checkIfCarAvailable(carId);
        if (!response.isSuccess()) {
            throw new BusinessException(response.getMessage());
        }
    }*/
}
