---
hide:
  - navigation
---

# Model8.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model8.java`

```java
package com.practice.multi.threading;

import lombok.Getter;

public class Model8 {

	@Getter
	private volatile long count = 0;

	public void increment() {
		count++;
	}
}

```
