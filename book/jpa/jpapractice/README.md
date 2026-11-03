# JPA Practice

Maven project of JPA examples on EclipseLink and H2. [`pom.xml`](pom.xml) compiles with Java 8. It depends on `javax.persistence-api` 2.2, EclipseLink 2.5.1, H2 1.4.193, JUnit 4.12, and Lombok 1.18.28. There is no `src/test` tree. The examples are classes under `src/main/java`.

EclipseLink 2.5.1 implements JPA 2.1. The API jar on the classpath is JPA 2.2, and [`persistence.xml`](src/main/resources/META-INF/persistence.xml) declares persistence version `2.0`.

## Prerequisites

- JDK 8
- Maven 3.6+

`maven-compiler-plugin` 3.5.1 fails on JDK 21 because `jdk.compiler` does not open `com.sun.tools.javac.processing`. Use JDK 8.

## Run

`mvn test` does not run these examples. JUnit is a compile dependency, and the `@Test` methods live in `src/main/java`. Run a class from the IDE.

[`CRUDPractice`](src/main/java/com/jpa/practice/crud/CRUDPractice.java) has a `main` method. The other examples are `@Test` methods.

## Persistence units

Both units use `RESOURCE_LOCAL`, provider `org.eclipse.persistence.jpa.PersistenceProvider`, user `admin`, password `admin`, and `eclipselink.ddl-generation` `create-tables`.

| Unit | Used by | H2 file URL |
| --- | --- | --- |
| `jpa-practice` | mappings, relationships, callbacks, listeners | `jdbc:h2:file:C:/github/java/java/jpa/jpapractice/src/main/resources/db;MV_STORE=FALSE` |
| `queries-practice` | JPQL and named queries | `jdbc:h2:file:E:/Backup/JavaPrep/practiceProjects/JPAProjects/JpaPractice/src/main/resources/queries-db/db;MV_STORE=FALSE` |

Those paths are outside this repository. The checked-in files are [`db.h2.db`](src/main/resources/db.h2.db) and [`queries-db/db.h2.db`](src/main/resources/queries-db/db.h2.db).

`javax.persisitence.database-product-name` is misspelled, so that property is ignored. `hibernate.format_sql` is set on the EclipseLink unit. The commented schema-generation block spells the drop script property `scrits`.

## Examples

### CRUD and types

[`CRUDPractice`](src/main/java/com/jpa/practice/crud/CRUDPractice.java) persists, reads, updates, and deletes a [`Book`](src/main/java/com/jpa/practice/datatype/mapping/Book.java) with id `1`. [`InsertPractice`](src/main/java/com/jpa/practice/crud/InsertPractice.java), [`SelectPractice`](src/main/java/com/jpa/practice/crud/SelectPractice.java), and [`DeletePractice`](src/main/java/com/jpa/practice/crud/DeletePractice.java) split those operations.

[`DateMapping`](src/main/java/com/jpa/practice/datatype/mapping/DateMapping.java) and [`EnumMapping`](src/main/java/com/jpa/practice/datatype/mapping/EnumMapping.java) map dates and [`DaysEnum`](src/main/java/com/jpa/practice/datatype/mapping/DaysEnum.java). [Boolean columns](src/main/java/booleancolumn/mapping/boolean-columns.md) stores a boolean as `1` or `0` on [`Certificate`](src/main/java/booleancolumn/mapping/Certificate.java).

### Relationships

Each `App` persists a musician and a CD, then selects the CDs. The unit is `jpa-practice`.

| Relationship | Notes | Classes |
| --- | --- | --- |
| One-to-one `@JoinColumn` | [about](src/main/java/one/to/one/join/column/about.md) | [`Musician1`](src/main/java/one/to/one/join/column/Musician1.java), [`CD1`](src/main/java/one/to/one/join/column/CD1.java) |
| One-to-one `@JoinTable` | [about](src/main/java/one/to/one/join/table/about.md) | [`Musician2`](src/main/java/one/to/one/join/table/Musician2.java), [`CD2`](src/main/java/one/to/one/join/table/CD2.java) |
| One-to-many `@JoinColumn` | [about](src/main/java/one/to/many/join/column/about.md) | [`Musician3`](src/main/java/one/to/many/join/column/Musician3.java), [`CD3`](src/main/java/one/to/many/join/column/CD3.java) |
| One-to-many `@JoinTable` | [about](src/main/java/one/to/many/join/table/about.txt) | [`Musician4`](src/main/java/one/to/many/join/table/Musician4.java), [`CD4`](src/main/java/one/to/many/join/table/CD4.java) |
| Many-to-one `@JoinColumn` | [about](src/main/java/many/to/one/join/column/about.md) | [`Musician5`](src/main/java/many/to/one/join/column/Musician5.java), [`CD5`](src/main/java/many/to/one/join/column/CD5.java) |
| Many-to-one `@JoinTable` | [about](src/main/java/many/to/one/join/table/about.md) | [`Musician6`](src/main/java/many/to/one/join/table/Musician6.java), [`CD6`](src/main/java/many/to/one/join/table/CD6.java) |
| Many-to-many `@JoinTable` | [README](src/main/java/many/to/many/join/table/README.md) | [`Student3`](src/main/java/many/to/many/join/table/Student3.java), [`Course3`](src/main/java/many/to/many/join/table/Course3.java) |

### Callbacks and listeners

[Callback annotations](src/main/java/com/jpa/practice/callback/annotations/callback-annotation.md) on [`Student`](src/main/java/com/jpa/practice/callback/annotations/Student.java). [`Student2`](src/main/java/com/jpa/practice/listeners/Student2.java) uses [`AgeCalculateListener`](src/main/java/com/jpa/practice/listeners/AgeCalculateListener.java) and [`ValidationListener`](src/main/java/com/jpa/practice/listeners/ValidationListener.java).

### Queries

[`JPQLPractice`](src/main/java/com/jpa/queries/practice/JPQLPractice.java) and [`NamedQueries`](src/main/java/com/jpa/queries/practice/NamedQueries.java) use the `queries-practice` unit and the employee model: [`Employee`](src/main/java/com/jpa/queries/practice/model/Employee.java), [`Department`](src/main/java/com/jpa/queries/practice/model/Department.java), [`SalaryGrade`](src/main/java/com/jpa/queries/practice/model/SalaryGrade.java). Schema and seed SQL are [`schema.sql`](src/main/java/com/jpa/queries/practice/model/schema.sql) and [`data.sql`](src/main/java/com/jpa/queries/practice/model/data.sql). [`scripts/scripts.sql`](scripts/scripts.sql) is empty.

## Layout

```text
pom.xml
scripts/scripts.sql
src/main/resources/META-INF/persistence.xml
src/main/resources/db.h2.db
src/main/resources/queries-db/db.h2.db
src/main/java/com/jpa/practice/crud
src/main/java/com/jpa/practice/datatype/mapping
src/main/java/com/jpa/practice/callback/annotations
src/main/java/com/jpa/practice/listeners
src/main/java/com/jpa/queries/practice
src/main/java/booleancolumn/mapping
src/main/java/one/to/one
src/main/java/one/to/many
src/main/java/many/to/one
src/main/java/many/to/many
```
