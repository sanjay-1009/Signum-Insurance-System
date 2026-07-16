# ---------- Build Stage ----------
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM tomcat:9.0-jdk17-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=builder \
/app/target/InsuranceClaimSystem-0.0.1-SNAPSHOT.war \
/usr/local/tomcat/webapps/ROOT.war

ENV PORT=8080

EXPOSE 8080

CMD sh -c "sed -i 's/port=\"8080\"/port=\"${PORT}\"/' /usr/local/tomcat/conf/server.xml && catalina.sh run"