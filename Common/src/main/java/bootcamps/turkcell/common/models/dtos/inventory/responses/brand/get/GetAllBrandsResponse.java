package bootcamps.turkcell.common.models.dtos.inventory.responses.brand.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBrandsResponse {
    private UUID id;
    private String name;
}
