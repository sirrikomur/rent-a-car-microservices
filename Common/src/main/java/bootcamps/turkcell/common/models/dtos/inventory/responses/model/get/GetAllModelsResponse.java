package bootcamps.turkcell.common.models.dtos.inventory.responses.model.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllModelsResponse {
    private UUID id;
    private UUID brandId;
    private String name;
}