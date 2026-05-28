---
hide:
  - navigation
---
# Code Project: kafka-streams-001

Module: **kafka**
Path: `kafka/kafka-streams-001`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/java/example1.java"><a href="files/src/main/java/com/java/Example1.java.md">src/main/java/com/java/Example1.java</a></li>
<li data-filter="src/main/java/com/java/example2.java"><a href="files/src/main/java/com/java/Example2.java.md">src/main/java/com/java/Example2.java</a></li>
<li data-filter="src/main/java/com/java/example3.java"><a href="files/src/main/java/com/java/Example3.java.md">src/main/java/com/java/Example3.java</a></li>
<li data-filter="src/main/java/com/java/example4.java"><a href="files/src/main/java/com/java/Example4.java.md">src/main/java/com/java/Example4.java</a></li>
<li data-filter="src/main/java/com/java/example5.java"><a href="files/src/main/java/com/java/Example5.java.md">src/main/java/com/java/Example5.java</a></li>
<li data-filter="src/main/java/com/java/favouritecolortest.java"><a href="files/src/main/java/com/java/FavouriteColorTest.java.md">src/main/java/com/java/FavouriteColorTest.java</a></li>
<li data-filter="src/main/java/com/java/processor1.java"><a href="files/src/main/java/com/java/Processor1.java.md">src/main/java/com/java/Processor1.java</a></li>
<li data-filter="src/main/java/com/java/processor2.java"><a href="files/src/main/java/com/java/Processor2.java.md">src/main/java/com/java/Processor2.java</a></li>
<li data-filter="src/main/java/com/java/processor3.java"><a href="files/src/main/java/com/java/Processor3.java.md">src/main/java/com/java/Processor3.java</a></li>
<li data-filter="src/main/java/com/java/processor4.java"><a href="files/src/main/java/com/java/Processor4.java.md">src/main/java/com/java/Processor4.java</a></li>
<li data-filter="src/main/java/com/java/processor5.java"><a href="files/src/main/java/com/java/Processor5.java.md">src/main/java/com/java/Processor5.java</a></li>
<li data-filter="src/main/java/com/java/processor6.java"><a href="files/src/main/java/com/java/Processor6.java.md">src/main/java/com/java/Processor6.java</a></li>
<li data-filter="src/main/resources/log4j.xml"><a href="files/src/main/resources/log4j.xml.md">src/main/resources/log4j.xml</a></li>
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
