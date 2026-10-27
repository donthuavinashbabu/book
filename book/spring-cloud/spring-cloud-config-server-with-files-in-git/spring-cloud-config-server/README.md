# Spring Cloud Config Server

Spring Boot application that serves configuration from a Git repository. `@EnableConfigServer` on [`spring.cloud.config.server.App`](src/main/java/spring/cloud/config/server/App.java) starts the config server. Clients ask for an application name and a profile. The server reads the matching files from Git and returns them.

This server does not use Eureka. The Netflix Eureka client dependency in `pom.xml` is commented out.

Spring Boot 2.1.9 and Spring Cloud Greenwich.SR3. Java 8.

## Prerequisites

- Java 8
- Maven 3.6+

## Run

`pom.xml` has the Spring Boot plugin and `spring-cloud-config-server`. Run from this directory:

```bash
mvn spring-boot:run
```

The process listens on port `8888`.

`build.gradle` is the file `gradle init` wrote for this project. It applies the Java plugin and depends on JUnit 3.8.1. It does not apply the Spring Boot plugin and does not depend on the config server, so `gradlew bootRun` is not a way to start this application.

## Git backend

[`application.properties`](src/main/resources/application.properties) sets the backend here:

```properties
spring.cloud.config.server.git.uri=https://github.com/avinashbabudonthu/spring-cloud-configuration-files.git
spring.application.name=spring-cloud-config.server
```

The server clones that repository when a client requests configuration. The default Git label for Greenwich is `master`.

The public GitHub page for `avinashbabudonthu/spring-cloud-configuration-files` returns 404, so a request fails until that repository exists and this process can clone it.

## Read configuration

With a reachable repository, the environment for an application and profile is:

```text
GET http://localhost:8888/{application}/{profile}
GET http://localhost:8888/{application}/{profile}/{label}
```

`{application}` is the client’s `spring.application.name`. `{profile}` is the active profile, usually `default` when none is set. `{label}` is the Git branch, tag, or commit. Omitting it uses `master`.

A file named `{application}-{profile}.properties` or `{application}-{profile}.yml` in that repository is the file this URL returns. `application.properties` and `application.yml` in the same repository apply to every application.

Actuator is on the classpath. `GET http://localhost:8888/actuator/health` runs the config server health indicator, which asks the Git backend for an application named `app`, the `default` profile, and the label `master`. A repository that cannot be cloned fails that check.

## Configuration

| Property | Value | Purpose |
| --- | --- | --- |
| `server.port` | `8888` | Config server HTTP port |
| `spring.application.name` | `spring-cloud-config.server` | This server’s own name |
| `spring.cloud.config.server.git.uri` | `https://github.com/avinashbabudonthu/spring-cloud-configuration-files.git` | Git repository of config files |

## Layout

```text
src/main/java/spring/cloud/config/server
└── App.java    Config server entry point
```

## References

* [Spring Cloud Config 2.1](https://cloud.spring.io/spring-cloud-config/2.1.x/single/spring-cloud-config.html)
