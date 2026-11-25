# Apache Camel Spring Boot 3 Hello World
* [pom.xml](pom.xml)
* Main class - [App.java](src/main/java/com/java/App.java)
* Controller class - [AppController.java](src/main/java/com/java/controller/AppController.java)
------
# Timer route calling Rest API
* Rest Route - Camel route to call rest end point - [RestRoute.java](src/main/java/com/java/route/RestRoute.java)
* `timer.period` in [RestRoute.java](src/main/java/com/java/route/RestRoute.java) is in [application.yml](src/main/resources/application.yml)
------
# Timer route
* Simple timer route - [TimerRoute.java](src/main/java/com/java/route/TimerRoute.java)
* Sample output
```
2023-08-07T10:36:24.888+05:30  INFO 16116 --- [           main] o.a.c.impl.engine.AbstractCamelContext   : Apache Camel 4.0.0-RC1 (camel-1) started in 296ms (build:0ms init:0ms start:296ms)
2023-08-07T10:36:24.896+05:30  INFO 16116 --- [           main] com.java.App                             : Started App in 4.81 seconds (process running for 5.649)
2023-08-07T10:36:25.889+05:30  INFO 16116 --- [ timer://route3] route3                                   : running route3
2023-08-07T10:36:25.892+05:30  INFO 16116 --- [ timer://route2] route2                                   : running route2
2023-08-07T10:36:26.043+05:30  INFO 16116 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-08-07T10:36:26.044+05:30  INFO 16116 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-08-07T10:36:26.046+05:30  INFO 16116 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2023-08-07T10:36:26.211+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got pu9K5yg
2023-08-07T10:36:26.894+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got TbgAj1z
2023-08-07T10:36:27.892+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got p7zcSG
2023-08-07T10:36:27.893+05:30  INFO 16116 --- [ timer://route2] route2                                   : running route2
2023-08-07T10:36:28.886+05:30  INFO 16116 --- [ timer://route3] route3                                   : running route3
2023-08-07T10:36:28.890+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got Fn6baeV
2023-08-07T10:36:29.894+05:30  INFO 16116 --- [ timer://route2] route2                                   : running route2
2023-08-07T10:36:29.895+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got nqTPlgU
2023-08-07T10:36:30.895+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got 6pHhls
2023-08-07T10:36:31.887+05:30  INFO 16116 --- [ timer://route3] route3                                   : running route3
2023-08-07T10:36:31.891+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got rQCraKe
2023-08-07T10:36:31.894+05:30  INFO 16116 --- [ timer://route2] route2                                   : running route2
2023-08-07T10:36:32.893+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got hpFtnM7
2023-08-07T10:36:33.896+05:30  INFO 16116 --- [ timer://route2] route2                                   : running route2
2023-08-07T10:36:33.897+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got cqdZoZ
2023-08-07T10:36:34.887+05:30  INFO 16116 --- [ timer://route3] route3                                   : running route3
2023-08-07T10:36:34.892+05:30  INFO 16116 --- [ timer://route1] route1                                   : Got MGvKv1Xe
Disconnected from the target VM, address: '127.0.0.1:58703', transport: 'socket'
2023-08-07T10:36:35.356+05:30  INFO 16116 --- [ionShutdownHook] o.a.c.impl.engine.AbstractCamelContext   : Apache Camel 4.0.0-RC1 (camel-1) is shutting down (timeout:45s)
2023-08-07T10:36:35.361+05:30  INFO 16116 --- [ionShutdownHook] o.a.c.impl.engine.AbstractCamelContext   : Routes stopped (stopped:3)
2023-08-07T10:36:35.362+05:30  INFO 16116 --- [ionShutdownHook] o.a.c.impl.engine.AbstractCamelContext   :     Stopped route1 (timer://route1)
2023-08-07T10:36:35.362+05:30  INFO 16116 --- [ionShutdownHook] o.a.c.impl.engine.AbstractCamelContext   :     Stopped route3 (timer://route3)
2023-08-07T10:36:35.362+05:30  INFO 16116 --- [ionShutdownHook] o.a.c.impl.engine.AbstractCamelContext   :     Stopped route2 (timer://route2)
2023-08-07T10:36:35.366+05:30  INFO 16116 --- [ionShutdownHook] o.a.c.impl.engine.AbstractCamelContext   : Apache Camel 4.0.0-RC1 (camel-1) shutdown in 10ms (uptime:10s)

Process finished with exit code 130
```
------
# Stop Router
* Write route - [StopRoute.java](src/main/java/com/java/route/StopRoute.java)
* Processor to stop this route after 5 executions - [StopRouteProcessor.java](src/main/java/com/java/processor/StopRouteProcessor.java)
------
# Kafka Router
* Add dependency in pom or gradle file - [pom.xml](pom.xml)
```
<dependency>
    <groupId>org.apache.camel.springboot</groupId>
    <artifactId>camel-kafka-starter</artifactId>
</dependency>
```
* Add following properties in [application yaml or properties file](src/main/resources/application.yml)
```
camel:
  component:
    kafka:
      brokers: localhost:29092
      auto-offset-reset: earliest
  health.enabled: false
```
* [KafkaRouteComponent.java](src/main/java/com/java/processor/KafkaRouteComponent.java) used in route to build message
* Write route - [KafkaRoute.java](src/main/java/com/java/route/KafkaRoute.java)
* Property to enable/disable this route `app.kafka.route` - [application.yml](src/main/resources/application.yml)
------
# Kafka Route Send List with key
* Add dependency in pom or gradle file - [pom.xml](pom.xml)
```
<dependency>
    <groupId>org.apache.camel.springboot</groupId>
    <artifactId>camel-kafka-starter</artifactId>
</dependency>
```
* Add following properties in [application yaml or properties file](src/main/resources/application.yml)
```
camel:
  component:
    kafka:
      brokers: localhost:29092
      auto-offset-reset: earliest
  health.enabled: false
```
* [KafkaRouteComponent.java](src/main/java/com/java/processor/KafkaRouteComponent.java) - `kafkaMessageList` - used in route to build messages
* Write route - `kafkaRouteList` - [KafkaRoute.java](src/main/java/com/java/route/KafkaRoute.java)
* Property to enable/disable this route `app.kafka.route.list` - [application.yml](src/main/resources/application.yml)
------
# REST clients for the rest-api project
Clients in `com.java.restapi` call the controllers in the [rest-api](../../rest-api/rest-api) project. One client class per controller. HTTP calls go through Camel (`camel-http-starter`) in [CamelHttpClient.java](src/main/java/com/java/restapi/CamelHttpClient.java).

* [GetControllerClient.java](src/main/java/com/java/restapi/GetControllerClient.java) — `/get/api`
* [PostControllerClient.java](src/main/java/com/java/restapi/PostControllerClient.java) — `/post/api`
* [PutControllerClient.java](src/main/java/com/java/restapi/PutControllerClient.java) — `/put/api`
* [DeleteControllerClient.java](src/main/java/com/java/restapi/DeleteControllerClient.java) — `/delete/api`
* [HateoasControllerClient.java](src/main/java/com/java/restapi/HateoasControllerClient.java) — `/hateoas/api`
* [I18NControllerClient.java](src/main/java/com/java/restapi/I18NControllerClient.java) — `/i18n/api` (`Accept-Language`, for example `fr`)
* [VersioningControllerClient.java](src/main/java/com/java/restapi/VersioningControllerClient.java) — `/versioning/api` (URL, query param `VERSION`, header `X-API-VERSION`, media type)
* [H2ControllerClient.java](src/main/java/com/java/restapi/H2ControllerClient.java) — `/h2/apis` (`joiningDate` format `ddMMyyyy`)

Request and response types are in [src/main/java/com/java/restapi/model](src/main/java/com/java/restapi/model). `HateoasControllerClient.saveStudent` returns [CreatedResponse.java](src/main/java/com/java/restapi/model/CreatedResponse.java) (201 status and `Location` header).

Base URL property `app.rest-api.base-url` in [application.yml](src/main/resources/application.yml). This app and rest-api both listen on port `9000`, so change one port before running both. Otherwise these clients call this app.
------
# Camel routes calling those REST clients
Each route is a timer that calls the matching client with `.bean(...)` and logs the body. Period is `app.rest-api.timer.period` (milliseconds). Routes are off until the matching flag is `true`.

* [GetControllerRoute.java](src/main/java/com/java/route/GetControllerRoute.java) — `app.rest-api.get.route`
* [PostControllerRoute.java](src/main/java/com/java/route/PostControllerRoute.java) — `app.rest-api.post.route`
* [PutControllerRoute.java](src/main/java/com/java/route/PutControllerRoute.java) — `app.rest-api.put.route`
* [DeleteControllerRoute.java](src/main/java/com/java/route/DeleteControllerRoute.java) — `app.rest-api.delete.route`
* [HateoasControllerRoute.java](src/main/java/com/java/route/HateoasControllerRoute.java) — `app.rest-api.hateoas.route`
* [I18NControllerRoute.java](src/main/java/com/java/route/I18NControllerRoute.java) — `app.rest-api.i18n.route`
* [VersioningControllerRoute.java](src/main/java/com/java/route/VersioningControllerRoute.java) — `app.rest-api.versioning.route`
* [H2ControllerRoute.java](src/main/java/com/java/route/H2ControllerRoute.java) — `app.rest-api.h2.route`

Enable one route in [application.yml](src/main/resources/application.yml). Point `base-url` at rest-api, not at this app:
```
app:
  rest-api:
    base-url: http://localhost:8080
    timer.period: 10000
    get.route: true
```
Sample route step from [GetControllerRoute.java](src/main/java/com/java/route/GetControllerRoute.java):
```
.bean(getControllerClient, "helloWorld()")
.log("helloWorld=${body}")
.bean(getControllerClient, "studentsV5('Ann','Java')")
.log("studentsV5=${body}")
```
This is separate from [RestRoute.java](src/main/java/com/java/route/RestRoute.java), which calls this app's `/example/{id}` endpoint.