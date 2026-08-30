package edu.eiu.cse456.lab04.service;

import edu.eiu.cse456.lab04.entity.Invoice;
import edu.eiu.cse456.lab04.payment.PaymentMethod;
import edu.eiu.cse456.lab04.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentMethod paymentMethod;
    private final InvoiceRepository invoiceRepository;

    public PaymentService(PaymentMethod paymentMethod, InvoiceRepository invoiceRepository) {
        this.paymentMethod = paymentMethod;
        this.invoiceRepository = invoiceRepository;
    }

    public void processInvoicePayment(Invoice invoice) {
        boolean isSuccess = paymentMethod.makePayment(invoice.getTotalAmount());
        if (isSuccess) {
            invoice.setPaymentStatus("PAID");
        } else {
            invoice.setPaymentStatus("FAILED");
        }
        invoice.setPaymentMethod(paymentMethod.getClass().getSimpleName());
        invoiceRepository.save(invoice);

        System.out.println("Payment processed successfully. Status updated to: " + invoice.getPaymentStatus());
    }
}