package bootcamps.turkcell.inventoryservice.business.rules;



import bootcamps.turkcell.common.utilities.constants.ExceptionDetail;
import bootcamps.turkcell.common.utilities.exceptions.business.BusinessException;
import bootcamps.turkcell.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;

    public void licensePlateCannotBeRepeated(String licensePlate) {
        if (repository.existsByLicensePlateIgnoreCase(licensePlate)){
            throw new BusinessException(ExceptionDetail.Messages.Car.LICENSE_PLATE_ALREADY_EXISTS);
        }
    }
}
