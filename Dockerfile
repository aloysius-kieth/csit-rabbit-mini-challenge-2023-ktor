# Use the official Kotlin Docker image as the base image
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the Ktor project files to the container
COPY . .

# Build the Ktor project
RUN ./gradlew build

# Expose the port that your Ktor application is running on (change it if needed)
EXPOSE 8080

# Run the Ktor application
CMD ["java", "-jar", "./build/libs/ApplicationKt.jar"]