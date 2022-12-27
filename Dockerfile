# syntax=docker/dockerfile:experimental
FROM adoptopenjdk:11
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]