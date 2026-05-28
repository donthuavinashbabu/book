---
hide:
  - navigation
---

# LoginDto.java

Source: `spring-boot/spring-boot-3/jwt/src/main/java/com/java/LoginDto.java`

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
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
```
