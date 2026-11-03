# Commons Compress

Maven project of JUnit 4 examples for Apache Commons Compress `1.19`. The tests build and expand tar and tar.gz archives. [`pom.xml`](pom.xml) compiles with Java 8. It depends on `commons-compress` 1.19, JUnit 4.12, Lombok 1.18.6, and SLF4J with Log4j 1.2. Console logging is in [`log4j.properties`](src/main/resources/log4j.properties).

[`build.gradle`](build.gradle) lists the same dependencies and sets `sourceCompatibility` to `1.8`. JUnit is on the `compile` configuration there.

## Prerequisites

- JDK 8
- Maven 3.6+

[`pom.xml`](pom.xml) sets the compiler source and target to `1.8`. `maven-compiler-plugin` 3.5.1 and Lombok `1.18.6` fail on JDK 21 with `ExceptionInInitializerError` because `jdk.compiler` does not open `com.sun.tools.javac.processing`. Use JDK 8 for `mvn test`.

The tests read and write absolute paths under `C:\Java-Prep\practice-projects\third-party-libraries\commons-compress\files\`. Those sample files are outside this repository. Put `1.txt`, `1.docx`, and `1.xlsx` in that folder before running a compress test, and create the archive before running the matching decompress test.

## Dependencies

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-compress</artifactId>
    <version>1.19</version>
</dependency>
```

## Run

```bash
mvn test
```

Run one example:

```bash
mvn -Dtest=com.tar.Compress#compressToTar test
mvn -Dtest=com.tar.UnCompress#decompress test
mvn -Dtest=com.tar.gz.Compress#compressToTar test
mvn -Dtest=com.tar.gz.UnCompress#decompress test
```

[`App`](src/main/java/commons/compress/App.java) is the Maven quickstart class. It prints `Hello World!`.

```bash
mvn -q compile
java -cp target/classes commons.compress.App
```

The tests catch `IOException` and log it. A missing path still leaves `mvn test` with exit code 0.

## Create

The module was created with the Maven quickstart archetype:

```bash
mvn archetype:generate -DgroupId=commons.compress -DartifactId=commons-compress -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

[`build.gradle`](build.gradle) was added from that pom:

```bash
gradle init --type pom
```

## Examples

### Tar

[`Compress`](src/test/java/com/tar/Compress.java) writes `files\tar-1.tar`.

It opens a `TarArchiveOutputStream` on that file and sets:

| Setting | Effect |
| --- | --- |
| `BIGNUMBER_STAR` | Entry sizes above the 8 GiB ustar limit |
| `LONGFILE_GNU` | Names longer than 100 bytes |
| `setAddPaxHeadersForNonAsciiNames(true)` | Pax headers for non-ASCII names |

The entries are `1.txt`, `1.docx`, and `1.xlsx`. Each archive name is `.\` plus the file name. `addFilesToTar` copies a file with `IOUtils.copy`. For a directory it walks the children and keeps the same name prefix, so a nested file is stored under its own name.

Each archive entry stays open, and `TarArchiveOutputStream` stays open. The file on disk has no end-of-archive marker until that stream is closed.

[`UnCompress`](src/test/java/com/tar/UnCompress.java) reads `files\tar-1.tar` and writes files under `files\tar-1-decompress`. Directory entries are skipped. Each file's parent is created with `mkdir()`, which creates one directory level. The `FileOutputStream` for each extracted file stays open.

### Tar.gz

[`Compress`](src/test/java/com/tar/gz/Compress.java) writes `files\tar-1.tar.gz`. The tar stream is wrapped in `GzipCompressorOutputStream`. Entry selection, names, and the open archive stream match the tar example. The gzip stream stays open as well, so the gzip trailer is missing.

[`UnCompress`](src/test/java/com/tar/gz/UnCompress.java) opens `files\tar-1.tar.gz` with `GzipCompressorInputStream` (`decompressConcatenated` is true) and copies the inflated bytes to `tar-1.tar` in the working directory. The following `TarArchiveInputStream` reads that same gzip stream after the copy has reached the end, so the loop writes nothing into `files\tar-1-decompress`. The class comment links `com.tar.Compress#compressToTar`. The log message on failure is `Exception while unzipping`.

## Layout

```text
pom.xml
build.gradle
src/main/java/commons/compress/App.java
src/main/resources/log4j.properties
src/test/java/com/tar/Compress.java
src/test/java/com/tar/UnCompress.java
src/test/java/com/tar/gz/Compress.java
src/test/java/com/tar/gz/UnCompress.java
```
