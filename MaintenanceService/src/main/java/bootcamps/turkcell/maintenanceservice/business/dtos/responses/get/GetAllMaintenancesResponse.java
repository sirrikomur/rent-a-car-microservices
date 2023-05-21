package bootcamps.turkcell.maintenanceservice.business.dtos.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMaintenancesResponse {
    private UUID id;
    private UUID carId;
    private String information;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
