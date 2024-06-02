package com.example.payment.controller;

import com.example.payment.dto.request.PayRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/pay")
    public List<PayResponse> pay(PayRequest payRequest){
        return paymentService.pay(payRequest);
    }


}
