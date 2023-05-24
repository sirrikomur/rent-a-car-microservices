package bootcamps.turkcell.common.utilities.dtos.maintenance.responses.update;

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
public class UpdateMaintenanceResponse {
    private UUID id;
    private UUID carId;
    private String information;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}