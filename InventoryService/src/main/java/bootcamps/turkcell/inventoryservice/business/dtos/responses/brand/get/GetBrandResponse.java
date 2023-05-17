package bootcamps.turkcell.inventoryservice.business.dtos.responses.brand.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBrandResponse {
    private UUID id;
    private String name;
}