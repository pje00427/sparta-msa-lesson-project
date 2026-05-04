# Docker 볼륨을 활용한 데이터 유지

목표: Docker 볼륨을 사용해 MySQL 데이터베이스를 컨테이너 재생성 후에도 유지하는 방법을 실습합니다.

## 1) 볼륨 생성

```bash
docker volume create myvolume

(base) PS C:\project\sparta-project\project-4\sparta-msa-project-part-4\week-09\ExamplePart4\demo> docker volume create myvolume
myvolume
```

## 2) MySQL 컨테이너 실행 (볼륨 마운트)

```bash
docker run -d \
  --name mysql-db \
  -e MYSQL_ROOT_PASSWORD=rootpass \
  -e MYSQL_DATABASE=testdb \
  -v myvolume:/var/lib/mysql \
  mysql:8.0


  >>   mysql:8.0
위치 줄:2 문자:5
+   --name mysql-db \
+     ~
단항 연산자 '--' 뒤에 식이 없습니다.
위치 줄:2 문자:5
+   --name mysql-db \
+     ~~~~
식 또는 문에서 예기치 않은 'name' 토큰입니다.
    + CategoryInfo          : ParserError: (:) [], ParentContainsErrorRecordException
    + FullyQualifiedErrorId : MissingExpressionAfterOperator
```

## 3) 데이터 생성 및 확인
- MySQL 접속 후 데이터베이스/테이블 생성 및 데이터 삽입.

```bash
docker exec -it mysql-db mysql -uroot -prootpass -e "CREATE TABLE testdb.kv (k VARCHAR(100), v VARCHAR(100)); INSERT INTO testdb.kv VALUES ('a','1'); SELECT * FROM testdb.kv;"

Error response from daemon: No such container: mysql-db
```

## 4) 컨테이너 삭제 후 데이터 확인

```bash
docker stop mysql-db
docker rm mysql-db
# 다시 같은 볼륨으로 컨테이너 실행
docker run -d --name mysql-db -e MYSQL_ROOT_PASSWORD=rootpass -v myvolume:/var/lib/mysql mysql:8.0
# 데이터 확인
docker exec -it mysql-db mysql -uroot -prootpass -e "SELECT * FROM testdb.kv;"
```

- 볼륨에 데이터가 남아 있으면 삭제/재생성 이후에도 테이블 및 데이터가 유지됩니다.

## 5) docker-compose 예시

```yaml
version: '3.8'
services:
  db:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpass
      MYSQL_DATABASE: testdb
    volumes:
      - myvolume:/var/lib/mysql
volumes:
  myvolume:s
```

- `docker-compose up -d` 후 동일한 방식으로 데이터 유지 확인.
