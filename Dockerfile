FROM openjdk:18-jdk-alpine
WORKDIR /app
COPY app/build/libs/app-all.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
