# Rest Template
------
- [Folders](#folders)
- [Files](#files)
---
# Folders
_No subfolders_
---
## Files
* [RestTemplate examples](rest-template.md)

[`rest-template.md`](rest-template.md) calls `http://localhost:9090` with `RestTemplate`. The examples are:

* A bean that turns SSL verification off when `is.dev.mode` is true, and logs each call
* Connection, connection-request, and read timeouts
* `GET /hello` returning a string
* `GET /students` returning three students
* `GET /students2` with `email` and `password` request parameters
* `GET /students/{email}/{password}` with path variables, including a `Map` of path parameters
* `GET /students3` with `email` and `password` request headers
* `POST /students` with a JSON body
* `POST /students2` with a JSON body and request headers
* `POST /students3` with request headers only
* `POST /students4` with an `email` header and a `password` query parameter
* `POST /students5` with `email` and `password` query parameters, read as `String` and as `Student`
* `PUT /student/{email}` with a body, path variable, `rememberMe` header, and `password` query parameter
* `DELETE /student/{email}` with the same inputs

GET examples in the REST API project are in [GetControllerTest](../rest-api/rest-api/src/test/java/com/java/rest/client/resttemplate/GetControllerTest.java).
