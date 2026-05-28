---
hide:
  - navigation
---

# Authors.java

Source: `core-java/core-java/src/main/java/com/practice/annotations/repeating/Authors.java`

```java
package com.practice.annotations.repeating;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Authors {
	Author[] value(); // method name should be value() only
}
```
