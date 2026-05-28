---
hide:
  - navigation
---

# Model7.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model7.java`

```java
package com.practice.multi.threading;

import lombok.Getter;

public class Model7 {

	@Getter
	private long count;

	public void increment() {
		synchronized (this) {
			count++;
		}
	}
}

```
