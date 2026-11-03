# OpenCSV

Maven project of JUnit 4 examples for OpenCSV `5.9`. The tests read [`students.csv`](src/main/resources/students.csv) and write new CSV files. [`pom.xml`](pom.xml) sets the compiler `release` to 17. It depends on `opencsv` 5.9, JUnit 4.12, Lombok 1.18.6, and SLF4J with Log4j 1.2. Console logging is in [`log4j.properties`](src/main/resources/log4j.properties).

## Prerequisites

- JDK 17
- Maven 3.6+

Lombok `1.18.6` does not support JDK 17 or 21. The compiler `release` is 17, so the build needs a JDK that can compile that release and a Lombok version that matches it. Use JDK 17 with a newer Lombok, or lower the `release` to a JDK this Lombok release supports.

JUnit is a compile dependency, not a test dependency.

## Dependencies

```xml
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.9</version>
</dependency>
```

## Run

```bash
mvn test
```

Run one example:

```bash
mvn -Dtest=ReadCSV#readCSV test
mvn -Dtest=ReadCSV#readCSV2 test
mvn -Dtest=ReadCSV#readCSVFileToListOfObjects test
mvn -Dtest=WriteCSV#writeLineByLine test
mvn -Dtest=WriteCSV#writeAllLines test
mvn -Dtest=WriteCSV#beanToCSV test
```

[`App`](src/main/java/open/csv/App.java) is the Maven quickstart class. It prints `Hello World!`.

## Sample file

[`students.csv`](src/main/resources/students.csv) has a header and four rows. Values are separated by a comma and a space.

| id | name | course |
| --- | --- | --- |
| 1 | jim | java |
| 2 | jill | spring |
| 3 | jack | spring boot |
| 4 | jane | scala |

## Read

[`ReadCSV`](src/test/java/com/open/csv/ReadCSV.java) loads the file from the classpath.

`readCSV` reads every row, including the header, as `String[]`.

`readCSV2` uses `CSVReaderBuilder`. It skips one line and parses with a comma separator and quotations ignored.

`readCSVFileToListOfObjects` maps columns by position to [`Student`](src/main/java/open/csv/model/Student.java) fields `id`, `name`, and `course`. It does not skip the header, so the first value `id` is parsed as an `Integer`.

## Write

[`WriteCSV`](src/test/java/com/open/csv/WriteCSV.java) writes `csv/students-<uuid>.csv` under the working directory. `createNewFile` does not create the `csv` directory, so the write fails until that directory exists.

`writeLineByLine` calls `writeNext` for the header and three rows: `1,jim,java`, `2,jill,csv`, `3,john,cloud`. An `IOException` is rethrown as `RuntimeException`.

`writeAllLines` writes those same four rows with `writeAll`.

`beanToCSV` writes three `Student` objects with `StatefulBeanToCsv`. The quote character is `'`. The students are `1,jill,java`, `2,jim,csv`, and `3,john,cloud`.

## Layout

```text
pom.xml
src/main/java/open/csv/App.java
src/main/java/open/csv/model/Student.java
src/main/resources/log4j.properties
src/main/resources/students.csv
src/test/java/com/open/csv/ReadCSV.java
src/test/java/com/open/csv/WriteCSV.java
```
