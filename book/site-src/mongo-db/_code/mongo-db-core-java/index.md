---
hide:
  - navigation
---
# Code Project: mongo-db-core-java

Module: **mongo-db**
Path: `mongo-db/mongo-db-core-java`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="build.gradle"><a href="files/build.gradle.md">build.gradle</a></li>
<li data-filter="src/main/java/com/create/create.java"><a href="files/src/main/java/com/create/Create.java.md">src/main/java/com/create/Create.java</a></li>
<li data-filter="src/main/java/com/delete/delete.java"><a href="files/src/main/java/com/delete/Delete.java.md">src/main/java/com/delete/Delete.java</a></li>
<li data-filter="src/main/java/com/read/read.java"><a href="files/src/main/java/com/read/Read.java.md">src/main/java/com/read/Read.java</a></li>
<li data-filter="src/main/java/com/update/update.java"><a href="files/src/main/java/com/update/Update.java.md">src/main/java/com/update/Update.java</a></li>
<li data-filter="src/main/resources/log4j.properties"><a href="files/src/main/resources/log4j.properties.md">src/main/resources/log4j.properties</a></li>
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
