package bootcamps.turkcell.common.models.dtos.invoice.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvoiceRequest {
    private String modelName;
    private String brandName;
    private short modelYear;
    private String licencePlate;
    private double dailyRental;
}
