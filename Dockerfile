# Dockerfile for Spring Boot backend using Java 21
FROM eclipse-temurin:21-jdk-alpine

# Set work directory
WORKDIR /app

# Copy the JAR file built from Maven
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
