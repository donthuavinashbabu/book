# Serialization and Deserialization Using Parsers

Read [emp.avsc](src/main/resources/emp.avsc) at runtime with `Schema.Parser` and use `GenericRecord`. No class is generated from the schema.

Avro `1.9.2`. Java 8. Dependencies are in [pom.xml](pom.xml). [build.gradle](build.gradle) does not declare Avro.

The schema matches the generated-class example: record `Employee` in namespace `emp.schema.classes`, fields `name`, `id`, `salary`, `age`, and `address`.

## Serialize

[SerializePractice.java](src/main/java/serialization/using/parsers/SerializePractice.java) is a JUnit 4 test. It parses the schema, fills three `GenericData.Record` values, and writes them with `GenericDatumWriter` and `DataFileWriter`.

Both paths are hardcoded under `C:\practice-projects\others\avro\serialization-using-parsers\`. Point the schema path at [emp.avsc](src/main/resources/emp.avsc) and the output path at [files/emp.txt](files/emp.txt). That output file is Avro binary, not plain text.

## Deserialize

[DeSerializePractice.java](src/main/java/deserialization/using/parsers/DeSerializePractice.java) parses the same schema and reads `emp.txt` with `GenericDatumReader` and `DataFileReader`. Run it after serialization.

## How this project was created

```text
mvn archetype:generate -DgroupId=serialization.using.parsers -DartifactId=serialization-using-parsers -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

```text
gradle init --type pom
```
