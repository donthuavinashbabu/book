mvn archetype:generate -DgroupId=com.hait -DartifactId=open-libery-1 -Dversion=1 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

---
run application:

* liberty:dev command starts the Open Liberty server in development mode, which hot-reloads any changes made to the code or configuration without restarting the server
```
mvn liberty:dev
```
* Command is available to start the server in production mode
```
liberty:run
```
* we can use liberty:start-server and `liberty:stop-server` to start/stop the server in the background

---
# APIs
* http://localhost:9080/app
* http://localhost:9080/api/v1/persons
* persons post
```
http://localhost:9080/api/v1/persons

{
  "name": "a",
  "mail": "a@mail.com"
}
```
* http://localhost:9080/api/v1/persons/51
* http://localhost:9080/api/v1/persons/all

---
# Rest client
* MicroProfile Rest Client by adding the mpRestClient-1.3 feature to consume the RESTful web services. It provides the RestClientBuilder interface to request the RESTful web service endpoints