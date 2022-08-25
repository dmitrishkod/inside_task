FROM openjdk:8-jdk-alpine
MAINTAINER Dmitry
COPY target/inside_task-0.0.1-SNAPSHOT.war inside_task-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/inside_task-0.0.1-SNAPSHOT.war"]