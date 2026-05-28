---
hide:
  - navigation
---

# MyAutoCloseable2.java

Source: `core-java/core-java/src/main/java/com/practice/exceptions/MyAutoCloseable2.java`

```java
package com.practice.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyAutoCloseable2 implements AutoCloseable {

	@Override
	public void close() throws Exception {
		throw new RuntimeException("Exception from MyAutoCloseable close method");
	}

	public void hello() {
		log.info("Hello from MyAutoCloseable");
	}

}

```
