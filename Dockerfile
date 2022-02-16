FROM openjdk:11

RUN mkdir -p /var/www/point-api
WORKDIR /var/www/point-api

ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]