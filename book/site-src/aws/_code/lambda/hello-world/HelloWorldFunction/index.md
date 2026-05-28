---
hide:
  - navigation
---
# Code Project: lambda/hello-world/HelloWorldFunction

Module: **aws**
Path: `aws/lambda/hello-world/HelloWorldFunction`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/helloworld/app.java"><a href="files/src/main/java/helloworld/App.java.md">src/main/java/helloworld/App.java</a></li>
<li data-filter="src/main/java/helloworld/gatewayresponse.java"><a href="files/src/main/java/helloworld/GatewayResponse.java.md">src/main/java/helloworld/GatewayResponse.java</a></li>
<li data-filter="src/test/java/helloworld/apptest.java"><a href="files/src/test/java/helloworld/AppTest.java.md">src/test/java/helloworld/AppTest.java</a></li>
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
