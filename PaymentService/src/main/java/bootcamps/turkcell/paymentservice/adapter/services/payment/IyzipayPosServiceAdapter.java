package bootcamps.turkcell.paymentservice.adapter.services.payment;

import bootcamps.turkcell.common.models.dtos.payment.requests.PaymentRequest;
import bootcamps.turkcell.paymentservice.external.services.iyzico.CreatePaymentRequest;
import bootcamps.turkcell.paymentservice.external.services.iyzico.IyzipayPaymentService;
import bootcamps.turkcell.paymentservice.external.services.iyzico.Options;
import bootcamps.turkcell.paymentservice.external.services.iyzico.PaymentCard;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IyzipayPosServiceAdapter implements PosService {

    @Override
    public boolean pay(PaymentRequest paymentRequest) {
        var card = paymentRequest.getCard();

        IyzipayPaymentService iyzipayPaymentService = new IyzipayPaymentService();

        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
        createPaymentRequest.setPaymentCard(new PaymentCard());
        createPaymentRequest.getPaymentCard().setCardNumber(card.getNumber());
        createPaymentRequest.getPaymentCard().setCardHolderName(card.getHolder());
        createPaymentRequest.getPaymentCard().setExpireYear(String.valueOf(card.getExpireYear()));
        createPaymentRequest.getPaymentCard().setExpireMonth(String.valueOf(card.getExpireMonth()));
        createPaymentRequest.getPaymentCard().setCvc(card.getCvc());
        createPaymentRequest.setPrice(BigDecimal.valueOf(paymentRequest.getAmount()));

        Options options = new Options();
        options.setApiKey("API KEY");
        options.setSecretKey("SECRET KEY");
        options.setBaseUrl("https://sandbox-api.iyzipay.com");

        return iyzipayPaymentService.create(createPaymentRequest, options);
       /* if (!iyzipayPaymentService.create(createPaymentRequest, options))
            throw new RuntimeException("Verification failed!");*/
    }
}
