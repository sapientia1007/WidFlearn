package com.wid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/payments")
public class PageController {
    @GetMapping("/order")
    public String index() {
        return "/payment/orderPage";
    }

    @GetMapping("/checkout")
    public String index2() {
        return "/payment/checkout";
    }
}
