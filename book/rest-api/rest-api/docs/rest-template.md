# RestTemplate
* Tests use Spring [`RestTemplate`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html) against the running API on port `9000`
* Package [`com.java.rest.client.resttemplate`](../src/test/java/com/java/rest/client/resttemplate)
* `RestTemplate` comes with `spring-boot-starter-web`. There is no extra test dependency
* Each test creates `new RestTemplate()`, calls one endpoint, and logs the body
* These classes are not `@SpringBootTest` and they do not assert. Start the application first. See [controllers.md](controllers.md)
------
# How this client calls the API
* `getForObject` and `getForEntity` read a body straight into a Java type
* `postForObject` and `postForEntity` send a `Student` or `Employee` as JSON
* `exchange` is used when the call needs headers, a `List`, or a request body on DELETE
* A `List` is read with `ParameterizedTypeReference<List<Student>>`, or as `Student[]`
* Path variables use a `Map`, for example `{name}` and `{book}`
* [`RestTemplate.delete`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html#delete(java.lang.String,java.lang.Object...)) cannot send a body, so [DeleteControllerTest](../src/test/java/com/java/rest/client/resttemplate/DeleteControllerTest.java) uses `exchange` with `HttpMethod.DELETE`

```java
RestTemplate restTemplate = new RestTemplate();
Student student = restTemplate.getForObject(
        "http://localhost:9000/get/api/v1/students", Student.class);
```
------
# How to run
* Start the application on port `9000`
* Run one class from the IDE, or run the package:

```
mvn -Dtest=com.java.rest.client.resttemplate.*Test test
```
------
# Test classes
* [GetControllerTest](../src/test/java/com/java/rest/client/resttemplate/GetControllerTest.java) calls [GetControllerImpl](../src/main/java/com/java/controller/impl/GetControllerImpl.java)
* [PostControllerTest](../src/test/java/com/java/rest/client/resttemplate/PostControllerTest.java) calls [PostControllerImpl](../src/main/java/com/java/controller/impl/PostControllerImpl.java)
* [PutControllerTest](../src/test/java/com/java/rest/client/resttemplate/PutControllerTest.java) calls [PutControllerImpl](../src/main/java/com/java/controller/impl/PutControllerImpl.java)
* [DeleteControllerTest](../src/test/java/com/java/rest/client/resttemplate/DeleteControllerTest.java) calls [DeleteControllerImpl](../src/main/java/com/java/controller/impl/DeleteControllerImpl.java)
* [HateoasControllerTest](../src/test/java/com/java/rest/client/resttemplate/HateoasControllerTest.java) calls [HateoasControllerImpl](../src/main/java/com/java/controller/impl/HateoasControllerImpl.java)
* [I18NControllerTest](../src/test/java/com/java/rest/client/resttemplate/I18NControllerTest.java) calls [I18NControllerImpl](../src/main/java/com/java/controller/impl/I18NControllerImpl.java)
* [VersioningControllerTest](../src/test/java/com/java/rest/client/resttemplate/VersioningControllerTest.java) calls [VersioningControllerImpl](../src/main/java/com/java/controller/impl/VersioningControllerImpl.java)
------
# GetControllerTest
| Test | Request |
| --- | --- |
| `helloWorld` | `GET /get/api/v1/hello-world` |
| `studentsV1` | `GET /get/api/v1/students` returns `Student` |
| `studentsV2` | `GET /get/api/v2/students` returns `ResponseEntity<Student>` |
| `studentsV3` | `GET /get/api/v3/students` returns `Student[]` and `List<Student>` |
| `studentsV4` | `GET /get/api/v4/students` with headers `name=a` and `book=b1` |
| `studentsV5` | `GET /get/api/v5/students?name=a&book=b1` |
| `studentsV6` | `GET /get/api/v6/students/a/b1`, also with path variables `{name}` and `{book}` |
| `studentsV7` | `GET /get/api/v7/students?page=0&size=2&sort=asc` |
| `users1` | `GET /get/api/v1/users` returns `List<User>` |
------
# PostControllerTest
* `studentV1` reads [student-v1.json](../src/test/resources/student-v1.json) and posts it as `text/plain`
* `studentV2` through `studentV6` and `employee` send a JSON body
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
* Same inputs as the POST student calls, with `PUT` and the `/put` prefix
* `studentV1` sends [student-v1.json](../src/test/resources/student-v1.json) as `text/plain`
* `studentV2` through `studentV6` send JSON

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
* Every call uses `exchange`, because the body has to travel with the request

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
| `findAllStudents` | `GET /hateoas/api/v1/students` returns `List<Student>` |
| `findStudentById` | `GET /hateoas/api/v1/students/1` |
| `saveStudent` | `POST /hateoas/api/v1/students` with `name=a` and `book=b1` |
| `saveStudents2` | `POST /hateoas/api/v2/students` and logs the JSON, including `_links` |
------
# I18NControllerTest
* Each test calls the endpoint twice
* The first call uses the default language
* The second call sends `Accept-Language: fr`

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
