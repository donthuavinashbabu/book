create database if not exists flywaymigration;
create user 'user1'@'localhost' identified by 'Password1';
grant all on flywaymigration.* to 'username'@'localhost';
grant super on *.* to 'user1'@localhost;

use flywaymigration;

select * from flyway_schema_history;

select * from department;

select * from employee;

drop table employee;
drop table department;
drop table flyway_schema_history;