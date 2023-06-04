package bootcamps.turkcell.common.models.dtos.rental.responses.rental.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalResponse {
    private UUID id;
    private UUID carId;
    private LocalDate startDate;
    private LocalDate endDate;
}