---
hide:
  - navigation
---

# Table.java

Source: `core-java/core-java/src/main/java/com/practice/annotations/Table.java`

```java
package com.practice.annotations;

public class Table {

    @Column(name = "col_1", date = "27-08-2019", aliasNames = {"column_1", "column1"})
    private String column1;

    @Column(date = "23-Dec-2024", aliasNames = {"column_2", "COLUMN_2"})
    private String column2;

}
```
