---
hide:
  - navigation
---
# Code Project: deserialization-error-handling-spring-boot

Module: **kafka**
Path: `kafka/deserialization-error-handling-spring-boot`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="build.gradle"><a href="files/build.gradle.md">build.gradle</a></li>
<li data-filter="files/deserialization-error-handling-spring-boot.postman_collection.json"><a href="files/files/deserialization-error-handling-spring-boot.postman_collection.json.md">files/deserialization-error-handling-spring-boot.postman_collection.json</a></li>
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/app/app.java"><a href="files/src/main/java/com/app/App.java.md">src/main/java/com/app/App.java</a></li>
<li data-filter="src/main/java/com/app/avro/model/user.java"><a href="files/src/main/java/com/app/avro/model/User.java.md">src/main/java/com/app/avro/model/User.java</a></li>
<li data-filter="src/main/java/com/app/config/appconfig.java"><a href="files/src/main/java/com/app/config/AppConfig.java.md">src/main/java/com/app/config/AppConfig.java</a></li>
<li data-filter="src/main/java/com/app/controller/appcontroller.java"><a href="files/src/main/java/com/app/controller/AppController.java.md">src/main/java/com/app/controller/AppController.java</a></li>
<li data-filter="src/main/java/com/app/kafka/avroproducercomponent.java"><a href="files/src/main/java/com/app/kafka/AvroProducerComponent.java.md">src/main/java/com/app/kafka/AvroProducerComponent.java</a></li>
<li data-filter="src/main/java/com/app/kafka/consumercomponent.java"><a href="files/src/main/java/com/app/kafka/ConsumerComponent.java.md">src/main/java/com/app/kafka/ConsumerComponent.java</a></li>
<li data-filter="src/main/java/com/app/kafka/stringproducercomponent.java"><a href="files/src/main/java/com/app/kafka/StringProducerComponent.java.md">src/main/java/com/app/kafka/StringProducerComponent.java</a></li>
<li data-filter="src/main/resources/application.yml"><a href="files/src/main/resources/application.yml.md">src/main/resources/application.yml</a></li>
<li data-filter="src/main/resources/avro/user.avsc"><a href="files/src/main/resources/avro/user.avsc.md">src/main/resources/avro/user.avsc</a></li>
<li data-filter="src/test/java/com/app/config/appconfigtest.java"><a href="files/src/test/java/com/app/config/AppConfigTest.java.md">src/test/java/com/app/config/AppConfigTest.java</a></li>
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
