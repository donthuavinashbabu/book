# Force download dependencies from remote repositories
* Use maven command with `-U` flag
```
mvn -U [goal]
mvn -U clean package
mvn --update-snapshots [goal]
mvn --update-snapshots clean package
```
