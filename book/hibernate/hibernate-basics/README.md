# Hibernate Basics

Maven project of JUnit 4 examples for Hibernate `4.3.6.Final`. The tests create, read, update, and delete a `Student` row. [`pom.xml`](pom.xml) compiles with Java 8. It depends on `hibernate-core` 4.3.6.Final, H2 1.4.193, MySQL Connector/J 8.0.27, JUnit 4.12, Lombok 1.18.6, and SLF4J with Log4j 1.2. Console logging is in [`log4j.properties`](src/main/resources/log4j.properties).

The same pom also declares `hibernate` 3.2.3.ga, `hibernate-annotations` 3.5.6-Final, and `javassist` 3.12.1.GA. Those artifacts predate Hibernate 4 and can clash with `hibernate-core` 4.3.6.Final on the classpath.

## Prerequisites

- JDK 8
- Maven 3.6+
- MySQL on `localhost:3306`, database `my_sql_practice`, user `admin`, password `admin`

[`pom.xml`](pom.xml) sets the compiler source and target to `1.8`. `maven-compiler-plugin` 3.5.1 and Lombok `1.18.6` fail on JDK 21 with `ExceptionInInitializerError` because `jdk.compiler` does not open `com.sun.tools.javac.processing`. Use JDK 8 for `mvn test`.

## Dependencies

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>4.3.6.Final</version>
</dependency>
```

## Configuration

[`hibernate.cfg.xml`](src/main/resources/hibernate.cfg.xml) uses the MySQL session factory. Dialect `MySQL5Dialect`, driver `com.mysql.cj.jdbc.Driver`, `hibernate.show_sql` true, and `hibernate.hbm2ddl.auto` `update`. The mapped class is [`Student`](src/main/java/com/hibernate/basics/model/Student.java).

An H2 session factory is commented out in the same file. Its URL points at `C:/practice-projects/hibernate/hibernate-basics/h2/hibernate-db` with `MV_STORE=FALSE`. The file [`h2/hibernate-db.h2.db`](h2/hibernate-db.h2.db) is that style of H2 database.

## Run

```bash
mvn test
```

Run one example:

```bash
mvn -Dtest=Create#save test
mvn -Dtest=Create#persist test
mvn -Dtest=Create#saveOrUpdate test
mvn -Dtest=Read#findAll test
mvn -Dtest=Read#get test
mvn -Dtest=Update#update test
mvn -Dtest=Delete#delete test
```

`get` and `delete` load id `1`. `update` loads id `2`. Those rows have to exist already. Each test builds a `SessionFactory` with the deprecated `Configuration.buildSessionFactory()` and leaves that factory open.

## Create

The module was created with the Maven quickstart archetype:

```bash
mvn archetype:generate -DgroupId=com.hibernate.basics -DartifactId=hibernate-basics -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Student

[`Student`](src/main/java/com/hibernate/basics/model/Student.java) maps to table `STUDENT`.

| Field | Column | Mapping |
| --- | --- | --- |
| `id` | `ID` | `GenerationType.IDENTITY` |
| `name` | `NAME` | string |
| `courseName` | `COURSE_NAME` | [`CourseEnum`](src/main/java/com/hibernate/basics/model/CourseEnum.java) stored with `EnumType.STRING` (`JAVA`, `HIBERNATE`, `SPRING`) |
| `courseRank` | `course_rank` | [`CourseRankEnum`](src/main/java/com/hibernate/basics/model/CourseRankEnum.java) stored with `EnumType.ORDINAL` (`ONE` is 0, `TWO` is 1, `THREE` is 2) |
| `courseStartDate` | `COURSE_START_DATE` | `java.util.Date` as `TemporalType.TIMESTAMP` |

## Examples

[`Create`](src/test/java/com/hibernate/basics/Create.java) inserts a student named `jack`.

| Test | Call | Values |
| --- | --- | --- |
| `save` | `session.save` | course `JAVA`, rank `ONE`. Logs the generated id |
| `persist` | `session.persist` | course `HIBERNATE`, rank `TWO` |
| `saveOrUpdate` | `save`, then `saveOrUpdate` | saved as `jack`, then the name is set to `jane` and saved again |

[`Read`](src/test/java/com/hibernate/basics/Read.java) loads rows. `findAll` uses the deprecated `Criteria` API. `get` loads id `1` and logs null when that id is missing.

[`Update`](src/test/java/com/hibernate/basics/Update.java) loads id `2`, sets `courseName` to `HIBERNATE`, and calls `session.update`.

[`Delete`](src/test/java/com/hibernate/basics/Delete.java) loads id `1` and calls `session.delete`.

## SQL

Scripts are in [`db-mysql`](db-mysql). They use database `my_sql_practice`.

[`create.sql`](db-mysql/create.sql) creates `student` with `id`, `name`, `course_name`, and `course_start_date`. It has no `course_rank` column. With `hbm2ddl.auto` set to `update`, Hibernate adds the missing column on startup.

[`drop.sql`](db-mysql/drop.sql) drops `student`. [`select.sql`](db-mysql/select.sql) reads every row.

## References

* [Persisting enums in JPA](https://www.baeldung.com/jpa-persisting-enums-in-jpa)

## Layout

```text
pom.xml
db-mysql/create.sql
db-mysql/drop.sql
db-mysql/select.sql
h2/hibernate-db.h2.db
src/main/java/com/hibernate/basics/model/CourseEnum.java
src/main/java/com/hibernate/basics/model/CourseRankEnum.java
src/main/java/com/hibernate/basics/model/Student.java
src/main/resources/hibernate.cfg.xml
src/main/resources/log4j.properties
src/test/java/com/hibernate/basics/Create.java
src/test/java/com/hibernate/basics/Read.java
src/test/java/com/hibernate/basics/Update.java
src/test/java/com/hibernate/basics/Delete.java
```
