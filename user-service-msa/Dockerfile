FROM openjdk:17-ea-11-jdk-slim
VOLUME /service
COPY build/libs/eureka-server-1.0.jar EurekaServer.jar
ENTRYPOINT ["java", "-jar", "EurekaServer.jar"]