---
hide:
  - navigation
---

# log4j.properties

Source: `json-jackson/jackson-json/src/main/resources/log4j.properties`

```properties
log4j.rootLogger = DEBUG, stdout

#Redirect log messages to console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%-5p %C:%L: %m%n
```
