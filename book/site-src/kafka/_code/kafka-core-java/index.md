---
hide:
  - navigation
---
# Code Project: kafka-core-java

Module: **kafka**
Path: `kafka/kafka-core-java`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="dockerfile"><a href="files/Dockerfile.md">Dockerfile</a></li>
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/app/avro/model/user.java"><a href="files/src/main/java/com/app/avro/model/User.java.md">src/main/java/com/app/avro/model/User.java</a></li>
<li data-filter="src/main/java/com/kafka/adminclienttest.java"><a href="files/src/main/java/com/kafka/AdminClientTest.java.md">src/main/java/com/kafka/AdminClientTest.java</a></li>
<li data-filter="src/main/java/com/kafka/consumertest.java"><a href="files/src/main/java/com/kafka/ConsumerTest.java.md">src/main/java/com/kafka/ConsumerTest.java</a></li>
<li data-filter="src/main/java/com/kafka/employee.java"><a href="files/src/main/java/com/kafka/Employee.java.md">src/main/java/com/kafka/Employee.java</a></li>
<li data-filter="src/main/java/com/kafka/jsondeserializer.java"><a href="files/src/main/java/com/kafka/JsonDeserializer.java.md">src/main/java/com/kafka/JsonDeserializer.java</a></li>
<li data-filter="src/main/java/com/kafka/jsonserializer.java"><a href="files/src/main/java/com/kafka/JsonSerializer.java.md">src/main/java/com/kafka/JsonSerializer.java</a></li>
<li data-filter="src/main/java/com/kafka/multipleconsumersbypartitiontest.java"><a href="files/src/main/java/com/kafka/MultipleConsumersByPartitionTest.java.md">src/main/java/com/kafka/MultipleConsumersByPartitionTest.java</a></li>
<li data-filter="src/main/java/com/kafka/producertest.java"><a href="files/src/main/java/com/kafka/ProducerTest.java.md">src/main/java/com/kafka/ProducerTest.java</a></li>
<li data-filter="src/main/resources/avro/user.avsc"><a href="files/src/main/resources/avro/user.avsc.md">src/main/resources/avro/user.avsc</a></li>
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
