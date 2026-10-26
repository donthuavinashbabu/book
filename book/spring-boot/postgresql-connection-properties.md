# Spring Boot 3 PostgreSQL Dependencies
------
* Driver. The Spring Boot parent manages the version
```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```
* Spring data jpa dependency
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

# Spring boot application properties to connect to postgresql database
------
* Application and PostgreSQL are running on localhost
```
spring.datasource.url=jdbc:postgresql://${DATABASE_HOST:localhost}:${DATABASE_PORT:5432}/${DATABASE_NAME:db1}
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. Default to "create-drop" when using an embedded database, "none" otherwise
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
------
* Application running in a docker container and PostgreSQL running on localhost
```
spring.datasource.url=jdbc:postgresql://${DATABASE_HOST:host.docker.internal}:${DATABASE_PORT:5432}/${DATABASE_NAME:db1}
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. Default to "create-drop" when using an embedded database, "none" otherwise
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
------
* Application running locally. PostgreSQL running in a docker container
```
spring.datasource.url=jdbc:postgresql://localhost:5432/db1?sslmode=disable
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. Default to "create-drop" when using an embedded database, "none" otherwise
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
* Database and user creation scripts
```
CREATE DATABASE db_name;
CREATE USER user1 WITH PASSWORD 'Password03042026';
GRANT ALL PRIVILEGES ON DATABASE db_name TO user1;
\c db_name
GRANT ALL ON SCHEMA public TO user1;
```
