---
hide:
  - navigation
---

# RoleRepository.java

Source: `spring-boot/spring-boot-3/jwt/src/main/java/com/java/RoleRepository.java`

```java
package com.java;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
```
