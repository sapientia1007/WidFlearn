package com.wid.enums;

public enum PaymentMethod {

    METHOD_CARD("카드"),
    METHOD_TRANSFER("가상계좌"),
    METHOD_POINT("포인트 결제");

    private String description;

    PaymentMethod(String description) {
        this.description = description;
    }
}
