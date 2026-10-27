# Application Service

Spring Boot application that registers with the Eureka server and exposes one HTTP endpoint. The discovery server uses the actuator health endpoint when it checks this instance.

Start [Eureka Discovery Server](../eureka-discovery-server/README.md) first. This service listens on port `9090` and registers as `application-service`.

Spring Boot 2.1.9 and Spring Cloud Greenwich.SR3.

## Prerequisites

- Java 8
- Maven 3.6+ or Gradle
- Eureka server on port `8761`

## Run

```bash
mvn spring-boot:run
```

Gradle:

```bash
gradlew bootRun
```

The main class is [`application.service.App`](src/main/java/application/service/App.java). `@EnableDiscoveryClient` registers this process with Eureka.

Open [http://localhost:8761/](http://localhost:8761/). The dashboard lists `APPLICATION-SERVICE` at `localhost:9090`.

`eureka.instance.hostname` and `spring.cloud.client.hostname` are both `localhost`, so the registered address stays `localhost` when Docker is running on the same machine.

## Call the service

`GET /hello` returns the text `Hello World`.

```bash
curl http://localhost:9090/hello
```

The same request is in [`files/application-service.postman_collection.json`](files/application-service.postman_collection.json).

[`AppController`](src/main/java/application/service/controller/AppController.java) maps that path and produces `text/plain`.

Actuator is on the classpath. `GET /actuator/health` is the health URL Eureka checks.

## Configuration

Values in [`src/main/resources/application.properties`](src/main/resources/application.properties):

| Property | Value | Purpose |
| --- | --- | --- |
| `server.port` | `9090` | HTTP port |
| `spring.application.name` | `application-service` | Name used to look this service up in Eureka |
| `eureka.client.service-url.defaultZone` | `http://localhost:8761/eureka/` | Eureka server |
| `eureka.instance.hostname` | `localhost` | Hostname advertised to Eureka |
| `spring.cloud.client.hostname` | `localhost` | Hostname advertised by the Spring Cloud client |

## Layout

```text
src/main/java/application/service
├── App.java                      Entry point, registers with Eureka
└── controller/AppController.java GET /hello
```

## References

* [Spring Cloud Commons 2.1: @EnableDiscoveryClient](https://cloud.spring.io/spring-cloud-commons/2.1.x/single/spring-cloud-commons.html)
