FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/bfhl-api-1.0.0.jar app.jar

ENV GEMINI_API_KEY=${GEMINI_API_KEY}
ENV APP_OFFICIAL_EMAIL=${APP_OFFICIAL_EMAIL}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
