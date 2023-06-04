package bootcamps.turkcell.filterservice.repository;

import bootcamps.turkcell.filterservice.domain.entities.InventoryFilter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CarFilterRepository extends MongoRepository<InventoryFilter, UUID> {
    InventoryFilter findByCarId(UUID carId);
    void deleteByCarId(UUID carId);
    void deleteAllByBrandId(UUID brandId);
    void deleteAllByModelId(UUID modelId);
}
