---
hide:
  - navigation
---

# Model9.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model9.java`

```java
package com.practice.multi.threading;

public class Model9 {

	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static void put(String value) {
		threadLocal.set(value);
	}

	public static String get() {
		return threadLocal.get();
	}
}

```
