FROM maven:3.8.1-openjdk-17-slim AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean install -DskipTests=true

FROM openjdk:17-jdk-slim

COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-Dspring.devtools.restart.enabled=true", "-jar", "app.jar"]