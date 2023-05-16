package bootcamps.turkcell.inventoryservice.business.dtos.requests.model.create;

import jakarta.validation.constraints.NotBlank;
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
public class CreateModelRequest {
    @NotBlank
    private UUID brandId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}