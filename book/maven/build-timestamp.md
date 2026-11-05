# Using maven build timestamp in jar file name
* Define property for timestamp format
```
<properties>
	<maven.build.timestamp.format>yyyy-MM-dd-HH-mm</maven.build.timestamp.format>
</properties>
```
* Use in-build variable `${maven.build.timestamp}`
```
<build>
	<finalName>${project.artifactId}-${project.version}-${maven.build.timestamp}</finalName>
</build>
```
