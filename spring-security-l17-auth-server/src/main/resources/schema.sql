drop table if exists client ;
CREATE TABLE client
(
    id bigint primary key ,
    client_id varchar,
    secret varchar,
    grant_type varchar,
    scope varchar
);

