package com.wid.controller;

import com.wid.dto.PaymentRequestDto;
import com.wid.dto.PaymentRespDto;
import com.wid.dto.response.CommonResponseEntity;
import com.wid.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // 결제 승인
    @PostMapping("/request")
    public CommonResponseEntity<PaymentRespDto> requestPayment(@RequestBody PaymentRequestDto paymentRequestDto) throws IOException, InterruptedException {
        return CommonResponseEntity.success(paymentService.confirmPayment(paymentRequestDto.getPaymentKey(), paymentRequestDto.getOrderId(), paymentRequestDto.getAmount()));
    }

    // 결제 취소

    // 주문 정보 저장
    
    // 결제 조회

}

