package com.sparta.order_service.kafka.producer;

import com.sparta.order_service.kafka.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventProducer {

  private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

  @Value("${kafka.topic.order-created:order-created}")
  private String orderCreatedTopic;

  public void sendOrderCreatedEvent(OrderCreatedEvent event) {
    kafkaTemplate.send(orderCreatedTopic, String.valueOf(event.getOrderId()), event);
    log.info("Order created event published - orderId: {}", event.getOrderId());
  }
}