---
hide:
  - navigation
---

# Subject.java

Source: `spring-boot/spring-boot-2/data-mongo/spring-data-mongo/src/main/java/com/app/entity/Subject.java`

```java
package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    private String name;
    private Double grade;
    private Double marks;
}

```
