create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('smartphone',1204);
insert into devices (name, price) values ('walkman',201);
insert into devices (name, price) values ('web-camera',120);
insert into devices (name, price) values ('mixer',50);

insert into people (name) values ('sveta');
insert into people (name) values ('lena');
insert into people (name) values ('misha');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (2, 2);
insert into devices_people (device_id, people_id) values (3, 3);
insert into devices_people (device_id, people_id) values (3, 1);
insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (4, 3);

-- средняя цена всех устройств (в таблице devices_people)
select avg(devices.price) 
from devices_people 
join devices 
on devices.id = devices_people.device_id;

-- средняя цена всех устройств у каждого человека 
select avg(devices.price) 
from devices_people 
join devices 
on devices.id = devices_people.device_id 
group by people_id;

-- средняя цена всех устройств у каждого человека и больше 5000
select avg(devices.price)
from devices_people
join devices 
on devices.id = devices_people.device_id
group by people_id
having avg(devices.price) > 5000;

