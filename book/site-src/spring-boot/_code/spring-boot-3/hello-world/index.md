---
hide:
  - navigation
---
# Code Project: spring-boot-3/hello-world

Module: **spring-boot**
Path: `spring-boot/spring-boot-3/hello-world`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="dockerfile"><a href="files/Dockerfile.md">Dockerfile</a></li>
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/java/application.java"><a href="files/src/main/java/com/java/Application.java.md">src/main/java/com/java/Application.java</a></li>
<li data-filter="src/main/resources/application.yml"><a href="files/src/main/resources/application.yml.md">src/main/resources/application.yml</a></li>
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
