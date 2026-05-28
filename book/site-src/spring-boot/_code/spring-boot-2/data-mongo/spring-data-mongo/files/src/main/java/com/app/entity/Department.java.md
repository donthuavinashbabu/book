---
hide:
  - navigation
---

# Department.java

Source: `spring-boot/spring-boot-2/data-mongo/spring-data-mongo/src/main/java/com/app/entity/Department.java`

```java
package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private String name;

    @Field(value = "location")
    private String location;

}

```
