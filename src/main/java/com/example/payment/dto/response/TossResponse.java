package com.example.payment.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class TossResponse {

    private String mId;
    private String version;
    private String paymentKey;
    private String status;
    private String lastTransactionKey;
    private String orderId;
    private String orderName;
    private String requestedAt;
    private String approvedAt;
    private boolean useEscrow;
    private boolean cultureExpense;
    private Card card;
    private Transfer transfer;
    private String type;
    private boolean isPartialCancelable;
    private Receipt receipt;
    private Checkout checkout;
    private String currency;
    private long totalAmount;
    private long balanceAmount;
    private long suppliedAmount;
    private long vat;
    private int taxFreeAmount;
    private String method;
    private List<Cancel> cancels;
    private TossError failure;

    @Getter
    @Setter
    public static class Card {
        private String issuerCode;
        private String acquirerCode;
        private String number;
        private int installmentPlanMonths;
        private boolean isInterestFree;
        private String interestPayer;
        private String approveNo;
        private boolean useCardPoint;
        private String cardType;
        private String ownerType;
        private String acquireStatus;
        private long amount;
    }

    @Getter
    @Setter
    public static class Transfer {
        private String bankCode;
        private String settlementStatus;
    }

    @Getter
    @Setter
    public static class Receipt {
        private String url;
    }

    @Getter
    @Setter
    public static class Checkout {
        private String url;
    }

    @Getter
    @Setter
    public static class Cancel {

        private String cancelReason;
        private String canceledAt;
        private int cancelAmount;
        private int taxFreeAmount;
        private int taxExemptionAmount;
        private int refundableAmount;
        private int easyPayDiscountAmount;
        private String transactionKey;
        private String receiptKey;
    }
}
