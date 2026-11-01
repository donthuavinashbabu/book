# Query practice

Spring Boot 2.6.15 GraphQL query examples. [`pom.xml`](pom.xml) uses Java 8. The schema is [`query.graphqls`](src/main/resources/graphql/query.graphqls). The resolver is [`Query`](src/main/java/com/ab/component/Query.java). [`application.yaml`](src/main/resources/application.yaml) sets port `9000`.

## Run

```bash
mvn spring-boot:run
```

GraphQL is at `http://localhost:9000/graphql`. GraphiQL is at `http://localhost:9000/graphiql`.

## Queries

[`query.graphqls`](src/main/resources/graphql/query.graphqls) defines:

* `findName` and `findAllNames`
* `fullName(firstName, lastName)` and `fullName2(student)`
* `student` and `allStudents`, including `filteredSubjects` and `filteredSubjectsWithArray`

`Student.firstName` is non-null (`String!`). Subject names use `SubjectEnum`. Requests are in [`query-practice.postman_collection.json`](files/postman/query-practice.postman_collection.json).
