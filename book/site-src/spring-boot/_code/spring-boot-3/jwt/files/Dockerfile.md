---
hide:
  - navigation
---

# Dockerfile

Source: `spring-boot/spring-boot-3/jwt/Dockerfile`

```text
FROM openjdk:17

COPY target/hello-world-1.0.0.jar app.jar

EXPOSE 9000

CMD java -jar app.jar
```
