package com.example.payment.define;

import com.example.payment.processor.PaymentProcessor;
import com.example.payment.processor.impl.TossProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentTypeTest {

    @Test
    void toss_processor_test(){
        String pgCode  = PgType.TOSS.getCode();
        String payWayCode = PayWayType.ACCOUNT.getCode();

        PaymentProcessor paymentProcessor = PaymentType.lookUpPaymentProcessor(pgCode, payWayCode);
        assertInstanceOf(TossProcessor.class, paymentProcessor);
    }

}