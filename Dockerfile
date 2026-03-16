# 1. Use a multi-arch compatible base image
FROM openjdk:11-jre-slim

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the jar file from the target folder to the container
# Ensure the name matches what Maven generates
COPY target/*.jar app.jar

# 4. Define the default command to run the app
CMD ["java", "-jar", "app.jar"]
