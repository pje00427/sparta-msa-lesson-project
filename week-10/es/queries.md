# Elasticsearch 검색 및 집계 쿼리

## 샘플 데이터
이커머스 주문 로그 10개를 Bulk API로 입력
- 필드: order_id, user_id, product, category, price, quantity, status, timestamp
- 카테고리: 신발, 의류, 가방
- 주문 상태: completed, pending, cancelled, shipping

---

## 쿼리 1 - 날짜 범위 필터

### 설명
특정 기간(2024-01-16 ~ 2024-01-18) 사이의 주문을 조회합니다.

### 쿼리
```json
GET /ecommerce-logs/_search
{
  "query": {
    "range": {
      "timestamp": {
        "gte": "2024-01-16T00:00:00",
        "lte": "2024-01-18T23:59:59"
      }
    }
  }
}
```

### 결과
- 총 7개 문서 조회 (ORD-004 ~ ORD-010)

---

## 쿼리 2 - 카테고리별 주문 수 집계

### 설명
카테고리별로 주문이 몇 건인지 집계합니다.

### 쿼리
```json
GET /ecommerce-logs/_search
{
  "size": 0,
  "aggs": {
    "orders_by_category": {
      "terms": {
        "field": "category.keyword"
      }
    }
  }
}
```

### 결과
- 의류: 4개
- 가방: 3개
- 신발: 3개

---

## 쿼리 3 - 주문 상태 필터

### 설명
completed 상태인 주문만 필터링합니다.

### 쿼리
```json
GET /ecommerce-logs/_search
{
  "query": {
    "term": {
      "status.keyword": "completed"
    }
  }
}
```

### 결과
- 총 5개 문서 조회 (ORD-001, ORD-004, ORD-005, ORD-007, ORD-009)

---

## 쿼리 4 - 카테고리별 평균 가격 집계

### 설명
카테고리별 평균 주문 금액을 집계합니다.

### 쿼리
```json
GET /ecommerce-logs/_search
{
  "size": 0,
  "aggs": {
    "avg_price_by_category": {
      "terms": {
        "field": "category.keyword"
      },
      "aggs": {
        "avg_price": {
          "avg": {
            "field": "price"
          }
        }
      }
    }
  }
}
```

### 결과
- 의류: 평균 47,750원
- 가방: 평균 96,667원
- 신발: 평균 91,333원

---

## 쿼리 5 - 가격 범위 필터 + 주문 상태별 집계

### 설명
5만원 이상 주문 중 주문 상태별 건수를 집계합니다.

### 쿼리
```json
GET /ecommerce-logs/_search
{
  "size": 0,
  "query": {
    "range": {
      "price": {
        "gte": 50000
      }
    }
  },
  "aggs": {
    "status_count": {
      "terms": {
        "field": "status.keyword"
      }
    }
  }
}
```

### 결과
- 5만원 이상 주문 총 7개
- completed: 3개
- pending: 2개
- cancelled: 1개
- shipping: 1개
