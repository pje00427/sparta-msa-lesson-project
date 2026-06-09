package com.sparta.order_service.domain.order.dto.response;

import com.sparta.order_service.domain.order.entity.Order;
import com.sparta.order_service.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponse {

  private Long id;
  private Long productId;
  private Integer quantity;
  private OrderStatus status;

  public static OrderResponse from(Order order) {
    return OrderResponse.builder()
        .id(order.getId())
        .productId(order.getProductId())
        .quantity(order.getQuantity())
        .status(order.getStatus())
        .build();
  }
}