package com.example.payment.processor.impl;

import com.example.payment.dto.CancelRequest;
import com.example.payment.dto.request.PayUnitRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.entity.PayBase;
import com.example.payment.processor.PaymentProcessor;
import org.springframework.stereotype.Component;

@Component
public class PointProcessor implements PaymentProcessor {
    @Override
    public PayResponse pay(PayUnitRequest payUnitRequest) {
        return null;
    }

    @Override
    public PayBase cancel(CancelRequest cancelRequest) {
        return null;
    }

}
