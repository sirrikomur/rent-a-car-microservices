package bootcamps.turkcell.common.events.inventory;

import bootcamps.turkcell.common.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDeletedEvent implements Event {
    private UUID brandId;
}
