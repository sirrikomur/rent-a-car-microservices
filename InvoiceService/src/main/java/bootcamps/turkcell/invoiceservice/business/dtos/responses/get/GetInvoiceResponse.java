package bootcamps.turkcell.invoiceservice.business.dtos.responses.get;

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
public class GetInvoiceResponse {
    private UUID id;
    private String modelName;
    private String brandName;
    private short modelYear;
    private String licencePlate;
    private double dailyRental;
    private LocalDateTime createdDate;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
    private double taxRate;
    private double rentalPrice;
}

