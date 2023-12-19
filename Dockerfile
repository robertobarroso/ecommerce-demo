FROM openjdk:17-jdk-slim
COPY target/ecommerce-demo-0.0.1-SNAPSHOT.jar ecommerce-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ecommerce-demo-0.0.1-SNAPSHOT.jar"]