package com.example.payment.processor;

import com.example.payment.dto.CancelRequest;
import com.example.payment.dto.request.PayUnitRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.entity.PayBase;

public interface PaymentProcessor {

    PayResponse pay(PayUnitRequest payUnitRequest);
    PayBase cancel(CancelRequest cancelRequest);

}
