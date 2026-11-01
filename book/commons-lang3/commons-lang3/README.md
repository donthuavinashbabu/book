# Commons Lang3

Maven project of JUnit 4 examples for Apache Commons Lang `3.9`. The tests are in `src/test/java/com/lang3`. [`pom.xml`](pom.xml) compiles with Java 8. It depends on `commons-lang3` 3.9, JUnit 4.12, Lombok 1.18.6, and SLF4J with Log4j 1.2. Console logging is in [`log4j.properties`](src/main/resources/log4j.properties).

[`build.gradle`](build.gradle) lists the same dependencies and sets `sourceCompatibility` to `1.8`.

## Prerequisites

- JDK 8
- Maven 3.6+

[`pom.xml`](pom.xml) sets the compiler source and target to `1.8`. `maven-compiler-plugin` 3.5.1 and Lombok `1.18.6` fail on JDK 21 with `ExceptionInInitializerError` because `jdk.compiler` does not open `com.sun.tools.javac.processing`. Use JDK 8 for `mvn test`.

## Dependencies

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.9</version>
</dependency>
```

## Run

```bash
mvn test
```

Run one example class:

```bash
mvn -Dtest=StringUtilsPractice test
mvn -Dtest=BooleanUtilsPractice test
mvn -Dtest=ObjectUtilsPractice test
mvn -Dtest=RandomStringUtilsPractice test
mvn -Dtest=ToStringBuilderPractice test
```

## Create

The module was created with the Maven quickstart archetype:

```bash
mvn archetype:generate -DgroupId=commons.lang3 -DartifactId=commons-lang3 -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

[`build.gradle`](build.gradle) was added from that pom:

```bash
gradle init --type pom
```

## Examples

### StringUtils

[`StringUtilsPractice`](src/test/java/com/lang3/StringUtilsPractice.java)

`isNoneBlank` is true when every argument has text. `"john"`, `"jim"`, `"jane"` is true. Replacing one of them with `""` is false.

`equalsAny("jack", "john", "jim", "jane")` is false. `equalsAny("jim", "john", "jim", "jane")` is true.

`equalsAnyIgnoreCase("jack", "john", "jim", "jane")` is false. `equalsAnyIgnoreCase("JIM", "john", "jim", "jane")` is true.

`substring` is null-safe and stops at the end of the string:

| Call | Result |
| --- | --- |
| `substring("abcd", 1)` | `bcd` |
| `substring("abcd", 0, 10)` | `abcd` |
| `substring("", 1)` | `""` |
| `substring("", 0, 10)` | `""` |
| `substring(null, 1)` | `null` |
| `substring(null, 0, 10)` | `null` |

### BooleanUtils

[`BooleanUtilsPractice`](src/test/java/com/lang3/BooleanUtilsPractice.java)

`toBoolean("true")` is true. `toBoolean("false")` is false.

`toBoolean(int)` is false only for `0`. `1`, `2`, and `-1` are true.

### ObjectUtils

[`ObjectUtilsPractice`](src/test/java/com/lang3/ObjectUtilsPractice.java)

`isEmpty` is true for null and for an empty string (`StringUtils.EMPTY`). It is false for `new Object()`, `"jack"`, `Integer` `10`, and a [`Person`](src/main/java/com/lang3/Person.java) created with the no-arg constructor.

`isNotEmpty` returns the opposite value for each of those inputs.

### RandomStringUtils

[`RandomStringUtilsPractice`](src/test/java/com/lang3/RandomStringUtilsPractice.java)

Each call prints a new random string. Two-argument methods take an inclusive minimum length and an exclusive maximum length, so `(3, 10)` and `(5, 10)` produce a length from the first number up to one less than the second.

| Method | Result |
| --- | --- |
| `random(10)` | 10 characters from the full Unicode range |
| `randomAlphabetic(5)` | 5 letters |
| `randomAlphabetic(3, 10)` | 3 to 9 letters |
| `randomAlphanumeric(10)` | 10 letters or digits |
| `randomAlphanumeric(5, 10)` | 5 to 9 letters or digits |
| `randomNumeric(10)` | 10 digits |
| `randomNumeric(5, 10)` | 5 to 9 digits |
| `randomPrint(10)` | 10 characters from ASCII 32 through 125 (space through `}`) |

### ToStringBuilder

[`ToStringBuilderPractice`](src/test/java/com/lang3/ToStringBuilderPractice.java)

[`Person`](src/main/java/com/lang3/Person.java) builds `toString` with `ToStringBuilder`, appending `name` and `address`. For name `jack` and address `round street` the value is:

```text
com.lang3.Person@<hash>[name=jack,address=round street]
```

`ToStringBuilder.reflectionToString(person)` prints the same fields. The identity hash changes between runs.

[`ExceptionUtils`](src/test/java/com/lang3/ExceptionUtils.java) is an empty class.

## Layout

```text
pom.xml
build.gradle
src/main/java/com/lang3/Person.java
src/main/resources/log4j.properties
src/test/java/com/lang3/BooleanUtilsPractice.java
src/test/java/com/lang3/ExceptionUtils.java
src/test/java/com/lang3/ObjectUtilsPractice.java
src/test/java/com/lang3/RandomStringUtilsPractice.java
src/test/java/com/lang3/StringUtilsPractice.java
src/test/java/com/lang3/ToStringBuilderPractice.java
```
