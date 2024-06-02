package com.example.payment.dto.response;

import com.example.payment.constants.PaymentConstants;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
@Setter
public class TossProperty {

    private String mid;
    private String clientKey;
    private String secretKey;
    private String customerKey;
    private String payUrl;

    public String makeAuthorizationHeaderValue(){
        String encodingValue = this.secretKey + PaymentConstants.COLON;
        return Base64.getEncoder().encodeToString(encodingValue.getBytes(StandardCharsets.UTF_8));
    }

}
