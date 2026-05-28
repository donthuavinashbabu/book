---
hide:
  - navigation
---

# EmployeeRepository.java

Source: `rest-api/rest-api/src/main/java/com/java/datajpa/h2/EmployeeRepository.java`

```java
package com.java.datajpa.h2;

import com.java.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}
```
