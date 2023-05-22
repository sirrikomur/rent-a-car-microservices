package bootcamps.turkcell.paymentservice.business.managers;


import bootcamps.turkcell.paymentservice.business.dtos.PaymentRequest;
import bootcamps.turkcell.paymentservice.business.services.PaymentService;
import bootcamps.turkcell.paymentservice.business.services.PosService;
import bootcamps.turkcell.paymentservice.common.fakes.FakeCards;
import bootcamps.turkcell.paymentservice.domain.entities.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PosService posService;

    @Override
    public void pay(Card card, double amount) {
        FakeCards fakeCards = new FakeCards();
        card = fakeCards.getCards().get(0);
        posService.pay(new PaymentRequest(card, amount));
    }
}
