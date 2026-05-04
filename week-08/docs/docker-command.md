# Docker 설치 및 기본 명령어 실습

## 1) Docker 설치 확인
- 설치 후 버전 확인:

```bash
docker --version
```
Docker version 29.2.1, build a5c7197

## 2) `hello-world` 실행

```bash
docker run hello-world
```

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/

## 3) 실행 중인 컨테이너 확인

```bash
docker ps
# 모든 컨테이너(중지 포함)

CONTAINER ID   IMAGE                    COMMAND                   CREATED       STATUS         PORTS                                         NAMES
298c78bf1247   redis                    "docker-entrypoint.s…"   3 weeks ago   Up 8 minutes   0.0.0.0:6379->6379/tcp, [::]:6379->6379/tcp   redis-server
1e73fe1ed71e   mysql:8.0                "docker-entrypoint.s…"   4 weeks ago   Up 8 minutes   0.0.0.0:3306->3306/tcp, [::]:3306->3306/tcp   spring-mysql
7cf56430dfbe   pgvector/pgvector:pg16   "docker-entrypoint.s…"   4 weeks ago   Up 8 minutes   0.0.0.0:5433->5432/tcp, [::]:5433->5432/tcp   local-postgres-vector
cf7fd21c43bf   pgvector/pgvector:pg16   "docker-entrypoint.s…"   5 weeks ago   Up 8 minutes   0.0.0.0:5432->5432/tcp, [::]:5432->5432/tcp   local-postgres

docker ps -a

CONTAINER ID   IMAGE                    COMMAND                   CREATED         STATUS                     PORTS                                         NAMES
2f639eb24698   hello-world              "/hello"                  8 minutes ago   Exited (0) 8 minutes ago                                                 sharp_stonebraker
b02518aeb4b8   9d4471944ed4             "/docker-entrypoint.…"   7 days ago      Created                                                                  quirky_beaver
07644a2a3c02   nginx                    "/docker-entrypoint.…"   7 days ago      Created                                                                  mystifying_ramanujan
28ba99d863f8   nginx                    "/docker-entrypoint.…"   7 days ago      Exited (255) 6 days ago    0.0.0.0:8080->80/tcp                          zealous_carver
05181a0733ff   hello-world              "/hello"                  7 days ago      Exited (0) 7 days ago                                                    vigilant_shaw
298c78bf1247   redis                    "docker-entrypoint.s…"   3 weeks ago     Up 8 minutes               0.0.0.0:6379->6379/tcp, [::]:6379->6379/tcp   redis-server
1e73fe1ed71e   mysql:8.0                "docker-entrypoint.s…"   4 weeks ago     Up 8 minutes               0.0.0.0:3306->3306/tcp, [::]:3306->3306/tcp   spring-mysql
7cf56430dfbe   pgvector/pgvector:pg16   "docker-entrypoint.s…"   4 weeks ago     Up 8 minutes               0.0.0.0:5433->5432/tcp, [::]:5433->5432/tcp   local-postgres-vector
cf7fd21c43bf   pgvector/pgvector:pg16   "docker-entrypoint.s…"   5 weeks ago     Up 8 minutes               0.0.0.0:5432->5432/tcp, [::]:5432->5432/tcp   local-postgres
1a41df632b93   ollama/ollama            "/bin/ollama serve"       5 weeks ago     Exited (255) 4 weeks ago   0.0.0.0:11434->11434/tcp
```

## 4) 컨테이너/이미지 정리

- 컨테이너 중지:

```bash
docker stop <container-id or name>
```
docker stop sharp_stonebraker
sharp_stonebraker

- 컨테이너 삭제:

```bash
docker rm <container-id or name>
```
sharp_stonebraker
- 이미지 목록 확인:

```bash
docker images
IMAGE                    ID             DISK USAGE   CONTENT SIZE   EXTRA
hello-world:latest       f9078146db2e       25.9kB         9.49kB    U
my-python-app:latest     7e3837038cc8       1.59GB          408MB
mysql:8.0                64756cc92f70       1.08GB          247MB    U
nginx:alpine             5616878291a2       93.5MB         26.9MB
nginx:latest             6e23479198b9        240MB         65.8MB    U
ollama/ollama:latest     e305a7b6529c        9.7GB         3.65GB    U
pgvector/pgvector:pg16   7d400e340efb        621MB          156MB    U
postgres:latest          69e8582b781c        649MB          168MB
redis:alpine             c5e375abb885        134MB           35MB
redis:latest             970b561d2348        204MB         55.3MB    U
spring-docker:dev        73c76d0cd675        599MB          216MB
spring-docker:prod       550a40c1132c        599MB          216MB

```

- 이미지 삭제:

```bash
docker rmi <image-id or name>

C:\Users\dclee>docker rmi hello-world
Error response from daemon: conflict: unable to delete hello-world:latest (must be forced) - container 05181a0733ff is using its referenced image f9078146db2e

C:\Users\dclee>docker rmi -f hello-world
Untagged: hello-world:latest

```

## 5) 실습 팁
- 컨테이너가 중지되지 않으면 `docker rm -f <id>`로 강제 제거 가능.
- 이미지를 삭제하려면 해당 이미지로 생성된 컨테이너를 먼저 삭제해야 함.

(참고: 이 환경에서는 Docker를 직접 실행할 수 없습니다. 위 명령을 로컬에서 실행한 뒤 출력 결과를 여기에 붙여넣어 주세요.)
