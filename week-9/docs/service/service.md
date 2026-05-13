# k8s Service 구성요소 역할

| 용어 | 역할 |
|------|------|
| Service | Pod에 접근하는 고정된 입구, 로드밸런서 역할 |
| Endpoints | Service가 트래픽을 보낼 준비된 Pod의 IP 목록 |
| Deployment | Pod를 몇 개 띄울지 선언하고 관리하는 설정 |
| kubelet | 각 노드에서 Pod 상태를 감시하고 Probe 결과를 보고하는 에이전트 |
| Pod | 실제 컨테이너가 실행되는 최소 단위 |