package bootcamps.turkcell.common.clients.invoice;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "InvoiceService", fallback = InvoiceClientFallback.class)
public interface InvoiceClient {
    @PostMapping("/api/invoices")
    CreateInvoiceResponse create(@Valid @RequestBody CreateInvoiceRequest invoiceRequest)
}
