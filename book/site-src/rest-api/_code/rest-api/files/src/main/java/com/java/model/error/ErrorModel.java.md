---
hide:
  - navigation
---

# ErrorModel.java

Source: `rest-api/rest-api/src/main/java/com/java/model/error/ErrorModel.java`

```java
package com.java.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {

    private int code;
    private String message;
    private String details;
}

```
