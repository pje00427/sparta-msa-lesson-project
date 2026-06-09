package com.sparta.payment_service.kafka.consumer;

import com.sparta.payment_service.domain.payment.entity.Payment;
import com.sparta.payment_service.domain.payment.entity.PaymentStatus;
import com.sparta.payment_service.domain.payment.repository.PaymentRepository;
import com.sparta.payment_service.kafka.event.OrderCreatedEvent;
import com.sparta.payment_service.kafka.event.PaymentResultEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventConsumer {

  private final PaymentRepository paymentRepository;
  private final KafkaTemplate<String, PaymentResultEvent> kafkaTemplate;

  private static final String PAYMENT_RESULT_TOPIC = "payment-result";
  private static final int MAX_RETRY = 3;

  @KafkaListener(
      topics = "order-created",
      groupId = "payment-service-group",
      containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumeOrderCreatedEvent(OrderCreatedEvent event, Acknowledgment acknowledgment) {
    log.info("Order created event received - orderId: {}", event.getOrderId());

    boolean paymentSuccess = processPaymentWithRetry(event.getOrderId());

    Payment payment = Payment.builder()
        .orderId(event.getOrderId())
        .status(paymentSuccess ? PaymentStatus.COMPLETED : PaymentStatus.FAILED)
        .build();

    paymentRepository.save(payment);

    PaymentResultEvent resultEvent = PaymentResultEvent.builder()
        .orderId(event.getOrderId())
        .status(paymentSuccess ? "PAYMENT_COMPLETED" : "PAYMENT_FAILED")
        .build();

    kafkaTemplate.send(PAYMENT_RESULT_TOPIC, String.valueOf(event.getOrderId()), resultEvent);
    log.info("Payment result event published - orderId: {}, status: {}", event.getOrderId(), resultEvent.getStatus());

    acknowledgment.acknowledge();
  }

  private boolean processPaymentWithRetry(Long orderId) {
    for (int i = 0; i < MAX_RETRY; i++) {
      if (orderId % 5 != 0) {
        log.info("Payment success - orderId: {}", orderId);
        return true;
      }
      log.warn("Payment failed, retrying... {}/{} - orderId: {}", i + 1, MAX_RETRY, orderId);
    }
    log.error("Payment finally failed after {} retries - orderId: {}", MAX_RETRY, orderId);
    return false;
  }
}