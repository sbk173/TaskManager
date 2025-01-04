FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/Task-1.0-SNAPSHOT.jar /app/
COPY config.yml /app/
EXPOSE 8080
CMD ["java", "-jar", "Task-1.0-SNAPSHOT.jar", "server", "config.yml"]
