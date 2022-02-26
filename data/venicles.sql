drop table venicle;
drop table shassi cascade;
drop table enjine cascade;
drop table transmission cascade;

-- Создать структур данных в базе.
-- Таблицы.
--   Кузов.
create table shassi(
	id serial primary key, 
	name varchar(255));
--   Двигатель	
create table enjine(
	id serial primary key, 
	name varchar(255));
--   Коробка передач.
create table transmission(
	id serial primary key, 
	name varchar(255));
-- Создать структуру Машина. Машина не может существовать без данных из п.1.	
create table venicle(
	id serial primary key, 
	name varchar(255),
	shassi_id int references shassi(id),
	enjine_id int references enjine(id),
	transmission_id int references transmission(id)
);
-- Заполнить таблицы через insert. 
insert into shassi (name) values ('кабриолет');
insert into shassi (name) values ('седан');
insert into shassi (name) values ('универсал');
insert into shassi (name) values ('хэтчбэк 3дв.');
insert into shassi (name) values ('хэтчбэк 5дв.');
insert into shassi (name) values ('лифтбэк');
insert into shassi (name) values ('внедорожник 3дв.');
insert into shassi (name) values ('внедорожник 5дв.');
insert into shassi (name) values ('купе');
insert into shassi (name) values ('минивэн');
insert into shassi (name) values ('пикап');
insert into shassi (name) values ('лимузин');
insert into shassi (name) values ('фургон');
insert into shassi (name) values ('хэтчбэк 3дв.');

insert into enjine (name) values ('2TR-FE');
insert into enjine (name) values ('1GD-FTV');
insert into enjine (name) values ('2GR-FKS');
insert into enjine (name) values ('M20A-FKS');
insert into enjine (name) values ('1ZR-FE');

insert into transmission (name) values ('5MT');
insert into transmission (name) values ('6AT');
insert into transmission (name) values ('8AT');
insert into transmission (name) values ('2AT');
insert into transmission (name) values ('4MT');

insert into venicle (name, shassi_id, enjine_id, transmission_id) 
		values ('Toyota Land Cruiser classic', 8, 1, 1);
insert into venicle (name, shassi_id, enjine_id, transmission_id) 
		values ('Toyota Land Cruiser standart', 8, 1, 2);
insert into venicle (name, shassi_id, enjine_id, transmission_id) 
		values ('Toyota Land Cruiser comfort', 8, 2, 2);
insert into venicle (name, shassi_id, enjine_id, transmission_id) 
		values ('Toyota Land Cruiser ', 2, 4, 4);
insert into venicle (name, shassi_id, enjine_id, transmission_id) 
		values ('TOYOTA CAMRY GR SPORT', 2, 3, 3);

-- 2. Создать SQL запросы:
-- 1) Вывести список всех машин и все привязанные к ним детали.
select venicle.id, venicle.name, shassi.name, enjine.name, transmission.name from venicle 
join shassi
on shassi.id = venicle.shassi_id
join enjine
on enjine.id = venicle.enjine_id
join transmission
on transmission.id = venicle.transmission_id;

-- 2) Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, кузова, двигатели, коробки передач.
-- неиспользуемые корпуса
select shassi.id, shassi.name from shassi
left join venicle
on shassi.id = venicle.shassi_id
where venicle.id is null;
-- неиспользуемые двигатели
select enjine.id, enjine.name from enjine
left join venicle
on enjine.id = venicle.enjine_id
where venicle.id is null;
-- неиспользуемые коробки
select transmission.id, transmission.name from transmission
left join venicle
on transmission.id = venicle.transmission_id
where venicle.id is null;


