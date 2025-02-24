package com.wid.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PaymentRespDto {
    private String mId;
    private String orderId;
    private String orderName;
    private String paymentKey;
    private String status;
    private LocalDateTime approvedAt;
    private String type;
    private Integer totalAmount;
}
