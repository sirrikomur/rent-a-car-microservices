package bootcamps.turkcell.rentalservice.api.clients.invoice;

import bootcamps.turkcell.rentalservice.business.dtos.requests.clients.CreateInvoiceClientRequest;
import bootcamps.turkcell.rentalservice.business.dtos.responses.clients.CreateInvoiceClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InvoiceClientFallback implements InvoiceClient {
    @Override
    public CreateInvoiceClientResponse create(CreateInvoiceClientRequest invoiceRequest) {
        log.info("Invoice Service: DISABLED!");
        throw new RuntimeException("Invoice Service: DISABLED!");
    }
}
