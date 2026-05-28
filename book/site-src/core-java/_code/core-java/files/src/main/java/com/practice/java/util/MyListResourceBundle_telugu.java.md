---
hide:
  - navigation
---

# MyListResourceBundle_telugu.java

Source: `core-java/core-java/src/main/java/com/practice/java/util/MyListResourceBundle_telugu.java`

```java
package com.practice.java.util;

import java.util.ListResourceBundle;

public class MyListResourceBundle_telugu extends ListResourceBundle {

	private Object[][] contents = { { "name", "john" }, { "course", "Groovy" }, { "grade", 3.8 } };

	@Override
	protected Object[][] getContents() {
		return contents;
	}

}

```
