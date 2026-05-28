---
hide:
  - navigation
---

# Thread5.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Thread5.java`

```java
package com.practice.multi.threading;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Thread5 extends Thread {

	@SneakyThrows
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			log.info("i={}", i);
			Thread.sleep(1000 * 2);
		}
	}
}

```
