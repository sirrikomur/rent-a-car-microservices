package bootcamps.turkcell.inventoryservice.business.dtos.responses.car.get;

import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarsResponse {
    private UUID id;
    private UUID modelId;
    private String modelName;
    private short modelYear;
    private String licensePlate;
    private CarState carState;
    private double dailyRental;
}

