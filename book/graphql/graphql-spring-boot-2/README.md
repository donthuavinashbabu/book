# GraphQL Spring Boot 2

Spring Boot 2.6.15 application for GraphQL Kickstart examples. [`pom.xml`](pom.xml) uses Java 8, `graphql-spring-boot-starter` 11.1.0, `graphiql-spring-boot-starter` 11.1.0, `graphql-java-tools` 11.0.1, and `graphql-webclient-spring-boot-starter` 1.0.0.

[`Application`](src/main/java/com/ab/Application.java) starts the app. [`application.properties`](src/main/resources/application.properties) sets port `9000` and `graphql.client.url` to `http://localhost:9000/graphql`. Queries are in [`schema.graphqls`](src/main/resources/schema.graphqls). The create-student mutation is in [`mutation.graphqls`](src/main/resources/mutation.graphqls).

## Run

```bash
mvn spring-boot:run
```

* GraphQL: `http://localhost:9000/graphql`
* GraphiQL: `http://localhost:9000/graphiql`

## Examples

* [Hello world](src/main/java/com/ab/hello/world/README.md) returns a string from `fullName`. [`HelloWorldController`](src/main/java/com/ab/hello/world/HelloWorldController.java) calls the same API with the GraphQL web client.
* [Example 1](src/main/java/com/ab/example0001/README.md) takes an `Integer` and a `String`. [`Example0001Controller`](src/main/java/com/ab/example0001/Example0001Controller.java) is the web client.
* [Example 2](src/main/java/com/ab/example0002/README.md) takes a request object.
* [Example 3](src/main/java/com/ab/example0003/README.md) rejects nulls on schema fields marked with `!`.
* [Example 4](src/main/java/com/ab/example0004/README.md) moves `/graphql` to `/api/v1/app` and `/graphiql` to `/api/v1/app-ui`. Those properties are commented out in `application.properties`.
* [Example 5](src/main/java/com/ab/example0005/README.md) takes a request object and returns a response object.
* [Example 6](src/main/java/com/ab/example0006/README.md) loads a student's subjects in a field resolver, only when the query asks for them.
* [Example 7](src/main/java/com/ab/example0007/README.md) adds a `subjectName` argument to that subjects resolver.
* [Example 8](src/main/java/com/ab/example0008/README.md) is a `createStudent` mutation.
