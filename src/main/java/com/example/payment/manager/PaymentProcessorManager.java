package com.example.payment.manager;

import com.example.payment.define.PaymentType;
import com.example.payment.processor.PaymentProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentProcessorManager {

    private static final Map<PaymentType, PaymentProcessor> PAYMENT_TYPE_PAYMENT_PROCESSOR_MAP = new HashMap<>();

    public PaymentProcessorManager(List<PaymentProcessor> paymentProcessorList){
        for (PaymentProcessor paymentProcessor : paymentProcessorList) {
            PaymentType paymentType = paymentProcessor.definePaymentType();
            PAYMENT_TYPE_PAYMENT_PROCESSOR_MAP.put(paymentType, paymentProcessor);
        }
    }

    public PaymentProcessor lookupPaymentProcessor(PaymentType paymentType){
        PaymentProcessor paymentProcessor = PAYMENT_TYPE_PAYMENT_PROCESSOR_MAP.get(paymentType);
        if(paymentProcessor != null){
            return paymentProcessor;
        }

        throw new RuntimeException("유효하지 않은 결제유형입니다.");
    }
}
