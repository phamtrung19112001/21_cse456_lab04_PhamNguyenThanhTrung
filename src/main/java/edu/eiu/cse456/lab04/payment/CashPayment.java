package edu.eiu.cse456.lab04.payment;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("cashPayment")
public class CashPayment implements PaymentMethod {
    @Override
    public boolean makePayment(BigDecimal amount) {
        System.out.println(">>> [STRATEGY] Receiving Cash Payment of $" + amount + "...");
        return true;
    }
}