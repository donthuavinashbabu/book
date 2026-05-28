---
hide:
  - navigation
---

# AppController.java

Source: `spring-security/credentials-in-properties/src/main/java/com/java/controller/AppController.java`

```java
package com.java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class AppController {

    @GetMapping(value = "/status", produces = TEXT_PLAIN_VALUE)
    public String status() {
        return "UP";
    }

}
```
