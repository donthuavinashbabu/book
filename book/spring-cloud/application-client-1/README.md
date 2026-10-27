# Application Client 1

Spring Boot application that looks up `application-service` in Eureka and calls `GET /hello`. It does not register itself. The dashboard still shows only `APPLICATION-SERVICE`.

Start these two first:

1. [Eureka Discovery Server](../eureka-discovery-server/README.md) on port `8761`
2. [Application Service](../application-service/README.md) on port `9090`

This client listens on port `9091`.

Spring Boot 2.1.9 and Spring Cloud Greenwich.SR3.

## Prerequisites

- Java 8
- Maven 3.6+ or Gradle
- Eureka server on port `8761`
- `application-service` registered and serving `GET /hello`

## Run

```bash
mvn spring-boot:run
```

Gradle:

```bash
gradlew bootRun
```

The main class is [`com.application.client.App`](src/main/java/com/application/client/App.java). `@EnableDiscoveryClient` creates the Eureka and discovery clients. `eureka.client.register-with-eureka=false` keeps this process out of the registry. It still reads the registry, which is how the lookups below find `application-service`.

Both requests are in [`files/application-client-1.postman_collection.json`](files/application-client-1.postman_collection.json).

## Eureka client

`GET /eureka-client/hello` uses Netflix `EurekaClient`.

[`EurekaClientRestController`](src/main/java/com/application/client/eureka/client/EurekaClientRestController.java) calls `getNextServerFromEureka("application-service", false)`. The second argument is `false`, so it asks for an instance whose secure port is not required. `InstanceInfo.getHomePageUrl()` includes a trailing slash, and the controller appends `hello`, which calls `http://localhost:9090/hello`.

```bash
curl http://localhost:9091/eureka-client/hello
```

The response body is `Hello World`.

## Discovery client

`GET /discovery-client/hello` uses Spring Cloud `DiscoveryClient`.

[`DiscoveryClientRestController`](src/main/java/com/application/client/discovery/client/DiscoveryClientRestController.java) calls `getInstances("application-service")` and takes the first `ServiceInstance`. It appends the string `hello` to `getUri()`. That URI has no trailing slash, so the request target is `http://localhost:9090hello`.

```bash
curl http://localhost:9091/discovery-client/hello
```

## Configuration

Values in [`src/main/resources/application.properties`](src/main/resources/application.properties):

| Property | Value | Purpose |
| --- | --- | --- |
| `server.port` | `9091` | HTTP port |
| `spring.application.name` | `application-client-1` | Application name |
| `eureka.client.service-url.defaultZone` | `http://localhost:8761/eureka/` | Eureka server to read |
| `eureka.client.register-with-eureka` | `false` | Do not register this process |

## Layout

```text
src/main/java/com/application/client
├── App.java                                         Entry point
├── eureka/client/EurekaClientRestController.java    GET /eureka-client/hello
└── discovery/client/DiscoveryClientRestController.java
                                                     GET /discovery-client/hello
```

## References

* [Spring Cloud Commons 2.1: @EnableDiscoveryClient](https://cloud.spring.io/spring-cloud-commons/2.1.x/single/spring-cloud-commons.html)
