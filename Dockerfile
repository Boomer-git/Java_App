FROM openjdk:17

WORKDIR /usr/src/myapp

# Add Spark dependency (fat jar)
ADD https://repo1.maven.org/maven2/com/sparkjava/spark-core/2.9.4/spark-core-2.9.4.jar /usr/src/myapp/

COPY CalculatorServer.java .

RUN javac -cp "spark-core-2.9.4.jar" CalculatorServer.java

EXPOSE 80

CMD ["java", "-cp", ".:spark-core-2.9.4.jar", "CalculatorServer"]
