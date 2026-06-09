package com.sparta.payment_service.domain.payment.dto.response;

import com.sparta.payment_service.domain.payment.entity.Payment;
import com.sparta.payment_service.domain.payment.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponse {

  private Long id;
  private Long orderId;
  private PaymentStatus status;

  public static PaymentResponse from(Payment payment) {
    return PaymentResponse.builder()
        .id(payment.getId())
        .orderId(payment.getOrderId())
        .status(payment.getStatus())
        .build();
  }
}