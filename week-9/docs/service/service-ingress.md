# Service vs Ingress 차이

## 차이점

| 구분 | Service | Ingress |
|------|---------|---------|
| 역할 | Pod에 접근하는 고정 주소 제공 | 도메인/경로 기반 라우팅 규칙 정의 |
| 접근 방법 | IP:포트 | 도메인이름/경로 |
| 분기 기준 | 포트 번호 | Host 헤더, URL 경로 |
| 네트워크 계층 | L4 (Transport Layer) | L7 (Application Layer) |

## 한 줄 요약
- **Service**: Pod에 접근하는 문(고정 IP/포트)
- **Ingress**: 문 앞의 안내원(도메인/경로로 어느 문으로 갈지 결정)