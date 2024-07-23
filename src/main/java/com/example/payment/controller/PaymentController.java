package com.example.payment.controller;

import com.example.payment.dto.request.PayRequest;
import com.example.payment.dto.request.PgPropertyRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.dto.response.TossProperty;
import com.example.payment.service.PaymentService;
import com.example.payment.service.PgPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PgPropertyService pgPropertyService;
    private final PaymentService paymentService;


    @PostMapping("/tossProperty")
    public TossProperty getTossProperty(@RequestBody PgPropertyRequest pgPropertyRequest){
        return pgPropertyService.getTossProperty(pgPropertyRequest);
    }

    @PostMapping("/pay")
    public List<PayResponse> pay(@RequestBody PayRequest payRequest){
        return paymentService.pay(payRequest);
    }


}
