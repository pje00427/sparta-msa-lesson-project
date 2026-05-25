package com.sparta;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class OrderLog {
  private String orderId;
  private String userId;
  private String product;
  private String category;
  private int price;
  private int quantity;
  private String status;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime timestamp;

  public OrderLog(String orderId, String userId, String product,
      String category, int price, int quantity, String status) {
    this.orderId = orderId;
    this.userId = userId;
    this.product = product;
    this.category = category;
    this.price = price;
    this.quantity = quantity;
    this.status = status;
    this.timestamp = LocalDateTime.now();
  }

  // Getters
  public String getOrderId() { return orderId; }
  public String getUserId() { return userId; }
  public String getProduct() { return product; }
  public String getCategory() { return category; }
  public int getPrice() { return price; }
  public int getQuantity() { return quantity; }
  public String getStatus() { return status; }
  public LocalDateTime getTimestamp() { return timestamp; }
}