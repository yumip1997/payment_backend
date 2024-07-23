package com.example.payment.dto.response;

import com.example.payment.constants.PaymentConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
@Setter
public class TossProperty {

    private String pgId;
    private String clientKey;
    private String customerKey;
    @JsonIgnore
    private String secretKey;
    @JsonIgnore
    private String payUrl;

    public String makeAuthorizationHeaderValue(){
        String encodingValue = this.secretKey + PaymentConstants.COLON;
        return Base64.getEncoder().encodeToString(encodingValue.getBytes(StandardCharsets.UTF_8));
    }

}
