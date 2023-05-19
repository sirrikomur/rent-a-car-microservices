package bootcamps.turkcell.rentalservice.business.dtos.responses.clients;

import bootcamps.turkcell.common.utilities.annotations.NotFutureYear;
import bootcamps.turkcell.common.utilities.constants.Regex;
import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarClientRequest {
    @NotNull
    private UUID modelId;

    @NotNull
    @Min(1885)
    @NotFutureYear
    private int modelYear;

    @Pattern(regexp = Regex.LICENSE_PLATE_TR)
    private String licensePlate;

    @NotNull
    @PositiveOrZero
    private double dailyRental;

    private CarState carState;
}
