package bootcamps.turkcell.invoiceservice.repository;


import bootcamps.turkcell.invoiceservice.domain.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
