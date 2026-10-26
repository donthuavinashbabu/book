# Spring Boot 3 Oracle Dependencies
------
* Driver for Java 17, which Spring Boot 3 requires. The Spring Boot parent manages the version
```
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc11</artifactId>
</dependency>
```
* Spring data jpa dependency
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

# Spring boot application properties to connect to oracle database
------
* Application and Oracle are running on localhost. URL uses a service name
```
spring.datasource.url=jdbc:oracle:thin:@//${DATABASE_HOST:localhost}:${DATABASE_PORT:1521}/${DATABASE_NAME:db1}
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. Default to "create-drop" when using an embedded database, "none" otherwise
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
* SID form, when the database is identified by SID instead of service name
```
spring.datasource.url=jdbc:oracle:thin:@${DATABASE_HOST:localhost}:${DATABASE_PORT:1521}:${DATABASE_NAME:db1}
```
------
* Application running in a docker container and Oracle running on localhost
```
spring.datasource.url=jdbc:oracle:thin:@//${DATABASE_HOST:host.docker.internal}:${DATABASE_PORT:1521}/${DATABASE_NAME:db1}
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. Default to "create-drop" when using an embedded database, "none" otherwise
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
------
* Application running locally. Oracle running in a docker container
```
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/db1
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:root}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.show-sql=${JPA_SHOW_SQL:true}
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. Default to "create-drop" when using an embedded database, "none" otherwise
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.properties.hibernate.format_sql=${HIBERNATE_FORMAT_SQL:true}
```
* User creation scripts. In Oracle the user is the schema
```
CREATE USER user1 IDENTIFIED BY Password03042026;
GRANT CONNECT, RESOURCE TO user1;
ALTER USER user1 QUOTA UNLIMITED ON USERS;
```
