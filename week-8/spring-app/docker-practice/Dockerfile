# 1단계: 빌드 환경
FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

# 캐시 최적화를 위해 Gradle 관련 파일 먼저 복사
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# gradlew 실행 권한 부여
RUN chmod +x gradlew

# 의존성 다운로드
RUN ./gradlew dependencies --no-daemon

# 전체 소스 복사 후 빌드
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean bootJar -x test --no-daemon

# 2단계: 실행 환경
FROM amazoncorretto:21-alpine

WORKDIR /app

# jar 매치 수정 ( plain 으로 충돌 날 수 있으므로 )
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]