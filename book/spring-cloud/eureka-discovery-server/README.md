# Eureka Discovery Server

Spring Boot application that runs a Netflix Eureka server. Other applications in this folder register here and look each other up by name. The server does not register with itself.

Spring Boot 2.1.9 and Spring Cloud Greenwich.SR3.

## Prerequisites

- Java 8
- Maven 3.6+ or Gradle

## Run

```bash
mvn spring-boot:run
```

Gradle:

```bash
gradlew bootRun
```

The main class is [`com.discovery.server.App`](src/main/java/com/discovery/server/App.java). `@EnableEurekaServer` turns on the Eureka server.

The dashboard is [http://localhost:8761/](http://localhost:8761/). Before the other applications start, the instance list is empty.

## Configuration

Values in [`src/main/resources/application.properties`](src/main/resources/application.properties):

| Property | Value | Purpose |
| --- | --- | --- |
| `server.port` | `8761` | Eureka HTTP port |
| `spring.application.name` | `discovery-server` | Application name |
| `eureka.client.register-with-eureka` | `false` | This server does not register as a client |
| `eureka.client.fetch-registry` | `false` | This server does not replicate a registry from another Eureka server |
| `eureka.dashboard.enabled` | `true` | Serves the dashboard at `/` |

## What to start next

1. [Application Service](../application-service/README.md) registers as `application-service` and serves `GET /hello`.
2. [Application Client 1](../application-client-1/README.md) looks that service up and calls it. The client does not register.

After the service is up, the dashboard lists `APPLICATION-SERVICE`.

## Layout

```text
src/main/java/com/discovery/server
└── App.java    Eureka server entry point
```
