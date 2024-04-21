package com.example.payment.define;

import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
public enum PaymentType {

    TOSS_CREDIT(PgType.TOSS, PayWayType.CREDIT_CARD),
    TOSS_ACCOUNT(PgType.TOSS, PayWayType.ACCOUNT),
    SETTLE_BANK_ACCOUNT(PgType.SETTLE_BANK, PayWayType.ACCOUNT),
    POINT(PgType.NONE_PG, PayWayType.POINT);

    private final PgType pgType;
    private final PayWayType payWayType;

    public static PaymentType findPaymentType(String pgCode, String payWayCode){
        return Stream.of(values())
                .filter(e -> e.pgType.getCode().equals(pgCode) && e.payWayType.getCode().equals(payWayCode))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("결제 유형을 찾을 수 없습니다"));
    }

}
