FROM openjdk:17-ea-11-jdk-slim
VOLUME /service
COPY build/libs/product-service-1.0.jar ProductServer.jar
ENTRYPOINT ["java", "-jar", "ProductServer.jar"]