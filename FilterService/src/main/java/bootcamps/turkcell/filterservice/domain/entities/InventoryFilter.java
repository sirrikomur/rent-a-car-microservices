package bootcamps.turkcell.filterservice.domain.entities;

import bootcamps.turkcell.common.models.enums.inventory.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryFilter {
    @Id
    private UUID id;
    private UUID carId;
    private UUID brandId;
    private UUID modelId;
    private String brandName;
    private String modelName;
    private String licensePlate;
    private short modelYear;
    private double dailyRental;

    private CarState carState;
}
