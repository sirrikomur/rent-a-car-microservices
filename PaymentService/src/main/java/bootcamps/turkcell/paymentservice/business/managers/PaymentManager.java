package bootcamps.turkcell.paymentservice.business.managers;

import bootcamps.turkcell.common.models.dtos.payment.requests.PaymentRequest;
import bootcamps.turkcell.common.models.entities.Card;
import bootcamps.turkcell.common.models.fakes.FakeCards;
import bootcamps.turkcell.paymentservice.adapter.services.payment.PosService;
import bootcamps.turkcell.paymentservice.business.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PosService posService;

    @Override
    public void create(PaymentRequest paymentRequest) {
        FakeCards fakeCards = new FakeCards();
        Card card = fakeCards.getCards().get(0);

        paymentRequest.setCard(card);
        /*try {
            posService.pay(paymentRequest);
            clientResponse.setSuccess(true);
        } catch (RuntimeException exception) {
            clientResponse.setSuccess(false);
            clientResponse.setMessage("Verification failed!");
        }*/

        if (!posService.pay(paymentRequest)) {
            throw new RuntimeException("Verification failed!");
        }


    }
}
