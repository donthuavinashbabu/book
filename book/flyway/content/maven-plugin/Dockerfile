FROM openjdk:17
COPY target/maven-plugin*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]