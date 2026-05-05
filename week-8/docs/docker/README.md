# Docker 및 클라우드 개념 정리

## 1. 클라우드 환경에서 컨테이너 활용 방식

### AWS (Amazon Web Services)
- ECS (Elastic Container Service): Docker 컨테이너를 AWS 환경에서 실행하고 관리하는 서비스
- EKS (Elastic Kubernetes Service): Kubernetes 기반으로 컨테이너를 오케스트레이션하는 서비스
- ECR (Elastic Container Registry): Docker 이미지를 저장하고 관리하는 프라이빗 레지스트리
- Fargate: 서버 관리 없이 컨테이너를 실행할 수 있는 서버리스 컨테이너 서비스

### GCP (Google Cloud Platform)
- GKE (Google Kubernetes Engine): Google이 제공하는 관리형 Kubernetes 서비스
- Cloud Run: 컨테이너 이미지를 서버리스 환경에서 실행하는 서비스
- Artifact Registry: Docker 이미지를 저장하고 관리하는 레지스트리

### Azure (Microsoft Azure)
- AKS (Azure Kubernetes Service): Azure에서 제공하는 관리형 Kubernetes 서비스
- ACR (Azure Container Registry): Docker 이미지를 저장하고 관리하는 레지스트리
- Azure Container Apps: 서버리스 환경에서 컨테이너를 실행하는 서비스

---


## 2. VM과 Docker의 차이점

| 항목 | VM (Virtual Machine) | Docker (Container) |
|------|---------------------|-------------------|
| 가상화 방식 | 하이퍼바이저를 통해 하드웨어 레벨 가상화 | 호스트 OS의 커널을 공유하여 OS 레벨 가상화 |
| OS       | 각 VM마다 독립적인 OS 보유          | 독립적인 OS 없이 프로세스 수준 격리 |
| 용량      | 수 GB 수준으로 무거움              | 수 MB 수준으로 가벼움 |
| 시작 시간  | 수 분 소요                       | 수 초 이내 |
| 자원 관리  | 자원을 미리 고정 할당               | 자원을 동적으로 사용 |
| 격리 수준  | 완전한 격리                       | 프로세스 수준 격리 |
| 사용 상황  | 보안이 중요한 경우                  | 빠른 배포와 확장이 필요한 경우 |

---

## 3. 주요 질문 답변

### Q. Docker의 주요 개념은 무엇인가?

- Image: 컨테이너를 실행하기 위한 설계도. 애플리케이션 코드, 라이브러리, 환경설정 등을 포함한 읽기 전용 템플릿
- Container: 이미지를 기반으로 실행된 독립적인 인스턴스. 격리된 환경에서 애플리케이션이 실행됨
- Dockerfile: Docker 이미지를 빌드하기 위한 설정 파일. 레이어 기반으로 이미지를 구성
- Docker Hub: 공개된 Docker 이미지를 저장하고 공유하는 레지스트리
- Docker Compose: 여러 컨테이너를 하나의 서비스로 정의하고 관리하는 도구
- Volume: 컨테이너가 삭제되어도 데이터를 유지할 수 있는 독립적인 저장소
- Network: 컨테이너 간 통신을 담당하는 기능. Bridge, Host, None, Overlay 등의 종류가 있음

### Q. VM과 컨테이너의 차이점은 무엇인가?

VM은 하이퍼바이저를 통해 하드웨어를 가상화하며 각 VM마다 독립적인 OS를 가집니다.
반면 컨테이너는 호스트 OS의 커널을 공유하고 그 위에서 격리된 프로세스로 실행됩니다.

VM은 완전한 격리를 제공하지만 무겁고 시작 시간이 길다는 단점이 있습니다.
컨테이너는 가볍고 빠르게 실행되며 이식성이 높지만 VM에 비해 격리 수준이 낮습니다.

### Q. 클라우드에서 Docker를 사용하는 이유는 무엇인가?

1. 환경 일관성: 개발, 테스트, 운영 환경을 동일하게 유지할 수 있어 "내 환경에서는 됐는데" 문제를 해결
2. 빠른 배포: 이미지를 기반으로 컨테이너를 빠르게 실행할 수 있어 배포 속도가 향상됨
3. 효율적인 자원 사용: VM에 비해 가볍기 때문에 동일한 인프라에서 더 많은 애플리케이션 실행 가능
4. 확장성: 트래픽 증가 시 컨테이너를 빠르게 증설하여 대응 가능
5. 이식성: 클라우드 벤더에 종속되지 않고 AWS, GCP, Azure 등 어디서든 동일하게 실행 가능