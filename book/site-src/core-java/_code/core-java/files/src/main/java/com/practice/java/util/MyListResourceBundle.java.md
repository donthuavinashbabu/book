---
hide:
  - navigation
---

# MyListResourceBundle.java

Source: `core-java/core-java/src/main/java/com/practice/java/util/MyListResourceBundle.java`

```java
package com.practice.java.util;

import java.util.ListResourceBundle;

public class MyListResourceBundle extends ListResourceBundle {

	private Object[][] contents = { { "name", "jack" }, { "course", "Java" }, { "grade", 3.9 } };

	@Override
	protected Object[][] getContents() {
		return contents;
	}

}

```
