---
hide:
  - navigation
---
# Code Project: spring-boot2-kafka-producer

Module: **kafka**
Path: `kafka/spring-boot2-kafka-producer`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="files/spring-boot2-kafka-producer.postman_collection.json"><a href="files/files/spring-boot2-kafka-producer.postman_collection.json.md">files/spring-boot2-kafka-producer.postman_collection.json</a></li>
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/app/app.java"><a href="files/src/main/java/com/app/App.java.md">src/main/java/com/app/App.java</a></li>
<li data-filter="src/main/java/com/app/config/appconfig.java"><a href="files/src/main/java/com/app/config/AppConfig.java.md">src/main/java/com/app/config/AppConfig.java</a></li>
<li data-filter="src/main/java/com/app/controller/appcontroller.java"><a href="files/src/main/java/com/app/controller/AppController.java.md">src/main/java/com/app/controller/AppController.java</a></li>
<li data-filter="src/main/java/com/app/model/student.java"><a href="files/src/main/java/com/app/model/Student.java.md">src/main/java/com/app/model/Student.java</a></li>
<li data-filter="src/main/java/com/app/service/kafkaproducer.java"><a href="files/src/main/java/com/app/service/KafkaProducer.java.md">src/main/java/com/app/service/KafkaProducer.java</a></li>
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
