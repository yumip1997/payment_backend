package com.example.payment.processor.impl;

import com.example.payment.constants.PaymentConstants;
import com.example.payment.dto.CancelRequest;
import com.example.payment.dto.request.PayUnitRequest;
import com.example.payment.dto.request.TossApproveRequest;
import com.example.payment.dto.response.PayResponse;
import com.example.payment.dto.response.TossError;
import com.example.payment.dto.response.TossProperty;
import com.example.payment.dto.response.TossResponse;
import com.example.payment.entity.PayBase;
import com.example.payment.manager.PgPropertyManager;
import com.example.payment.processor.PaymentProcessor;
import com.example.payment.utils.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TossProcessor implements PaymentProcessor {

    private final PgPropertyManager pgPropertyManager;
    @Override
    public PayResponse pay(PayUnitRequest payUnitRequest) {
        TossProperty tossProperty = pgPropertyManager.lookupTossProperty(payUnitRequest.getMid());
        TossApproveRequest request = ObjectMapperUtils.OBJECT_MAPPER
                .convertValue(payUnitRequest.getAuthMap(), TossApproveRequest.class);

        TossResponse tossResponse = callTossPayAPI(tossProperty, request);
        validateAfterPayCall(tossResponse);

        return tossResponse.toPayResponse();
    }

    private TossResponse callTossPayAPI(TossProperty tossProperty, TossApproveRequest tossApproveRequest){
        return WebClient
                .create()
                .post()
                .uri(tossProperty.getPayUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, PaymentConstants.BASIC + PaymentConstants.SPACE + tossProperty.makeAuthorizationHeaderValue())
                .bodyValue(tossApproveRequest)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError()
                        , clientResponse -> clientResponse
                                .bodyToMono(TossError.class)
                                .map(e -> new RuntimeException(e.getMessage())))
                .bodyToMono(TossResponse.class)
                .block();
    }

    public void validateAfterPayCall(TossResponse tossResponse) {

    }

    @Override
    public PayBase cancel(CancelRequest cancelRequest) {
        return null;
    }

}
