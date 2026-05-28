---
hide:
  - navigation
---
# Code Project: in-memory

Module: **spring-security**
Path: `spring-security/in-memory`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="postman/in-memory.postman_collection.json"><a href="files/postman/in-memory.postman_collection.json.md">postman/in-memory.postman_collection.json</a></li>
<li data-filter="src/main/java/com/java/main.java"><a href="files/src/main/java/com/java/Main.java.md">src/main/java/com/java/Main.java</a></li>
<li data-filter="src/main/java/com/java/config/securityconfig.java"><a href="files/src/main/java/com/java/config/SecurityConfig.java.md">src/main/java/com/java/config/SecurityConfig.java</a></li>
<li data-filter="src/main/java/com/java/controller/appcontroller.java"><a href="files/src/main/java/com/java/controller/AppController.java.md">src/main/java/com/java/controller/AppController.java</a></li>
<li data-filter="src/main/resources/application.properties"><a href="files/src/main/resources/application.properties.md">src/main/resources/application.properties</a></li>
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
