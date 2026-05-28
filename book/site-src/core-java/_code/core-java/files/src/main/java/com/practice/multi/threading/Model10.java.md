---
hide:
  - navigation
---

# Model10.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model10.java`

```java
package com.practice.multi.threading;

import java.util.Map;

public class Model10 {

	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

	public static void put(Map<String, Object> value) {
		threadLocal.set(value);
	}

	public static Map<String, Object> get() {
		return threadLocal.get();
	}
}

```
