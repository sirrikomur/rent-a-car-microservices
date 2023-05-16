package bootcamps.turkcell.inventoryservice.repository;

import bootcamps.turkcell.inventoryservice.domain.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ModelRepository extends JpaRepository<Model, UUID> {
    boolean existsByNameIgnoreCase(String name);
}
