package bootcamps.turkcell.common.utilities.dtos.inventory.responses.brand.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandResponse {
    private UUID id;
    private String name;
}