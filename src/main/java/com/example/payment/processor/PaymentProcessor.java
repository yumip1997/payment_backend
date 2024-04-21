package com.example.payment.processor;

import com.example.payment.define.PaymentType;
import com.example.payment.dto.CancelRequest;
import com.example.payment.dto.request.PayRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.entity.PayBase;

public interface PaymentProcessor {

    PayResponse pay(PayRequest payRequest);
    PayBase cancel(CancelRequest cancelRequest);
    PaymentType definePaymentType();

}
