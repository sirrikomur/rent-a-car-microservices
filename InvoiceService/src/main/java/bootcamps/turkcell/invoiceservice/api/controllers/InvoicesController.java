package bootcamps.turkcell.invoiceservice.api.controllers;

import bootcamps.turkcell.common.utilities.dtos.invoice.requests.create.CreateInvoiceRequest;
import bootcamps.turkcell.common.utilities.dtos.invoice.requests.update.UpdateInvoiceRequest;
import bootcamps.turkcell.common.utilities.dtos.invoice.responses.create.CreateInvoiceResponse;
import bootcamps.turkcell.common.utilities.dtos.invoice.responses.get.GetAllInvoicesResponse;
import bootcamps.turkcell.common.utilities.dtos.invoice.responses.get.GetInvoiceResponse;
import bootcamps.turkcell.common.utilities.dtos.invoice.responses.update.UpdateInvoiceResponse;
import bootcamps.turkcell.invoiceservice.business.services.InvoiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService service;

    @GetMapping
    public List<GetAllInvoicesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CreateInvoiceResponse create(@Valid @RequestBody CreateInvoiceRequest invoiceRequest) {
        return service.create(invoiceRequest);
    }

    @PutMapping("/{id}")
    public UpdateInvoiceResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateInvoiceRequest invoiceRequest) {
        return service.update(id, invoiceRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
