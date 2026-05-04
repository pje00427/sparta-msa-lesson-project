# 1. 자바 실행 환경(JRE)이 있는 가벼운 이미지 선택
FROM eclipse-temurin:17-jre-alpine

# 2. 작업할 폴더 설정
WORKDIR /app

# 3. 아까 빌드해서 나온 jar 파일을 컨테이너 내부로 복사
# (파일명이 다르면 아래 파일명을 실제 파일명과 똑같이 수정해 주세요)
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# 4. 8080 포트 열어주기
EXPOSE 8080

# 5. 컨테이너가 시작될 때 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]