drop table if exists oauth_access_token ;
CREATE TABLE oauth_access_token
(
    token_id varchar primary key ,
    token blob,
    authentication_id varchar,
    user_name varchar,
    client_id varchar,
    authentication blob,
    refresh_token varchar
);

drop table if exists oauth_refresh_token ;
CREATE TABLE oauth_refresh_token
(
    token_id varchar primary key ,
    token blob,
    authentication blob
);
