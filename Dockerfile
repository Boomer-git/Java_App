FROM openjdk:17
WORKDIR /usr/src/myapp

# Download Spark and SLF4J JARs
ADD https://repo1.maven.org/maven2/com/sparkjava/spark-core/2.9.4/spark-core-2.9.4.jar spark-core.jar
ADD https://repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.9/slf4j-api-2.0.9.jar slf4j-api.jar
ADD https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/2.0.9/slf4j-simple-2.0.9.jar slf4j-simple.jar

# Copy Java code
COPY CalculatorServer.java .

# Compile with all JARs
RUN javac -cp ".:spark-core.jar:slf4j-api.jar:slf4j-simple.jar" CalculatorServer.java

EXPOSE 80

# Run with all JARs
CMD ["java", "-cp", ".:spark-core.jar:slf4j-api.jar:slf4j-simple.jar", "CalculatorServer"]
