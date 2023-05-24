package bootcamps.turkcell.common.utilities.dtos.inventory.responses.car.update;

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
public class UpdateCarResponse {
    private UUID id;
    private UUID modelId;
    private short modelYear;
    private String licensePlate;
    private double dailyRental;
    private CarState state;
}
