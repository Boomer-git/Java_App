FROM openjdk:17
COPY App.java /usr/src/myapp/
WORKDIR /usr/src/myapp
RUN javac App.java
EXPOSE 80
CMD ["java", "App"]
