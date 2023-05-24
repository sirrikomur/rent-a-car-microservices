package bootcamps.turkcell.common.utilities.dtos.inventory.requests.model.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateModelRequest {
    @NotNull
    private UUID brandId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}
