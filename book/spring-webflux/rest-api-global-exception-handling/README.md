# Spring WebFlux REST API Global Exception Handling

Annotated REST endpoints return `Mono`. Exceptions signaled with `Mono.error` are mapped to a JSON body by a `@ControllerAdvice` class.

Spring Boot 3.3.4. Java 17.

## Prerequisites

- Java 17
- Maven 3.6+

## Run

From this directory:

```bash
mvn spring-boot:run
```

The main class is [`com.java.Main`](src/main/java/com/java/Main.java). The server listens on port `8080`.

The same calls are in [`postman/rest-api-global-exception-handling.postman_collection.json`](postman/rest-api-global-exception-handling.postman_collection.json). The collection pre-request script sets `http://localhost:8080`.

## APIs

[`MyController`](src/main/java/com/java/MyController.java) exposes three GET endpoints. Each produces `application/json` and returns `Mono<Student>`. `Student` is a record with `id` and `name`. A successful call returns the requested id and the name `a`:

```json
{"id":1,"name":"a"}
```

An error returns [`ErrorResponse`](src/main/java/com/java/ErrorResponse.java):

| Field | Type | Meaning |
| --- | --- | --- |
| `code` | int | HTTP status value |
| `message` | string | Exception message |

### `GET /api1?id={id}`

`id == 0` emits [`ResourceNotFoundException`](src/main/java/com/java/ResourceNotFoundException.java). Any other integer returns a student.

```bash
curl "http://localhost:8080/api1?id=1"
curl "http://localhost:8080/api1?id=0"
```

`id=0` responds with HTTP 404:

```json
{"code":404,"message":"Resource not found"}
```

### `GET /api2?id={id}`

`id < 0` emits [`BadRequestException`](src/main/java/com/java/BadRequestException.java). `0` and positive ids return a student.

```bash
curl "http://localhost:8080/api2?id=1"
curl "http://localhost:8080/api2?id=-1"
```

`id=-1` responds with HTTP 400:

```json
{"code":400,"message":"Bad request"}
```

### `GET /api3?id={id}`

The method emits `RuntimeException` when `id` is null. `@RequestParam("id")` is required, so a normal HTTP call does not pass null: a missing `id` fails during argument binding and never enters the method. A present `id` returns a student.

```bash
curl "http://localhost:8080/api3?id=10"
```

## Exception handling

[`GlobalExceptionHandler`](src/main/java/com/java/GlobalExceptionHandler.java) is a `@ControllerAdvice` that extends `ResponseStatusExceptionHandler`. The methods that handle requests are the `@ExceptionHandler` methods. Each builds an `ErrorResponse` and returns `ResponseEntity`.

| Method | Exception | Status |
| --- | --- | --- |
| `handleResourceNotFoundException` | `ResourceNotFoundException` | 404 |
| `handleBadRequestException` | `BadRequestException` | 400 |
| `handleGenericException` | `Exception` | 500 |

`handleGenericException` is the fallback. Its body is HTTP 500 and `ErrorResponse.code` is `500`. `handleGenericException2` has no `@ExceptionHandler` and is not used.

Both custom exceptions extend `RuntimeException` and take a message.

## Stack

[`pom.xml`](pom.xml) depends on `spring-boot-starter-web` and `spring-boot-starter-webflux`. With both starters present and no `spring.main.web-application-type`, Spring Boot starts the servlet stack. The controller still returns `Mono`, and `@ControllerAdvice` still applies.

`spring-boot-starter-actuator` and Lombok are also on the classpath. `ErrorResponse` uses Lombok `@Data` and `@Builder`.

## Configuration

[`src/main/resources/application.properties`](src/main/resources/application.properties) sets the application name and the console log pattern. It does not set `server.port`.

| Property | Value |
| --- | --- |
| `spring.application.name` | `Spring WebFlux Rest API Global Exception Handling` |
| `logging.level.*` | `INFO` |

## Other classes in the tree

These files are not part of the running application:

- [`RouterConfig`](src/main/java/com/java/RouterConfig.java) sketches functional routes and `RouterFunctions.route().onError(...)`. The class body and `@Configuration` are commented out.
- [`ReactiveExceptionHandler`](src/main/java/com/java/ReactiveExceptionHandler.java) sketches an `AbstractErrorWebExceptionHandler`. The class body is commented out and the type is not a Spring bean.
