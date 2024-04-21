package com.example.payment.dto;

import lombok.Getter;

@Getter
public class CancelRequest {

    private String ordNo;
    private String pgCode;
    private String payWayCode;

}
