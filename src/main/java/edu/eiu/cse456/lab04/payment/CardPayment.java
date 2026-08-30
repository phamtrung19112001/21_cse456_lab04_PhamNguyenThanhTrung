package edu.eiu.cse456.lab04.payment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("cardPayment")
@Primary
public class CardPayment implements PaymentMethod {
    @Override
    public boolean makePayment(BigDecimal amount) {
        System.out.println(">>> [STRATEGY] Processing Card Payment of $" + amount + "...");
        return true;
    }
}