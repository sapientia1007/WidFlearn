package com.wid.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SaveOrderRespDto {
    private String orderId;
    private Integer amount;
    private String lectureName;
    private Long userId;
    private String userName;
    private LocalDateTime orderDate;
    private String userEmail;
    private String userPhone;
}
