---
hide:
  - navigation
---

# Model2.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model2.java`

```java
package com.practice.multi.threading;

import lombok.Getter;

/**
 * Using synchronized method to avoid race condition on critical section
 */
public class Model2 {

	@Getter
	private long count = 0;

	public synchronized void increment() {
		count++;
	}
}

```
