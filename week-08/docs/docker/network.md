# Docker 네트워크 실습

목표: 사용자 정의 네트워크에서 Spring 앱과 Redis를 함께 실행하고, Spring 애플리케이션이 Redis에 데이터를 저장/조회하도록 구성합니다.

## 1) 네트워크 생성

```bash
docker network create mynetwork

(base) PS C:\project\sparta-project\project-4\sparta-msa-project-part-4\week-09\ExamplePart4\demo> docker network create mynetwork
bd2f14c6bd210c58445e982d96bade2aeafa0a39d62f0abe239812b54c14e449
```

## 2) Redis 컨테이너 실행

```bash
docker run -d --name redis --network mynetwork redis:7-alpine

(base) PS C:\project\sparta-project\project-4\sparta-msa-project-part-4\week-09\ExamplePart4\demo> docker run -d --name redis --network mynetwork redis:7-alpine
Unable to find image 'redis:7-alpine' locally
7-alpine: Pulling from library/redis
6447bc944d85: Pull complete 
1f0676652ae4: Pull complete 
2da9501c87e4: Pull complete 
4f4fb700ef54: Pull complete 
9572acab3f32: Pull complete 
47836b8a3274: Pull complete 
897d797d2723: Pull complete 
51bf34b872a6: Pull complete 
c3c3ab8d6b01: Download complete 
2074d4e4f1bb: Download complete 
Digest: sha256:7aec734b2bb298a1d769fd8729f13b8514a41bf90fcdd1f38ec52267fbaa8ee6
Status: Downloaded newer image for redis:7-alpine
ecefe36bf7c32fc39f524a0fc75fa6e20c88ca4cc1598b38a0c6013b1b9712ca
```

## 3) Spring 앱을 같은 네트워크에서 실행
- 위에서 만든 `examplepart4:latest` 이미지를 사용:

```bash
docker run -d --name spring-app --network mynetwork -p 8080:8080 examplepart4:latest

(base) PS C:\project\sparta-project\project-4\sparta-msa-project-part-4\week-09\ExamplePart4\demo> docker run -d --name spring-app --network mynetwork -p 8080:8080 examplepart4:latest
Unable to find image 'examplepart4:latest' locally
docker: Error response from daemon: pull access denied for examplepart4, repository does not exist or may require 'docker login'

Run 'docker run --help' for more informatio
```

## 4) Spring Boot에서 Redis 사용 (간단한 설정)
- 의존성(Gradle 예):

```gradle
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
```

- `application.yml` 예:

```yaml
spring:
  redis:
    host: redis
    port: 6379
```

- 간단한 예제 코드 (서비스 레이어):

```java
@Service
public class RedisExampleService {
    private final StringRedisTemplate redis;
    public RedisExampleService(StringRedisTemplate redis) { this.redis = redis; }

    public void save(String key, String value) { redis.opsForValue().set(key, value); }
    public String get(String key) { return redis.opsForValue().get(key); }
}
```

이제 `spring-app`은 네트워크 내에서 `redis:6379`로 Redis에 접근 가능합니다.

## 5) docker-compose 예시 (더 쉽게 구성)

```yaml
version: '3.8'
services:
  redis:
    image: redis:7-alpine
    container_name: redis
    networks:
      - mynetwork

  spring-app:
    image: examplepart4:latest
    container_name: spring-app
    ports:
      - "8080:8080"
    networks:
      - mynetwork

networks:
  mynetwork:
    driver: bridge
```

- 실행:

```bash
docker-compose up --build
```

## 6) 확인
- Redis CLI로 접속하거나 Spring 엔드포인트로 데이터를 저장/조회 테스트.

```bash
docker exec -it redis redis-cli
127.0.0.1:6379> GET mykey
```
