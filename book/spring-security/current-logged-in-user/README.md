# Current Logged In User

Spring Boot application that reads the authenticated user from `SecurityContextHolder` and returns the username. [`SecurityConfig`](src/main/java/com/java/config/SecurityConfig.java) registers one in-memory user and a `BCryptPasswordEncoder`.

`GET /api/v1/username` returns JSON `{"username":"admin"}`.

[`AppController`](src/main/java/com/java/controller/AppController.java) loads `Authentication` from `SecurityContextHolder.getContext()`. `getName()` is the username. `getCredentials()` is null after authentication, because Spring Security does not keep the password on the `Authentication`. The principal is `org.springframework.security.core.userdetails.User`, with authorities `ROLE_USER` and `ROLE_ADMIN`. The controller logs those three values.

Spring Boot 3.1.1. Java 17.

## Prerequisites

- Java 17
- Maven 3.6+

## Run

From this directory:

```bash
mvn spring-boot:run
```

The main class is [`com.java.App`](src/main/java/com/java/App.java). [`application.properties`](src/main/resources/application.properties) is empty, so the process listens on port `8080`.

## Call the API

| Username | Password | Roles |
| --- | --- | --- |
| `admin` | `dummy` | `USER`, `ADMIN` |

```bash
curl -u admin:dummy http://localhost:8080/api/v1/username
```

The response body is:

```json
{"username":"admin"}
```

The same request is in [`postman/current-logged-in-user.postman_collection.json`](postman/current-logged-in-user.postman_collection.json).

## Configuration

Users are defined in [`SecurityConfig`](src/main/java/com/java/config/SecurityConfig.java). `User.builder().passwordEncoder(...)` hashes `dummy` with the `BCryptPasswordEncoder` bean before the user is stored. The same bean checks the password on the request.

`pom.xml` depends on `spring-boot-starter-security`, `spring-boot-starter-web`, `spring-boot-starter-actuator`, and Lombok. `@Slf4j` on the controller writes the authentication log lines.

## Layout

```text
src/main/java/com/java
├── App.java                       Entry point
├── config/SecurityConfig.java     In-memory user admin and BCrypt encoder
└── controller/AppController.java  GET /api/v1/username
```

## References

* [Spring Security: Authentication architecture](https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html)
* [Spring Boot 3.1: Security](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#web.security)
