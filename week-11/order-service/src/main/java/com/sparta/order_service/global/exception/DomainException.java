package com.sparta.order_service.global.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

  private final DomainExceptionCode exceptionCode;

  public DomainException(DomainExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }
}