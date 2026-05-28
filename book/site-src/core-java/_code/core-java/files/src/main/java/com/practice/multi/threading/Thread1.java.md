---
hide:
  - navigation
---

# Thread1.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Thread1.java`

```java
package com.practice.multi.threading;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Thread1 extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			log.info("i={}", i);
		}
	}
}

```
