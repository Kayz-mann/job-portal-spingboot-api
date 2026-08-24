FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY jobms/pom.xml jobms/
COPY companyms/pom.xml companyms/
COPY reviewms/pom.xml reviewms/
COPY jobms/src jobms/src
RUN mvn clean package -DskipTests -pl jobms -am

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/jobms/target/jobms-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
