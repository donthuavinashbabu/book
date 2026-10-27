# Spring Batch Hello World

Spring Boot application that reads `users.csv`, replaces each department code with a name, and saves the rows with JPA. The job does not start on its own. A GET request launches it.

Spring Batch job metadata and the user rows share an embedded H2 database.

## Prerequisites

- Java 8
- Maven 3.6+

## Run

```bash
mvn spring-boot:run
```

The HTTP server listens on port `9000`. `spring.batch.job.enabled` is `false`, so startup only creates the job beans, the Spring Batch tables, and the `USER_ENTITY` table.

## Launch the job

`GET /` starts `csvReaderJob` with a `time` parameter set to the current time, so each request is a new job instance.

```bash
curl http://localhost:9000/
```

The response is the `BatchStatus` after the job finishes, usually `COMPLETED`.

[`users.csv`](src/main/resources/users.csv) has a header and four data rows. The reader skips the header. The step chunk size is 100, so the writer runs once for the whole file.

The writer logs the chunk, then saves it:

```text
users=[UserEntity(id=1, name=jack, dept=Engineering, salary=2001), ...]
```

## Job flow

1. [`AppController`](src/main/java/com/cerebro/controller/AppController.java) builds job parameters and calls `JobLauncher.run`.
2. [`AppConfig`](src/main/java/com/cerebro/config/AppConfig.java) defines one step, `csvFileLoad`, on the job `csvReaderJob`. The step uses a chunk of 100.
3. A `FlatFileItemReader` opens `app.input`, which points at `users.csv`. It skips the first line and maps `id`, `name`, `dept`, and `salary` onto [`UserEntity`](src/main/java/com/cerebro/model/UserEntity.java).
4. [`CSVProcessor`](src/main/java/com/cerebro/batch/CSVProcessor.java) replaces the department code in `dept` with a name.

| Code | Name |
| --- | --- |
| `101` | Engineering |
| `102` | R&D |
| `103` | QA |
| `104` | Devops |

5. [`UserWriter`](src/main/java/com/cerebro/batch/UserWriter.java) logs the chunk and calls `UserRepository.saveAll`.

After one run, `USER_ENTITY` contains:

| id | name | dept | salary |
| --- | --- | --- | --- |
| 1 | jack | Engineering | 2001 |
| 2 | john | R&D | 2002 |
| 3 | jim | QA | 2003 |
| 4 | jill | Devops | 2004 |

## Batch metadata and saved users

H2 holds both the Spring Batch tables and `USER_ENTITY`. Spring Boot creates the in-memory database on startup.

| Setting | Value |
| --- | --- |
| JDBC URL | `jdbc:h2:mem:testdb` |
| User | `sa` |
| Password | empty |

Queries for the job, step, and user tables are in [`sqls/selects.sql`](sqls/selects.sql). The console login below uses that same database. `application.yml` does not set `spring.h2.console.enabled`, so `/h2-console` stays off unless that property is turned on.

![picture](imgs/h2-console.jpg)

The database is in memory. Tables disappear when the application stops.

## Configuration

Values in [`src/main/resources/application.yml`](src/main/resources/application.yml):

| Property | Value | Purpose |
| --- | --- | --- |
| `server.port` | `9000` | HTTP port |
| `spring.batch.job.enabled` | `false` | Do not run the job at startup |
| `app.input` | `file:src/main/resources/users.csv` | CSV read by `FlatFileItemReader` |
| `logging.file` | `logs/app.log` | Log file |

## Layout

```text
src/main/java/com/cerebro
├── App.java                         Application entry point
├── config/AppConfig.java            Job, step, and CSV reader
├── batch/CSVProcessor.java          Maps department codes to names
├── batch/UserWriter.java            Saves each chunk with JPA
├── controller/AppController.java    GET / launches the job
├── model/UserEntity.java            CSV row and USER_ENTITY
└── repository/UserRepository.java   JpaRepository for UserEntity
```

## References

* [Spring Batch in Spring Boot: CSV to Database](https://www.youtube.com/watch?v=1XEX-u12i0A)
