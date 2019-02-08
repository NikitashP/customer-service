
FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8083

ARG JAR_FILE=target/customer-0.0.1-SNAPSHOT.jar


ADD ${JAR_FILE} customer-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/customer-service.jar"]
