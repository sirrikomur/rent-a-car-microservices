package bootcamps.turkcell.paymentservice.external.services.iyzico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCard {
    private String cardHolderName;
    private String cardNumber;
    private String expireYear;
    private String expireMonth;
    private String cvc;
    private Integer registerCard;
    private String cardAlias;
    private String cardToken;
    private String cardUserKey;
    private Map<String, String> metadata;
    private String consumerToken;
    private boolean registerConsumerCard;
    private String ucsToken;
}
