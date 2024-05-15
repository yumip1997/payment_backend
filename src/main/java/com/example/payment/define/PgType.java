package com.example.payment.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum PgType {

    TOSS("10", Set.of("")),
    SETTLE_BANK("20", Set.of("")),
    NONE_PG("99", null);

    private final String code;
    private final Set<String> successCodeSet;

}
