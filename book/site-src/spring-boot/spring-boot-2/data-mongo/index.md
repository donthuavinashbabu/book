# Materials
* Udemy - MongoDB with Java Spring Boot & Spring Framework
* https://www.baeldung.com/spring-data-mongodb-tutorial
* Spring Data Mongo: Getting Started
------
# Project setup
* Project - [spring-data-mongo](spring-data-mongo)
* Add following dependency in [pom.xml](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/pom.xml.md)
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```
* Add below properties in [application.yml](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/src/main/resources/application.yml.md)
```
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: database1
```
* Write repository interface extends `org.springframework.data.mongodb.repository.MongoRepository`. Refer [StudentRepository](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/src/main/java/com/app/repository/StudentRepository.java.md)
* [StudentRepository](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/src/main/java/com/app/repository/StudentRepository.java.md)
* [StudentService](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/src/main/java/com/app/service/StudentService.java.md)
* [Postman collection](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/files/spring-data-mongo.postman_collection.json.md)
* APIs - refer [StudentController](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/src/main/java/com/app/controller/StudentController.java.md)
	* /save-student
	* /student-by-id/{id}
	* /all-students
	* /delete-student-by-id/{id}
	* /students-by-name/{name}
	* /students-by-name-email
	* /students-by-name-or-email
	* /all-students-pagination
	* /all-students-with-sorting
	* /all-students-by-department-name
	* /all-students-by-email-like
	* /all-students-name-starting-with
	* /students-by-name-native-query/{name}
------
# Print mongo queries in logging
* Add below property in application.properties/yaml in spring boot project. Refer - [application.yml](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/src/main/resources/application.yml.md)
```
logging.level.org.springframework.data.mongodb.core.MongoTemplate: DEBUG
```
* Output
```
o.s.data.mongodb.core.MongoTemplate      : find using query: { } fields: Document{{}} for class: class com.app.entity.Student in collection: student

o.s.data.mongodb.core.MongoTemplate      : find using query: { "name" : "Ana" } fields: Document{{}} for class: class com.app.entity.Student in collection: student
```
* Reference - https://www.baeldung.com/spring-boot-mongodb-logging
------
# Relatioship between collections
* use `org.springframework.data.mongodb.core.mapping.DBRef` annotation
* Refer
	* `address` property in [Student class](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/src/main/java/com/app/entity/Student.java.md)
	* [Address class](../../_code/spring-boot-2/data-mongo/spring-data-mongo/files/src/main/java/com/app/entity/Address.java.md)