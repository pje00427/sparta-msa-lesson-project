package com.sparta.payment_service.domain.payment.service;

import com.sparta.payment_service.domain.payment.dto.response.PaymentResponse;
import com.sparta.payment_service.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;

  @Transactional(readOnly = true)
  public List<PaymentResponse> getPayments() {
    return paymentRepository.findAll().stream()
        .map(PaymentResponse::from)
        .toList();
  }
}