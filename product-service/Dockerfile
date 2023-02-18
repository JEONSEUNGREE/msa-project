FROM openjdk:17-ea-11-jdk-slim
VOLUME /service/product-images
COPY build/libs/product-service-1.0.jar /service/ProductServer.jar
COPY ./product-images /service/product-images
ENTRYPOINT ["java", "-jar", "./service/ProductServer.jar"]