create table roles (
id serial primary key,
	roleName text
);

create table users (
id serial primary key,
	userName text,
	roles_id int references roles(id)
);

create table rules (
id serial primary key,
	ruleName text
);

create table rolesRules (
id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table category (
id serial primary key,
	categoryName text
);

create table stateTable (
	id serial primary key,
	stateName text
);

create table item (
id serial primary key,
	users_id int references users(id),
	category_id int references category(id),
	state_id int references stateTable(id)
);

create table commentsTable (
id serial primary key,
	commentsText text,
	item_id int references item(id)
);

create table attachs (
id serial primary key,
	attachFileName text,
	item_id int references item(id)
);