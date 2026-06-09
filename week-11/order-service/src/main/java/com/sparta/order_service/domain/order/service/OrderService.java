package com.sparta.order_service.domain.order.service;

import com.sparta.order_service.domain.order.dto.request.OrderRequest;
import com.sparta.order_service.domain.order.dto.response.OrderResponse;
import com.sparta.order_service.domain.order.entity.Order;
import com.sparta.order_service.domain.order.entity.OrderStatus;
import com.sparta.order_service.domain.order.repository.OrderRepository;
import com.sparta.order_service.kafka.event.OrderCreatedEvent;
import com.sparta.order_service.kafka.producer.OrderEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderEventProducer orderEventProducer;

  @Transactional
  public OrderResponse createOrder(OrderRequest request) {
    // 1. 주문 생성 (PENDING_PAYMENT 상태)
    Order order = Order.builder()
        .productId(request.getProductId())
        .quantity(request.getQuantity())
        .status(OrderStatus.PENDING_PAYMENT)
        .build();

    Order savedOrder = orderRepository.save(order);

    // 2. Kafka 이벤트 발행
    OrderCreatedEvent event = OrderCreatedEvent.builder()
        .orderId(savedOrder.getId())
        .productId(savedOrder.getProductId())
        .quantity(savedOrder.getQuantity())
        .build();

    orderEventProducer.sendOrderCreatedEvent(event);

    return OrderResponse.from(savedOrder);
  }

  @Transactional(readOnly = true)
  public List<OrderResponse> getOrders() {
    return orderRepository.findAll().stream()
        .map(OrderResponse::from)
        .toList();
  }

  @Transactional
  public void updateOrderStatus(Long orderId, OrderStatus status) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
    order.updateStatus(status);
    log.info("Order status updated - orderId: {}, status: {}", orderId, status);
  }
}