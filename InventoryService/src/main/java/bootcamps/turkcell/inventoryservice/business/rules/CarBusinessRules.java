package bootcamps.turkcell.inventoryservice.business.rules;



import bootcamps.turkcell.common.utilities.constants.ExceptionDetail;
import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.exceptions.business.BusinessException;
import bootcamps.turkcell.inventoryservice.domain.entities.Car;
import bootcamps.turkcell.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;

    public void licensePlateCannotBeRepeated(String licensePlate) {
        if (repository.existsByLicensePlateIgnoreCase(licensePlate)){
            throw new BusinessException(ExceptionDetail.Messages.Car.LICENSE_PLATE_ALREADY_EXISTS);
        }
    }

    public void carStateMustAvailable(UUID id) {
        Car car = repository.findById(id).orElseThrow();
        if (!car.getState().equals(CarState.AVAILABLE)) {
            throw new BusinessException(ExceptionDetail.Messages.Car.NOT_AVAILABLE);
        }
    }
}
