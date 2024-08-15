# Use a base image with Java runtime
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the host into the container
COPY target/Properties-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run the JAR file
CMD ["java", "-jar", "myapp.jar"]