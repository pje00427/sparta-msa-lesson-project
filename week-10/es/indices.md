# Elasticsearch 인덱스 설계

## 인덱스명: user-activity

### 설명
사용자 활동 로그를 저장하는 인덱스입니다.
로그인/로그아웃, 상품 조회, 장바구니 담기, 주문 등 사용자 행동을 기록합니다.

---

## 필드 설계

| 필드명 | 타입 | 설명 |
|---|---|---|
| user_id | keyword | 사용자 ID (정확히 일치 검색) |
| action | keyword | 사용자 행동 (login, view, cart, order) |
| product_id | keyword | 상품 ID |
| product_name | text | 상품명 (전문 검색) |
| category | keyword | 카테고리 (집계용) |
| description | text | 활동 설명 (전문 검색) |
| ip_address | keyword | 접속 IP |
| timestamp | date | 활동 시간 |

### text vs keyword 선택 기준
- **keyword**: 정확히 일치 검색, 집계(aggregation)가 필요한 필드
- **text**: 부분 검색이 필요한 필드 (상품명, 설명 등)

---

## 정적 매핑 JSON

```json
PUT /user-activity
{
  "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 0
  },
  "mappings": {
    "properties": {
      "user_id":      { "type": "keyword" },
      "action":       { "type": "keyword" },
      "product_id":   { "type": "keyword" },
      "product_name": { "type": "text" },
      "category":     { "type": "keyword" },
      "description":  { "type": "text" },
      "ip_address":   { "type": "keyword" },
      "timestamp":    { "type": "date" }
    }
  }
}
```

---

## REST API 매핑 추가 결과

```json
{
  "acknowledged": true,
  "shards_acknowledged": true,
  "index": "user-activity"
}
```
