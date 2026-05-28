---
hide:
  - navigation
---

# Student.java

Source: `core-java/core-java/src/main/java/com/practice/reflection/Student.java`

```java
package com.practice.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    public String name;
    private String[] courses;
    public Date joiningDate;
    private Double grade;
}

```
