package com.example.payment.define;

import com.example.payment.processor.PaymentProcessor;
import com.example.payment.processor.impl.PointProcessor;
import com.example.payment.processor.impl.TossProcessor;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum PaymentType {

    TOSS_CREDIT(PgType.TOSS, PayWayType.CREDIT_CARD),
    TOSS_ACCOUNT(PgType.TOSS, PayWayType.ACCOUNT),
    POINT(PgType.NONE_PG, PayWayType.POINT);

    private final PgType pgType;
    private final PayWayType payWayType;
    private PaymentProcessor processor;


    @Component
    @RequiredArgsConstructor
    static class PaymentProcessorInjector {

        private final TossProcessor tossProcessor;
        private final PointProcessor pointProcessor;

        @PostConstruct
        void injectProcessor(){
            PaymentType.TOSS_CREDIT.processor = tossProcessor;
            PaymentType.TOSS_ACCOUNT.processor = tossProcessor;
            PaymentType.POINT.processor = pointProcessor;
        }
    }

    public static PaymentProcessor lookUpPaymentProcessor(String pgCode, String payWayCode){
        PaymentType paymentType = lookUpaymentType(pgCode, payWayCode);
        return paymentType.processor;
    }

    public static PaymentType lookUpaymentType(String pgCode, String payWayCode){
        return Stream.of(values())
                .filter(e -> e.pgType.getCode().equals(pgCode)
                        && e.payWayType.getCode().equals(payWayCode))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("결제 유형을 찾을 수 없습니다."));
    }

}
