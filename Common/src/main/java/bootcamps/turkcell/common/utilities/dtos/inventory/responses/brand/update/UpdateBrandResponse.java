package bootcamps.turkcell.common.utilities.dtos.inventory.responses.brand.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBrandResponse {
    private UUID id;
    private String name;
}
