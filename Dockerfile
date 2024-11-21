
FROM gradle:7.6-jdk17-alpine AS builder

WORKDIR /app
COPY build.gradle.kts .
COPY src src

RUN gradle build

FROM openjdk:17-jdk-alpine

COPY --from=builder /target/EcoGreen-0.0.1-SNAPSHOT.jar EcoWatt.jar

# Environment variables for Spring Boot configuration
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/gs_javaa
ENV SPRING_DATASOURCE_USERNAME=paulinslatt
ENV SPRING_DATASOURCE_PASSWORD=#Paulinho11
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENV SPRING_FLYWAY_LOCATIONS=classpath:db/migration

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Ecogreen.jar"]