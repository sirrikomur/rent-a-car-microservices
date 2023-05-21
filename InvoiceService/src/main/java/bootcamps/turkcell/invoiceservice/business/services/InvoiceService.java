package bootcamps.turkcell.invoiceservice.business.services;


import bootcamps.turkcell.invoiceservice.business.dtos.requests.create.CreateInvoiceRequest;
import bootcamps.turkcell.invoiceservice.business.dtos.requests.update.UpdateInvoiceRequest;
import bootcamps.turkcell.invoiceservice.business.dtos.responses.create.CreateInvoiceResponse;
import bootcamps.turkcell.invoiceservice.business.dtos.responses.get.GetAllInvoicesResponse;
import bootcamps.turkcell.invoiceservice.business.dtos.responses.get.GetInvoiceResponse;
import bootcamps.turkcell.invoiceservice.business.dtos.responses.update.UpdateInvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(UUID id);
    CreateInvoiceResponse create(CreateInvoiceRequest invoiceRequest);
    UpdateInvoiceResponse update(UUID id, UpdateInvoiceRequest invoiceRequest);
    void delete(UUID id);
}
