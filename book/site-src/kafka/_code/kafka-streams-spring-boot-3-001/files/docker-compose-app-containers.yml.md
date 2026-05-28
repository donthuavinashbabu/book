---
hide:
  - navigation
---

# docker-compose-app-containers.yml

Source: `kafka/kafka-streams-spring-boot-3-001/docker-compose-app-containers.yml`

```yaml
version: '2'
services:
  kafkaStreamSpringBoot3:
    image: donthuavinashbabu/kafka-stream-spring-boot-3-001:3
    container_name: kafkaStreamSpringBoot3001
    ports:
      - 9001:9000
    networks:
      - my_network_1

  kafkaProducer:
    image: donthuavinashbabu/kafka-example-001
    container_name: kafkaProducer001
    depends_on:
      - kafkaStreamSpringBoot3
    networks:
      - my_network_1

networks:
  my_network_1:
    external: true
```
