package bootcamps.turkcell.paymentservice.external.services.iyzico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {
    private BigDecimal price;
    private BigDecimal paidPrice;
    private String posOrderId;
    private PaymentCard paymentCard;
}
