FROM  openjdk:11.0.13-jre-slim

RUN mkdir /app
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mpms.jar

ENTRYPOINT ["java","-jar","mpms.jar"]