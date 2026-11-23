# OpenFeign
* Tests use Spring Cloud OpenFeign against the running API on port `9000`
* Package [`com.java.rest.client.openfeign`](../src/test/java/com/java/rest/client/openfeign)
* Dependency is `spring-cloud-starter-openfeign` with `test` scope in [pom.xml](../pom.xml). Spring Cloud version is `2024.0.1`
* The API is declared on an interface with Spring MVC annotations (`@GetMapping`, `@PostMapping`, `@RequestHeader`, `@RequestParam`, `@PathVariable`)
* [OpenFeignClients](../src/test/java/com/java/rest/client/openfeign/OpenFeignClients.java) builds the client with `Feign.builder()` and does not start a Spring context
* It uses `SpringMvcContract`, `SpringEncoder`, and `ResponseEntityDecoder` with a `StringHttpMessageConverter` and `MappingJackson2HttpMessageConverter`
* Base URL is `http://localhost:9000`
* These classes are not `@SpringBootTest` and they do not assert. Start the application first. See [controllers.md](controllers.md)
------
# How this client calls the API
* A test asks for a typed client, then calls a Java method. Feign sends the HTTP request

```java
GetClient client = OpenFeignClients.target(GetClient.class);
Student student = client.studentsV1();
```

* Client interfaces
  * [GetClient](../src/test/java/com/java/rest/client/openfeign/GetClient.java)
  * [PostClient](../src/test/java/com/java/rest/client/openfeign/PostClient.java)
  * [PutClient](../src/test/java/com/java/rest/client/openfeign/PutClient.java)
  * [DeleteClient](../src/test/java/com/java/rest/client/openfeign/DeleteClient.java)
  * [HateoasClient](../src/test/java/com/java/rest/client/openfeign/HateoasClient.java)
  * [I18NClient](../src/test/java/com/java/rest/client/openfeign/I18NClient.java)
  * [VersioningClient](../src/test/java/com/java/rest/client/openfeign/VersioningClient.java)
* `studentsV2` returns `ResponseEntity<Student>` so the test can log the status code
* `studentV1` on POST, PUT, and DELETE consumes `text/plain` and takes the JSON file as a `String`
* Other write methods consume `application/json` and take a `Student` or `Employee`
------
# How to run
* Start the application on port `9000`
* Run one class from the IDE, or run the package:

```
mvn -Dtest=com.java.rest.client.openfeign.*Test test
```
------
# Test classes
* [GetControllerTest](../src/test/java/com/java/rest/client/openfeign/GetControllerTest.java) calls [GetControllerImpl](../src/main/java/com/java/controller/impl/GetControllerImpl.java)
* [PostControllerTest](../src/test/java/com/java/rest/client/openfeign/PostControllerTest.java) calls [PostControllerImpl](../src/main/java/com/java/controller/impl/PostControllerImpl.java)
* [PutControllerTest](../src/test/java/com/java/rest/client/openfeign/PutControllerTest.java) calls [PutControllerImpl](../src/main/java/com/java/controller/impl/PutControllerImpl.java)
* [DeleteControllerTest](../src/test/java/com/java/rest/client/openfeign/DeleteControllerTest.java) calls [DeleteControllerImpl](../src/main/java/com/java/controller/impl/DeleteControllerImpl.java)
* [HateoasControllerTest](../src/test/java/com/java/rest/client/openfeign/HateoasControllerTest.java) calls [HateoasControllerImpl](../src/main/java/com/java/controller/impl/HateoasControllerImpl.java)
* [I18NControllerTest](../src/test/java/com/java/rest/client/openfeign/I18NControllerTest.java) calls [I18NControllerImpl](../src/main/java/com/java/controller/impl/I18NControllerImpl.java)
* [VersioningControllerTest](../src/test/java/com/java/rest/client/openfeign/VersioningControllerTest.java) calls [VersioningControllerImpl](../src/main/java/com/java/controller/impl/VersioningControllerImpl.java)
------
# GetControllerTest
| Test | `GetClient` method | Request |
| --- | --- | --- |
| `helloWorld` | `helloWorld()` | `GET /get/api/v1/hello-world` |
| `studentsV1` | `studentsV1()` | `GET /get/api/v1/students` |
| `studentsV2` | `studentsV2()` | `GET /get/api/v2/students` as `ResponseEntity` |
| `studentsV3` | `studentsV3()` | `GET /get/api/v3/students` |
| `studentsV4` | `studentsV4("a", "b1")` | `GET /get/api/v4/students` with headers `name` and `book` |
| `studentsV5` | `studentsV5("a", "b1")` | `GET /get/api/v5/students?name=a&book=b1` |
| `studentsV6` | `studentsV6("a", "b1")` | `GET /get/api/v6/students/a/b1` |
| `studentsV7` | `studentsV7(0, 2, "asc")` | `GET /get/api/v7/students?page=0&size=2&sort=asc` |
| `users1` | `users1()` | `GET /get/api/v1/users` |
------
# PostControllerTest
* `studentV1` reads [student-v1.json](../src/test/resources/student-v1.json) and posts it as `text/plain`
* The request body for the student calls is `Student(name=request-name, book=request-book)` except `studentV2`, which sends `name=a` and `book=b1`
* `employee` sends `Employee(id=1, name=a, dept=engineering)`

| Test | Request |
| --- | --- |
| `studentV1` | `POST /post/api/v1/students` |
| `studentV2` | `POST /post/api/v2/students` |
| `studentV3` | `POST /post/api/v3/students` with headers `name=a` and `book=b1` |
| `studentV4` | `POST /post/api/v4/students?name=a&book=b1` |
| `studentV5` | `POST /post/api/v5/students/a/b1` |
| `studentV6` | `POST /post/api/v6/students/ABC123?book=b1` with header `name=a` |
| `employee` | `POST /post/api/v1/employee` |
------
# PutControllerTest
* Same inputs as the POST student calls, declared on [PutClient](../src/test/java/com/java/rest/client/openfeign/PutClient.java) with `@PutMapping`

| Test | Request |
| --- | --- |
| `studentV1` | `PUT /put/api/v1/students` |
| `studentV2` | `PUT /put/api/v2/students` |
| `studentV3` | `PUT /put/api/v3/students` with headers `name=a` and `book=b1` |
| `studentV4` | `PUT /put/api/v4/students?name=a&book=b1` |
| `studentV5` | `PUT /put/api/v5/students/a/b1` |
| `studentV6` | `PUT /put/api/v6/students/ABC123?book=b1` with header `name=a` |
------
# DeleteControllerTest
* Same inputs as the PUT student calls, declared on [DeleteClient](../src/test/java/com/java/rest/client/openfeign/DeleteClient.java) with `@DeleteMapping`
* The body is a `@RequestBody` argument on the interface method

| Test | Request |
| --- | --- |
| `studentV1` | `DELETE /delete/api/v1/students` |
| `studentV2` | `DELETE /delete/api/v2/students` |
| `studentV3` | `DELETE /delete/api/v3/students` with headers `name=a` and `book=b1` |
| `studentV4` | `DELETE /delete/api/v4/students?name=a&book=b1` |
| `studentV5` | `DELETE /delete/api/v5/students/a/b1` |
| `studentV6` | `DELETE /delete/api/v6/students/ABC123?book=b1` with header `name=a` |
------
# HateoasControllerTest
| Test | Request |
| --- | --- |
| `findAllStudents` | `GET /hateoas/api/v1/students` |
| `findStudentById` | `GET /hateoas/api/v1/students/1` |
| `saveStudent` | `POST /hateoas/api/v1/students` with `name=a` and `book=b1` |
| `saveStudents2` | `POST /hateoas/api/v2/students` and logs the JSON, including `_links` |
------
# I18NControllerTest
* Each test calls the endpoint twice
* The first call uses the default language
* The second call passes `Accept-Language: fr` through the client method

| Test | Request |
| --- | --- |
| `helloWorld` | `GET /i18n/api/v1/hello-world` |
| `helloWorldWithName` | `GET /i18n/api/v1/hello-world/a` |
------
# VersioningControllerTest
| Test | Request |
| --- | --- |
| `urlVersioning1` | `GET /versioning/api/v1/students` |
| `urlVersioning2` | `GET /versioning/api/v2/students` |
| `requestParamVersioning1` | `GET /versioning/api/students?VERSION=1` |
| `requestParamVersioning2` | `GET /versioning/api/students?VERSION=2` |
| `headerVersioning1` | `GET /versioning/api/students` with header `X-API-VERSION: 1` |
| `headerVersioning2` | `GET /versioning/api/students` with header `X-API-VERSION: 2` |
| `mediaTypeVersioning1` | `GET /versioning/api/students` with `Accept: application/my-company-v1+json` |
| `mediaTypeVersioning2` | `GET /versioning/api/students` with `Accept: application/my-company-v2+json` |
