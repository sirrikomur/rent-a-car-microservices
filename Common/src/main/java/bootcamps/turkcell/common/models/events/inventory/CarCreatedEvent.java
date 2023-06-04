package bootcamps.turkcell.common.models.events.inventory;

import bootcamps.turkcell.common.models.events.Event;
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
public class CarCreatedEvent implements Event {
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
