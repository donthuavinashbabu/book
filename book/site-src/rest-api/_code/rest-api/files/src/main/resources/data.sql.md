---
hide:
  - navigation
---

# data.sql

Source: `rest-api/rest-api/src/main/resources/data.sql`

```sql
insert into employee (id, name, joining_date) values (random_uuid(), 'a', current_date());
insert into employee (id, name, joining_date) values (random_uuid(), 'b', current_date());
insert into employee (id, name, joining_date) values (random_uuid(), 'c', current_date());
```
