FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY servicereg/pom.xml servicereg/
COPY jobms/pom.xml jobms/
COPY companyms/pom.xml companyms/
COPY reviewms/pom.xml reviewms/
COPY servicereg/src servicereg/src
RUN mvn clean package -DskipTests -pl servicereg -am

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/servicereg/target/servicereg-*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
