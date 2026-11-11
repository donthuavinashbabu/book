# DynamoDB Practice

Maven project that creates one DynamoDB table against DynamoDB Local. [`pom.xml`](pom.xml) compiles with Java 17 (`maven-compiler-plugin` `3.8.1`, `<release>17</release>`). It depends on AWS SDK for Java `1.11.163`, JUnit Jupiter `5.8.1`, `junit-platform-launcher` `1.4.2`, and `commons-io` `2.5`.

[`App`](src/main/java/com/ab/App.java) prints `Hello World!`.

How to start DynamoDB Local is in [Local setup](../local-setup.md). Listing tables is in [Commands](../commands.md).

## Prerequisites

- JDK 17
- Maven 3.6+
- DynamoDB Local listening on `http://localhost:8000`

`createTable` builds the client with `new AmazonDynamoDBAsyncClient()`. That constructor uses the default AWS credential chain. DynamoDB Local ignores the key values, and the SDK still has to resolve credentials before the call.

[`pom.xml`](pom.xml) does not set `maven-surefire-plugin`. JUnit Jupiter needs Surefire `2.22.0` or newer. If `mvn test` reports that no tests ran, add that plugin.

## Run

Start DynamoDB Local, then from this directory:

```bash
mvn -Dtest=DynamoDBPracticeTest test
```

## Create table

[`DynamoDBPracticeTest.createTable`](src/test/java/com/ab/DynamoDBPracticeTest.java) points the client at `http://localhost:8000` with `withEndpoint`, wraps it in the document API `DynamoDB`, and creates table `student_0002`.

* Partition key (`HASH`): `ID`, number (`N`)
* Sort key (`RANGE`): `firstName`, string (`S`)
* Provisioned throughput: 10 read capacity units and 10 write capacity units

`table.waitForActive()` blocks until the table status is `ACTIVE`. The test then prints:

```text
Created table. status = ACTIVE
```

The test has no assertions. If `student_0002` already exists, `createTable` throws `ResourceInUseException`. The method only catches `InterruptedException`.

## Layout

```text
pom.xml
src/main/java/com/ab/App.java
src/test/java/com/ab/DynamoDBPracticeTest.java
```
