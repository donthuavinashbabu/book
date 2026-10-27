# Dependency Injection using Annotations

Spring Framework 5.1 project that discovers beans with `@Service` and `@Repository`, then injects them with `@Autowired` and `@Value`. Each example still starts from a small XML file. That file turns on annotation processing and component scan, and the Java classes do the wiring.

Every example is a JUnit 4 `@Test` method on an `App` class under `src/main/java`. There is no `main` method. Run the test method from the IDE.

## Prerequisites

- Java 8
- Maven 3.6+ or Gradle

## Dependencies

Declared in [`pom.xml`](pom.xml) and [`build.gradle`](build.gradle):

| Dependency | Version |
| --- | --- |
| `org.springframework:spring-core` | `5.1.9.RELEASE` |
| `org.springframework:spring-context` | `5.1.9.RELEASE` |
| `junit:junit` | `4.12` |
| `org.projectlombok:lombok` | `1.18.8` |
| `org.slf4j:slf4j-api` | `1.7.28` |
| `org.slf4j:slf4j-log4j12` | `1.7.28` |

## Examples

| Example | Test class | Context file |
| --- | --- | --- |
| Field injection | [`dependency.injection.setter.App`](src/main/java/dependency/injection/setter/App.java) | [`dependency.injection.setter/applicationContext.xml`](src/main/resources/dependency.injection.setter/applicationContext.xml) |
| Constructor injection | [`dependency.injection.constructor.App`](src/main/java/dependency/injection/constructor/App.java) | [`dependency.injection.constructor/applicationContext.xml`](src/main/resources/dependency.injection.constructor/applicationContext.xml) |
| `@Value` from a properties file | [`properties.injection.App`](src/main/java/properties/injection/App.java) | [`properties.injection/applicationContext.xml`](src/main/resources/properties.injection/applicationContext.xml) |

Each context file contains the same two lines, plus a property placeholder in the third example:

```xml
<context:annotation-config />
<context:component-scan base-package="..."/>
```

`annotation-config` makes Spring honor `@Autowired` and `@Value`. `component-scan` registers classes in that package that carry a stereotype annotation.

## Field injection

Package [`dependency.injection.setter`](src/main/java/dependency/injection/setter).

[`StudentServiceImpl`](src/main/java/dependency/injection/setter/StudentServiceImpl.java) is a `@Service`. `@Autowired` sits on the `studentRepository` field, so Spring sets that field after it creates the bean. [`StudentRepositoryImpl`](src/main/java/dependency/injection/setter/StudentRepositoryImpl.java) is a `@Repository`. Its `findStudent()` builds a student in code: id `1`, name `jill`, and `joiningDate` set to `new Date()`.

[`App`](src/main/java/dependency/injection/setter/App.java) loads the setter context, gets the `StudentService` by type, and logs that student. The logged date is the time the test runs.

## Constructor injection

Package [`dependency.injection.constructor`](src/main/java/dependency/injection/constructor).

[`StudentServiceImpl`](src/main/java/dependency/injection/constructor/StudentServiceImpl.java) is a `@Service`. `@Autowired` sits on the constructor that takes a `StudentRepository`, so Spring passes the repository in when it calls that constructor. [`StudentRepositoryImpl`](src/main/java/dependency/injection/constructor/StudentRepositoryImpl.java) is a `@Repository` and returns the same hardcoded student: id `1`, name `jill`, joining date `new Date()`.

[`App`](src/main/java/dependency/injection/constructor/App.java) loads the constructor context, gets `StudentService` by type, and logs that student.

## `@Value` from a properties file

Package [`properties.injection`](src/main/java/properties/injection).

The context adds:

```xml
<context:property-placeholder location="properties.injection/app.properties"/>
```

[`app.properties`](src/main/resources/properties.injection/app.properties) contains `student.id=1` and `student.name=jill`.

[`StudentRepositoryImpl`](src/main/java/properties/injection/StudentRepositoryImpl.java) is a `@Repository`. `@Value("${student.id}")` and `@Value("${student.name}")` fill two `String` fields. `findStudent()` parses the id with `Long.parseLong` and builds the student from those fields. [`StudentServiceImpl`](src/main/java/properties/injection/StudentServiceImpl.java) is a `@Service` with `@Autowired` on its repository field.

[`App`](src/main/java/properties/injection/App.java) gets the bean named `studentServiceImpl` and logs `Student(id=1, name=jill)`.

## Layout

```text
src/main/java
├── dependency/injection/setter        @Autowired on a field
├── dependency/injection/constructor   @Autowired on a constructor
└── properties/injection               @Value from app.properties
src/main/resources
├── dependency.injection.setter/applicationContext.xml
├── dependency.injection.constructor/applicationContext.xml
├── properties.injection/applicationContext.xml
├── properties.injection/app.properties
└── log4j.properties
```

## References

* [Spring Framework 5.1 Reference: Annotation-based Container Configuration](https://docs.spring.io/spring-framework/docs/5.1.x/spring-framework-reference/core.html#beans-annotation-config)
* [Spring Framework 5.1 Reference: Classpath Scanning](https://docs.spring.io/spring-framework/docs/5.1.x/spring-framework-reference/core.html#beans-classpath-scanning)
