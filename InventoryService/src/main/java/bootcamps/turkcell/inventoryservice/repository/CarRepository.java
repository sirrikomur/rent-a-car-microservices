package bootcamps.turkcell.inventoryservice.repository;

import bootcamps.turkcell.inventoryservice.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    boolean existsByLicensePlateIgnoreCase(String licensePlate);
}
