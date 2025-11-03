FROM eclipse-temurin:17-jre-jammy
COPY target/classes /app/classes
WORKDIR /app
ENTRYPOINT ["java", "-cp", "classes", "com.napier.devops.App"]
