# Technical stack
* Java 17
* Maven
* Spring Boot 3.3.1
* Lombok
------
# How to run application?
* Application starts on port `9000`

## Method 1
* Set Java 17 and Maven to path
* Import application to IntelliJ
* Run main class [Main](../_code/rest-api/files/src/main/java/com/java/Main.java.md)

## Method 2
* Run below maven command
```
mvn clean compile spring-boot:run
```
* This command clean compile and run application
------
# Build docker image and push to docker hub
* Write [Dockerfile](../_code/rest-api/files/Dockerfile.md)
* We are using `openjdk java 17` base image
* Build image using below command
```
docker build . -t rest-api
``` 
* Run and check the container
```
docker run -it -p 9000:9000 rest-api
```
* Open url - http://localhost:9000/swagger-ui/index.html
* Swagger should start without any issues
* Tag an image before pushing to docker hub
```
docker image tag rest-api donthuavinashbabu/rest-api
```
* Push image to docker hub
```
docker image push donthuavinashbabu/rest-api
```
------
# Files
* [pom.xml](../_code/rest-api/files/pom.xml.md)
* [application.properties](../_code/rest-api/files/src/main/resources/application.properties.md)
* Main class [Main](../_code/rest-api/files/src/main/java/com/java/Main.java.md)
* Student Model class [Student](../_code/rest-api/files/src/main/java/com/java/model/Student.java.md)
* StudentService interface [StudentService](../_code/rest-api/files/src/main/java/com/java/service/StudentService.java.md)
* StudentServiceImpl class [StudentServiceImpl](../_code/rest-api/files/src/main/java/com/java/service/impl/StudentServiceImpl.java.md)
* GetController interface [GetController](../_code/rest-api/files/src/main/java/com/java/controller/GetController.java.md)
* GetControllerImpl class [GetControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/GetControllerImpl.java.md)
* PostController interface [PostController](../_code/rest-api/files/src/main/java/com/java/controller/PostController.java.md)
* PostControllerImpl class [PostControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/PostControllerImpl.java.md)
* PutController interface [PutController](../_code/rest-api/files/src/main/java/com/java/controller/PutController.java.md)
* PutControllerImpl class [PutControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/PutControllerImpl.java.md)
* DeleteController interface [DeleteController](../_code/rest-api/files/src/main/java/com/java/controller/DeleteController.java.md)
* DeleteControllerImpl class [DeleteControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/DeleteControllerImpl.java.md)
* HateoasController interface [HateoasController](../_code/rest-api/files/src/main/java/com/java/controller/HateoasController.java.md)
* HateoasControllerImpl class [HateoasControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/HateoasControllerImpl.java.md)
* GlobalExceptionHandler class [GlobalExceptionHandler](../_code/rest-api/files/src/main/java/com/java/config/GlobalExceptionHandler.java.md)
* I18NController interface [I18NController](../_code/rest-api/files/src/main/java/com/java/controller/I18NController.java.md)
* I18NControllerImpl class [I18NControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/I18NControllerImpl.java.md)
* VersioningController interface [VersioningController](../_code/rest-api/files/src/main/java/com/java/controller/VersioningController.java.md)
* VersioningControllerImpl class [VersioningControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/VersioningControllerImpl.java.md)
------
# REST APIs
* Download and import [Postman collection](../_code/rest-api/files/postman/rest-api.postman_collection.json.md) for below APIs
* [GetController](../_code/rest-api/files/src/main/java/com/java/controller/GetController.java.md)
  * Interface has GET API declarations
  * Has different ways of writing GET APIs
* [GetControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/GetControllerImpl.java.md) class has API implementation
* Why interface and class for APIs
  * Implementation has only business logic
  * Interface will have API annotations, method comments, swagger documentation etc
* [PostController](../_code/rest-api/files/src/main/java/com/java/controller/PostController.java.md)
  * Interface has POST API declarations
  * Has different ways of writing POST APIs
* [PostControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/PostControllerImpl.java.md) class has API implementation
* [PutController](../_code/rest-api/files/src/main/java/com/java/controller/PutController.java.md)
  * Interface has PUT API declarations
  * Has different ways of writing PUT APIs
* [PutControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/PutControllerImpl.java.md) class has API implementation
* [DeleteController](../_code/rest-api/files/src/main/java/com/java/controller/DeleteController.java.md)
  * Interface has DELETE API declarations
  * Has different ways of writing DELETE APIs
* [DeleteControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/DeleteControllerImpl.java.md) class has API implementation
* HateoasController interface [HateoasController](../_code/rest-api/files/src/main/java/com/java/controller/HateoasController.java.md)
  * Return hateoas link in api response
* [HateoasControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/HateoasControllerImpl.java.md) class has API implementation
* GlobalExceptionHandler class [GlobalExceptionHandler](../_code/rest-api/files/src/main/java/com/java/config/GlobalExceptionHandler.java.md)
  * Global exception handler class
  * Handles jakarta validations also. Refer method `handleMethodArgumentNotValid`
* I18NController interface [I18NController](../_code/rest-api/files/src/main/java/com/java/controller/I18NController.java.md)
  * Internationalization in rest api
* [I18NControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/I18NControllerImpl.java.md) class has API implementation
* VersioningController interface [VersioningController](../_code/rest-api/files/src/main/java/com/java/controller/VersioningController.java.md)
  * Different ways to implement rest api versioning
* [VersioningControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/VersioningControllerImpl.java.md) class has API implementation
------
# Hateoas APIs
* Hateoas - Hypermedia as the engine of application state
* Add below dependency
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```

## Adding link to access newly created student in header
* HateoasControllerImpl class [HateoasControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/HateoasControllerImpl.java.md)
* Refer `saveStudent` method. This returns link to access newly created student in response header `location`
```
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;

URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getId()).toUri();
ResponseEntity.created(location).build();
```

## Add `_link` property
* HateoasControllerImpl class [HateoasControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/HateoasControllerImpl.java.md)
* Refer `saveStudents2` method
* Returns response as below
```
{
    "id": "3d6a18a7-0131-4a56-b978-d7d24ff78925",
    "name": "a",
    "book": "b1",
    "_links": {
        "all-students": {
            "href": "http://localhost:9000/hateoas/api/v1/students"
        },
        "this-student-by-id": {
            "href": "http://localhost:9000/hateoas/api/v1/students/3d6a18a7-0131-4a56-b978-d7d24ff78925"
        }
    }
}
```
------
# API with pagination
* Refer `studentsV7` method in [GetController](../_code/rest-api/files/src/main/java/com/java/controller/GetController.java.md)
* We can directly use `Pageable`
* `ParameterObject` is used for swagger documentation
* Swagger without `ParameterObject`\
![picture](img/004.jpg)
* Swagger with `ParameterObject`\
![picture](img/005.jpg)
------
# Swagger Spring Doc Open API Documentation
* Pre-requisite for this is to understand how to write APIs. Refer [here](#REST-APIs)
* Add below dependency. Refer [pom.xml](../_code/rest-api/files/pom.xml.md)
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.3</version>
</dependency>
```
* Add below annotations to API method. Refer [GetController](../_code/rest-api/files/src/main/java/com/java/controller/GetController.java.md) & [PostController](../_code/rest-api/files/src/main/java/com/java/controller/PostController.java.md)
```
@Operation(summary = "Hello World GET API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hello World",
                content = {@Content(mediaType = TEXT_PLAIN_VALUE, schema = @Schema(implementation = String.class))}
            )
    })
```
* Add below annotation to define API class
```
@Tag(name = "Get APIs")
```
* Start application
* Open URL - http://localhost:9000/swagger-ui/index.html
![picture](img/001.jpg)
* We can find OpenAPI descriptions at `/v3/api-docs` - http://localhost:9000/v3/api-docs
* We can customize path using below property. Refer property in [application.properties](../_code/rest-api/files/src/main/resources/application.properties.md). Now we can access docs using http://localhost:9000/rest-api-docs
```
springdoc.api-docs.path=/rest-api-docs
```
* Above OpenAPI definitions are in JSON format by default. For yaml format, use this link http://localhost:9000/rest-api-docs.yaml
* We can change default swagger ui path using below property. Refer [application.properties](../_code/rest-api/files/src/main/resources/application.properties.md)
```
springdoc.swagger-ui.path=/rest-api-swagger.html
```
* So now our documentation is available in http://localhost:9000/swagger-ui/index.html
* We can sort the API paths according to their HTTP methods with the `springdoc.swagger-ui.operationsSorter` property
```
springdoc.swagger-ui.operationsSorter=alpha
```
* Refer swagger-ui properties here - https://springdoc.org/#swagger-ui-properties
* Reference - https://www.baeldung.com/spring-rest-openapi-documentation

## To change OpenAPI definition heading
* Add below annotation. Refer [Main](../_code/rest-api/files/src/main/java/com/java/Main.java.md)
```
@OpenAPIDefinition(info = @Info(title = "Rest API Documentation"))
```
* Before
![picture](img/002.jpg)
* After
![picture](img/003.jpg)
 
## Annotations explanation
* io.swagger.v3.oas.annotations.OpenAPIDefinition
  * Used to define main title of swagger documentation UI
  * Refer [Main.java](../_code/rest-api/files/src/main/java/com/java/Main.java.md)
* io.swagger.v3.oas.annotations.Operation
  * Used to define API
  * Refer controller classes in [com.java.controller](src/main/java/com/java/controller)
* io.swagger.v3.oas.annotations.responses.ApiResponses
  * container for different responses returned by API
  * Refer controller classes in [com.java.controller](src/main/java/com/java/controller)
* io.swagger.v3.oas.annotations.responses.ApiResponse
  * Define details of response for different scenarios
  * Refer controller classes in [com.java.controller](src/main/java/com/java/controller)
* io.swagger.v3.oas.annotations.tags.Tag
  * Used define API class description
  * Declare at controller class level
  * Refer controller classes in [com.java.controller](src/main/java/com/java/controller)
* io.swagger.v3.oas.annotations.media.Schema
  * Used for properties of model. Refer [Student.java](../_code/rest-api/files/src/main/java/com/java/model/Student.java.md)
* io.swagger.v3.oas.annotations.Parameter
  * Used for parameters of API resource request
  * Refer [DeleteController.java](../_code/rest-api/files/src/main/java/com/java/controller/DeleteController.java.md)
------
# Validation
* Add below validation dependency. Refer [pom.xml](../_code/rest-api/files/pom.xml.md)
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
* Add `org.springframework.validation.annotation.Validated` annotation to API request body. Refer `studentV2` method in [PostController](../_code/rest-api/files/src/main/java/com/java/controller/PostController.java.md)
* Add validation annotations in Student Model class [Student](../_code/rest-api/files/src/main/java/com/java/model/Student.java.md)
  * import jakarta.validation.constraints.Min 
  * import jakarta.validation.constraints.NotBlank 
  * import jakarta.validation.constraints.NotNull
* Like this we have more annotation. Refer jar file\
![picture](img/006.jpg)
* API response without passing `name` and `book`\
![picture](img/007.jpg)
* API response without passing `book`\
![picture](img/008.jpg)
------
# Support json and xml responses
* Add below dependency in [pom.xml](../_code/rest-api/files/pom.xml.md)
```
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```
* Add `org.springframework.http.MediaType.APPLICATION_XML_VALUE` to `produces` in API declaration. Refer `studentsV3` method in [GetController](../_code/rest-api/files/src/main/java/com/java/controller/GetController.java.md)
* Hit API - http://localhost:9000/get/api/v3/students with header - `Accept:application/xml`\
![picture](img/009.jpg)
------
# Internationalization - I18N
* Add language specific properties files
  * [messages.properties](../_code/rest-api/files/src/main/resources/messages.properties.md)
  * [messages_fr.properties](../_code/rest-api/files/src/main/resources/messages_fr.properties.md)
* Inject `org.springframework.context.MessageSource` into our class
* Get locale specific messages using `org.springframework.context.MessageSource`. Refer `helloWorld` method [I18NControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/I18NControllerImpl.java.md)
* Get locale specific messages using `org.springframework.context.MessageSource` with parameters. Refer `helloWorldWithName` method [I18NControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/I18NControllerImpl.java.md)
------
# Filtering
## Static Filtering
* Static filtering will be done using annotations in model class
  * `com.fasterxml.jackson.annotation.JsonIgnoreProperties` at class level
  * `com.fasterxml.jackson.annotation.JsonIgnore` at property level
* Refer [Student](../_code/rest-api/files/src/main/java/com/java/model/Student.java.md)
* Both are not needed. We can use any one them. Preferred is `com.fasterxml.jackson.annotation.JsonIgnore` because if property name updated then we need to update property name in `com.fasterxml.jackson.annotation.JsonIgnoreProperties` also

## Dynamic Filtering
* Using `org.springframework.http.converter.json.MappingJacksonValue`
* Define `com.fasterxml.jackson.annotation.JsonFilter` annotation at class level. Refer [User](../_code/rest-api/files/src/main/java/com/java/model/User.java.md) class
* Write below logic to filter properties. We can have custom logic specific to API. Refer `users1` method in [GetControllerImpl](../_code/rest-api/files/src/main/java/com/java/controller/impl/GetControllerImpl.java.md) class
```
final String jsonFilterName = "StudentPropertyFilter";

List<User> users = userService.findAllUsers();
MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("username", "firstName", "lastName");
FilterProvider filterProvider = new SimpleFilterProvider().addFilter(jsonFilterName, simpleBeanPropertyFilter);
mappingJacksonValue.setFilters(filterProvider);
```
* Note `StudentPropertyFilter` should be match in `API` and `model` class
------
# Actuator
* Add following dependency
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
* Add this property in [application properties/yaml](../_code/rest-api/files/src/main/resources/application.properties.md) to enable all actuator end points
```
management.endpoints.web.exposure.include: '*'
```
* Open the api - http://localhost:9000/actuator
* We will get more actuator end points. Explore them
------
# HAL
* HAL - JSON Hypertext Application Language
* Used to explore APIs
* Add below dependency to enable spring boot HAL
```
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-rest-hal-explorer</artifactId>
</dependency>
```
* Access this url - http://localhost:9000
![picture](img/010.jpg)
![picture](img/011.jpg)
------
# Connect to H2 Database
* Add below dependencies
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```
* Add below properties in [application properties/yaml](../_code/rest-api/files/src/main/resources/application.properties.md)
```
# database properties
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true

# H2 database
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:db1
spring.datasource.username=user1
spring.datasource.password=07102024
```
* Create entities. For example below
  * [EmployeeEntity](../_code/rest-api/files/src/main/java/com/java/entity/EmployeeEntity.java.md)
* To insert data to tables create [data.sql](../_code/rest-api/files/src/main/resources/data.sql.md) in `src/main/resources` and write insert queries
* Start the application and access h2 console - http://localhost:9000/h2-console
* Login using url, username and password given in [application properties/yaml](../_code/rest-api/files/src/main/resources/application.properties.md)
------
# Spring Boot 3 Data JPA with H2 Database
* Add dependencies and properties for H2 database connect. Refer [Connect to H2 Database](#connect-to-h2-database)
* Employee Repository interface [EmployeeRepository](../_code/rest-api/files/src/main/java/com/java/datajpa/h2/EmployeeRepository.java.md)
  * Spring data jpa repository used to query h2 database
* [H2Service](../_code/rest-api/files/src/main/java/com/java/datajpa/h2/H2Service.java.md) interface
* [H2ServiceImpl](../_code/rest-api/files/src/main/java/com/java/datajpa/h2/H2ServiceImpl.java.md) implementation of above interface
* [H2Controller](../_code/rest-api/files/src/main/java/com/java/datajpa/h2/H2Controller.java.md) interface
  * API to perform operations on h2 database
* [H2ControllerImpl](../_code/rest-api/files/src/main/java/com/java/datajpa/h2/H2ControllerImpl.java.md) implementation of above interface
* Refer `h2` folder in [postman collection](../_code/rest-api/files/postman/rest-api.postman_collection.json.md)
* We are saving [EmployeeEntity](../_code/rest-api/files/src/main/java/com/java/entity/EmployeeEntity.java.md)
  * We are using custom primary key generator
  * Write class to generate id value [PrimaryKeyGenerator](../_code/rest-api/files/src/main/java/com/java/entity/PrimaryKeyGenerator.java.md)
  * Write new interface [MyPrimaryKeyGenerator](../_code/rest-api/files/src/main/java/com/java/entity/MyPrimaryKeyGenerator.java.md) that use [PrimaryKeyGenerator](../_code/rest-api/files/src/main/java/com/java/entity/PrimaryKeyGenerator.java.md)
  * Use [MyPrimaryKeyGenerator](../_code/rest-api/files/src/main/java/com/java/entity/MyPrimaryKeyGenerator.java.md) that use [PrimaryKeyGenerator](../_code/rest-api/files/src/main/java/com/java/entity/PrimaryKeyGenerator.java.md) on [EmployeeEntity](../_code/rest-api/files/src/main/java/com/java/entity/EmployeeEntity.java.md)
------
# Spring Boot 3 Data JPA with MySQL Database

------
# Rest Clients
## RestTemplate Examples
* [Get APIs](../_code/rest-api/files/src/test/java/com/java/rest/client/resttemplate/GetControllerTest.java.md)

## WebClient Examples
* [Get APIs](../_code/rest-api/files/src/test/java/com/java/rest/client/webclient/GetControllerTest.java.md)