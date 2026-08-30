package edu.eiu.cse456.lab04.service;

import edu.eiu.cse456.lab04.entity.*;
import edu.eiu.cse456.lab04.repository.CustomerRepository;
import edu.eiu.cse456.lab04.repository.InvoiceRepository;
import edu.eiu.cse456.lab04.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(CustomerRepository customerRepository,
                          ProductRepository productRepository,
                          InvoiceRepository invoiceRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createInvoice(Long customerId, List<Long> productIds, List<Integer> quantities) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setPaymentStatus("PENDING");

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            int quantity = quantities.get(i);

            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0");
            }

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            if (product.getStockQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }

            product.setStockQuantity(product.getStockQuantity() - quantity);

            BigDecimal lineTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            totalAmount = totalAmount.add(lineTotal);

            InvoiceItem item = new InvoiceItem();
            item.setInvoice(invoice);
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setUnitPrice(product.getPrice());
            item.setLineTotal(lineTotal);

            invoice.getItems().add(item);
        }

        invoice.setTotalAmount(totalAmount);
        return invoiceRepository.save(invoice);
    }
}