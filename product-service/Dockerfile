FROM openjdk:17-ea-11-jdk-slim
VOLUME /service
COPY build/libs/user-service-msa-1.0.jar UserServer.jar
ENTRYPOINT ["java", "-jar", "UserServer.jar"]