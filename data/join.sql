drop table emploers;
drop table departments cascade;

-- 1. Создать таблицы и заполнить их начальными данными
create table departments(
    id serial primary key,
    name varchar(255));

create table emploers(
    id serial primary key,
    name varchar(255),
	department_id int references departments(id));

insert into departments (name) values ('logistic');
insert into departments (name) values ('human resources');
insert into departments (name) values ('it');
insert into departments (name) values ('menegement');
insert into departments (name) values ('public relations');
insert into departments (name) values ('accounting');
insert into departments (name) values ('legal');
insert into departments (name) values ('finance');
insert into departments (name) values ('production');
insert into departments (name) values ('development centre');
insert into departments (name) values ('quality');

insert into emploers (name, department_id) values ('Ivan Drozdov',1);
insert into emploers (name, department_id) values ('Nina Ivanova',1);
insert into emploers (name, department_id) values ('Sergey Petrov',1);
insert into emploers (name, department_id) values ('Sveta Shmeleva',1);

insert into emploers (name, department_id) values ('Inga Tarasova',2);
insert into emploers (name, department_id) values ('Ekaterina Sidorova',2);
insert into emploers (name, department_id) values ('Sveta Buzova',2);

insert into emploers (name, department_id) values ('Andrey Sherstkov',3);
insert into emploers (name, department_id) values ('Andrey Kurjakov',3);

insert into emploers (name, department_id) values ('Vissarion Miller',4);
insert into emploers (name, department_id) values ('Muhhamed Valuev',4);
insert into emploers (name, department_id) values ('Leonid Shmit',4);
insert into emploers (name, department_id) values ('Marc Zinger',4);
insert into emploers (name, department_id) values ('Eduart Koh',4);

insert into emploers (name, department_id) values ('Irina Verbitsky',5);
insert into emploers (name, department_id) values ('Eleonora Shveik',5);
insert into emploers (name, department_id) values ('Polina Grigorjeva',5);

insert into emploers (name, department_id) values ('Eleva Valieva',6);
insert into emploers (name, department_id) values ('Olga Mishkina',6);

insert into emploers (name, department_id) values ('Kristina Goldberg',7);
insert into emploers (name, department_id) values ('Marc Mondeshtamm',7);

insert into emploers (name, department_id) values ('Kris Goldberg',8);
insert into emploers (name, department_id) values ('Eric Zucermann',8);

insert into emploers (name, department_id) values ('Anton Plaksin',9);
insert into emploers (name, department_id) values ('Sergey Vasin',9);
insert into emploers (name, department_id) values ('Oleg Kuznetsov',9);
insert into emploers (name, department_id) values ('Anton Plotnikov',9);

insert into emploers (name, department_id) values ('Egor Sikorskiy',10);
insert into emploers (name, department_id) values ('Elon Mask',10);

--insert into emploers (name, department_id) values ('Petr Yachenko',11);

--2. Выполнить запросы с left, rigth, full, cross соединениями
-- с left
select * from emploers
left join departments
on emploers.department_id = departments.id;

select * from departments
left join emploers
on emploers.department_id = departments.id;

-- c rigth
select * from emploers
right join departments
on emploers.department_id = departments.id;

select * from departments
right join emploers
on emploers.department_id = departments.id;

-- c full
select * from emploers
full join departments
on emploers.department_id = departments.id;

select * from departments
right join emploers
on emploers.department_id = departments.id;

--cross 
select * from emploers
cross join departments;

select * from departments
cross join emploers;

-- 3. Используя left join найти департаменты, у которых нет работников
select * from departments
left join emploers
on emploers.department_id = departments.id
where emploers.department_id is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный). 
-- 1-ая пара.
select * from emploers
left join departments
on emploers.department_id = departments.id;

select * from emploers
right join departments
on emploers.department_id = departments.id
where emploers.department_id is not null;

-- 2-ая пара
select * from departments
left join emploers
on emploers.department_id = departments.id
where emploers.department_id is not null;

select * from departments
right join emploers
on emploers.department_id = departments.id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
drop table teens;
create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(5)
);

insert into teens (name, gender) values ('Grisha', 'man');
insert into teens (name, gender) values ('Lena','woman');
insert into teens (name, gender) values ('Sveta','woman');
insert into teens (name, gender) values ('Oleg','man');
insert into teens (name, gender) values ('Olga','woman');
insert into teens (name, gender) values ('Irina','woman');
insert into teens (name, gender) values ('Petr','man');
insert into teens (name, gender) values ('Ivan','man');

select * from teens as teens2
cross join teens as teens3
where teens2.gender != teens3.gender;

