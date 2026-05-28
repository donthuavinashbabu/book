# Requirement
* Start spring boot 3 application with embedded tomcat on random available port
------
# Why do we need a random or dynamic port?
* Sometimes we want to run multiple instances of a single app on the same server. And if you want to do this then we need to assign different ports in the run time only. And for this reason, we need to allocate random/dynamic ports at app startup
------
# Technical Stack
* Java 17
* Maven 3.8.x
* Spring Boot 3.3.1
------
# How to run application?
* Application starts on random available port

## Method 1
* Set Java 17 and Maven to path
* Import application to IntelliJ
* Run main class [Application](../../_code/spring-boot-3/random-port/files/src/main/java/com/java/Application.java.md)

## Method 2
* Run below maven command
```
mvn clean compile spring-boot:run
```
* This command clean compile and run application
------
# Files
* Main class [Application](../../_code/spring-boot-3/random-port/files/src/main/java/com/java/Application.java.md)
* Application yaml file [application.yml](../../_code/spring-boot-3/random-port/files/src/main/resources/application.yml.md)
* POM file [pom.xml](../../_code/spring-boot-3/random-port/files/pom.xml.md)
------
# Explanation

## Method 1 - Using `server.port` property in application yaml/properties file
* Go to https://start.spring.io/
* Give required details and generate application
* Create [application.yml](../../_code/spring-boot-3/random-port/files/src/main/resources/application.yml.md) file
* Add below property in [application.yml](../../_code/spring-boot-3/random-port/files/src/main/resources/application.yml.md) file
```
server.port: 0
```
* Now application will start on random available port
* We can see port number on console

## Method 2 - Using `SpringApplication` class in main method
* Add below code in main method
```
SpringApplication.run(Application.class, "--server.port=0");
```
* Refer [Application](../../_code/spring-boot-3/random-port/files/src/main/java/com/java/Application.java.md)
* Now application will start on random available port
* We can see port number on console