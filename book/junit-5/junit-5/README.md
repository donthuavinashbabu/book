# Junit 5

Maven project of JUnit Jupiter 5.8.1 examples. [`pom.xml`](pom.xml) targets Java 8. It depends on `junit-jupiter-engine` 5.8.1, `junit-platform-launcher` 1.4.2, SLF4J 1.7.5 with Log4j 1.2, Lombok 1.18.6, and Mockito 2.21.0 with `mockito-junit-jupiter` 2.23.0. Console logging is in [`log4j.properties`](src/main/resources/log4j.properties).

`pluginManagement` lists Surefire `2.17` and, again, Surefire `2.19.1` with `junit-platform-surefire-provider` 1.0.1. The compiler plugin is `3.5.1`. `maven-compiler-plugin` 3.5.1 and Lombok `1.18.6` fail on JDK 21 with `ExceptionInInitializerError` because `jdk.compiler` does not open `com.sun.tools.javac.processing`. Use JDK 8 for `mvn test`.

[`build.gradle`](build.gradle) targets Java 8 and uses the `compile` configuration. It depends on `junit-jupiter-engine` 5.1.0 and Lombok 1.18.4. Its launcher coordinate is `org.junit.jupiter:junit-platform-launcher:1.1.0`. Mockito is only in the pom, so [`MockitoExtensionsPractice`](src/test/java/com/junit5/mockito/MockitoExtensionsPractice.java) is a Maven test.

[`App`](src/main/java/com/junit5/App.java) prints `Hello World!`.

Snippet notes for `@BeforeEach`, `@AfterEach`, and grouped assertions are in the parent folder: [before annotations](../before-annotations.md), [after annotations](../after-annotation.md), and [assertions](../assertions.md).

## Run

```bash
mvn test
```

Run one class:

```bash
mvn -Dtest=DisplayNameAnnotation test
mvn -Dtest=DisabledAnnotation test
mvn -Dtest=Exceptions test
mvn -Dtest=Assumptions test
mvn -Dtest=Tags test
mvn -Dtest=MockitoExtensionsPractice test
```

## Examples

### Display name

[`DisplayNameAnnotation`](src/test/java/com/junit5/DisplayNameAnnotation.java) names `test1` as `Test method 1` and logs `test1`.

### Disabled

[`DisabledAnnotation`](src/test/java/com/junit5/DisabledAnnotation.java) marks `test1` with `@Disabled`. JUnit skips that method.

### Exceptions

[`Exceptions`](src/test/java/com/junit5/Exceptions.java) uses `assertThrows` for a `RuntimeException` whose message is `run time exception`, then asserts that message.

### Assumptions

[`Assumptions`](src/test/java/com/junit5/Assumptions.java):

* `assumeTrue(10 > 5)` is true, so the test asserts `10` equals `10`.
* `assumeFalse(10 > 20)` is true, so that test also asserts `10` equals `10`. Changing the comparison so the assumption fails skips the assertion.
* `assumingThat` runs `assertEquals(10, 10)` because the name is `jack`.

### Tags

[`Tags`](src/test/java/com/junit5/Tags.java) tags the class `Test case` and `test1` as `Test method 1`. `test1` asserts `10` equals `10`.

### Dynamic tests

[`DynamicTest`](src/test/java/com/junit5/DynamicTest.java) has `generateDynamicTestFromStream`, which returns `null`. The method has no `@Test` or `@TestFactory`, so JUnit does not run it.

### Select packages

[`SelectPackagesTestSuite`](src/test/java/com/junit5/SelectPackagesTestSuite.java) is an empty class. It has no `@SelectPackages` annotation and no tests.

### Mockito extension

[`MockitoExtensionsPractice`](src/test/java/com/junit5/mockito/MockitoExtensionsPractice.java) uses `@ExtendWith(MockitoExtension.class)`. It mocks [`EmployeeDao`](src/main/java/com/junit5/mockito/EmployeeDao.java) so `getEmployeeNames` returns `jim`, then calls [`EmployeeService.getEmployeeNames`](src/main/java/com/junit5/mockito/EmployeeService.java). The real DAO returns `jack` and `jill`. The test logs the mock result and does not assert it.

## Layout

```text
pom.xml
build.gradle
src/main/java/com/junit5/App.java
src/main/java/com/junit5/mockito/EmployeeDao.java
src/main/java/com/junit5/mockito/EmployeeService.java
src/main/resources/log4j.properties
src/test/java/com/junit5/Assumptions.java
src/test/java/com/junit5/DisabledAnnotation.java
src/test/java/com/junit5/DisplayNameAnnotation.java
src/test/java/com/junit5/DynamicTest.java
src/test/java/com/junit5/Exceptions.java
src/test/java/com/junit5/SelectPackagesTestSuite.java
src/test/java/com/junit5/Tags.java
src/test/java/com/junit5/mockito/MockitoExtensionsPractice.java
```
