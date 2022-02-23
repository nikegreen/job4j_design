insert into roles(roleName) values ('admin');
insert into roles(roleName) values ('user');
insert into roles(roleName) values ('guest');

insert into users(userName, roles_id) values ('admin', 1);
insert into users(userName, roles_id) values ('user', 1);
insert into users(userName, roles_id) values ('guest', 1);

insert into rules(ruleName) values ('deny');
insert into rules(ruleName) values ('read');
insert into rules(ruleName) values ('full');

insert into rolesRules(roles_id, rules_id) values (1,3);
insert into rolesRules(roles_id, rules_id) values (2,2);
insert into rolesRules(roles_id, rules_id) values (3,1);

insert into category(categoryName) values ('speed');
insert into category(categoryName) values ('normal');

insert into stateTable(stateName) values ('unread');
insert into stateTable(stateName) values ('in process');
insert into stateTable(stateName) values ('ready');

insert into item(users_id, category_id, state_id) values (1, 1, 2);
insert into item(users_id, category_id, state_id) values (2, 2, 3);
insert into item(users_id, category_id, state_id) values (3, 2, 1);

insert into commentsTable(commentsText, item_id) values ('admin files', 1);
insert into commentsTable(commentsText, item_id) values ('user files', 2);
insert into commentsTable(commentsText, item_id) values ('guest files', 3);

insert into attachs(attachFileName, item_id) values ('config.sys', 1);
insert into attachs(attachFileName, item_id) values ('data.xml', 2);
insert into attachs(attachFileName, item_id) values ('readme.txt', 3);
