package bootcamps.turkcell.paymentservice.business.services;

import bootcamps.turkcell.common.models.dtos.payment.requests.PaymentRequest;

public interface PaymentService {
    void create(PaymentRequest paymentRequest);
}
