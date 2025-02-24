package com.wid.enums;

public enum PaymentStatus {

    PAYMENT_VALIDATED("검증 완료"),
    PAYMENT_VALIDATING("검증 진행"),
    PAYMENT_SUCCESS("결제 완료"),
    PAYMENT_FAIL("결제 실패");

    private String description;

    PaymentStatus(String description) {
        this.description = description;
    }
}
