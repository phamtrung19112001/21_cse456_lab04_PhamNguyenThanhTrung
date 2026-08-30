package edu.eiu.cse456.lab04.payment;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("bankTransferPayment")
public class BankTransferPayment implements PaymentMethod {
    @Override
    public boolean makePayment(BigDecimal amount) {
        System.out.println(">>> [STRATEGY] Processing Bank Transfer of $" + amount + "...");
        return true;
    }
}