package bootcamps.turkcell.rentalservice.api.clients.invoice;

import bootcamps.turkcell.rentalservice.business.dtos.requests.clients.CreateInvoiceClientRequest;
import bootcamps.turkcell.rentalservice.business.dtos.responses.clients.CreateInvoiceClientResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "invoice-service", fallback = InvoiceClientFallback.class)
public interface InvoiceClient {
    @PostMapping("/api/invoices")
    CreateInvoiceClientResponse create(@RequestBody CreateInvoiceClientRequest invoiceRequest);
}
