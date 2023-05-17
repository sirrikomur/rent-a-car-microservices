package bootcamps.turkcell.common.events.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarCreatedEvent {
    private UUID carId;
    private UUID brandId;
    private UUID modelId;
    private String brandName;
    private String modelName;
    private String licensePlate;
    private short modelYear;
    private double dailyRental;
    private String carState;
}
