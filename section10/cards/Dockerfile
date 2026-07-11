# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre

# THE MAINTAINER FOR THIS IMAGE
LABEL maintainer="gabriel.morato139@gmail.com"

WORKDIR /app

COPY --from=builder /app/target/*.jar cards.jar

ENTRYPOINT ["java", "-jar", "cards.jar"]