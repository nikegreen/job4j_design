drop table product;
drop table type cascade;

create table type(
	id serial primary key, 
	name varchar(255));

create table product(
	id serial primary key, 
	name varchar(255), 
	type_id int references type(id), 
	expired_date date, 
	price int);

insert into type (name) values ('СЫР');
insert into type (name) values ('КОЛБАСА');
insert into type (name) values ('ХЛЕБ');
insert into type (name) values ('МОЛОКО');
insert into type (name) values ('ПИРОГ');

insert into product(name, type_id, expired_date, price) values ('Сыр плавленный',1,'2022-02-09',50);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла',1,'2022-02-19',650);
insert into product(name, type_id, expired_date, price) values ('Сыр голландский',1,'2022-01-09',550);
insert into product(name, type_id, expired_date, price) values ('Сыр с плесенью',1,'2022-01-29',750);
insert into product(name, type_id, expired_date, price) values ('Сыр колбасный',1,'2022-03-01',300);

insert into product(name, type_id, expired_date, price) values ('Колбаса варёная',2,'2022-01-08',300);
insert into product(name, type_id, expired_date, price) values ('Колбаса полукопчёная',2,'2022-03-08',500);
insert into product(name, type_id, expired_date, price) values ('Колбаса копчёная',2,'2022-03-18',600);
insert into product(name, type_id, expired_date, price) values ('Колбаса сырокопчёная',2,'2022-04-08',800);

insert into product(name, type_id, expired_date, price) values ('Хлеб бородинский',3,'2022-02-08',60);
insert into product(name, type_id, expired_date, price) values ('Хлеб пшеничный',3,'2022-01-18',50);
insert into product(name, type_id, expired_date, price) values ('Лаваш',3,'2022-02-23',40);

insert into product(name, type_id, expired_date, price) values ('Молоко сгущёное',4,'2022-06-03',100);
insert into product(name, type_id, expired_date, price) values ('Молоко обезжиренное',4,'2022-01-08',50);
insert into product(name, type_id, expired_date, price) values ('Молоко',4,'2022-01-08',60);
insert into product(name, type_id, expired_date, price) values ('мороженое пломбир',4,'2022-01-08',60);
insert into product(name, type_id, expired_date, price) values ('Молочное мороженое',4,'2022-01-08',60);

insert into product(name, type_id, expired_date, price) values ('Пирог с мясом',5,'2022-02-08',100);
insert into product(name, type_id, expired_date, price) values ('Пирог с горбушей',5,'2022-02-08',100);
insert into product(name, type_id, expired_date, price) values ('Пирог с курицей',5,'2022-02-01',90);
insert into product(name, type_id, expired_date, price) values ('Пирог с курагой',5,'2022-02-14',80);
insert into product(name, type_id, expired_date, price) values ('Пирог с черносливом',5,'2022-02-14',80);
insert into product(name, type_id, expired_date, price) values ('Пирог с вишней',5,'2022-02-14',70);
insert into product(name, type_id, expired_date, price) values ('Пирог с яблоком',5,'2022-02-14',60);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product
join type
on product.type_id = type.id
where type.name = 'СЫР';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select * from product
where name like '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product
where current_date > expired_date;

-- 4. Написать запрос, который выводит самый дорогой продукт.
select * from product
order by price DESC limit 1;

-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select type.name as имя_типа, count(type.name) as количество from product
join type
on product.type_id = type.id
group by type.name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product
join type
on product.type_id = type.id
where type.name = 'СЫР' or type.name = 'МОЛОКО';

-- Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select type.name as имя_типа, count(type.name) as количество from product
join type
on product.type_id = type.id
group by type.name
having count(type.name) < 10;

-- 8. Вывести все продукты и их тип.
select product.name, type.name from product
join type
on product.type_id = type.id;

