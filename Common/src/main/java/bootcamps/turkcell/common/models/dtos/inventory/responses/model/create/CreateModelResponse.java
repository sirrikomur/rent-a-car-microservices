package bootcamps.turkcell.common.models.dtos.inventory.responses.model.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelResponse {
    private UUID id;
    private UUID brandId;
    private String name;
}