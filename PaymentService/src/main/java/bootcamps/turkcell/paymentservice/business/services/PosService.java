package bootcamps.turkcell.paymentservice.business.services;

import bootcamps.turkcell.paymentservice.business.dtos.PaymentRequest;

public interface PosService {
    boolean pay(PaymentRequest paymentRequest);
}
