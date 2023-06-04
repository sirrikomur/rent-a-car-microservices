package bootcamps.turkcell.rentalservice.api.clients;

import bootcamps.turkcell.common.models.dtos.payment.requests.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", path = "/api")
public interface PaymentServiceClient {
    Logger logger = LoggerFactory.getLogger(PaymentServiceClient.class);

    @PostMapping("/payments")
    @CircuitBreaker(name = "createCircuitBreaker", fallbackMethod = "createFallback")
    void create(@RequestBody PaymentRequest request);

    default void createFallback(PaymentRequest request, Exception exception) {
        logger.info("Payment Service is down!");
    }
}
