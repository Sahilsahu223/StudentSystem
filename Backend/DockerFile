# Stage 1: Build the application using Maven
# We use a specific Maven image that includes JDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project file first to leverage Docker layer caching
COPY pom.xml .

# Copy the rest of your source code
COPY src ./src

# Run the Maven package command to build the .jar file
# This is the same command from the article, ensuring tests are skipped
RUN mvn -f pom.xml clean package -DskipTests


# Stage 2: Create the final, lightweight production image
# We use a slim OpenJDK image which is much smaller, making it more efficient
FROM openjdk:17-jdk-slim

# Set the working directory for the final image
WORKDIR /app

# Copy the .jar file created in the 'build' stage
# Make sure to replace 'StudentSystem-0.0.1-SNAPSHOT.jar' with the actual name
# of the .jar file that is generated in your 'target' folder.
COPY --from=build /app/target/StudentSystem-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot application runs on (default is 8080)
EXPOSE 8080

# The command to run your application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
