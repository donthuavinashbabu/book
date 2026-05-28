---
hide:
  - navigation
---
# Code Project: cucumber

Module: **cucumber**
Path: `cucumber/cucumber`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/service/calculator.java"><a href="files/src/main/java/com/service/Calculator.java.md">src/main/java/com/service/Calculator.java</a></li>
<li data-filter="src/main/resources/log4j.xml"><a href="files/src/main/resources/log4j.xml.md">src/main/resources/log4j.xml</a></li>
<li data-filter="src/test/java/com/features/runnertest.java"><a href="files/src/test/java/com/features/RunnerTest.java.md">src/test/java/com/features/RunnerTest.java</a></li>
<li data-filter="src/test/java/com/step/defs/calculatorstepdef.java"><a href="files/src/test/java/com/step/defs/CalculatorStepDef.java.md">src/test/java/com/step/defs/CalculatorStepDef.java</a></li>
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
