create table senders (
    id serial primary key,
    name text,
    outbox_id int
);

create table receivers (
    id serial primary key,
    name text,
    inbox_id int
);

insert into senders (name, outbox_id) values ('ivan',1204);
insert into senders (name, outbox_id) values ('misha',201);
insert into senders (name, outbox_id) values ('oleg',320);

insert into receivers (name, inbox_id) values ('sveta',127);
insert into receivers (name, inbox_id) values ('lena',201);
insert into receivers (name, inbox_id) values ('misha',320);

select senders.name as sender, senders.outbox_id as box_id, receivers.name as receiver
from senders join  receivers
on senders.outbox_id = receivers.inbox_id;


