# syntax=docker/dockerfile:1
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app
COPY . .
RUN ./gradlew build --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/app/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"] 