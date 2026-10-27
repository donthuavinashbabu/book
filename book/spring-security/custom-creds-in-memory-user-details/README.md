# Custom Credentials In Memory User Details

Spring Boot application that registers two in-memory users and hashes their passwords with BCrypt. The registered bean is `inMemoryUserDetailsManager3` in [`AppConfig`](src/main/java/com/java/config/AppConfig.java).

`User.builder().passwordEncoder(...)` hashes each password before it is stored. The `PasswordEncoder` bean is a `BCryptPasswordEncoder`, and Spring Security uses that bean when it checks the password on a request.

`GET /api/v1/students` returns one student. Any authenticated user can call it.

Spring Boot 3.1.1. Java 17.

## Prerequisites

- Java 17
- Maven 3.6+

## Run

From this directory:

```bash
mvn spring-boot:run
```

The main class is [`com.java.App`](src/main/java/com/java/App.java). The process listens on port `9000`.

## Call the API

| Username | Password | Roles |
| --- | --- | --- |
| `admin1` | `dummy1` | `USER`, `ADMIN` |
| `admin2` | `dummy2` | `USER`, `ADMIN` |

```bash
curl -u admin1:dummy1 http://localhost:9000/api/v1/students
```

The response body is:

```json
{"id":100,"name":"Ram","course":"AI"}
```

[`postman/custom-creds-in-memory-user-details.postman_collection.json`](postman/custom-creds-in-memory-user-details.postman_collection.json) calls the same URL. The request named `/api/v1/students-customPasswordEncoder` signs in as `admin2` / `dummy2`.

[`AppController`](src/main/java/com/java/controller/AppController.java) maps `GET /api/v1/students` and produces `application/json`.

## Other beans in AppConfig

Two factory methods in the same class are commented out:

* `inMemoryUserDetailsManager` builds `admin` / `dummy` with `User.withDefaultPasswordEncoder()`.
* `inMemoryUserDetailsManager2` builds one user, `admin2` / `dummy2`, with the BCrypt encoder.

Uncommenting more than one `InMemoryUserDetailsManager` `@Bean` method at a time gives the context two beans of that type.

## Configuration

| Property | Value | Purpose |
| --- | --- | --- |
| `server.port` | `9000` | HTTP port |

Values are in [`src/main/resources/application.properties`](src/main/resources/application.properties). Users are defined in [`AppConfig`](src/main/java/com/java/config/AppConfig.java).

`pom.xml` depends on `spring-boot-starter-security`, `spring-boot-starter-web`, and `spring-boot-starter-actuator`.

## Layout

```text
src/main/java/com/java
├── App.java                       Entry point
├── config/AppConfig.java          BCrypt encoder and in-memory users
└── controller/AppController.java  GET /api/v1/students
```

## References

* [Spring Boot 3.1: Security](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#web.security)
