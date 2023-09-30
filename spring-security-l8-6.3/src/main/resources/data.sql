insert into users (id, username, password) values (1, 'bill', '12345');
insert into users (id, username, password) values (2, 'gill', '54321');

-- insert into otps (id, username, otp) values(1, 'test', 'test');

CREATE SEQUENCE OTPS_SEQ
    MINVALUE 1
    START WITH 50
    INCREMENT BY 50

