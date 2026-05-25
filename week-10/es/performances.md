# Logstash 데이터 손실 최소화 설정

## 1. Persistent Queue (영구 큐)

데이터를 메모리가 아닌 디스크에 저장하여 Logstash 장애 시에도 데이터를 보존합니다.

```yaml
# logstash.yml
queue.type: persisted
queue.max_bytes: 1gb
queue.checkpoint.writes: 1024
```

### 동작 방식
- input에서 받은 데이터를 디스크에 먼저 저장
- output으로 전송 성공 후 큐에서 제거
- Logstash 재시작 시 큐에 남은 데이터부터 재처리

---

## 2. Dead Letter Queue (DLQ)

처리 실패한 데이터를 버리지 않고 별도 큐에 저장하여 나중에 재처리할 수 있습니다.

```yaml
# logstash.yml
dead_letter_queue.enable: true
dead_letter_queue.max_bytes: 1gb
path.dead_letter_queue: /var/log/logstash/dlq
```

### 동작 방식
- Elasticsearch 인덱싱 실패 시 DLQ에 저장
- 원인 분석 후 DLQ 데이터를 다시 처리 가능
- 데이터 유실 없이 오류 복구 가능

---

## 3. Retry 설정

Elasticsearch가 일시적으로 응답하지 않을 때 자동으로 재시도합니다.

```ruby
# logstash.conf
output {
  elasticsearch {
    hosts => ["http://elasticsearch:9200"]
    index => "applogs-%{+YYYY.MM.dd}"
    retry_on_conflict => 3
    retry_initial_interval => 2
    retry_max_interval => 64
  }
}
```

### 동작 방식
- 전송 실패 시 지정한 횟수만큼 재시도
- 재시도 간격을 점진적으로 늘려 과부하 방지
- 최대 재시도 후에도 실패 시 DLQ로 이동

---

## 정리

| 설정 | 목적 | 효과 |
|---|---|---|
| Persistent Queue | Logstash 장애 대비 | 재시작 시 데이터 복구 |
| Dead Letter Queue | 처리 실패 데이터 보존 | 데이터 유실 방지 |
| Retry | 일시적 장애 대비 | 자동 재전송 |
