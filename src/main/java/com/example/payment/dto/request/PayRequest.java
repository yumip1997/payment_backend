package com.example.payment.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class PayRequest {

    private String ordNo;
    private String mbrNo;
    private List<PayUnitRequest> payUnitRequestList;

}
