---
hide:
  - navigation
---
# Code Project: hikari-cp

Module: **hikari-cp**
Path: `hikari-cp/hikari-cp`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/mysql/mysqldatasource.java"><a href="files/src/main/java/com/mysql/MySQLDataSource.java.md">src/main/java/com/mysql/MySQLDataSource.java</a></li>
<li data-filter="src/main/java/com/mysql/mysqlhikaricptest.java"><a href="files/src/main/java/com/mysql/MySQLHikariCPTest.java.md">src/main/java/com/mysql/MySQLHikariCPTest.java</a></li>
<li data-filter="src/main/java/com/oracle/oracledatasource.java"><a href="files/src/main/java/com/oracle/OracleDataSource.java.md">src/main/java/com/oracle/OracleDataSource.java</a></li>
<li data-filter="src/main/java/com/oracle/oraclehikaricptest.java"><a href="files/src/main/java/com/oracle/OracleHikariCPTest.java.md">src/main/java/com/oracle/OracleHikariCPTest.java</a></li>
<li data-filter="src/main/java/com/postgresql/postgresqldatasource.java"><a href="files/src/main/java/com/postgresql/PostgreSQLDataSource.java.md">src/main/java/com/postgresql/PostgreSQLDataSource.java</a></li>
<li data-filter="src/main/java/com/postgresql/postgresqlhikaricptest.java"><a href="files/src/main/java/com/postgresql/PostgreSQLHikariCPTest.java.md">src/main/java/com/postgresql/PostgreSQLHikariCPTest.java</a></li>
<li data-filter="src/main/resources/log4j.xml"><a href="files/src/main/resources/log4j.xml.md">src/main/resources/log4j.xml</a></li>
<li data-filter="src/test/java/com/java/apptest.java"><a href="files/src/test/java/com/java/AppTest.java.md">src/test/java/com/java/AppTest.java</a></li>
</ul>

<script>
const searchInput = document.getElementById('code-file-search');
const items = Array.from(document.querySelectorAll('#code-file-list li'));
searchInput.addEventListener('input', () => {
  const q = searchInput.value.trim().toLowerCase();
  items.forEach((item) => {
    const text = item.getAttribute('data-filter') || '';
    item.style.display = !q || text.includes(q) ? '' : 'none';
  });
});
</script>
