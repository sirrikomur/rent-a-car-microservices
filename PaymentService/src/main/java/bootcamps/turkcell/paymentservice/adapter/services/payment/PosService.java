package bootcamps.turkcell.paymentservice.adapter.services.payment;

import bootcamps.turkcell.common.models.dtos.payment.requests.PaymentRequest;

public interface PosService {
    boolean pay(PaymentRequest paymentRequest);
}
