---
hide:
  - navigation
---

# Employee.java

Source: `core-java/core-java/src/main/java/com/practice/reflection/Employee.java`

```java
package com.practice.reflection;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends Person {

	private String department;
}

```
