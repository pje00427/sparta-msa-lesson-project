AWS (Amazon Web Services):
      Amazon EKS (Elastic Kubernetes Service): 쿠버네티스를 통해 컨테이너화된 애플리케이션을 AWS 내에서 쉽게 실행할 수 있는 관리형 서비스.
      Amazon ECS (Elastic Container Service): AWS 맞춤형 컨테이너 오케스트레이션 서비스로, 도커(Docker) 컨테이너를 지원.
      AWS Fargate: 서버나 클러스터를 관리할 필요 없이 컨테이너를 실행할 수 있는 서버리스 컴퓨팅 엔진.

GCP (Google Cloud Platform):
      Google Kubernetes Engine (GKE): 쿠버네티스의 원조답게 가장 고도화된 관리형 Kubernetes 서비스를 제공하며, 자동 확장 및 운영 최적화에 강력합니다.

Azure (Microsoft Azure):
      Azure Kubernetes Service (AKS): Azure에서 쿠버네티스 사용을 단순화하며, 특히 마이크로소프트의 기존 엔터프라이즈 환경(Windows Server, Active Directory)과 높은 호환성을 제공합니다.

항목              가상 머신 (VM)                     도커 컨테이너 (Docker)
가상화 방식       하드웨어 가상화 (Hardware)          OS 가상화 (OS 수준)
운영체제          Guest OS 필요 (무거움)              Host OS 커널 공유 (가벼움)
시작 속도         분 단위 (느림)                      초 단위 (매우 빠름)
이미지 크기       GB 단위 (큼)                        MB 단위 (작음)
이식성            낮음 (환경 의존적)                  매우 높음 (어디서나 동일)
주요 활용         독립적인 서버 환경 필요 시           마이크로서비스, CI/CD, 경량화
