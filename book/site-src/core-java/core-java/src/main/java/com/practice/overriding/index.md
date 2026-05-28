# Overriding
* Define 2 methods in [Parent1](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Parent1.java.md)
  * method1
  * method2
* Define [Child1](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Child1.java.md) extends [Parent1](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Parent1.java.md)
  * Override method2
* Call method2 from method1
* If we create [Parent1](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Parent1.java.md) object and call `method1` then
  * method1 from [Parent1](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Parent1.java.md) is called
  * method2 from [Parent1](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Parent1.java.md) is called
* If we create [Child1](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Child1.java.md) object and call `method1` then
    * method1 from [Parent1](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Parent1.java.md) is called
    * method2 from [Child1](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Child1.java.md) is called
* Refer [Main](../../../../../../../_code/core-java/files/src/main/java/com/practice/overriding/Main.java.md)