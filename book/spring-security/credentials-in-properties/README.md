# Credentials In Properties

Spring Boot application that sets the single Spring Security user in `application.properties`. Spring Security uses those values and does not print a generated password.

`GET /status` returns the text `UP`. Every request requires authentication. A browser shows the default login form. HTTP clients can send Basic authentication.

Spring Boot 3.3.1. Java 17.

## Prerequisites

- Java 17
- Maven 3.6+

## Run

From this directory:

```bash
mvn spring-boot:run
```

The main class is [`com.java.Main`](src/main/java/com/java/Main.java). The process listens on port `9000` unless `APP_PORT` is set.

## Call the API

Default username `user1`, default password `pwd1`.

```bash
curl -u user1:pwd1 http://localhost:9000/status
```

The response body is `UP`.

Open [http://localhost:9000/status](http://localhost:9000/status) in a browser to see the login form.

![Default login form](img/001.jpg)

The same request is in [`postman/credentials-in-properties.postman_collection.json`](postman/credentials-in-properties.postman_collection.json).

[`AppController`](src/main/java/com/java/controller/AppController.java) maps `GET /status` and produces `text/plain`.

## Configuration

Values in [`src/main/resources/application.properties`](src/main/resources/application.properties):

```properties
server.port=${APP_PORT:9000}
spring.security.user.name=${SECURITY_USERNAME:user1}
spring.security.user.password=${SECURITY_PASSWORD:pwd1}
```

| Property | Default | Purpose |
| --- | --- | --- |
| `server.port` | `9000` (`APP_PORT`) | HTTP port |
| `spring.application.name` | `credentials-in-properties` | Application name |
| `spring.security.user.name` | `user1` (`SECURITY_USERNAME`) | Login name |
| `spring.security.user.password` | `pwd1` (`SECURITY_PASSWORD`) | Login password |

`pom.xml` depends on `spring-boot-starter-security` and `spring-boot-starter-web`.

## Layout

```text
src/main/java/com/java
├── Main.java                      Entry point
└── controller/AppController.java  GET /status
```

## References

* [Spring Boot 3.3: Security](https://docs.spring.io/spring-boot/3.3/reference/web/spring-security.html)
