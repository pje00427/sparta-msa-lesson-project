# ingress-nginx Deprecated 이후 대안

## 배경
- 2025년 3월 IngressNightmare(CVE-2025-1974) 보안 취약점 발생
- 2025년 11월 Kubernetes 공식 ingress-nginx 은퇴 발표
- 2026년 3월 이후 버그 수정, 보안 패치, 신규 릴리스 없음

## 대안 1. Gateway API (공식 권고)
- Kubernetes 공식 차세대 표준
- L4/L7 트래픽 모두 처리 가능
- 구현 컨트롤러: Istio, Cilium, Envoy Gateway, NGINX Gateway Fabric, Traefik

## 대안 2. 기존 Ingress 표준 유지 시 대안 컨트롤러

| 컨트롤러 | 특징 |
|---------|------|
| NGINX Ingress Controller (NGINX/F5 공식) | 상용 지원 가능 |
| Traefik | 자동 디스커버리, Let's Encrypt 통합 |
| HAProxy Ingress | 고성능 |
| Istio Gateway | 서비스 메시까지 함께 도입할 때 |
| Cilium Ingress | eBPF 기반, 네트워크 정책 통합 |