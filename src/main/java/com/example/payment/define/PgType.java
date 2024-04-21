package com.example.payment.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum PgType {

    TOSS("10", ""),
    SETTLE_BANK("20", ""),
    NONE_PG("99", "");

    private final String code;
    private final String successCode;

}
