---
hide:
  - navigation
---
# Code Project: dynamo-db/dynamo-db-practice

Module: **aws**
Path: `aws/dynamo-db/dynamo-db-practice`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/ab/app.java"><a href="files/src/main/java/com/ab/App.java.md">src/main/java/com/ab/App.java</a></li>
<li data-filter="src/test/java/com/ab/dynamodbpracticetest.java"><a href="files/src/test/java/com/ab/DynamoDBPracticeTest.java.md">src/test/java/com/ab/DynamoDBPracticeTest.java</a></li>
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
