package com.example.payment.service;

import com.example.payment.define.PaymentType;
import com.example.payment.dto.CancelRequest;
import com.example.payment.dto.request.PayRequest;
import com.example.payment.dto.request.PayUnitRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.processor.PaymentProcessor;
import com.example.payment.repository.PaymentRepository;
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

    private final PaymentRepository paymentRepository;

    @Transactional
    // TODO bean validation 추가
    public List<PayResponse> pay(PayRequest payRequest){
        List<PayResponse> payResponseList = new ArrayList<>();

        try{
            for (PayUnitRequest payUnitRequest : payRequest.getPayUnitRequestList()) {
                PaymentProcessor paymentProcessor = PaymentType
                        .lookUpPaymentProcessor(payUnitRequest.getPgCode(), payUnitRequest.getPayWayCode());

                PayResponse payResponse = paymentProcessor.pay(payUnitRequest);
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
                PaymentProcessor paymentProcessor = PaymentType.lookUpPaymentProcessor(payResponse.getPgCode(), payResponse.getPayWayCode());
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
                PaymentProcessor paymentProcessor = PaymentType.lookUpPaymentProcessor(cancelRequest.getPgCode(), cancelRequest.getPayWayCode());
                paymentProcessor.cancel(cancelRequest);
            }catch (Exception e){
                log.error("cancel error ordNo :  {}", cancelRequest.getOrdNo(), e);
                throw e;
            }
        }
    }

}
