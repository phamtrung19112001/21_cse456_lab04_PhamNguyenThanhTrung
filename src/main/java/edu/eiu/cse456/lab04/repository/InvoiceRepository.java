package edu.eiu.cse456.lab04.repository;

import edu.eiu.cse456.lab04.entity.Invoice;
import java.util.Optional;

public interface InvoiceRepository {
    Invoice save(Invoice invoice);
    Optional<Invoice> findById(Long id);
}
