---
hide:
  - navigation
---
# Code Project: csv-processor

Module: **spring-batch**
Path: `spring-batch/csv-processor`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/itools/main.java"><a href="files/src/main/java/com/itools/Main.java.md">src/main/java/com/itools/Main.java</a></li>
<li data-filter="src/main/java/com/itools/component/jobcompletionnotificationlistener.java"><a href="files/src/main/java/com/itools/component/JobCompletionNotificationListener.java.md">src/main/java/com/itools/component/JobCompletionNotificationListener.java</a></li>
<li data-filter="src/main/java/com/itools/component/personitemprocessor.java"><a href="files/src/main/java/com/itools/component/PersonItemProcessor.java.md">src/main/java/com/itools/component/PersonItemProcessor.java</a></li>
<li data-filter="src/main/java/com/itools/config/appconfiguration.java"><a href="files/src/main/java/com/itools/config/AppConfiguration.java.md">src/main/java/com/itools/config/AppConfiguration.java</a></li>
<li data-filter="src/main/java/com/itools/controller/jobcontroller.java"><a href="files/src/main/java/com/itools/controller/JobController.java.md">src/main/java/com/itools/controller/JobController.java</a></li>
<li data-filter="src/main/java/com/itools/record/person.java"><a href="files/src/main/java/com/itools/record/Person.java.md">src/main/java/com/itools/record/Person.java</a></li>
<li data-filter="src/main/resources/application.properties"><a href="files/src/main/resources/application.properties.md">src/main/resources/application.properties</a></li>
<li data-filter="src/main/resources/person-data.csv"><a href="files/src/main/resources/person-data.csv.md">src/main/resources/person-data.csv</a></li>
<li data-filter="src/main/resources/schema-all.sql"><a href="files/src/main/resources/schema-all.sql.md">src/main/resources/schema-all.sql</a></li>
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
