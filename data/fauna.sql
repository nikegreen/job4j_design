create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('red fish',11000,'1010-01-01');
insert into fauna (name, avg_age, discovery_date) values ('white fish',9000,'1002-01-03');
insert into fauna (name, avg_age, discovery_date) values ('gold fish',24000,'2002-02-07');
insert into fauna (name, avg_age) values ('sharc',22000);

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';
