# AsyncHttpClient
* Tests use [AsyncHttpClient 2.12.4](https://github.com/AsyncHttpClient/async-http-client) against the running API on port `9000`
* Package [`com.java.rest.client.asynchttpclient`](../src/test/java/com/java/rest/client/asynchttpclient)
* Dependency is `org.asynchttpclient:async-http-client:2.12.4` with `test` scope in [pom.xml](../pom.xml)
* Each test opens `Dsl.asyncHttpClient()` in try-with-resources, prepares a request, and waits with `execute().toCompletableFuture().join()`
* The body is `response.getResponseBody()`. JSON is parsed with Jackson `ObjectMapper`
* These classes are not `@SpringBootTest` and they do not assert. Start the application first. See [controllers.md](controllers.md)
------
# How this client calls the API
* `prepareGet`, `preparePost`, `preparePut`, and `prepareDelete` build the request
* A body is set with `.setBody(...)`. `studentV1` sets `Content-Type: text/plain`. Other write calls set `Content-Type: application/json` and send `objectMapper.writeValueAsString(...)`
* Headers use `.setHeader("name", "a")`

```java
try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
    Response response = client.prepareGet("http://localhost:9000/get/api/v1/students")
            .execute().toCompletableFuture().join();
    Student student = objectMapper.readValue(response.getResponseBody(), Student.class);
}
```
------
# How to run
* Start the application on port `9000`
* Run one class from the IDE, or run the package:

```
mvn -Dtest=com.java.rest.client.asynchttpclient.*Test test
```
------
# Test classes
* [GetControllerTest](../src/test/java/com/java/rest/client/asynchttpclient/GetControllerTest.java) calls [GetControllerImpl](../src/main/java/com/java/controller/impl/GetControllerImpl.java)
* [PostControllerTest](../src/test/java/com/java/rest/client/asynchttpclient/PostControllerTest.java) calls [PostControllerImpl](../src/main/java/com/java/controller/impl/PostControllerImpl.java)
* [PutControllerTest](../src/test/java/com/java/rest/client/asynchttpclient/PutControllerTest.java) calls [PutControllerImpl](../src/main/java/com/java/controller/impl/PutControllerImpl.java)
* [DeleteControllerTest](../src/test/java/com/java/rest/client/asynchttpclient/DeleteControllerTest.java) calls [DeleteControllerImpl](../src/main/java/com/java/controller/impl/DeleteControllerImpl.java)
* [HateoasControllerTest](../src/test/java/com/java/rest/client/asynchttpclient/HateoasControllerTest.java) calls [HateoasControllerImpl](../src/main/java/com/java/controller/impl/HateoasControllerImpl.java)
* [I18NControllerTest](../src/test/java/com/java/rest/client/asynchttpclient/I18NControllerTest.java) calls [I18NControllerImpl](../src/main/java/com/java/controller/impl/I18NControllerImpl.java)
* [VersioningControllerTest](../src/test/java/com/java/rest/client/asynchttpclient/VersioningControllerTest.java) calls [VersioningControllerImpl](../src/main/java/com/java/controller/impl/VersioningControllerImpl.java)
------
# GetControllerTest
| Test | Request |
| --- | --- |
| `helloWorld` | `GET /get/api/v1/hello-world` |
| `studentsV1` | `GET /get/api/v1/students` parsed as `Student` |
| `studentsV2` | `GET /get/api/v2/students`, logs `getStatusCode()` and the `Student` |
| `studentsV3` | `GET /get/api/v3/students` parsed as `List<Student>` |
| `studentsV4` | `GET /get/api/v4/students` with headers `name=a` and `book=b1` |
| `studentsV5` | `GET /get/api/v5/students?name=a&book=b1` |
| `studentsV6` | `GET /get/api/v6/students/a/b1` |
| `studentsV7` | `GET /get/api/v7/students?page=0&size=2&sort=asc` |
| `users1` | `GET /get/api/v1/users` parsed as `List<User>` |
------
# PostControllerTest
* `studentV1` reads [student-v1.json](../src/test/resources/student-v1.json) and posts it as `text/plain`
* `studentV2` through `studentV6` and `employee` send JSON written by `ObjectMapper`
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
* Same inputs as the POST student calls, with `preparePut` and the `/put` prefix
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
* Same inputs as the PUT student calls, with `prepareDelete` and the `/delete` prefix
* The body is set with `.setBody(...)` on the delete request

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
| `saveStudents2` | `POST /hateoas/api/v2/students` and logs the raw JSON, including `_links` |
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
