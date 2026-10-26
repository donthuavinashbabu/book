* Dependencies
```
<!-- Spring boot data jpa -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- H2 dependency -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
```

* H2 console
```
spring.h2.console.enabled=true
spring.h2.console.path=/h2
```

* primary data source
* `application.properties` points the database at an absolute path on `E:`. Point it at the file in this module before starting. H2 adds the `.h2.db` suffix when `MV_STORE=FALSE`, so the URL has no suffix
```
spring.datasource.url=jdbc:h2:file:E:/myPath/src/main/resources/springDataJpa;MV_STORE=FALSE
spring.datasource.username=springDataJpa
spring.datasource.password=springDataJpa
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# JPA properties
spring.jpa.show-sql=true
#This is actually a shortcut for the "hibernate.hbm2ddl.auto" property. 
#Default to "create-drop" when using an embedded database, "none" otherwise.
spring.jpa.hibernate.ddl-auto=none
```