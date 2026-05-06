package com.sparta.docker_practice;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

  private final StringRedisTemplate redisTemplate;

  public RedisController(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostMapping("/set")
  public String setValue(@RequestParam String key, @RequestParam String value) {
    redisTemplate.opsForValue().set(key, value);
    return "저장 완료! key: " + key + ", value: " + value;
  }

  @GetMapping("/get")
  public String getValue(@RequestParam String key) {
    String value = redisTemplate.opsForValue().get(key);
    return "조회 결과 - key: " + key + ", value: " + value;
  }
}