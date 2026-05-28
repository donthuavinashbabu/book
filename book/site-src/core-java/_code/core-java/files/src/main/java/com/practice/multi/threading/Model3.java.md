---
hide:
  - navigation
---

# Model3.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model3.java`

```java
package com.practice.multi.threading;

import lombok.Getter;

public class Model3 {

	@Getter
	private long counter1 = 0;

	@Getter
	private long counter2 = 0;

	public synchronized void incrementCounter1() {
		counter1++;
	}

	public synchronized void incrementCounter2() {
		counter2++;
	}
}

```
