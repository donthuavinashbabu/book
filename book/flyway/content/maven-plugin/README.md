# With H2 database
* Add h2 dependency - refer [pom.xml](pom.xml)

## Different methods of configuration
* configure the plugin directly via the <configuration> tag. Refer `flyway-maven-plugin` in [pom.xml](pom.xml)
* Maven Properties. Refer properties in [pom.xml](pom.xml)
* External configuration file. Refer [flyway-h2.conf](flyway-h2.conf) This file should be in root directory
* The default configuration file name is flyway.conf and by default it loads configuration files from:
  * installDir/conf/flyway.conf 
  * userhome/flyway.conf 
  * workingDir/flyway.conf
* Give external file name during run `$ mvn -Dflyway.configFiles=customConfig.conf`
* System Properties
* all configuration properties can also be specified as system properties when invoking Maven on the command line
```
$ mvn -Dflyway.user=databaseUser -Dflyway.password=databasePassword -Dflyway.schemas=schemaName
```
* The following is an order of precedence when a configuration is specified in more than one way:
  * System properties 
  * External configuration file 
  * Maven properties 
  * Plugin configuration

flyway.conf - in memory H2 DB
```
flyway.user=user1
flyway.password=Password1
flyway.schemas=appDb1
flyway.url=jdbc:h2:mem:DATABASE
flyway.locations=filesystem:db/migration
```

flyway.conf - file based H2 DB
```
flyway.user=user1
flyway.password=Password1
flyway.schemas=appDb1
flyway.url=jdbc:h2:file:C:/data/testdb;DATABASE_TO_UPPER=false;AUTO_SERVER=TRUE;MODE=PostgreSQL
flyway.locations=filesystem:db/migration
```
* Migration files - Refer files in [db/migration](db/migration)
* Run migration using below command
```
mvn clean flyway:migrate -Dflyway.configFiles=flyway-h2.conf
```

---
# With Mysql
* Add following dependencies
```
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-mysql</artifactId>
  <version>11.17.0</version>
</dependency>

<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <version>9.7.0</version>
</dependency>
```
* Create conf file - [flyway-mysql.conf](flyway-mysql.conf)
* Run command
```
mvn flyway:migrate -Dflyway.configFiles=flyway-mysql.conf
```

---
# With PostgreSQL
* Add below dependencies
```
<!-- 1. PostgreSQL JDBC Driver -->
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <version>42.7.4</version>
  <scope>runtime</scope>
</dependency>

<!-- 3. Flyway Extension required explicitly for PostgreSQL support -->
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-database-postgresql</artifactId>
  <version>13.10.0</version>
  <scope>runtime</scope>
</dependency>
```
* Create conf file - [flyway-postgresql.conf](flyway-postgresql.conf)
* Run command
```
mvn clean flyway:migrate -Dflyway.configFiles=flyway-postgresql.conf
```

---
# Callbacks
Java and SQL callbacks are configured in [flyway-h2.conf](flyway-h2.conf), [flyway-mysql.conf](flyway-mysql.conf), and [flyway-postgresql.conf](flyway-postgresql.conf). Both can be active for the same migrate.

## Java callback
* Implementation - [ExampleFlywayCallback.java](src/main/java/com/avinashbabu/callbacks/ExampleFlywayCallback.java)
* It implements `org.flywaydb.core.api.callback.Callback` and handles `AFTER_EACH_MIGRATE`
* Register the class with `flyway.callbacks`
```
flyway.callbacks=com.avinashbabu.callbacks.ExampleFlywayCallback
```
* The Flyway Maven plugin loads `target/classes`, so compile the project before migrate
```
mvn clean compile flyway:migrate -Dflyway.configFiles=flyway-h2.conf
```
* `handle` logs `handle method` once for each migration script that is applied

## SQL callback
* Folder - [db/callbacks](db/callbacks)
* Register the folder with `flyway.callbackLocations`
```
flyway.callbackLocations=filesystem:db/callbacks
```
* Name each script with the event id. An optional description goes after `__`
```
db/callbacks/afterMigrate.sql
db/callbacks/afterEachMigrate__log.sql
```
* `afterMigrate.sql` runs once after a successful migrate
* `afterEachMigrate__log.sql` runs after each migration script, the same event as `ExampleFlywayCallback`
* These scripts run again on later commands. They are not versioned migrations and are not stored as rows in `flyway_schema_history`
* Use SQL that is valid for the database in the selected conf file