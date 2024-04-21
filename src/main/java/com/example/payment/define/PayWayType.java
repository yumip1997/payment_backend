package com.example.payment.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PayWayType {

    CREDIT_CARD("10"),
    ACCOUNT("20"),
    POINT("30");

    private final String code;

}
