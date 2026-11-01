# Apache POI

Maven project that reads and writes `.xlsx` workbooks with Apache POI 5.2.2. The examples are JUnit 5 tests in [`ExcelPractice`](src/test/java/com/excel/ExcelPractice.java). Run Maven from this directory so the `files/` paths resolve.

[`pom.xml`](pom.xml) compiles with Java 17. It depends on `poi` and `poi-ooxml` 5.2.2, `commons-lang3`, Lombok, JUnit 5, and SLF4J with Log4j 1.2. Console logging is in [`log4j.properties`](src/main/resources/log4j.properties).

## Prerequisites

- JDK 17
- Maven 3.6+

[`pom.xml`](pom.xml) sets the compiler `release` to 17. Lombok `1.18.24` fails to compile on JDK 21 with `NoSuchFieldError` on `JCTree$JCImport`. Use JDK 17 for `mvn test`.

## Dependencies

`poi-ooxml` provides `XSSFWorkbook`, which this project uses for `.xlsx` files.

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.2.2</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.2</version>
</dependency>
```

## Run

```bash
mvn test
```

Run one example:

```bash
mvn -Dtest=ExcelPractice#writeExcel test
mvn -Dtest=ExcelPractice#readExcel test
mvn -Dtest=ExcelPractice#readExcelInGeneric test
```

[`App`](src/main/java/com/apache/poi/App.java) is the Maven quickstart class. It prints `Hello World!`.

```bash
mvn -q compile
java -cp target/classes com.apache.poi.App
```

## Workbooks

Sample files are in [`files/`](files). Each sheet has a header row, then five data rows.

### [employees.xlsx](files/employees.xlsx)

Columns: `id`, `name`, `designation`. `id` is stored as a number.

| Sheet | Rows |
| --- | --- |
| `employeeDetails` | 1 jim dev, 2 jill dev, 3 jack qa, 4 john qa, 5 jane manager |
| `employeeDetails2` | 6 jim dev, 7 jill dev, 8 jack qa, 9 john qa, 10 jane manager |

### [students.xlsx](files/students.xlsx)

Columns: `id`, `name`, `course`. `id` is stored as a number.

| Sheet | Rows |
| --- | --- |
| `studentDetails` | 1 ana java, 2 jimmy sql, 3 elsa spring, 4 queen aws, 5 king cloud |
| `studentDetails2` | 6 ana java, 7 jimmy sql, 8 elsa spring, 9 queen aws, 10 king cloud |

### [file-1.xlsx](files/file-1.xlsx)

Written by `writeExcel`. One sheet, `students`. Every cell is a string.

| id | name | course |
| --- | --- | --- |
| 1 | Jim | Java |
| 2 | Jill | J2EE |
| 3 | Jack | Spring |
| 4 | John | SQL |

`writeExcel` overwrites this file. `readExcel` reads it back.

## Examples

### Write a workbook

[`writeExcel`](src/test/java/com/excel/ExcelPractice.java) builds a workbook in memory and saves it to `files/file-1.xlsx`.

1. Create an `XSSFWorkbook`.
2. Create a sheet named `students`.
3. Create a row for each entry in a `TreeMap`. Keys `"1"` through `"5"` keep header and data in that order.
4. Add a cell for each value. `String` and `Integer` values are written.
5. Write the workbook with `FileOutputStream`.

### Read every sheet

[`readExcel`](src/test/java/com/excel/ExcelPractice.java) opens `files/file-1.xlsx` and walks each sheet, row, and cell. It logs `NUMERIC` and `STRING` cell values.

### Read a sheet into objects

[`readExcelInGeneric`](src/test/java/com/excel/ExcelPractice.java) maps one named sheet onto a class.

* The property list is the column order. `id`, `name`, `designation` matches the employee sheets. `id`, `name`, `course` matches the student sheets.
* The first row is a header and is skipped.
* Each data row becomes an object from the no-arg constructor.
* Column `i` is passed to a `String` setter. Property `id` calls `setId`. `StringUtils.capitalize` builds the setter name.
* The return value is `Map<sheetName, List<T>>`.

[`Employee`](src/main/java/com/excel/model/Employee.java) has `id`, `name`, and `designation`. [`Student`](src/main/java/com/excel/model/Student.java) has `id`, `name`, and `course`. Lombok `@Data` and `@NoArgsConstructor` supply the setters and the no-arg constructor.

`readCellData` reads `NUMERIC` and `STRING` cells. A numeric cell is stored with `String.valueOf`, so the number `1` in `employees.xlsx` and `students.xlsx` becomes the string `1.0`. Ids in `file-1.xlsx` stay `1` because that file stores them as strings.

A blank cell is null. `readCellData` calls `getCellType()` on that cell, so a blank column fails the row.

## Layout

```text
pom.xml
files/employees.xlsx
files/students.xlsx
files/file-1.xlsx
src/main/java/com/apache/poi/App.java
src/main/java/com/excel/model/Employee.java
src/main/java/com/excel/model/Student.java
src/main/resources/log4j.properties
src/test/java/com/excel/ExcelPractice.java
```
