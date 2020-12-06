FROM openjdk:8-jdk-alpine
ADD xite-0.0.1-SNAPSHOT.jar prachijulka-calc-test-api.jar
EXPOSE 8080
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/prachijulka-calc-test-api.jar"]