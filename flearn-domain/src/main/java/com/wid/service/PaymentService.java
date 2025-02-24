package com.wid.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wid.dto.PaymentRespDto;
import com.wid.entity.OrderEntity;
import com.wid.entity.PaymentEntity;
import com.wid.enums.PaymentMethod;
import com.wid.enums.PaymentStatus;
import com.wid.repository.OrderRepository;
import com.wid.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${toss_payments.widget_secret_key}")
    private String SECRET_KEY;

    @Value("${toss_payments.widget_secret_key:}")
    private String WIDGET_SECRET_KEY;

    @Value("${toss_payments.url}")
    private String TOSS_PAYMENTS_URL;

    @Value("${toss_payments.confirm_endpoint}")
    private String TOSS_CONFIRM_END_POINT;

    // 결제 요청

    // 결제 승인
    @Transactional
    public PaymentRespDto confirmPayment(String paymentKey, String orderId, int amount) throws IOException, InterruptedException {
        String authorization = "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes());

        String requestBody = String.format(
                "{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%d}",
                paymentKey, orderId, amount
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOSS_PAYMENTS_URL + TOSS_CONFIRM_END_POINT))
                .header("Authorization", authorization)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // 응답 확인
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(response.body());

        JsonNode jsonNode = objectMapper.readTree(response.body());

        String dateStr = jsonNode.get("requestedAt").asText();
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDateTime dateTime = zonedDateTime.toLocalDateTime();

        OrderEntity savedOrder = orderRepository.findByOrderId(orderId).orElseThrow();

        PaymentEntity toSavePayment = PaymentEntity.builder()
                .paymentId(jsonNode.get("paymentKey").asText())
                .status(PaymentStatus.PAYMENT_VALIDATED)
                .paymentDate(dateTime)
                .amount(jsonNode.get("totalAmount").asInt())
                .method(PaymentMethod.METHOD_TRANSFER)
                .order(savedOrder)
                .build();

        paymentRepository.save(toSavePayment);

        return PaymentRespDto.builder()
                .mId(jsonNode.get("mId").asText())
                .orderId(jsonNode.get("orderId").asText())
                .orderName(jsonNode.get("orderName").asText())
                .paymentKey(jsonNode.get("paymentKey").asText())
                .status(jsonNode.get("status").asText())
                .approvedAt(dateTime)
                .type(jsonNode.get("method").asText())
                .totalAmount(jsonNode.get("totalAmount").asInt())
                .build();
    }

}
