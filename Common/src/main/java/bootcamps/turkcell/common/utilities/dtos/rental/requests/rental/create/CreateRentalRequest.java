package bootcamps.turkcell.common.utilities.dtos.rental.requests.rental.create;

import bootcamps.turkcell.common.utilities.constants.Regex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
    @NotNull
    private UUID carId;

    @NotBlank
    @Pattern(regexp = Regex.DATE_FORMAT_DAY_MONTH_YEAR)
    private String startDate;

    @Pattern(regexp = Regex.DATE_FORMAT_DAY_MONTH_YEAR)
    private String endDate;
}