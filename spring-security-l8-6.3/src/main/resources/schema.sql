drop table if exists users ;
CREATE TABLE users
(
    id bigint primary key ,
    username varchar,
    password varchar
);

drop table if exists otps;
create table otps
(
  id bigint primary key ,
  username varchar,
  otp varchar
);
