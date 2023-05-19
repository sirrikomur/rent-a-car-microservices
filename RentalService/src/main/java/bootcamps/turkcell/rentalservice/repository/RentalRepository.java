package bootcamps.turkcell.rentalservice.repository;

import bootcamps.turkcell.rentalservice.domain.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {
    boolean existsByCarId(UUID carId);
    boolean existsByCarIdAndEndDateNull(UUID carId);
    Rental findByCarIdAndEndDateNull(UUID carId);

    Rental findByCarId(UUID carId);
}
