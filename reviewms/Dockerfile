FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY jobms/pom.xml jobms/
COPY companyms/pom.xml companyms/
COPY reviewms/pom.xml reviewms/
COPY reviewms/src reviewms/src
RUN mvn clean package -DskipTests -pl reviewms -am

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/reviewms/target/reviewms-*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
