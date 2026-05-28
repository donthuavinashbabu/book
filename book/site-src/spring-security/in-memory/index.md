# Spring Security In Memory Credentials
* Implementing basic spring boot 3 spring security 6
* Define in memory credentials in java class 
------
# How to run application?
* Application starts on port 9000

## Method 1
* Import code to IDE (IntelliJ or Eclipse)
* Run main class - [Main](../_code/in-memory/files/src/main/java/com/java/Main.java.md)

## Method 2
* Run below maven command
```
mvn clean compile spring-boot:run
```
------
# Explanation
* Add spring security dependency in [pom.xml](../_code/in-memory/files/pom.xml.md)
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
* Define bean with user credentials in [SecurityConfig](../_code/in-memory/files/src/main/java/com/java/config/SecurityConfig.java.md)
* Start the application
* Hit this API in browser - http://localhost:9000/status
* Following form will prompt for credentials\
![picture](img/001.jpg)
* Enter credentials defined in [SecurityConfig](../_code/in-memory/files/src/main/java/com/java/config/SecurityConfig.java.md)
------
# Files
* [pom.xml](../_code/in-memory/files/pom.xml.md)
* Main class - [Main](../_code/in-memory/files/src/main/java/com/java/Main.java.md)
* Controller - [AppController](../_code/in-memory/files/src/main/java/com/java/controller/AppController.java.md)
* [SecurityConfig](../_code/in-memory/files/src/main/java/com/java/config/SecurityConfig.java.md)
* [application.properties](../_code/in-memory/files/src/main/resources/application.properties.md)
* [Postman collection](../_code/in-memory/files/postman/in-memory.postman_collection.json.md)