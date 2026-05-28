---
hide:
  - navigation
---

# Parent1.java

Source: `core-java/core-java/src/main/java/com/practice/overriding/Parent1.java`

```java
package com.practice.overriding;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parent1 {

    public void method1() {
        log.info("test1");
        method2();
    }

    public void method2() {
        log.info("test2");
    }
}

```
