package com.wid.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentInfoDto {
    private String paymentId;
    private Integer amount;
    private Long userId;
}
