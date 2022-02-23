FROM openjdk:11
LABEL maintainer "HwayoungJun <jun9813@gmail.com>"

RUN mkdir -p /var/www/point-api
WORKDIR /var/www/point-api

ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]