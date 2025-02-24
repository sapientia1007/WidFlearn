package com.wid.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderRequestDto {
    private String method;
    private Integer amount;
    private String orderId;
    private String customerEmail;
    private String customerName;
    private Long lectureId;
    private Long userId;
}
