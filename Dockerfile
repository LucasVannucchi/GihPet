FROM eclipse-temurin:21-jdk-alpine

LABEL authors="lucas.vannucchi"
LABEL description="Gihpet - Spring Boot Application"

WORKDIR /app

COPY build/libs/*.jar app.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080

ENV JAVA_OPTS="-Xmx512m -Xms256m"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]