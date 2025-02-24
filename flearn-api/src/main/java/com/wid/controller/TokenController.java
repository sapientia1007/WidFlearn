package com.wid.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Value("${toss_payments.client_key}")
    private String tossClientKey;

    @GetMapping("/api/toss/client-key")
    public String getTossClientKey() {
        return tossClientKey;
    }
}
