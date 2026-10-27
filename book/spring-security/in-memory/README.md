# In Memory Credentials

Spring Boot application that replaces the generated user with two in-memory users. [`SecurityConfig`](src/main/java/com/java/config/SecurityConfig.java) exposes an `InMemoryUserDetailsManager` as the `UserDetailsService`.

Passwords use the `{noop}` prefix, so Spring Security stores and compares them as plain text.

`GET /status` returns the text `UP`. Any authenticated user can call it. The roles on the users are `read` and `write`. This application does not add a `SecurityFilterChain`, so those roles are not checked on the endpoint.

Spring Boot 3.3.1. Java 17.

## Prerequisites

- Java 17
- Maven 3.6+

## Run

From this directory:

```bash
mvn spring-boot:run
```

The main class is [`com.java.Main`](src/main/java/com/java/Main.java). The process listens on port `9000`.

## Call the API

| Username | Password | Role |
| --- | --- | --- |
| `user1` | `pwd1` | `read` |
| `user2` | `pwd2` | `write` |

```bash
curl -u user1:pwd1 http://localhost:9000/status
```

The response body is `UP`.

Open [http://localhost:9000/status](http://localhost:9000/status) in a browser to see the login form.

![Default login form](img/001.jpg)

The same request is in [`postman/in-memory.postman_collection.json`](postman/in-memory.postman_collection.json).

[`AppController`](src/main/java/com/java/controller/AppController.java) maps `GET /status` and produces `text/plain`.

## Configuration

| Property | Value | Purpose |
| --- | --- | --- |
| `server.port` | `9000` | HTTP port |
| `spring.application.name` | `in-memory` | Application name |

Values are in [`src/main/resources/application.properties`](src/main/resources/application.properties). Users are defined in Java, not in this file.

`pom.xml` depends on `spring-boot-starter-security` and `spring-boot-starter-web`.

## Layout

```text
src/main/java/com/java
├── Main.java                      Entry point
├── config/SecurityConfig.java     In-memory users user1 and user2
└── controller/AppController.java  GET /status
```

## References

* [Spring Boot 3.3: Security](https://docs.spring.io/spring-boot/3.3/reference/web/spring-security.html)
