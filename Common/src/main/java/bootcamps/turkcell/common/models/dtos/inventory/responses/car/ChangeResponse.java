package bootcamps.turkcell.common.models.dtos.inventory.responses.car;

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
public class ChangeResponse {
    private UUID carId;
    private CarState carState;
}
