create table users (id serial primary key, email varchar(127), openKey bigint, contract text);
insert into users (email, openKey, contract) values ('test1\@myserver.ru', '17890456', '0xC078936' );
update users set openKey = '6797002';
delete from users;
