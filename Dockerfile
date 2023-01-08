FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod","-jar","/app.jar"]
#Docker file referenc: https://docs.docker.com/engine/reference/builder/