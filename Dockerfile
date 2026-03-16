# Use the official Eclipse Temurin image (successor to openjdk)
FROM eclipse-temurin:11-jre

WORKDIR /app

# Ensure the wildcard matches the jar created by Maven
COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
