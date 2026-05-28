---
hide:
  - navigation
---

# PaginatedResponse.java

Source: `spring-boot/spring-boot-2/data-mongo/spring-data-mongo/src/main/java/com/app/model/PaginatedResponse.java`

```java
package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginatedResponse<T> {

    private Integer pageNumber;
    private Integer pageSize;
    private T t;
    private List<T> list;
    private Long totalSize;
    private Integer resultSize;

}

```
