package bootcamps.turkcell.maintenanceservice.repository;

import bootcamps.turkcell.maintenanceservice.domain.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {
    boolean existsByCarIdAndEndDateNull(UUID carId);
    Maintenance findByCarIdAndEndDateNull(UUID carId);
}
