---
hide:
  - navigation
---

# Thread2.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Thread2.java`

```java
package com.practice.multi.threading;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Thread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			log.info("i={}", i);
		}
	}

}

```
