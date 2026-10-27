# Spring JDBC

Maven quickstart project. [`pom.xml`](pom.xml) depends on JUnit 3.8.1. There is no Spring or JDBC dependency in this module.

## Prerequisites

- Java 8
- Maven 3.6+

## Run

[`App`](src/main/java/com/java/App.java) prints `Hello World!`.

```bash
mvn -q compile
java -cp target/classes com.java.App
```

[`AppTest`](src/test/java/com/java/AppTest.java) is a JUnit 3 test whose `testApp` method asserts `true`.

```bash
mvn test
```

## Layout

```text
src/main/java/com/java/App.java       Prints Hello World!
src/test/java/com/java/AppTest.java   JUnit 3 test
```
