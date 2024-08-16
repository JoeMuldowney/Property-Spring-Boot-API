# Use a Maven image with Java and Maven pre-installed
FROM maven:3.8.3-openjdk-17 AS build
# Set the working directory inside the container
WORKDIR /app
# Copy the Maven project files
COPY pom.xml .
COPY src ./src
# Run Maven clean and package the application
RUN mvn clean package -DskipTests

# Second stage, copy the JAR from the build stage into a slimmed runtime stage
FROM openjdk:17-jdk-alpine
# Set the working directory inside the container
WORKDIR /app
# Copy the JAR file from the build stage
COPY --from=build /app/target/Properties-0.0.1-SNAPSHOT.jar /app/myapp.jar
# Expose the port the application runs on

# Set environment variables for PostgreSQL configuration
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres.ca82kaadrj5r.us-east-2.rds.amazonaws.com/PropertyApp
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres
EXPOSE 8080
# Define the command to run the JAR file
CMD ["java", "-jar", "myapp.jar"]