package com.wid.entity;

import com.wid.enums.PaymentMethod;
import com.wid.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payment")
public class PaymentEntity {

    @Id
    private String paymentId;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime paymentDate;

    private Integer amount;

    private PaymentMethod method;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

}
