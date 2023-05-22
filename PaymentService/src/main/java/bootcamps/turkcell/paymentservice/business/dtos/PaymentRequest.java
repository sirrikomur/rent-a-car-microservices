package bootcamps.turkcell.paymentservice.business.dtos;

import bootcamps.turkcell.paymentservice.domain.entities.Card;
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
