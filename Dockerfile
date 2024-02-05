# Use an official OpenJDK runtime as a parent image

FROM openjdk:11-jre-slim


# Set the working directory in the container
WORKDIR /app

# Copy the application JAR (assuming it's in the target directory)
COPY target/Student-0.0.1-SNAPSHOT.jar /app/
# Expose the port your app runs on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "Student-0.0.1-SNAPSHOT.jar"]
