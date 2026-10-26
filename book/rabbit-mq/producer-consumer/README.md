# Producer Consumer

Spring Boot application that publishes an `Employee` to RabbitMQ and consumes it from a bound queue. A direct exchange routes each message with a single routing key.

## Prerequisites

- Java 21
- Maven 3.9+
- RabbitMQ on `localhost:5672` (default user `guest` / `guest`)

The management UI is at [http://localhost:15672](http://localhost:15672).

## Run

```bash
mvn spring-boot:run
```

The HTTP server listens on port `8080`. On startup the application declares the queue, the direct exchange, and the binding from [`application.properties`](src/main/resources/application.properties).

Run the context test with:

```bash
mvn test
```

## Send a message

`POST /send-employee` accepts an `Employee` JSON body and publishes it. The same request is in the [producer-consumer Postman collection](postman/producer-consumer.postman_collection.json).

```bash
curl -X POST http://localhost:8080/send-employee \
  -H "Content-Type: application/json" \
  -d "{\"id\":1,\"name\":\"Ada Lovelace\",\"department\":\"Engineering\"}"
```

`Employee` fields:

| Field | Type |
| --- | --- |
| `id` | number |
| `name` | string |
| `department` | string |

The listener logs the received employee:

```text
Received employee from queue=employeeQueue, message=Employee(id=1, name=Ada Lovelace, department=Engineering)
```

## Message flow

1. `EmployeeProducer` calls `AmqpTemplate.convertAndSend` with the exchange and routing key.
2. `JacksonJsonMessageConverter` writes the `Employee` as JSON. Spring Boot 4 uses Jackson 3, so the converter is `JacksonJsonMessageConverter`.
3. The direct exchange `employeeExchange` routes the message to `employeeQueue` when the routing key is `employeeRoutingKey`.
4. `EmployeeConsumer.handleMessage` receives an `Employee`. The listener parameter must be `Employee`, because the body is a JSON object.

## Configuration

Values in [`src/main/resources/application.properties`](src/main/resources/application.properties):

| Property | Default | Purpose |
| --- | --- | --- |
| `server.port` | `8080` | HTTP port |
| `spring.rabbitmq.host` | `localhost` | Broker host |
| `spring.rabbitmq.port` | `5672` | AMQP port |
| `spring.rabbitmq.username` | `guest` | Broker user |
| `spring.rabbitmq.password` | `guest` | Broker password |
| `rabbitmq.queue` | `employeeQueue` | Queue name |
| `rabbitmq.exchange` | `employeeExchange` | Direct exchange name |
| `rabbitmq.routingkey` | `employeeRoutingKey` | Binding and publish routing key |

## Layout

```text
src/main/java/com/practice
├── Main.java                      Application entry point
├── config/RabbitMqConfig.java     Queue, exchange, binding, JSON converter
├── producer/EmployeeProducer.java POST /send-employee
├── consumer/EmployeeConsumer.java Queue listener
└── model/Employee.java            Message payload
```
