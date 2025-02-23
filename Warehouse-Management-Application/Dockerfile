FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /build

COPY . .

RUN ./mvnw clean package

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /build/target/Warehouse-Management-Application-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]