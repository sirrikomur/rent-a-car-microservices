package bootcamps.turkcell.maintenanceservice.business.rules;


import bootcamps.turkcell.common.utilities.constants.ExceptionDetail;
import bootcamps.turkcell.common.models.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.exceptions.business.BusinessException;
import bootcamps.turkcell.maintenanceservice.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository maintenanceRepository;

    public void carCannotBeFinishedWhenNotUnderMaintenance(UUID carId) {
        if (!maintenanceRepository.existsByCarIdAndEndDateNull(carId)) {
            throw new BusinessException(ExceptionDetail.Messages.Maintenance.CAR_NOT_UNDER_MAINTENANCE);
        }
    }

    public void carCannotBePutUnderMaintenanceWhenUnderMaintenance(UUID carId) {
        if (maintenanceRepository.existsByCarIdAndEndDateNull(carId)) {
            throw new BusinessException(ExceptionDetail.Messages.Maintenance.CAR_UNDER_MAINTENANCE);
        }
    }

    public void carCannotBePutUnderMaintenanceWhenNotAvailable(CarState carState) {
        if (!carState.equals(CarState.AVAILABLE)) {
            throw new BusinessException(ExceptionDetail.Messages.Car.NOT_AVAILABLE);
        }
    }
}
