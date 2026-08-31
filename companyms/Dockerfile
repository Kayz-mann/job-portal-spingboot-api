FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY servicereg/pom.xml servicereg/
COPY jobms/pom.xml jobms/
COPY companyms/pom.xml companyms/
COPY reviewms/pom.xml reviewms/
COPY companyms/src companyms/src
RUN mvn clean package -DskipTests -pl companyms -am

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/companyms/target/companyms-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
