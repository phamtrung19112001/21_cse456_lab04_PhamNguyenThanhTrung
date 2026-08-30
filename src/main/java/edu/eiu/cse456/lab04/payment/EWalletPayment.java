package edu.eiu.cse456.lab04.payment;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("eWalletPayment")
public class EWalletPayment implements PaymentMethod {
    @Override
    public boolean makePayment(BigDecimal amount) {
        System.out.println(">>> [STRATEGY] Processing E-Wallet Payment of $" + amount + "...");
        return true;
    }
}