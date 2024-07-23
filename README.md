### 결제 개선 토이 프로젝트

### 개선 방향

1. Pg/결제수단 전략 객체를 명시적으로 관리

- Enum 활용
- Bean 객체 주입을 위해 PostConstruct 기능 사용

```java
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
}
```

- 결제 수단에 상관없이 동일 타입의 응답 값을 넘기는 Pg사의 경우
    - TOSS_CREDIT : 토스/신용카드 → TossProcessor
    - TOSS_ACCOUNT: 토스/실시간계좌이체 → TossProcessor
    
    ⇒ 동일 객체에서 승인/취소 로직 수행

2. WebClient 예외 처리
- onStatus 활용
```java
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError()
                        , clientResponse -> clientResponse
                                .bodyToMono(TossError.class)
                                .map(e -> new RuntimeException(e.getMessage())))
```
