package com.wid.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConfirmPaymentRequest {
    private String orderId;
    private String amount;
    private String paymentKey;
}
