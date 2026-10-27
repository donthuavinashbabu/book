# Dependency Injection using XML

Spring Framework 5.1 project that creates beans from XML and injects them into each other. Every example is a JUnit 4 `@Test` method on an `App` class. The test opens a `ClassPathXmlApplicationContext`, asks for a service bean, and logs the student or employee that service returns.

There is no `main` method. The `App` classes live under `src/main/java`, so run each test method from the IDE.

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

| Example | Test class | Context file |
| --- | --- | --- |
| Setter injection | [`setter.injection.App`](src/main/java/setter/injection/App.java) | [`setter.injection/setter-injection.xml`](src/main/resources/setter.injection/setter-injection.xml) |
| Constructor injection | [`basic.constructor.injection.App`](src/main/java/basic/constructor/injection/App.java) | [`basic.constructor.injection/applicationContext.xml`](src/main/resources/basic.constructor.injection/applicationContext.xml) |
| Autowire by type | [`autowire.by.type.App`](src/main/java/autowire/by/type/App.java) | [`autowire.by.type/application-context.xml`](src/main/resources/autowire.by.type/application-context.xml) |
| Autowire by constructor | [`autowire.constructor.App`](src/main/java/autowire/constructor/App.java) | [`autowire.constructor/application-context.xml`](src/main/resources/autowire.constructor/application-context.xml) |
| Date values | [`date.injection.App`](src/main/java/date/injection/App.java) | [`date.injection/applicationContext.xml`](src/main/resources/date.injection/applicationContext.xml) |
| Property placeholders | [`properties.injection.App`](src/main/java/properties/injection/App.java) | [`properties.injection/applicationContext.xml`](src/main/resources/properties.injection/applicationContext.xml) |

## Setter injection

[`setter-injection.xml`](src/main/resources/setter.injection/setter-injection.xml) builds three beans and wires them with `<property>` and the `p:` namespace.

1. `student1` is a [`Student`](src/main/java/setter/injection/Student.java). `p:id` and `p:grade` set `id` and `grade`. A nested `<property name="name" value="jill"/>` sets the name.
2. `studentRepository` is a [`StudentRepositoryImpl`](src/main/java/setter/injection/StudentRepositoryImpl.java). `<property name="student" ref="student1"/>` calls its setter with the student bean.
3. `studentService` is a [`StudentServiceImpl`](src/main/java/setter/injection/StudentServiceImpl.java). Its `studentRepository` property points at `studentRepository`.

[`App`](src/main/java/setter/injection/App.java) loads that file, gets `studentService`, and calls `findAllStudents()`. The repository returns a one-element list containing the injected student:

```text
students-list=[Student(id=1, name=jill, grade=3.24)]
```

## Constructor injection

[`applicationContext.xml`](src/main/resources/basic.constructor.injection/applicationContext.xml) uses `<constructor-arg>` instead of setters.

1. `student1` is a [`Student`](src/main/java/basic/constructor/injection/Student.java) with `p:id="1"` and `p:name="jill"`.
2. `studentRepositoryImpl` is a [`StudentRepositoryImpl`](src/main/java/basic/constructor/injection/StudentRepositoryImpl.java). `<constructor-arg index="0" ref="student1"/>` passes that student to the constructor.
3. `studentServiceImpl` is a [`StudentServiceImpl`](src/main/java/basic/constructor/injection/StudentServiceImpl.java). Its constructor receives `studentRepositoryImpl`.

[`App`](src/main/java/basic/constructor/injection/App.java) gets the bean named `studentServiceImpl` and logs:

```text
student=Student(id=1, name=jill)
```

## Autowire by type

[`application-context.xml`](src/main/resources/autowire.by.type/application-context.xml) sets `autowire="byType"` on the repository and the service. Spring matches each setter argument to the single bean of that type. The XML does not name the collaborator.

1. `student1` is a [`Student`](src/main/java/autowire/by/type/Student.java) with `id` `1` and `name` `jill`.
2. [`StudentRepositoryImpl`](src/main/java/autowire/by/type/StudentRepositoryImpl.java) has a `Student` setter, so Spring injects `student1`.
3. [`StudentServiceImpl`](src/main/java/autowire/by/type/StudentServiceImpl.java) has a `StudentRepository` setter, so Spring injects `studentRepository`.

[`App`](src/main/java/autowire/by/type/App.java) gets `studentService` and logs `Student(id=1, name=jill)`.

## Autowire by constructor

[`application-context.xml`](src/main/resources/autowire.constructor/application-context.xml) sets `autowire="constructor"` on the repository and the service. Each class has one constructor, and Spring supplies the bean whose type matches that argument.

1. `student1` is a [`Student`](src/main/java/autowire/constructor/Student.java) with `p:id="1"` and `p:name="jill"`.
2. [`StudentRepositoryImpl`](src/main/java/autowire/constructor/StudentRepositoryImpl.java) takes a `Student`.
3. [`StudentServiceImpl`](src/main/java/autowire/constructor/StudentServiceImpl.java) takes a `StudentRepository`.

[`App`](src/main/java/autowire/constructor/App.java) gets the bean named `studentServiceImpl` and logs `Student(id=1, name=jill)`.

## Date values

[`applicationContext.xml`](src/main/resources/date.injection/applicationContext.xml) turns date strings into `java.util.Date` in two ways. [`App`](src/main/java/date/injection/App.java) has a test method for each.

**Student, `SimpleDateFormat`.** A `dateFormat` bean is constructed with pattern `yyyy-MM-dd`. The `joiningDate` property of [`Student`](src/main/java/date/injection/Student.java) is an inner bean that calls `factory-method="parse"` on `dateFormat` with `2019-09-26`. `studentRepositoryImpl` and `studentServiceImpl` are then wired with `p:student-ref` and `p:studentRepository-ref`. The `student()` test logs a student named `jill` whose joining date is 26 September 2019.

**Employee, `CustomDateEditor`.** A `CustomEditorConfigurer` registers [`CustomDateEditorRegistrar`](src/main/java/date/injection/CustomDateEditorRegistrar.java). That registrar installs a `CustomDateEditor` for `Date` with pattern `dd-MM-yyyy`. [`Employee`](src/main/java/date/injection/Employee.java) can then take `p:joiningDate="27-09-2019"` as a plain string. The `employee()` test logs an employee named `jim` whose joining date is 27 September 2019.

## Property placeholders

[`applicationContext.xml`](src/main/resources/properties.injection/applicationContext.xml) loads [`app.properties`](src/main/resources/properties.injection/app.properties) with:

```xml
<context:property-placeholder location="properties.injection/app.properties"/>
```

The file contains `student.id=1` and `student.name=jill`. The `student1` bean sets `id` from `${student.id}` and `name` from `${student.name}`. The repository and service are wired with `p:*-ref`, the same shortcut used in the date example.

[`App`](src/main/java/properties/injection/App.java) gets `studentServiceImpl` and logs `Student(id=1, name=jill)`.

## XML used in these files

| Piece | Role |
| --- | --- |
| `<beans>` | Root of the Spring config file |
| `<bean>` | Creates one bean. `name` or `id` is the lookup key. `class` is the fully qualified class |
| `p:id`, `p:name`, `p:grade` | Sets a property from the `p` namespace. Needs `xmlns:p` |
| `<property name="..." value="..."/>` | Calls the setter with a literal |
| `<property name="..." ref="..."/>` | Calls the setter with another bean |
| `p:student-ref` | Same as `<property name="student" ref="..."/>` |
| `<constructor-arg index="0" ref="..."/>` | Passes a bean to the constructor argument at that index |
| `autowire="byType"` | Fills setters from the unique bean of each setter's type |
| `autowire="constructor"` | Fills the constructor from beans that match its argument types |
| `<context:property-placeholder>` | Replaces `${...}` with values from a properties file |

## Layout

```text
src/main/java
├── setter/injection                 Property and p: namespace injection
├── basic/constructor/injection      constructor-arg injection
├── autowire/by/type                 autowire="byType"
├── autowire/constructor             autowire="constructor"
├── date/injection                   SimpleDateFormat and CustomDateEditor
└── properties/injection             ${...} from app.properties
src/main/resources
├── setter.injection/setter-injection.xml
├── basic.constructor.injection/applicationContext.xml
├── autowire.by.type/application-context.xml
├── autowire.constructor/application-context.xml
├── date.injection/applicationContext.xml
├── properties.injection/applicationContext.xml
├── properties.injection/app.properties
└── log4j.properties
```

## References

* [Spring Framework 5.1 Reference: The IoC Container](https://docs.spring.io/spring-framework/docs/5.1.x/spring-framework-reference/core.html#beans)
