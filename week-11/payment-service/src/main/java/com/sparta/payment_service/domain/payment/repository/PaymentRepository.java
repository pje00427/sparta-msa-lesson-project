package com.sparta.payment_service.domain.payment.repository;

import com.sparta.payment_service.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}