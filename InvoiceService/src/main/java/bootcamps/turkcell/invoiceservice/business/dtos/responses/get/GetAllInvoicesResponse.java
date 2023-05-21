package bootcamps.turkcell.invoiceservice.business.dtos.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoicesResponse {
    private UUID id;
    private String modelName;
    private short modelYear;
    private String brandName;
    private String licencePlate;
    private double dailyRental;
    private LocalDateTime createdDate;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private double taxRate;
    private double rentalPrice;
}