package com.sparta.order_service.kafka.consumer;

import com.sparta.order_service.domain.order.entity.OrderStatus;
import com.sparta.order_service.domain.order.service.OrderService;
import com.sparta.order_service.kafka.event.PaymentResultEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentResultConsumer {

  private final OrderService orderService;

  @KafkaListener(
      topics = "payment-result",
      groupId = "order-service-group",
      containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumePaymentResultEvent(PaymentResultEvent event, Acknowledgment acknowledgment) {
    log.info("Payment result event received - orderId: {}, status: {}", event.getOrderId(), event.getStatus());

    OrderStatus status = event.getStatus().equals("PAYMENT_COMPLETED")
        ? OrderStatus.PAID
        : OrderStatus.PAYMENT_FAILED;

    orderService.updateOrderStatus(event.getOrderId(), status);

    acknowledgment.acknowledge();
  }
}