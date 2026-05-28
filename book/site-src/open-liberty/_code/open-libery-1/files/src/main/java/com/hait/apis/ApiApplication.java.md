---
hide:
  - navigation
---

# ApiApplication.java

Source: `open-liberty/open-libery-1/src/main/java/com/hait/apis/ApiApplication.java`

```java
package com.hait.apis;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// @ApplicationPath annotation for the URL path
@ApplicationPath("/api/v1")
public class ApiApplication extends Application {
}
```
