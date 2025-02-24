package com.wid.controller;

import com.wid.dto.OrderRequestDto;
import com.wid.dto.SaveOrderRespDto;
import com.wid.dto.response.CommonResponseEntity;
import com.wid.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    public void saveOrder(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.makeOrder(orderRequestDto);
    }

    @GetMapping("/read/{orderId}")
    public CommonResponseEntity<SaveOrderRespDto> readOrderInfo(@PathVariable  String orderId) {
        return  CommonResponseEntity.success(orderService.readOrder(orderId));
    }
}
