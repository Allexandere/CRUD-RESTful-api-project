FROM maven:alpine AS build
WORKDIR /workspace
COPY pom.xml .
ADD src ./src
RUN mvn clean install

FROM openjdk:8-alpine
COPY --from=build ./target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]