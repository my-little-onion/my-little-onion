FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE = build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080
EXPOSE 8082
EXPOSE 3306

ENTRYPOINT ["java", "-jar", "app.jar"]
