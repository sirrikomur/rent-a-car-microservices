package bootcamps.turkcell.common.events.inventory;

import bootcamps.turkcell.common.events.Event;
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
public class CarStateUpdatedEvent implements Event {
    private UUID carId;
    private CarState carState;
}
