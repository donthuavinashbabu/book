# Dependency Injection using Java Config

Spring Framework 5.1 project that builds the application context from `@Configuration` classes. There is no XML. Each example opens an `AnnotationConfigApplicationContext` on its `AppConfig` and asks for a `StudentService`.

The `App` classes are JUnit 4 `@Test` methods under `src/main/java`. Run those methods from the IDE. One example also has a test under `src/test/java`, and Maven can run that test.

## Prerequisites

- Java 8
- Maven 3.6+ or Gradle

## Dependencies

Declared in [`pom.xml`](pom.xml) and [`build.gradle`](build.gradle):

| Dependency | Version |
| --- | --- |
| `org.springframework:spring-core` | `5.1.9.RELEASE` |
| `org.springframework:spring-context` | `5.1.9.RELEASE` |
| `org.springframework:spring-test` | `5.1.9.RELEASE` |
| `junit:junit` | `4.12` |
| `org.projectlombok:lombok` | `1.18.8` |
| `org.slf4j:slf4j-api` | `1.7.28` |
| `org.slf4j:slf4j-log4j12` | `1.7.28` |

## Examples

| Example | Entry point | Configuration |
| --- | --- | --- |
| `@Bean` methods | [`dependency.inject.setter.injection.App`](src/main/java/dependency/inject/setter/injection/App.java) | [`AppConfig`](src/main/java/dependency/inject/setter/injection/AppConfig.java) |
| `@ComponentScan` with stereotypes | [`component.scan.with.annotations.App`](src/main/java/component/scan/with/annotations/App.java) | [`AppConfig`](src/main/java/component/scan/with/annotations/AppConfig.java) |
| `@PropertySource` and `@Value` | [`properties.injection.App`](src/main/java/properties/injection/App.java) | [`AppConfig`](src/main/java/properties/injection/AppConfig.java) |

## `@Bean` methods

Package [`dependency.inject.setter.injection`](src/main/java/dependency/inject/setter/injection).

[`AppConfig`](src/main/java/dependency/inject/setter/injection/AppConfig.java) is a `@Configuration` with `@ComponentScan` on this package. The service and repository classes have no stereotype annotation, so the scan does not register them. Two `@Bean` methods return `new StudentServiceImpl()` and `new StudentRepositoryImpl()`. The bean names are the method names, `getStudentService` and `getStudentRepository`.

[`StudentServiceImpl`](src/main/java/dependency/inject/setter/injection/StudentServiceImpl.java) has `@Autowired` on its `studentRepository` field. The class has no `@Service`. Spring still injects the repository because the instance was registered with `@Bean` and the field is annotated. [`StudentRepositoryImpl`](src/main/java/dependency/inject/setter/injection/StudentRepositoryImpl.java) builds a student in code: id `1`, name `jill`, joining date `new Date()`.

[`App`](src/main/java/dependency/inject/setter/injection/App.java) gets `StudentService` by type and logs that student. The logged date is the time the test runs.

## Component scan with stereotypes

Package [`component.scan.with.annotations`](src/main/java/component/scan/with/annotations).

[`AppConfig`](src/main/java/component/scan/with/annotations/AppConfig.java) declares `@ComponentScan` for this package and defines no `@Bean` methods. Spring registers the stereotyped classes it finds there.

[`StudentServiceImpl`](src/main/java/component/scan/with/annotations/StudentServiceImpl.java) is a `@Service` with `@Autowired` on its repository field. [`StudentRepositoryImpl`](src/main/java/component/scan/with/annotations/StudentRepositoryImpl.java) is a `@Repository` and returns a student with id `1`, name `jill`, and joining date `new Date()`.

[`App`](src/main/java/component/scan/with/annotations/App.java) gets `StudentService` by type and logs that student.

[`StudentServiceTest`](src/test/java/component/scan/with/annotations/StudentServiceTest.java) loads the same `AppConfig` through Spring's JUnit 4 runner:

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
```

The test `@Autowired`s `StudentService` and calls `findStudent()`. From the project directory:

```bash
mvn -Dtest=component.scan.with.annotations.StudentServiceTest test
```

## `@PropertySource` and `@Value`

Package [`properties.injection`](src/main/java/properties/injection).

[`AppConfig`](src/main/java/properties/injection/AppConfig.java) does three things:

1. `@ComponentScan(basePackages = "properties.injection")` picks up the `@Service` and `@Repository`.
2. `@PropertySource("properties.injection/app.properties")` loads [`app.properties`](src/main/resources/properties.injection/app.properties). That file sets `student.id=2` and `student.name=jack`.
3. A `static` `@Bean` method returns a `PropertySourcesPlaceholderConfigurer`, which resolves `${...}` in `@Value`.

[`StudentRepositoryImpl`](src/main/java/properties/injection/StudentRepositoryImpl.java) is a `@Repository`. `@Value("${student.id}")` fills a `Long` and `@Value("${student.name}")` fills a `String`. `findStudent()` builds the student from those fields. [`StudentServiceImpl`](src/main/java/properties/injection/StudentServiceImpl.java) is a `@Service` with `@Autowired` on its repository field. The default bean name is `studentServiceImpl`.

[`App`](src/main/java/properties/injection/App.java) gets `studentServiceImpl` by that name and logs `Student(id=2, name=jack)`.

## Layout

```text
src/main/java
├── dependency/inject/setter/injection   @Bean methods and @Autowired
├── component/scan/with/annotations      @ComponentScan, @Service, @Repository
└── properties/injection                 @PropertySource, @Value
src/test/java
└── component/scan/with/annotations/StudentServiceTest.java
src/main/resources
├── properties.injection/app.properties
└── log4j.properties
```

## References

* [Spring Framework 5.1 Reference: Java-based Container Configuration](https://docs.spring.io/spring-framework/docs/5.1.x/spring-framework-reference/core.html#beans-java)
* [Spring Framework 5.1 Reference: Classpath Scanning](https://docs.spring.io/spring-framework/docs/5.1.x/spring-framework-reference/core.html#beans-classpath-scanning)
