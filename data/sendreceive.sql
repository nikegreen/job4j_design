drop table postalParcel;
drop table people cascade;

create table people(
    id serial primary key,
    name varchar(255)
);

create table postalParcel(
    id serial primary key,
    description varchar(255),
    price float,
	sender_id int references people(id),
	receiver_id int references people(id)
);

insert into people (name) values ('Sveta');
insert into people (name) values ('Lena');
insert into people (name) values ('Misha');
insert into people (name) values ('Oleg');
insert into people (name) values ('Petr');

insert into postalParcel (description, price, sender_id, receiver_id) values ('smartphone',1204,1,2);
insert into postalParcel (description, price, sender_id, receiver_id) values ('walkman',201,1,4);
insert into postalParcel (description, price, sender_id, receiver_id) values ('web-camera',120,4,1);
insert into postalParcel (description, price, sender_id, receiver_id) values ('mixer',50,2,5);

-- список отправителей посылки
select people.name as senders, postalParcel.description as description
from postalParcel
join people
on postalParcel.sender_id = people.id;

-- список получателей посылки
select people.name as receivers, postalParcel.description as description
from postalParcel
join people
on postalParcel.receiver_id = people.id;

-- список всех посылок
select postalParcel.id, description, price, people2.name as sender, people3.name as receiver from postalParcel
join people as people2
on postalParcel.sender_id = people2.id
join people as people3
on postalParcel.receiver_id = people3.id;
