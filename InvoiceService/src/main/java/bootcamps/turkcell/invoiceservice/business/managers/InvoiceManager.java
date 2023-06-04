package bootcamps.turkcell.invoiceservice.business.managers;

import bootcamps.turkcell.common.utilities.constants.Values;
import bootcamps.turkcell.common.models.dtos.invoice.requests.create.CreateInvoiceRequest;
import bootcamps.turkcell.common.models.dtos.invoice.requests.update.UpdateInvoiceRequest;
import bootcamps.turkcell.common.models.dtos.invoice.responses.create.CreateInvoiceResponse;
import bootcamps.turkcell.common.models.dtos.invoice.responses.get.GetAllInvoicesResponse;
import bootcamps.turkcell.common.models.dtos.invoice.responses.get.GetInvoiceResponse;
import bootcamps.turkcell.common.models.dtos.invoice.responses.update.UpdateInvoiceResponse;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.invoiceservice.business.services.InvoiceService;
import bootcamps.turkcell.invoiceservice.domain.entities.Invoice;
import bootcamps.turkcell.invoiceservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        return invoices.stream().map(invoice -> mapper.forResponse().map(invoice, GetAllInvoicesResponse.class)).toList();
    }

    @Override
    public GetInvoiceResponse getById(UUID id) {
        Invoice invoice = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(invoice, GetInvoiceResponse.class);
    }

    @Override
    public CreateInvoiceResponse create(CreateInvoiceRequest invoiceRequest) {
        Invoice invoice = mapper.forRequest().map(invoiceRequest, Invoice.class);
        invoice.setNo(generateInvoiceNo());
        invoice.setCreatedDate(LocalDateTime.now());
        invoice.setTaxRate(Values.TaxRate.VAT);

        repository.save(invoice);

        return mapper.forResponse().map(invoice, CreateInvoiceResponse.class);
    }

    @Override
    public UpdateInvoiceResponse update(UUID id, UpdateInvoiceRequest invoiceRequest) {
        Invoice invoice = mapper.forRequest().map(invoiceRequest, Invoice.class);
        invoice.setId(id);
        repository.save(invoice);
        return mapper.forResponse().map(invoice, UpdateInvoiceResponse.class);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private String generateInvoiceNo() {
        Random random = new Random();
        return "INV-" + random.nextInt(1000000000);
    }
}
