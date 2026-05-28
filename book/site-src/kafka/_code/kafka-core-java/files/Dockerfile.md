---
hide:
  - navigation
---

# Dockerfile

Source: `kafka/kafka-core-java/Dockerfile`

```text
FROM openjdk:17
COPY target/kafka-core-java*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```
