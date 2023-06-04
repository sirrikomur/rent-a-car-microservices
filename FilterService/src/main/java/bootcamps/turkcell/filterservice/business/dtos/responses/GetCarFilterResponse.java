package bootcamps.turkcell.filterservice.business.dtos.responses;

import bootcamps.turkcell.common.models.enums.inventory.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCarFilterResponse {
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
