package bootcamps.turkcell.filterservice.repository;

import bootcamps.turkcell.filterservice.domain.entities.CarFilter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CarFilterRepository extends MongoRepository<CarFilter, UUID> {
    CarFilter findByCarId(UUID carId);
    void deleteByCarId(UUID carId);
    void deleteAllByBrandId(UUID brandId);
    void deleteAllByModelId(UUID modelId);
}
