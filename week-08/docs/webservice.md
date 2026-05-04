# Spring Boot 웹 애플리케이션 Docker 이미지 생성

이 가이드는 `week-09/ExamplePart4` 예제(Spring Boot 프로젝트)를 Docker 이미지로 빌드하고 실행하는 방법을 설명합니다.

## 1) 프로젝트 빌드 (예제: `week-09/ExamplePart4`)

- 프로젝트 루트로 이동:

```bash
cd week-09/ExamplePart4

(base) PS C:\project\sparta-project\project-4\sparta-msa-project-part-4\week-09\ExamplePart4> 
```

- 빌드 (Gradle wrapper 사용):

```bash
./gradlew clean bootJar
```

생성된 실행 가능한 JAR은 `build/libs/`에 위치합니다.

## 2) Dockerfile (권장)
프로젝트 루트(`week-09/ExamplePart4`)에 다음 `Dockerfile`을 사용합니다.

```Dockerfile
# Build stage (optional if you build jar locally)
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY . /app
RUN ./gradlew bootJar --no-daemon

# Run stage
FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=build/libs/*-boot.jar
COPY --from=build /app/${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

- 만약 로컬에서 이미 JAR을 빌드했다면 다중 스테이지 없이 간단히 JAR을 복사하는 `Dockerfile` 사용 가능.

## 3) 이미지 빌드

```bash
docker build -t examplepart4:latest .
```

## 4) 컨테이너 실행

```bash
docker run --name spring-app -p 8080:8080 examplepart4:latest
```

## 5) 헬스체크 및 테스트
- 브라우저 또는 `curl`로 `http://localhost:8080/` 엔드포인트 호출.

```bash
curl -v http://localhost:8080/
```

## 6) 추가 팁
- 프로덕션용 다중 스테이지 빌드: 빌드 이미지는 더 크므로 런타임 이미지는 가볍게 유지.
- 이미지 태깅: `examplepart4:1.0.0`처럼 버전 태그를 사용.
- 레지스트리에 푸시: `docker tag` 후 `docker push` (ECR/GCR/ACR 연동 문서 참고).

(참고: 이 저장소에는 이미 `week-09/ExamplePart4/Dockerfile`와 `build/` 산출물이 포함될 수 있으니, 필요 시 해당 파일을 확인하고 이 가이드를 참고하세요.)
