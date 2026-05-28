# Credentials In Properties
* Implementing basic spring boot 3 spring security 6
* Define credentials in application properties file
------
# How to run application?
* Application starts on port 9000

## Method 1
* Import code to IDE (IntelliJ or Eclipse)
* Run main class - [Main](../_code/credentials-in-properties/files/src/main/java/com/java/Main.java.md)
* Random password will be generated in application console

## Method 2
* Run below maven command
```
mvn clean compile spring-boot:run
```
* Random password will be generated in application console
------
# Explanation
* Add spring security dependency in [pom.xml](../_code/credentials-in-properties/files/pom.xml.md)
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
* Add below 2 files in [application.properties](../_code/credentials-in-properties/files/src/main/resources/application.properties.md)
```
spring.security.user.name=${SECURITY_USERNAME:user1}
spring.security.user.password=${SECURITY_PASSWORD:pwd1}
```
* Start the application
* Hit this API in browser - http://localhost:9000/status
* Following form will prompt for credentials\
![picture](img/001.jpg)
* Enter credentials defined in [application.properties](../_code/credentials-in-properties/files/src/main/resources/application.properties.md)
* We will get success response
------
# Files
* [pom.xml](../_code/credentials-in-properties/files/pom.xml.md)
* Main class - [Main](../_code/credentials-in-properties/files/src/main/java/com/java/Main.java.md)
* Controller - [AppController](../_code/credentials-in-properties/files/src/main/java/com/java/controller/AppController.java.md)
* [application.properties](../_code/credentials-in-properties/files/src/main/resources/application.properties.md)
* [Postman collection](../_code/credentials-in-properties/files/postman/credentials-in-properties.postman_collection.json.md)