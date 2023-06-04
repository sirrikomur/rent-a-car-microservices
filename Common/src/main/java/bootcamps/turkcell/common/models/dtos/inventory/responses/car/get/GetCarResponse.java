package bootcamps.turkcell.common.models.dtos.inventory.responses.car.get;

import bootcamps.turkcell.common.models.enums.inventory.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCarResponse {
    private UUID id;
    private UUID modelId;
    private String modelName;
    private short modelYear;
    private String brandName;
    private String licensePlate;
    private CarState carState;
    private double dailyRental;
}
