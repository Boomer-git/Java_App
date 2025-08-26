# Use OpenJDK 17 as base image
FROM openjdk:17

# Set working directory
WORKDIR /usr/src/myapp

# Copy the Java app into the container
COPY App.java /usr/src/myapp/

# Compile the Java program
RUN javac App.java

# Expose port 80 for web access
EXPOSE 80

# Run the application
CMD ["java", "App"]

