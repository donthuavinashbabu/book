---
hide:
  - navigation
---

# JwtAuthResponse.java

Source: `spring-boot/spring-boot-3/jwt/src/main/java/com/java/JwtAuthResponse.java`

```java
package com.java;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
```
