# Avro Serialization and Deserialization by Generating a Class

Compile [emp.avsc](src/main/resources/emp.avsc) into a Java class, then serialize and deserialize `Employee` records with the specific API.

Avro `1.9.2`. Java 8. Dependencies are in [pom.xml](pom.xml). [build.gradle](build.gradle) is a Gradle init stub and does not declare Avro.

## Schema and generated class

The schema is a record named `Employee` in namespace `emp.schema.classes`, with fields `name`, `id`, `salary`, `age`, and `address`.

The generated class is already in the tree: [Employee.java](src/main/java/emp/schema/classes/Employee.java).

Jar download steps are in [Avro Java Setup](../setup.md). Regenerate the class from this project folder:

```text
java -jar avro-tools-1.9.2.jar compile schema src/main/resources/emp.avsc src/main/java
```

## Serialize

[Serialization.java](src/main/java/serialization/practice/Serialization.java) is a JUnit 4 test. It builds three `Employee` records and writes them with `SpecificDatumWriter` and `DataFileWriter`.

The output path is hardcoded to `C:\practice-projects\others\avro\serialization-by-generating-class\files\Employee.avro`. Point it at this project's [files/Employee.avro](files/Employee.avro) before running.

## Deserialize

[DeserializationPractice.java](src/main/java/deserialization/practice/DeserializationPractice.java) reads that same file with `SpecificDatumReader` and `DataFileReader`, then logs each `Employee`. It uses the same hardcoded path. Run it after serialization has written the file.

## How this project was created

```text
mvn archetype:generate -DgroupId=serialization.by.generating.class -DartifactId=serialization-by-generating-class -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

```text
gradle init --type pom
```
