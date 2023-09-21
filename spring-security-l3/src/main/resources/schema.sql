CREATE TABLE users
(
    username varchar not null ,
    password varchar not null ,
    enabled int not null

);


create table authorities
(
    username  varchar(45) not null,
    authority varchar(45) not null
);
