FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts docker-compose-prod.yml /app/

COPY . /app
RUN ./gradlew build -x test --parallel


FROM openjdk:17-jdk-slim AS app

WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar /app/app.jar
# 프로필 지정이 없다면 local을 기본으로 실행

ENV ACTIVE_PROFILE=prod
# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "/app/app.jar"]