# Step 1: Build the WAR using Maven
FROM maven:3.8.7-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy all project files
COPY . .

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# Step 2: Use Tomcat as the runtime environment
FROM tomcat:10.1-jdk17 AS runtime

WORKDIR /usr/local/tomcat/webapps/

# Copy WAR file from the builder stage into Tomcat's webapps directory
COPY --from=builder /app/target/techtrix-0.0.1-SNAPSHOT.war ROOT.war

# Expose Tomcat's default port
EXPOSE 8080

# Set Tomcat to use Render's assigned port dynamically
ENV CATALINA_OPTS="-Dserver.port=8080"

# Start Tomcat
CMD ["catalina.sh", "run"]



# # Use an image that already has Maven installed
# FROM maven:3.8.7-eclipse-temurin-17 AS builder

# # Set working directory
# WORKDIR /app

# # Copy all project files
# COPY . .

# # Run Maven to build the project
# RUN mvn clean package -DskipTests

# # Use a minimal JDK image for the final container
# FROM openjdk:17-jdk-slim

# WORKDIR /app

# # Copy the built JAR from the builder stage
# # COPY --from=builder /app/target/techtrix-0.0.1-SNAPSHOT.jar app.jar
# COPY --from=builder /app/target/techtrix-0.0.1-SNAPSHOT.war app.war

# # COPY target/techtrix-0.0.1-SNAPSHOT.war app.war


# # Run the application
# # ENTRYPOINT ["java", "-jar", "/app.jar"]

# ENTRYPOINT ["java", "-jar", "/app.war"]













# # Use an official OpenJDK runtime as a parent image
# FROM openjdk:11-jre-slim

# # Set the working directory in the container
# WORKDIR /app

# # Copy the Maven wrapper and the pom.xml to the container
# COPY .mvn/ .mvn
# COPY mvnw .
# COPY pom.xml .

# # Copy the source code to the container
# COPY src/ src

# # Package the application using the Maven wrapper
# RUN ./mvnw package

# # Copy the packaged JAR file to the container
# # COPY techtrix.jar app.jar
# # COPY techtrix.jar app.jar
# COPY target/techtrix-0.0.1-SNAPSHOT.war app.war


# # COPY target/techtrix-0.0.1-SNAPSHOT.jar app.jar


# # Expose the port that the application will run on
# EXPOSE 8080

# # Run the JAR file
# ENTRYPOINT ["java", "-jar", "app.jar"]