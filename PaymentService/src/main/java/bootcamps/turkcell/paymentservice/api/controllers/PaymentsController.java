package bootcamps.turkcell.paymentservice.api.controllers;

import bootcamps.turkcell.common.models.dtos.payment.requests.PaymentRequest;
import bootcamps.turkcell.paymentservice.business.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;

    @PostMapping
    public void create(@RequestBody PaymentRequest request) {
        service.create(request);
    }
}
