package bootcamps.turkcell.rentalservice.business.dtos.responses.clients;

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
public class GetCarClientResponse {
    private UUID carId;
    private UUID modelId;
    private String modelName;
    private short modelYear;
    private String brandName;
    private String licensePlate;
    private CarState carState;
    private double dailyRental;
}
