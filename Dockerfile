 # Use a lightweight Java Runtime
FROM eclipse-temurin:11-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven into the container
# Note: The name must match exactly what Maven produced in your logs
COPY target/scientific-calculator-1.0-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

