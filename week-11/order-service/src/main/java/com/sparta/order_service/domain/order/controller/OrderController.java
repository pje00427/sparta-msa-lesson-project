package com.sparta.order_service.domain.order.controller;

import com.sparta.order_service.domain.order.dto.request.OrderRequest;
import com.sparta.order_service.domain.order.dto.response.OrderResponse;
import com.sparta.order_service.domain.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> getOrders() {
    return ResponseEntity.ok(orderService.getOrders());
  }
}
