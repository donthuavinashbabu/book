# WebClient
* Tests use Spring [`WebClient`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/client/WebClient.html) against the running API on port `9000`
* Package [`com.java.rest.client.webclient`](../src/test/java/com/java/rest/client/webclient)
* Dependency is `spring-boot-starter-webflux` in [pom.xml](../pom.xml)
* The client is created with `WebClient.create("http://localhost:9000")`
* Calls return `Mono` or `Flux`. The tests block with `block()`, `collectList().block()`, or `toStream()` so JUnit can finish
* These classes are not `@SpringBootTest` and they do not assert. Start the application first. See [controllers.md](controllers.md)
------
# How this client calls the API
* `get()`, `post()`, `put()`, and `delete()` build the request
* `retrieve().bodyToMono(Student.class)` reads one object
* `retrieve().bodyToFlux(Student.class)` reads a list
* `retrieve().toEntity(Student.class)` keeps the status code
* Headers use `.header("name", "a")`. Path variables use `uri("/get/api/v6/students/{name}/{book}", "a", "b1")`
* A JSON body is sent with `.bodyValue(student)`. `studentV1` sets `contentType(MediaType.TEXT_PLAIN)`

```java
WebClient webClient = WebClient.create("http://localhost:9000");
Student student = webClient.get().uri("/get/api/v1/students")
        .retrieve().bodyToMono(Student.class).block();
```

* `createWebClient` in [GetControllerTest](../src/test/java/com/java/rest/client/webclient/GetControllerTest.java) does not call the API. It logs three ways to build a client:
  * `WebClient.create()`
  * `WebClient.create("http://localhost:9000")`
  * `WebClient.builder().baseUrl("http://localhost:9000").build()`
------
# How to run
* Start the application on port `9000`
* Run one class from the IDE, or run the package:

```
mvn -Dtest=com.java.rest.client.webclient.*Test test
```
------
# Test classes
* [GetControllerTest](../src/test/java/com/java/rest/client/webclient/GetControllerTest.java) calls [GetControllerImpl](../src/main/java/com/java/controller/impl/GetControllerImpl.java)
* [PostControllerTest](../src/test/java/com/java/rest/client/webclient/PostControllerTest.java) calls [PostControllerImpl](../src/main/java/com/java/controller/impl/PostControllerImpl.java)
* [PutControllerTest](../src/test/java/com/java/rest/client/webclient/PutControllerTest.java) calls [PutControllerImpl](../src/main/java/com/java/controller/impl/PutControllerImpl.java)
* [DeleteControllerTest](../src/test/java/com/java/rest/client/webclient/DeleteControllerTest.java) calls [DeleteControllerImpl](../src/main/java/com/java/controller/impl/DeleteControllerImpl.java)
* [HateoasControllerTest](../src/test/java/com/java/rest/client/webclient/HateoasControllerTest.java) calls [HateoasControllerImpl](../src/main/java/com/java/controller/impl/HateoasControllerImpl.java)
* [I18NControllerTest](../src/test/java/com/java/rest/client/webclient/I18NControllerTest.java) calls [I18NControllerImpl](../src/main/java/com/java/controller/impl/I18NControllerImpl.java)
* [VersioningControllerTest](../src/test/java/com/java/rest/client/webclient/VersioningControllerTest.java) calls [VersioningControllerImpl](../src/main/java/com/java/controller/impl/VersioningControllerImpl.java)
------
# GetControllerTest
| Test | Request |
| --- | --- |
| `createWebClient` | Builds three `WebClient` instances and does not call the API |
| `helloWorld` | `GET /get/api/v1/hello-world` as `Flux<String>` |
| `studentsV1` | `GET /get/api/v1/students` returns `Student` |
| `studentsV2` | `GET /get/api/v2/students` returns `ResponseEntity<Student>` |
| `studentsV3` | `GET /get/api/v3/students` returns `List<Student>` |
| `studentsV4` | `GET /get/api/v4/students` with headers `name=a` and `book=b1` |
| `studentsV5` | `GET /get/api/v5/students?name=a&book=b1` |
| `studentsV6` | `GET /get/api/v6/students/a/b1` |
| `studentsV7` | `GET /get/api/v7/students?page=0&size=2&sort=asc` |
| `users1` | `GET /get/api/v1/users` returns `List<User>` |
------
# PostControllerTest
* `studentV1` reads [student-v1.json](../src/test/resources/student-v1.json) and posts it as `text/plain`
* `studentV2` through `studentV6` and `employee` send a JSON body with `bodyValue`
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
* Same inputs as the POST student calls, with `put()` and the `/put` prefix
* `studentV1` sends [student-v1.json](../src/test/resources/student-v1.json) as `text/plain`

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
* Same inputs as the PUT student calls, with `DELETE` and the `/delete` prefix
* `WebClient.delete()` cannot send a body, so each test uses `method(HttpMethod.DELETE)` and `bodyValue`

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
* The second call sends `Accept-Language: fr` with `.header(HttpHeaders.ACCEPT_LANGUAGE, "fr")`

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
