# GraphQL Spring Boot client

Spring Boot 2.3.3 client for a GraphQL server. [`pom.xml`](pom.xml) uses Java 8 and GraphQL Kickstart `11.0.0`, including `graphql-webclient-spring-boot-starter`.

[`application.yml`](src/main/resources/application.yml) sets this app to port `9001` and `graphql.client.url` to `http://localhost:9000/graphql`.

## Run

Start the GraphQL server on port `9000`, then:

```bash
mvn spring-boot:run
```

[`GraphQLClientController`](src/main/java/com/java/client/GraphQLClientController.java) posts queries with `GraphQLWebClient`.

* `GET /api/v1/studentRequest/{id}/{firstName}/{lastName}` sends the arguments inline.
* `GET /api/v1/studentRequest/variables/{id}/{firstName}/{lastName}` sends the same query with GraphQL variables.

Both requests are in [`graphql-spring-boot-client.postman_collection.json`](files/graphql-spring-boot-client.postman_collection.json).
