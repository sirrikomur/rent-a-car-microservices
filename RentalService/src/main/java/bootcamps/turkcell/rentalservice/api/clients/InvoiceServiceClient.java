package bootcamps.turkcell.rentalservice.api.clients;

import bootcamps.turkcell.common.models.dtos.invoice.requests.create.CreateInvoiceRequest;
import bootcamps.turkcell.common.models.dtos.invoice.responses.create.CreateInvoiceResponse;
import bootcamps.turkcell.common.models.dtos.payment.requests.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "invoice-service", path = "/api")
public interface InvoiceServiceClient {
    Logger logger = LoggerFactory.getLogger(InvoiceServiceClient.class);

    @PostMapping("/invoices")
    @CircuitBreaker(name = "createCircuitBreaker", fallbackMethod = "createFallback")
    CreateInvoiceResponse create(@Valid @RequestBody CreateInvoiceRequest invoiceRequest);

    default void createFallback(CreateInvoiceRequest request, Exception exception) {
        logger.info("Invoice Service is down!");
    }
}
