# Use Java 17 image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy your jar file into the container
COPY target/courses-backend.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
