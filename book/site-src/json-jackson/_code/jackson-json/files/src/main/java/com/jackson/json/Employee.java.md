---
hide:
  - navigation
---

# Employee.java

Source: `json-jackson/jackson-json/src/main/java/com/jackson/json/Employee.java`

```java
package com.jackson.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private Long id;
	private String name;

}

```
