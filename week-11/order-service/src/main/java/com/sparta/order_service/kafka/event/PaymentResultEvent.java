package com.sparta.order_service.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResultEvent {

  private Long orderId;
  private String status; // PAYMENT_COMPLETED or PAYMENT_FAILED
}