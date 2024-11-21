FROM maven:3.8.5-openjdk-17 AS builder

COPY . .
RUN mvn clean package -DskipTests

# Base image for efficient runtime
FROM openjdk:17.0.1-jdk-slim

# Copy the built JAR from the builder stage
COPY --from=builder /target/EcoGreen-0.0.1-SNAPSHOT.jar EcoWatt.jar

# Environment variables for Spring Boot configuration
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/gs_javaa
ENV SPRING_DATASOURCE_USERNAME=paulinslatt
ENV SPRING_DATASOURCE_PASSWORD=#Paulinho11
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENV SPRING_FLYWAY_LOCATIONS=classpath:db/migration


# Expose port for application access
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Ecogreen.jar"]

# Additional considerations:
# - Replace `#Paulinho11` with a secure password storage mechanism like environment variables.
# - Consider using a multi-stage build for smaller image size.
# - Explore volume mounts for persisting data outside the container.