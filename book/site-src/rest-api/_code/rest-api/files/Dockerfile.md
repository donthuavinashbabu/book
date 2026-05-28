---
hide:
  - navigation
---

# Dockerfile

Source: `rest-api/rest-api/Dockerfile`

```text
FROM openjdk:17
COPY target/rest-api-1.0.0.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]
```
