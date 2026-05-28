---
hide:
  - navigation
---

# Dockerfile

Source: `kafka/kafka-streams-spring-boot-3-001/Dockerfile`

```text
FROM openjdk:17
COPY target/kafka-streams-spring-boot-3*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]
```
