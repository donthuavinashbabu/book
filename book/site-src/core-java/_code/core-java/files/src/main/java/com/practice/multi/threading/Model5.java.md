---
hide:
  - navigation
---

# Model5.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model5.java`

```java
package com.practice.multi.threading;

import lombok.Getter;

public class Model5 {

	@Getter
	private static long count = 0;

	public static synchronized void increment() {
		count++;
	}
}

```
