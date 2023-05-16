package bootcamps.turkcell.inventoryservice.repository;


import bootcamps.turkcell.inventoryservice.domain.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
    boolean existsByNameIgnoreCase(String name);
}
