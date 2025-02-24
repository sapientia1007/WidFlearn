package com.wid.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveAmountRequest {
    String orderId;
    String amount;
}
