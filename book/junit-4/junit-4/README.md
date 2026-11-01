# Junit 4

Maven project of JUnit 4.12 examples. [`pom.xml`](pom.xml) targets Java 8. It also depends on SLF4J 1.7.5 with Log4j 1.2, and Lombok 1.18.6. The examples do not use Lombok. Console logging is in [`log4j.properties`](src/main/resources/log4j.properties).

The compiler plugin is `3.5.1` and Surefire is `2.17`, both under `pluginManagement`. `maven-compiler-plugin` 3.5.1 and Lombok `1.18.6` fail on JDK 21 with `ExceptionInInitializerError` because `jdk.compiler` does not open `com.sun.tools.javac.processing`. Use JDK 8 for `mvn test`.

[`App`](src/main/java/com/junit4/App.java) prints the current time as `HHmmss`.

## Run

```bash
mvn test
```

`mvn test` fails on purpose. [`TimeoutJunitPractice.timeOutMethod`](src/test/java/junit/practice/time/out/TimeoutJunitPractice.java) allows 5 seconds, and [`Model1.timeOutMethod`](src/test/java/junit/practice/model/Model1.java) sleeps 10 seconds.

Run one class:

```bash
mvn -Dtest=HelloWorldJunitPractice test
mvn -Dtest=BeforeAfterJunitPractice test
mvn -Dtest=BeforeClassAfterClassJunitPractice test
mvn -Dtest=ExceptionJunitPractice test
mvn -Dtest=ParamaterizedTestsJunitPractice test
mvn -Dtest=CategoriesJunitPractice test
mvn -Dtest=TestSuiteJunitPractice test
```

## Examples

### Hello world

[`HelloWorldJunitPractice`](src/test/java/junit/practice/hello/world/HelloWorldJunitPractice.java) calls [`Model1.method1`](src/test/java/junit/practice/model/Model1.java), which returns `Hello World`. The test asserts that value and asserts it is not `Hello`.

### Before and after

[`BeforeAfterJunitPractice`](src/test/java/junit/practice/before/after/annotations/BeforeAfterJunitPractice.java) prints `setup()` before each of two tests and `teardown()` after each test.

### BeforeClass and AfterClass

[`BeforeClassAfterClassJunitPractice`](src/test/java/junit/practice/beforeclass/afterclass/annotations/BeforeClassAfterClassJunitPractice.java) prints `setupClass()` once before both tests and `teardownClass()` once after both tests. Both methods are `public static`.

### Exceptions

[`ExceptionJunitPractice`](src/test/java/junit/practice/exception/ExceptionJunitPractice.java) calls [`Model1.exceptionMethod`](src/test/java/junit/practice/model/Model1.java). `false` returns `No Exception`. `true` throws `RuntimeException`, and that test is marked `@Test(expected = RuntimeException.class)`.

### Timeout

[`TimeoutJunitPractice`](src/test/java/junit/practice/time/out/TimeoutJunitPractice.java) calls `timeOutMethod`, which sleeps 10 seconds.

* `timeOutMethod` allows 5 seconds, so the test fails.
* `timeOutMethod2` allows 15 seconds, so the test passes.

### Test suite

[`TestSuiteJunitPractice`](src/test/java/junit/practice/test/suite/TestSuiteJunitPractice.java) runs `HelloWorldJunitPractice`, `BeforeAfterJunitPractice`, and `BeforeClassAfterClassJunitPractice` with `@RunWith(Suite.class)`.

### Parameterized

[`ParamaterizedTestsJunitPractice`](src/test/java/junit/practice/parameterized/test/ParamaterizedTestsJunitPractice.java) runs [`Model2.sum`](src/test/java/junit/practice/model/Model2.java) five times: `1+2=3`, `2+3=5`, `3+4=7`, `5+6=11`, and `7+8=15`.

### Categories

[`CategoriesJunitPractice`](src/test/java/junit/practice/cateogies/CategoriesJunitPractice.java) includes [`MyIncludeCategory`](src/test/java/junit/practice/cateogies/MyIncludeCategory.java) and excludes [`MyExcludeCategory`](src/test/java/junit/practice/cateogies/MyExcludeCategory.java).

* [`TestClass2`](src/test/java/junit/practice/cateogies/TestClass2.java) is marked with `MyIncludeCategory`, so `test1` and `test2` run.
* [`TestClass3.test1`](src/test/java/junit/practice/cateogies/TestClass3.java) is included.
* `TestClass3.test2` has no category, so the suite skips it.
* `TestClass3.test3` has both categories, so the exclude rule skips it.

Surefire also runs `TestClass2` and `TestClass3` on their own, so `TestClass3.test2` still runs outside this suite.

## Layout

```text
pom.xml
src/main/java/com/junit4/App.java
src/main/resources/log4j.properties
src/test/java/junit/practice/hello/world/HelloWorldJunitPractice.java
src/test/java/junit/practice/before/after/annotations/BeforeAfterJunitPractice.java
src/test/java/junit/practice/beforeclass/afterclass/annotations/BeforeClassAfterClassJunitPractice.java
src/test/java/junit/practice/exception/ExceptionJunitPractice.java
src/test/java/junit/practice/time/out/TimeoutJunitPractice.java
src/test/java/junit/practice/test/suite/TestSuiteJunitPractice.java
src/test/java/junit/practice/parameterized/test/ParamaterizedTestsJunitPractice.java
src/test/java/junit/practice/cateogies/CategoriesJunitPractice.java
src/test/java/junit/practice/model/Model1.java
src/test/java/junit/practice/model/Model2.java
```
