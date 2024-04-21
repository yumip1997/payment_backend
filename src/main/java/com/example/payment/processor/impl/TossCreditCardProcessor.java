package com.example.payment.processor.impl;

import com.example.payment.define.PaymentType;
import com.example.payment.dto.CancelRequest;
import com.example.payment.dto.request.PayRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.entity.PayBase;
import com.example.payment.processor.PaymentProcessor;

public class TossCreditCardProcessor implements PaymentProcessor {

    @Override
    public PayResponse pay(PayRequest payRequest) {
        return null;
    }

    @Override
    public PayBase cancel(CancelRequest cancelRequest) {
        return null;
    }

    @Override
    public PaymentType definePaymentType() {
        return PaymentType.TOSS_CREDIT;
    }

}
