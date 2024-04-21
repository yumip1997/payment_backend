package com.example.payment.dto.response;

import com.example.payment.dto.CancelRequest;

public class PayResponse {


    public CancelRequest toCancelRequest(){
        return new CancelRequest();
    }
}
