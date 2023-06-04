package bootcamps.turkcell.common.models.dtos.payment.requests;

import bootcamps.turkcell.common.models.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private Card card;
    private double amount;
}
