---
hide:
  - navigation
---

# EnumImpl.java

Source: `core-java/core-java/src/main/java/com/practice/enums/EnumImpl.java`

```java
package com.practice.enums;

public enum EnumImpl implements IEnum {
	ONE("value 1"), TWO("value 2");

	private String desc;

	private EnumImpl(String desc) {
		this.desc = desc;
	}

	@Override
	public String getDescription() {
		return desc;
	}

}
```
