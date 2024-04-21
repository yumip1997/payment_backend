package com.example.payment.service;

import com.example.payment.dto.CancelRequest;
import com.example.payment.dto.request.PayRequest;
import com.example.payment.dto.request.PayUnitRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.processor.PaymentProcessor;
import com.example.payment.processor.PaymentProcessorManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentProcessorManager paymentProcessorManager;

    @Transactional
    public List<PayResponse> pay(@NonNull PayRequest payRequest){
        if(CollectionUtils.isEmpty(payRequest.getPayUnitRequestList())){
            throw new RuntimeException("결제 요청 값이 비어있습니다.");
        }

        List<PayResponse> payResponseList = new ArrayList<>();

        try{
            for (PayUnitRequest payUnitRequest : payRequest.getPayUnitRequestList()) {
                PaymentProcessor paymentProcessor = paymentProcessorManager.lookupPaymentProcessor(payUnitRequest.getPgCode(), payUnitRequest.getPayWayCode());
                PayResponse payResponse = paymentProcessor.pay(payRequest);
                payResponseList.add(payResponse);
            }
        }catch (Exception e){
            netCancel(payResponseList);
            throw e;
        }

        return payResponseList;
    }

    @Transactional
    public void netCancel(List<PayResponse> payResponseList){
        if(CollectionUtils.isEmpty(payResponseList)){
            return;
        }


        List<CancelRequest> cancelRequestList = payResponseList.stream()
                .map(PayResponse::toCancelRequest).toList();
        cancel(cancelRequestList);
    }

    @Transactional
    public void cancel(List<CancelRequest> cancelRequestList){

    }

}
