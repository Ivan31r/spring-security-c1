insert into "users" (username, password, enabled) values ('bill', '12345',1);
insert into "users" (username, password, enabled) values ('gill', '54321',1);

insert into authorities (username, authority) values ('bill', 'write');
insert into authorities (username, authority) values ('gill', 'read');

