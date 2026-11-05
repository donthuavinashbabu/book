# Groovy

Maven multi-module project of JUnit 4 Groovy examples. The parent [`pom.xml`](pom.xml) is `com.groovy:groovy:1.0`. It depends on JUnit 4.12 and `groovy-all` 3.0.0-beta-1 (`type` `pom`). The compiler source and target are Java 1.8. Maven compiles Java sources only. The `.groovy` tests need a Groovy compiler plugin before `mvn test` can run them.

## Modules

| Module | What it covers |
| --- | --- |
| [basics](basics) | Variables, closures, enums, and loops |
| [collections](collections) | Ranges, plus empty array and list stubs |
| [date-time](date-time) | `java.util.Date` |
| [file-io](file-io) | Read a file and list directory entries |
| [strings](strings) | String declarations and GStrings |
| [xml](xml) | Read XML with `XmlSlurper` and write XML with `StreamingMarkupBuilder` |

[`grape`](grape/materials.md) is notes only. It is not a Maven module. The link is the [Grape documentation](http://docs.groovy-lang.org/latest/html/documentation/grape.html).

## Basics

### Variables

[`VariablesPractice`](basics/src/test/java/com/variables/VariablesPractice.groovy)

`declareVariable` assigns `3 + 5` with `def` and prints `sum=8`.

`stringVariable` assigns `"john"` with `def` and prints `name=john`.

`printClassOfVariable` prints the runtime class of an integer `10` and a string `"jill"`.

### Closures

[`ClosuresPractice`](basics/src/test/java/com/closures/ClosuresPractice.groovy)

`declareClosure` stores a closure that prints `welcome to closures` and `new Date()`, then calls it.

`forEachWithClosure` calls that same style of closure once for each value in `1..5`. The closure ignores the current number.

`eachWithClosure` walks `1..5`. For each number it calls an outer closure, which prints `outside closure - welcome to closures` plus the implicit `it`, then prints `inside closure - welcome to closures` plus the same `it`.

`findAll` keeps even numbers from `1..10` and prints each one as `number: <n>`.

`closureWithVariable` names the `each` parameter `number` and prints `number=<n>` for `1..5`.

### Enums

[`Days`](basics/src/main/java/com/enums/Days.groovy) is `MON`, `TUES`, `WED`, `THUR`, `FRI`, `SAT`, `SUN`.

[`EnumPractice`](basics/src/test/java/com/enums/EnumPractice.groovy) `printEnum` prints every constant from `Days.values()`.

### Loops

[`ForLoop`](basics/src/test/java/com/loops/ForLoop.groovy) uses the list `jack`, `jill`, `jim`, `tim`, `tony`.

`forLoop` prints `hello, <name>` with an index `for`.

`forEach` prints the same line with `for (name in names)`.

`forEachWithClosure` prints the line twice: once with the implicit `it`, then once with a named `name` parameter.

`eachWithClosure` prints the line with `each` and `it`.

`eachWithCloseWithCustomVariable` prints the line with `each` and a named `name` parameter.

[`WhileLoop`](basics/src/test/java/com/loops/WhileLoop.groovy) and [`DoWhile`](basics/src/test/java/com/loops/DoWhile.groovy) are empty classes.

## Collections

[`Days`](collections/src/main/java/com/util/Days.groovy) repeats the weekday enum used by the range tests: `MON` through `SUN`.

[`RangePractice`](collections/src/test/java/com/util/RangePractice.groovy)

`rangeOfNumbers` builds `1..10`, prints the range, `from` (`1`), and `to` (`10`), then prints each number. `from` and `to` are the range extents.

`rangeOfLetters` prints `'a'..'z'`.

`rangeOfEnums` prints `Days.MON..Days.FRI`.

`from` prints the first extent of that weekday range (`MON`). `to` prints the last (`FRI`).

`usingRangeInForLoop` prints `number: <n>` for `1..5`.

`rangeForEachWithClosure` prints the same line with `forEach` and `it`.

`rangeEachWithClosure` prints the number and `new Date()` for `1..5`, then sleeps one second on each value.

`rangeEachWithCloseWithCustomVariable` names the `each` parameter `i` and prints `number: <i>` for `1..5`.

[`ArrayPractice`](collections/src/test/java/com/array/ArrayPractice.groovy) `createArray` and [`ArrayListPractice`](collections/src/test/java/com/array/list/ArrayListPractice.groovy) `createArrayList` are empty tests.

## Date and time

[`DatePractice`](date-time/src/test/java/com/date/time/DatePractice.groovy) `todayDate` prints `new Date()` twice: once with string concatenation and once inside a GString.

## File I/O

[`students.txt`](file-io/src/main/resources/students.txt) contains `Welcome to groovy read file practice`. The tests read absolute paths from another machine.

[`ReadFile`](file-io/src/test/java/com/groovy/ReadFile.groovy)

`readAllContentOfFileFromAbsolutePath` reads `C:\Java-Prep\groovy\files\input-xml.txt` with `File.text` and prints the contents.

`exists` prints whether that same path exists, once in a GString and once with concatenation.

`displayFilesInADirectory` lists files in `E:\Backup\Java-Prep\groovy\projects\groovy\xml-io\src\test\java\com\read\xml` with `eachFile`. If the directory is missing it prints `path does not exist`.

`displayFilesInDirectoryAndSubDirectory` lists `E:\Backup\Java-Prep\groovy\projects\groovy\xml-io\src\test\java` with `eachFileRecurse`.

## Strings

[`StringsPractice`](strings/src/test/java/com/strings/StringsPractice.groovy)

`declareString` assigns `"jack"` with `def` and `"john"` with `String`, then prints `name1=` and `name2=`.

`gString` shows that `"name1=$name1"` interpolates and `'name2=$name2'` stays literal. The loop prints `hello, <name>` for `jack`, `jill`, `jim`, `tim`, and `tony` using `${names[i]}`, because the value is an expression.

## XML

[`students.xml`](xml/src/main/resources/students.xml) has three `student` nodes:

| firstName | lastName | gender | course | grade |
| --- | --- | --- | --- | --- |
| jack | a | male | Java | 3.5 |
| jill | b | female | Groovy | 3.6 |
| jim | c | male | Scala | 3.7 |

`lastName` and `gender` are attributes of the `other` element.

### Read

[`XmlSlurperPractice`](xml/src/test/java/com/read/xml/XmlSlurperPractice.groovy) parses `C:\Java-Prep\groovy\projects\groovy\xml\src\main\resources\students.xml`. The class comment describes `XmlSlurper` as lazy: it loads a portion of the document just before it is read, which suits a subset of a large file.

`readNodes` prints whether the file exists, the document, every `firstName`, the first student's `firstName`, then `firstName : course: grade` for each student.

`readAttribute` reads attributes with `@`. For each student it prints `firstName`, `other.@lastName`, and `other.@gender`.

`iterateEachNodeWithClosure` prints `firstName`, last name, gender, course, and grade with `each` and `it`.

[`XMLParserPractice`](xml/src/test/java/com/read/xml/XMLParserPractice.groovy) `xmlParser` is empty. The comment describes `XMLParser` as a high-level API that loads the whole document into memory before reading it.

[`DomCategoryPractice`](xml/src/test/java/com/read/xml/DomCategoryPractice.groovy) `usingDomCategory` is empty. The comment describes `DOMCategory` as a low-level API for parsing XML.

### Write

[`MarkupBuilderPractice`](xml/src/test/java/com/create/xml/MarkupBuilderPractice.groovy) is an empty class. The comment says `MarkupBuilder` is for simple XML creation.

[`StreamingMarkupBuilderPractice`](xml/src/test/java/com/create/xml/StreamingMarkupBuilderPractice.groovy) is for larger documents and adds processing instructions and namespaces on top of `MarkupBuilder`.

`createXmlFile` builds an `employees` document for `1..5`. Each `employee` has an `mkp.comment("employee node")`, a `timestamp` attribute set to `new Date()`, a `serialNumber`, and a `name` of `name-<n>`. It writes the XML to `C:\Java-Prep\groovy\projects\groovy\xml\src\main\resources\employees.xml`.

[`employees.xml`](xml/src/main/resources/employees.xml) is a saved result of that write: five employees, serial numbers `1` through `5`, names `name-1` through `name-5`, and timestamps from `Mon Jul 08 14:36:48 IST 2019`.

## Layout

```text
pom.xml
basics/
collections/
date-time/
file-io/
grape/materials.md
strings/
xml/
```
