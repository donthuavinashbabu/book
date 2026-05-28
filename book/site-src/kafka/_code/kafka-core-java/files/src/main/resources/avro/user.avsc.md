---
hide:
  - navigation
---

# user.avsc

Source: `kafka/kafka-core-java/src/main/resources/avro/user.avsc`

```json
{
  "namespace": "com.app.avro.model",
  "type": "record",
  "name": "User",
  "fields": [
    {
      "name": "name",
      "type": "string",
      "avro.java.string": "String"
    },
    {
      "name": "age",
      "type": "int"
    }
  ]
}
```
