package bootcamps.turkcell.paymentservice.business.services;


import bootcamps.turkcell.paymentservice.domain.entities.Card;

public interface PaymentService {
    void pay(Card card, double amount);
}
