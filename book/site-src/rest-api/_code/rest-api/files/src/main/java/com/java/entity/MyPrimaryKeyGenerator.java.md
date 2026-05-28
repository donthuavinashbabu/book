---
hide:
  - navigation
---

# MyPrimaryKeyGenerator.java

Source: `rest-api/rest-api/src/main/java/com/java/entity/MyPrimaryKeyGenerator.java`

```java
package com.java.entity;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@IdGeneratorType(PrimaryKeyGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface MyPrimaryKeyGenerator {
    String name();
}

```
