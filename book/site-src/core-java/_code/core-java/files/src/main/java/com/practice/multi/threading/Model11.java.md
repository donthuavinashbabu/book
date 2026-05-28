---
hide:
  - navigation
---

# Model11.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model11.java`

```java
package com.practice.multi.threading;

public class Model11 {

	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "thread local initial value";
		}
	};

	public static void put(String value) {
		threadLocal.set(value);
	}

	public static String get() {
		return threadLocal.get();
	}
}

```
