---
hide:
  - navigation
---

# MyAutoCloseable3.java

Source: `core-java/core-java/src/main/java/com/practice/exceptions/MyAutoCloseable3.java`

```java
package com.practice.exceptions;

public class MyAutoCloseable3 implements AutoCloseable {

	@Override
	public void close() throws Exception {
		throw new RuntimeException("Exception from MyAutoCloseable close method");
	}

	public void hello() {
		throw new RuntimeException("Exception from MyAutoCloseable hello method");
	}

}

```
