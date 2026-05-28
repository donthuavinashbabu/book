---
hide:
  - navigation
---

# Author.java

Source: `core-java/core-java/src/main/java/com/practice/annotations/repeating/Author.java`

```java
package com.practice.annotations.repeating;

import java.lang.annotation.Repeatable;

@Repeatable(Authors.class)
public @interface Author {

	String name() default "unknown";
}
```
