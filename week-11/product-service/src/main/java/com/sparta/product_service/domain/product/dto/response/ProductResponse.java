package com.sparta.product_service.domain.product.dto.response;

import com.sparta.product_service.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {

  private Long id;
  private String name;
  private Integer price;
  private String description;
  private Integer stock;

  public static ProductResponse from(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .description(product.getDescription())
        .stock(product.getStock())
        .build();
  }
}