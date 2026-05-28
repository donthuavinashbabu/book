---
hide:
  - navigation
---

# UserRepository.java

Source: `spring-boot/spring-boot-3/jwt/src/main/java/com/java/UserRepository.java`

```java
package com.java;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
}
```
