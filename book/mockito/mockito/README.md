# Mockito

Maven project of JUnit 4 examples for Mockito `5.14.2`. [`pom.xml`](pom.xml) compiles with Java 21. It depends on `mockito-core` 5.14.2, JUnit 4.13.2, Lombok 1.18.36, and SLF4J with Log4j 1.2. Console logging is in [`log4j.properties`](src/main/resources/log4j.properties).

## Prerequisites

- JDK 21
- Maven 3.6+

The tests use `org.mockito.junit.MockitoJUnitRunner`. [`EmployeeServiceTestAnnotations`](src/test/java/com/mockito/dependency/EmployeeServiceTestAnnotations.java) calls `MockitoAnnotations.initMocks(this)`.

## Dependencies

```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.14.2</version>
    <scope>test</scope>
</dependency>
```

## Run

```bash
mvn test
```

Run one example:

```bash
mvn -Dtest=HelloWorldTest test
mvn -Dtest=EmployeeServiceTest1 test
mvn -Dtest=EmployeeServiceTestAnnotations test
mvn -Dtest=EmployeeServiceTestAnnotations2 test
mvn -Dtest=MathUtilsTest test
mvn -Dtest=EmployeeControllerTest test
mvn -Dtest=WrongTypeOfReturnValueTest test
mvn -Dtest=CheckedException test
mvn -Dtest=UncheckedException test
mvn -Dtest=MockVoidMethods test
mvn -Dtest=PassNullValues test
```

## Examples

### Hello world

[`HelloWorld`](src/main/java/com/mockito/hello/world/HelloWorld.java) has `hello`, `sum`, and a void `add`. [`HelloWorldTest`](src/test/java/com/mockito/hello/world/HelloWorldTest.java) stubs a mock with `anyString`, a specific string, `anyInt`, and specific ints. `returnDifferentValuesOnMultipleInvocations` returns `10`, then `20`, then `30` from a mock with `thenReturn`, and from a spy with `doReturn`.

### Employee service

[`EmployeeService`](src/main/java/com/mockito/dependency/EmployeeService.java) delegates to [`EmployeeDao`](src/main/java/com/mockito/dependency/EmployeeDao.java).

[`EmployeeServiceTest1`](src/test/java/com/mockito/dependency/EmployeeServiceTest1.java) creates the mock with `Mockito.mock` and passes it through `setEmployeeDao`. It verifies `getEmployeeNames`. The assertions check the list passed to `thenReturn`, not the list returned by the service.

[`EmployeeServiceTestAnnotations`](src/test/java/com/mockito/dependency/EmployeeServiceTestAnnotations.java) uses `@Mock`, `@InjectMocks`, and `MockitoAnnotations.initMocks(this)` in `@Before`. [`EmployeeServiceTestAnnotations2`](src/test/java/com/mockito/dependency/EmployeeServiceTestAnnotations2.java) uses the same annotations with `@RunWith(MockitoJUnitRunner.class)`. Neither test asserts the returned names.

### Argument captor

[`MathUtilsTest`](src/test/java/com/mockito/argument/captor/MathUtilsTest.java) captures both arguments of [`MathUtils.add`](src/main/java/com/mockito/argument/captor/MathUtils.java) and expects `[1, 1]`. `printEmployee` calls the real method with `doCallRealMethod`, then captures the [`Employee`](src/main/java/com/mockito/argument/captor/Employee.java) passed to `print`.

### Lombok constructor injection

[`EmployeeController`](src/main/java/com/mockito/lombok/inject/EmployeeController.java) takes a final [`EmployeeService`](src/main/java/com/mockito/lombok/inject/EmployeeService.java) through `@RequiredArgsConstructor`. [`EmployeeControllerTest`](src/test/java/com/mockito/lombok/inject/EmployeeControllerTest.java) injects a mock with `@InjectMocks` and expects `getName` to return `test jim`.

### WrongTypeOfReturnValue

[`WrongTypeOfReturnValueTest`](src/test/java/com/mockito/wrong/type/of/returnvalue/WrongTypeOfReturnValueTest.java) spies [`Outside`](src/main/java/com/mockito/wrong/type/of/returnvalue/Outside.java). `when(outside.produce()).thenReturn(product)` runs the real `produce` method, which calls `foo.getBar()` on the mock. Mockito then reports `WrongTypeOfReturnValue` because that recorded call cannot return a `Product`. `doReturn(product).when(outside).produce()` stubs the spy without calling the real method.

### Exceptions and null

[`CheckedException`](src/test/java/com/mockito/CheckedException.java) throws a checked `Exception` with `thenAnswer`, because `thenThrow` accepts unchecked exceptions. [`UncheckedException`](src/test/java/com/mockito/UncheckedException.java) uses `thenThrow(new RuntimeException(...))`.

[`MockVoidMethods`](src/test/java/com/mockito/MockVoidMethods.java) stubs `HelloWorld.add` with `doThrow`, `doThrow` followed by `doNothing`, and `doAnswer`.

[`PassNullValues`](src/test/java/com/mockito/PassNullValues.java) stubs `getEmployeeNames(null)` with `Mockito.isNull()` and with `Mockito.eq(null)`.

## Layout

```text
pom.xml
src/main/java/com/mockito/hello/world/HelloWorld.java
src/main/java/com/mockito/dependency/EmployeeDao.java
src/main/java/com/mockito/dependency/EmployeeService.java
src/main/java/com/mockito/argument/captor/MathUtils.java
src/main/java/com/mockito/argument/captor/Employee.java
src/main/java/com/mockito/lombok/inject/EmployeeController.java
src/main/java/com/mockito/lombok/inject/EmployeeService.java
src/main/java/com/mockito/wrong/type/of/returnvalue/Foo.java
src/main/java/com/mockito/wrong/type/of/returnvalue/Bar.java
src/main/java/com/mockito/wrong/type/of/returnvalue/Product.java
src/main/java/com/mockito/wrong/type/of/returnvalue/Outside.java
src/main/resources/log4j.properties
src/test/java/com/mockito/hello/world/HelloWorldTest.java
src/test/java/com/mockito/dependency/EmployeeServiceTest1.java
src/test/java/com/mockito/dependency/EmployeeServiceTestAnnotations.java
src/test/java/com/mockito/dependency/EmployeeServiceTestAnnotations2.java
src/test/java/com/mockito/argument/captor/MathUtilsTest.java
src/test/java/com/mockito/lombok/inject/EmployeeControllerTest.java
src/test/java/com/mockito/wrong/type/of/returnvalue/WrongTypeOfReturnValueTest.java
src/test/java/com/mockito/CheckedException.java
src/test/java/com/mockito/UncheckedException.java
src/test/java/com/mockito/MockVoidMethods.java
src/test/java/com/mockito/PassNullValues.java
```
