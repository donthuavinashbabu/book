---
hide:
  - navigation
---

# Student.java

Source: `kafka/spring-boot2-kafka-producer/src/main/java/com/app/model/Student.java`

```java
package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private Integer id;
	private String name;
	private String course;

}
```
