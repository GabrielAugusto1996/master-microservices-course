# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar configserver.jar

RUN mkdir -p /app/libs && \
    curl -L \
    https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.22.0/opentelemetry-javaagent.jar \
    -o /app/libs/opentelemetry-javaagent.jar

ENTRYPOINT ["java", "-javaagent:/app/libs/opentelemetry-javaagent.jar", "-jar", "configserver.jar"]