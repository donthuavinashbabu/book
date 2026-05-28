---
hide:
  - navigation
---

# Model6.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model6.java`

```java
package com.practice.multi.threading;

import lombok.Getter;

public class Model6 {

	@Getter
	private static long count;

	public static void increment() {
		synchronized (Model6.class) {
			count++;
		}
	}
}

```
