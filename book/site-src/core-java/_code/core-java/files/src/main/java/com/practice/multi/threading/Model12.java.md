---
hide:
  - navigation
---

# Model12.java

Source: `core-java/core-java/src/main/java/com/practice/multi/threading/Model12.java`

```java
package com.practice.multi.threading;

import lombok.Getter;

public class Model12 {

	@Getter
	private String name;

	private boolean isNameSet = false;

	public void setName(String name) {
		this.name = name;
		isNameSet = true;
	}

	public boolean isNameSet() {
		return isNameSet;
	}

}

```
