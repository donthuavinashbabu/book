# Avro Maven Plugin Practice

`avro-maven-plugin` `1.8.2` generates a Java class from [user.avsc](src/main/resources/avro/user.avsc) during `generate-sources`. The record is `User` in namespace `com.app.avro.model`, with fields `name` (string) and `age` (int).

The generated class is already in the tree: [User.java](src/main/java/com/app/avro/model/User.java). The plugin writes it there because [pom.xml](pom.xml) sets the output directory to source:

```xml
<sourceDirectory>${project.basedir}/src/main/resources/avro</sourceDirectory>
<outputDirectory>${project.basedir}/src/main/java/</outputDirectory>
<stringType>String</stringType>
```

`mvn compile` runs the `schema` goal. `mvn clean` does not delete `User.java`, because that file lives under `src/main/java`, not `target`.

[App.java](src/main/java/avro/maven/plugin/practice/App.java) is the Maven archetype main class. It prints `Hello World!` and does not use `User`.

The plugin is configured only in [pom.xml](pom.xml). [build.gradle](build.gradle) is a Gradle init file for `com.date.api.practice` and does not generate the class.

## How this project was created

```text
mvn archetype:generate -DgroupId=avro.maven.plugin.practice -DartifactId=avro-maven-plugin-practice -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

```text
gradle init --type pom
```
