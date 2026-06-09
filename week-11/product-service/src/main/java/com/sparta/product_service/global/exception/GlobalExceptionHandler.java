package com.sparta.product_service.global.exception;

import com.sparta.product_service.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // DomainException 처리
  @ExceptionHandler(DomainException.class)
  public ResponseEntity<ApiResponse<Void>> handleDomainException(DomainException e) {
    return ResponseEntity
        .status(e.getExceptionCode().getHttpStatus())
        .body(ApiResponse.error(e.getExceptionCode().getMessage()));
  }

  // @Valid 검증 실패 처리
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
    String message = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
    return ResponseEntity
        .badRequest()
        .body(ApiResponse.error(message));
  }

  // 그 외 모든 예외 처리
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
    return ResponseEntity
        .internalServerError()
        .body(ApiResponse.error("서버 내부 오류가 발생했습니다."));
  }
}