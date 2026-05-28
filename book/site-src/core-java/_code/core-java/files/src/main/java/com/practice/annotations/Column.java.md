---
hide:
  - navigation
---

# Column.java

Source: `core-java/core-java/src/main/java/com/practice/annotations/Column.java`

```java
package com.practice.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    String name() default "unknown";
    String date();
    String[] aliasNames();
}

```
