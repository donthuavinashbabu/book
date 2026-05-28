---
hide:
  - navigation
---

# Processor3.java

Source: `kafka/kafka-streams-001/src/main/java/com/java/Processor3.java`

```java
package com.java;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.processor.api.ContextualProcessor;
import org.apache.kafka.streams.processor.api.Record;

@Slf4j
public class Processor3 extends ContextualProcessor<String, String, String, String> {

    @Override
    public void process(Record<String, String> record) {
        log.info("key={}, value={}, timestamp={}", record.key(), record.value(), record.timestamp());
    }

    @Override
    public void close() {
        super.close();
    }
}

```
