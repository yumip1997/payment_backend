package com.example.payment.dto.response;

import com.example.payment.dto.CancelRequest;
import com.example.payment.entity.PayBase;
import lombok.Getter;

@Getter
public class PayResponse {

    private String ordNo;
    private String pgCode;
    private String payWayCode;

    public CancelRequest toCancelRequest(){
        return new CancelRequest();
    }

    public PayBase toPayBase(){
        return new PayBase();
    }
}
