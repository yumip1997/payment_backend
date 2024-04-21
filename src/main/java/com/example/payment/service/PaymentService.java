package com.example.payment.service;

import com.example.payment.define.PaymentType;
import com.example.payment.dto.CancelRequest;
import com.example.payment.dto.request.PayRequest;
import com.example.payment.dto.request.PayUnitRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.processor.PaymentProcessor;
import com.example.payment.manager.PaymentProcessorManager;
import com.example.payment.repository.PaymentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentProcessorManager paymentProcessorManager;
    private final PaymentRepository paymentRepository;

    @Transactional
    // TODO bean validation 추가
    public List<PayResponse> pay(PayRequest payRequest){
        List<PayResponse> payResponseList = new ArrayList<>();

        try{
            for (PayUnitRequest payUnitRequest : payRequest.getPayUnitRequestList()) {
                PaymentType paymentType = PaymentType.findPaymentType(payUnitRequest.getPgCode(), payUnitRequest.getPayWayCode());
                PaymentProcessor paymentProcessor = paymentProcessorManager.lookupPaymentProcessor(paymentType);

                PayResponse payResponse = paymentProcessor.pay(payRequest);
                payResponseList.add(payResponse);

                paymentRepository.save(payResponse.toPayBase());
            }
        }catch (Exception e){
            log.error("pay error : ordNo {}", payRequest.getOrdNo(), e);
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

        for (PayResponse payResponse : payResponseList) {
            try{
                PaymentType paymentType = PaymentType.findPaymentType(payResponse.getPgCode(), payResponse.getPayWayCode());
                PaymentProcessor paymentProcessor = paymentProcessorManager.lookupPaymentProcessor(paymentType);
                paymentProcessor.cancel(payResponse.toCancelRequest());
            }catch (Exception e){
                log.error("net cancel error ordNo :  {}", payResponse.getOrdNo(), e);
            }
        }
    }

    @Transactional
    public void cancel(List<CancelRequest> cancelRequestList){
        for (CancelRequest cancelRequest : cancelRequestList) {
            try{
                PaymentType paymentType = PaymentType.findPaymentType(cancelRequest.getPgCode(), cancelRequest.getPayWayCode());
                PaymentProcessor paymentProcessor = paymentProcessorManager.lookupPaymentProcessor(paymentType);
                paymentProcessor.cancel(cancelRequest);
            }catch (Exception e){
                log.error("cancel error ordNo :  {}", cancelRequest.getOrdNo(), e);
                throw e;
            }
        }
    }

}
