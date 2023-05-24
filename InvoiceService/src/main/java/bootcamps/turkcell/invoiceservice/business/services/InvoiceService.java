package bootcamps.turkcell.invoiceservice.business.services;

import bootcamps.turkcell.common.utilities.dtos.invoice.requests.create.CreateInvoiceRequest;
import bootcamps.turkcell.common.utilities.dtos.invoice.requests.update.UpdateInvoiceRequest;
import bootcamps.turkcell.common.utilities.dtos.invoice.responses.create.CreateInvoiceResponse;
import bootcamps.turkcell.common.utilities.dtos.invoice.responses.get.GetAllInvoicesResponse;
import bootcamps.turkcell.common.utilities.dtos.invoice.responses.get.GetInvoiceResponse;
import bootcamps.turkcell.common.utilities.dtos.invoice.responses.update.UpdateInvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(UUID id);
    CreateInvoiceResponse create(CreateInvoiceRequest invoiceRequest);
    UpdateInvoiceResponse update(UUID id, UpdateInvoiceRequest invoiceRequest);
    void delete(UUID id);
}
