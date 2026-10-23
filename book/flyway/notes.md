Using Flyway Maven Plugin - Refer [maven-plugin](content/maven-plugin/README.md)
Spring Boot Flyway Integration - Refer [spring-boot-flyway](content/spring-boot-flyway/README.md)

---
What is Flyway?
Flyway is a database migration tool that helps version, track, and apply schema changes. It integrates smoothly with Spring Boot, meaning it can automatically detect and run migration scripts when your application starts.
---
Flyway commands:
* migrate
* clean
* info
* validate
* baseline
* repair
---
In Spring boot projects, Flyway migration files are typically stored in
```
src/main/resources/db/migration/
```

Each migration file must follow a naming convention like:
```
V1__init_schema.sql
V2__add_user_table.sql
```
