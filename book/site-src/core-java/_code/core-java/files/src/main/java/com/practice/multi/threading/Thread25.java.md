---
hide:
  - navigation
---

# Thread25.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Thread25.java`

```java
package com.practice.multi.threading;

import java.util.concurrent.Callable;

public class Thread25 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
	throw new Exception("Thread25 -> call() -> Exception thrown");
    }

}

```
