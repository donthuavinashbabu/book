-- Copy data from one column to another column for each row
create table table_1
(
    id number primary key,
    column_1 varchar2(50)
);

insert into table_1(id, column_1) values (1, 'a'); 
insert into table_1(id, column_1) values (2, 'b');
insert into table_1(id, column_1) values (3, 'c');
insert into table_1(id, column_1) values (4, 'd');
insert into table_1(id, column_1) values (5, 'e');
commit;

alter table table_1 add column_2 varchar2(50);

update table_1 set column_2 = column_1;

commit;
----------------
-- Copy all records from tab1 to tab2
-- This works only if both tables have exactly the same structure (same number of columns, same data types, same order)
INSERT INTO tab2
SELECT * FROM tab1;

-- If the structures differ, you’ll need to explicitly list the columns
INSERT INTO tab2 (col1, col2, col3)
SELECT col1, col2, col3
FROM tab1;

----------------
-- Create new table tab2 and copy all records from tab1
-- creates tab2 with the same columns as tab1. WHERE 1=0 ensures no data is copied, only the structure
CREATE TABLE tab2 AS
SELECT * FROM tab1 WHERE 1=0;

-- If you want both structure and data copied at once, simply omit the WHERE clause
CREATE TABLE tab2 AS
SELECT * FROM tab1;
