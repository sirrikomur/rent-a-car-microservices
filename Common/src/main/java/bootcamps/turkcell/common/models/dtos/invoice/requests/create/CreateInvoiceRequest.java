package bootcamps.turkcell.common.models.dtos.invoice.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
    private String brandName;
    private String modelName;
    private short modelYear;
    private String licencePlate;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private double dailyRental;
    private double rentalPrice;
}


