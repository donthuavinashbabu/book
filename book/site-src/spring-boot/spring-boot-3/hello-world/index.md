# Spring Boot 3 Hello World Application
------
# Technical Stack
* Java 17
* Maven 3.8.x
* Spring Boot 3.3.1
------
# How to run application
* Application starts on port `9000`

## Method 1
* Set Java 17 and Maven to path
* Import application to IntelliJ
* Run main class [Application](../../_code/spring-boot-3/hello-world/files/src/main/java/com/java/Application.java.md)

## Method 2
* Run below maven command
```
mvn clean compile spring-boot:run
```
* This command clean compile and run application
------
# Files
* Main class [Application](../../_code/spring-boot-3/hello-world/files/src/main/java/com/java/Application.java.md)
* Application yaml file [application.yml](../../_code/spring-boot-3/hello-world/files/src/main/resources/application.yml.md)
* POM file [pom.xml](../../_code/spring-boot-3/hello-world/files/pom.xml.md)
------
# Explanation
* Go to https://start.spring.io/
* Give required details and generate application
* Create [application.yml](../../_code/spring-boot-3/hello-world/files/src/main/resources/application.yml.md) file
* Add below property to start application on port `9000`
```
server.port: 9000
```