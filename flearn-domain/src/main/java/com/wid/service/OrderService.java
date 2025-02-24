package com.wid.service;

import com.wid.dto.OrderRequestDto;
import com.wid.dto.SaveOrderRespDto;
import com.wid.entity.LectureEntity;
import com.wid.entity.OrderEntity;
import com.wid.entity.UserEntity;
import com.wid.repository.LectureRepository;
import com.wid.repository.OrderRepository;
import com.wid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    // 주문
    @Transactional
    public void makeOrder(OrderRequestDto orderRequestDto) {

        LectureEntity savedLecture = lectureRepository.findById(orderRequestDto.getLectureId()).orElseThrow();
        UserEntity savedUser = userRepository.findById(orderRequestDto.getUserId()).orElseThrow();
        OrderEntity toSaveOrder = OrderEntity.builder()
                .orderId(orderRequestDto.getOrderId())
                .orderDate(LocalDateTime.now())
                .amount(orderRequestDto.getAmount())
                .lecture(savedLecture)
                .user(savedUser)
                .build();

        orderRepository.save(toSaveOrder);
    }

    // 주문 내역 조회
    public SaveOrderRespDto readOrder(String orderId) {
        OrderEntity savedOrder = orderRepository.findByOrderId(orderId).orElseThrow();
        return SaveOrderRespDto.builder()
                .orderId(savedOrder.getOrderId())
                .amount(savedOrder.getAmount())
                .lectureName(savedOrder.getLecture().getLecturerName())
                .userId(savedOrder.getUser().getUserId())
                .userName(savedOrder.getUser().getName())
                .orderDate(savedOrder.getOrderDate())
                .userEmail(savedOrder.getUser().getEmail())
                .userPhone(savedOrder.getUser().getPhone())
                .build();
    }
}
