package com.sparta.payment_service.domain.payment.controller;

import com.sparta.payment_service.domain.payment.dto.response.PaymentResponse;
import com.sparta.payment_service.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService paymentService;

  @GetMapping
  public ResponseEntity<List<PaymentResponse>> getPayments() {
    return ResponseEntity.ok(paymentService.getPayments());
  }
}