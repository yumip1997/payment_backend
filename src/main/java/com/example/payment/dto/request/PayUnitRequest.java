package com.example.payment.dto.request;

import lombok.Getter;

import java.util.Map;

@Getter
public class PayUnitRequest {

    private long payAmt;
    private String pgCode;
    private String payWayCode;
    private Map<String, String> authMap;

}
