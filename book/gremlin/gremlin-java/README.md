# Gremlin Java

Maven project that submits a Gremlin string to a server on `localhost:8182`. [`pom.xml`](pom.xml) sets `java.version` to 17 and then compiles with source and target `1.8`. The `release` line is commented out. It depends on JUnit Jupiter 5.8.1, Lombok 1.18.24, SLF4J with Log4j 1.2, and several Gremlin artifacts. Console logging is in [`log4j.properties`](src/main/resources/log4j.properties).

## Prerequisites

- JDK 8
- Maven 3.6+
- A Gremlin Server listening on `localhost:8182`

`maven-compiler-plugin` 3.8.1 and Lombok `1.18.24` fail on JDK 21. Lombok `1.18.24` throws `NoSuchFieldError` on `JCTree$JCImport`. Use JDK 8 or 17 for `mvn test`.

## Dependencies

The pom declares three Gremlin lines that do not belong on one classpath:

| Artifact | Version |
| --- | --- |
| `org.apache.tinkerpop:gremlin-core` | 3.7.1 |
| `com.tinkerpop:gremlin-core` | 3.0.0.M7 |
| `org.apache.tinkerpop:gremlin-driver` | 3.3.3 |
| `org.apache.tinkerpop:tinkergraph-gremlin` | 3.3.3 |

`com.tinkerpop:gremlin-core` 3.0.0.M7 is a pre-Apache milestone. A commented dependency asks for `gremlin-driver` 3.7.1. [`MyConnection`](src/test/java/com/java/MyConnection.java) uses the 3.3 `Cluster` and `Client` API.

## Run

```bash
mvn test
```

[`HelloWorld`](src/test/java/com/java/HelloWorld.java) is the only active test. It fails when nothing is listening on port `8182`.

[`App`](src/main/java/com/java/App.java) is the Maven quickstart class. It prints `Hello World!`.

## Connection

[`MyConnection`](src/test/java/com/java/MyConnection.java) builds a `Cluster` for `localhost` port `8182`, opens a `Client`, and submits the query string. `queryGraph` returns the `ResultSet` and does not wait for it. `closeConnection` closes the cluster. `hello3` never calls it.

`hello3` sends this script, with the player ids filled in as `0` and `1`:

```text
g.V().hasLabel('player').has('id', '0').as('p1')V().hasLabel('player').has('id', '1').as('p2').addE('friends').from('p1').to('p2')
```

The second `V()` is not preceded by `.`, so the script is not valid Gremlin. The ids are sent as strings.

Two older tests are commented out. They open `AnonymousTraversalSource.traversal().withRemote(...)` on the same host and port, read `elementMap` results, and add two `person` vertices joined by a `knows` edge.

## Layout

```text
pom.xml
src/main/java/com/java/App.java
src/main/resources/log4j.properties
src/test/java/com/java/HelloWorld.java
src/test/java/com/java/MyConnection.java
```
