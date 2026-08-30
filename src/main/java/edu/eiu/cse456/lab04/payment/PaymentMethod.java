package edu.eiu.cse456.lab04.payment;

import java.math.BigDecimal;

public interface PaymentMethod {
    // Đề bài bắt buộc dùng BigDecimal cho tiền tệ, không dùng float/double
    boolean makePayment(BigDecimal amount);
}