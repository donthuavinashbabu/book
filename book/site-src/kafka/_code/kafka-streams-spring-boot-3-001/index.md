---
hide:
  - navigation
---
# Code Project: kafka-streams-spring-boot-3-001

Module: **kafka**
Path: `kafka/kafka-streams-spring-boot-3-001`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="dockerfile"><a href="files/Dockerfile.md">Dockerfile</a></li>
<li data-filter="docker-compose-app-containers.yml"><a href="files/docker-compose-app-containers.yml.md">docker-compose-app-containers.yml</a></li>
<li data-filter="docker-compose-kafka.yml"><a href="files/docker-compose-kafka.yml.md">docker-compose-kafka.yml</a></li>
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/java/kafkaconfig.java"><a href="files/src/main/java/com/java/KafkaConfig.java.md">src/main/java/com/java/KafkaConfig.java</a></li>
<li data-filter="src/main/java/com/java/main.java"><a href="files/src/main/java/com/java/Main.java.md">src/main/java/com/java/Main.java</a></li>
<li data-filter="src/main/java/com/java/messagecaseconversionprocessor.java"><a href="files/src/main/java/com/java/MessageCaseConversionProcessor.java.md">src/main/java/com/java/MessageCaseConversionProcessor.java</a></li>
<li data-filter="src/main/java/com/java/messagereaderprocessor.java"><a href="files/src/main/java/com/java/MessageReaderProcessor.java.md">src/main/java/com/java/MessageReaderProcessor.java</a></li>
<li data-filter="src/main/java/com/java/topics.java"><a href="files/src/main/java/com/java/Topics.java.md">src/main/java/com/java/Topics.java</a></li>
<li data-filter="src/main/java/com/java/wordcountprocessor.java"><a href="files/src/main/java/com/java/WordCountProcessor.java.md">src/main/java/com/java/WordCountProcessor.java</a></li>
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
