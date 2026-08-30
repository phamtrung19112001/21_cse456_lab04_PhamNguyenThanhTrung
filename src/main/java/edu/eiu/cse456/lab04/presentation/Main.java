package edu.eiu.cse456.lab04.presentation;

import edu.eiu.cse456.lab04.config.AppConfig;
import edu.eiu.cse456.lab04.entity.Customer;
import edu.eiu.cse456.lab04.entity.Invoice;
import edu.eiu.cse456.lab04.entity.Product;
import edu.eiu.cse456.lab04.repository.CustomerRepository;
import edu.eiu.cse456.lab04.repository.ProductRepository;
import edu.eiu.cse456.lab04.service.InvoiceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            CustomerRepository customerRepo = context.getBean(CustomerRepository.class);
            ProductRepository productRepo = context.getBean(ProductRepository.class);
            InvoiceService invoiceService = context.getBean(InvoiceService.class);

            Customer customer = customerRepo.save(
                    new Customer("Nguyen Van An", "an.uyen" + System.currentTimeMillis() + "@example.com", "0901234567"));

            Product p1 = productRepo.save(new Product("Mechanical Keyboard", new BigDecimal("850000"), 10));
            Product p2 = productRepo.save(new Product("Gaming Mouse", new BigDecimal("450000"), 15));

            Invoice invoice = invoiceService.createInvoice(
                    customer.getId(),
                    List.of(p1.getId(), p2.getId()),
                    List.of(2, 1)
            );

            System.out.println("\n=================== INVOICE DETAILS ===================");
            System.out.println("Invoice ID       : " + invoice.getId());
            System.out.println("Invoice Date     : " + invoice.getInvoiceDate());
            System.out.println("Customer Name    : " + invoice.getCustomer().getFullName());
            System.out.println("Customer Email   : " + invoice.getCustomer().getEmail());
            System.out.println("-------------------------------------------------------");
            System.out.println("Items Purchased:");
            invoice.getItems().forEach(item -> {
                System.out.println(" - " + item.getProduct().getName()
                        + " | Qty: " + item.getQuantity()
                        + " | Unit Price: " + item.getUnitPrice()
                        + " | Line Total: " + item.getLineTotal());
            });
            System.out.println("-------------------------------------------------------");
            System.out.println("Grand Total      : " + invoice.getTotalAmount());
            System.out.println("Payment Method   : " + (invoice.getPaymentMethod() != null ? invoice.getPaymentMethod() : "PENDING"));
            System.out.println("Payment Status   : " + invoice.getPaymentStatus());
            System.out.println("=======================================================\n");
        }
    }
}