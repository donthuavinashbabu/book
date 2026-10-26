# Spring Boot 3 MongoDB Dependencies
------
* Spring Data MongoDB brings the MongoDB Java driver. The Spring Boot parent manages the version
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

# Spring boot application properties to connect to mongodb database
------
* Application and MongoDB are running on localhost
```
spring.data.mongodb.host=${DATABASE_HOST:localhost}
spring.data.mongodb.port=${DATABASE_PORT:27017}
spring.data.mongodb.database=${DATABASE_NAME:db1}
spring.data.mongodb.username=${DATABASE_USERNAME:root}
spring.data.mongodb.password=${DATABASE_PASSWORD:root}
spring.data.mongodb.authentication-database=admin
```
------
* Application running in a docker container and MongoDB running on localhost
```
spring.data.mongodb.host=${DATABASE_HOST:host.docker.internal}
spring.data.mongodb.port=${DATABASE_PORT:27017}
spring.data.mongodb.database=${DATABASE_NAME:db1}
spring.data.mongodb.username=${DATABASE_USERNAME:root}
spring.data.mongodb.password=${DATABASE_PASSWORD:root}
spring.data.mongodb.authentication-database=admin
```
------
* Application running locally. MongoDB running in a docker container
```
spring.data.mongodb.uri=mongodb://root:root@localhost:27017/db1?authSource=admin
```
* User creation script. Run this in `mongosh` against the `admin` database
```
use admin
db.createUser({
  user: "user1",
  pwd: "Password03042026",
  roles: [ { role: "readWrite", db: "db_name" } ]
})
```
