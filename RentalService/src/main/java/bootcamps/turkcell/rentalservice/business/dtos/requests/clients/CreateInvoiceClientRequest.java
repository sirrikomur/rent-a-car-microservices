package bootcamps.turkcell.rentalservice.business.dtos.requests.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceClientRequest {
    private String brandName;
    private String modelName;
    private short modelYear;
    private String licencePlate;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private double dailyRental;
    private double rentalPrice;
}


