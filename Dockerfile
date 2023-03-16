FROM openjdk:8-jdk-alpine
LABEL maintainer="ahmedbaz1024"
WORKDIR /usr/local/bin/
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mail-service.jar
EXPOSE 9999
CMD ["java","-jar","mail-service.jar"]
