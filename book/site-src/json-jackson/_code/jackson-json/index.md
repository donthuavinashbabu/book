---
hide:
  - navigation
---
# Code Project: jackson-json

Module: **json-jackson**
Path: `json-jackson/jackson-json`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/jackson/json/book.java"><a href="files/src/main/java/com/jackson/json/Book.java.md">src/main/java/com/jackson/json/Book.java</a></li>
<li data-filter="src/main/java/com/jackson/json/days.java"><a href="files/src/main/java/com/jackson/json/Days.java.md">src/main/java/com/jackson/json/Days.java</a></li>
<li data-filter="src/main/java/com/jackson/json/employee.java"><a href="files/src/main/java/com/jackson/json/Employee.java.md">src/main/java/com/jackson/json/Employee.java</a></li>
<li data-filter="src/main/java/com/jackson/json/jacksontest.java"><a href="files/src/main/java/com/jackson/json/JacksonTest.java.md">src/main/java/com/jackson/json/JacksonTest.java</a></li>
<li data-filter="src/main/java/com/jackson/json/jsonidentityinfotest.java"><a href="files/src/main/java/com/jackson/json/JsonIdentityInfoTest.java.md">src/main/java/com/jackson/json/JsonIdentityInfoTest.java</a></li>
<li data-filter="src/main/java/com/jackson/json/student.java"><a href="files/src/main/java/com/jackson/json/Student.java.md">src/main/java/com/jackson/json/Student.java</a></li>
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
