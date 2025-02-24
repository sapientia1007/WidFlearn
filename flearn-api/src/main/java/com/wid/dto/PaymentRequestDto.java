package com.wid.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentRequestDto {
    private String orderId;
    private Integer amount;
    private String paymentKey;

}
