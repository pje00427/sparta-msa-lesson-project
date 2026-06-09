package com.sparta.product_service.domain.product.service;

import com.sparta.product_service.domain.product.dto.request.ProductRequest;
import com.sparta.product_service.domain.product.dto.response.ProductResponse;
import com.sparta.product_service.domain.product.entity.Product;
import com.sparta.product_service.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional
  public ProductResponse createProduct(ProductRequest request) {
    Product product = Product.builder()
        .name(request.getName())
        .price(request.getPrice())
        .description(request.getDescription())
        .stock(request.getStock())
        .build();

    Product savedProduct = productRepository.save(product);
    return ProductResponse.from(savedProduct);
  }

  @Transactional(readOnly = true)
  public List<ProductResponse> getProducts() {
    return productRepository.findAll().stream()
        .map(ProductResponse::from)
        .toList();
  }
}