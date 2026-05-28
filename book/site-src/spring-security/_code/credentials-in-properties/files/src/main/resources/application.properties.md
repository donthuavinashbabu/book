---
hide:
  - navigation
---

# application.properties

Source: `spring-security/credentials-in-properties/src/main/resources/application.properties`

```properties
spring.application.name=credentials-in-properties

server.port=${APP_PORT:9000}

spring.security.user.name=${SECURITY_USERNAME:user1}
spring.security.user.password=${SECURITY_PASSWORD:pwd1}
```
