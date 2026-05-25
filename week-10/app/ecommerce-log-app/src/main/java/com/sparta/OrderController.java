package com.sparta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private static final Logger log = LoggerFactory.getLogger(OrderController.class);

  @PostMapping
  public String createOrder(@RequestBody OrderLog orderLog) {
    log.info("order_created", orderLog);
    return "주문이 생성되었습니다: " + orderLog.getOrderId();
  }

  @GetMapping("/sample")
  public String createSampleLogs() {
    List<OrderLog> sampleOrders = List.of(
        new OrderLog("ORD-" + UUID.randomUUID().toString().substring(0, 8),
            "user_01", "운동화", "신발", 89000, 1, "completed"),
        new OrderLog("ORD-" + UUID.randomUUID().toString().substring(0, 8),
            "user_02", "청바지", "의류", 65000, 2, "pending"),
        new OrderLog("ORD-" + UUID.randomUUID().toString().substring(0, 8),
            "user_03", "백팩", "가방", 120000, 1, "shipping")
    );

    sampleOrders.forEach(order ->
        log.info("order_created orderId={} userId={} product={} category={} price={} status={}",
            order.getOrderId(), order.getUserId(), order.getProduct(),
            order.getCategory(), order.getPrice(), order.getStatus())
    );
    return "샘플 로그 3개 생성 완료!";
  }
}