---
hide:
  - navigation
---
# Code Project: spring-boot-2/data-mongo/spring-data-mongo

Module: **spring-boot**
Path: `spring-boot/spring-boot-2/data-mongo/spring-data-mongo`

Use the search box below to filter code files. All files are also indexed by the site search.

<input type="search" id="code-file-search" placeholder="Search code files..." style="width:100%;padding:0.5rem;margin:0.5rem 0;" />

<ul id="code-file-list">
<li data-filter="files/spring-data-mongo.postman_collection.json"><a href="files/files/spring-data-mongo.postman_collection.json.md">files/spring-data-mongo.postman_collection.json</a></li>
<li data-filter="pom.xml"><a href="files/pom.xml.md">pom.xml</a></li>
<li data-filter="src/main/java/com/app/app.java"><a href="files/src/main/java/com/app/App.java.md">src/main/java/com/app/App.java</a></li>
<li data-filter="src/main/java/com/app/controller/studentcontroller.java"><a href="files/src/main/java/com/app/controller/StudentController.java.md">src/main/java/com/app/controller/StudentController.java</a></li>
<li data-filter="src/main/java/com/app/entity/address.java"><a href="files/src/main/java/com/app/entity/Address.java.md">src/main/java/com/app/entity/Address.java</a></li>
<li data-filter="src/main/java/com/app/entity/department.java"><a href="files/src/main/java/com/app/entity/Department.java.md">src/main/java/com/app/entity/Department.java</a></li>
<li data-filter="src/main/java/com/app/entity/student.java"><a href="files/src/main/java/com/app/entity/Student.java.md">src/main/java/com/app/entity/Student.java</a></li>
<li data-filter="src/main/java/com/app/entity/subject.java"><a href="files/src/main/java/com/app/entity/Subject.java.md">src/main/java/com/app/entity/Subject.java</a></li>
<li data-filter="src/main/java/com/app/model/paginatedresponse.java"><a href="files/src/main/java/com/app/model/PaginatedResponse.java.md">src/main/java/com/app/model/PaginatedResponse.java</a></li>
<li data-filter="src/main/java/com/app/repository/studentrepository.java"><a href="files/src/main/java/com/app/repository/StudentRepository.java.md">src/main/java/com/app/repository/StudentRepository.java</a></li>
<li data-filter="src/main/java/com/app/service/studentservice.java"><a href="files/src/main/java/com/app/service/StudentService.java.md">src/main/java/com/app/service/StudentService.java</a></li>
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
