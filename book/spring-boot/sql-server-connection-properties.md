# Spring Boot 3 SQL Server Dependencies
------
* Driver. The Spring Boot parent manages the version
```
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
</dependency>
```
* Spring data jpa dependency
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

# Spring boot application properties to connect to sql server database
------
* Application and SQL Server are running on localhost
```
spring.datasource.url=jdbc:sqlserver://${DATABASE_HOST:localhost}:${DATABASE_PORT:1433};databaseName=${DATABASE_NAME:db1};encrypt=true;trustServerCertificate=true
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. Default to "create-drop" when using an embedded database, "none" otherwise
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
------
* Application running in a docker container and SQL Server running on localhost
```
spring.datasource.url=jdbc:sqlserver://${DATABASE_HOST:host.docker.internal}:${DATABASE_PORT:1433};databaseName=${DATABASE_NAME:db1};encrypt=true;trustServerCertificate=true
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. Default to "create-drop" when using an embedded database, "none" otherwise
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
------
* Application running locally. SQL Server running in a docker container
```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=db1;encrypt=true;trustServerCertificate=true
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. Default to "create-drop" when using an embedded database, "none" otherwise
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
* Database and user creation scripts
```
CREATE DATABASE db_name;
GO
CREATE LOGIN user1 WITH PASSWORD = 'Password03042026';
GO
USE db_name;
GO
CREATE USER user1 FOR LOGIN user1;
GO
ALTER ROLE db_owner ADD MEMBER user1;
GO
```
