DELETE
FROM users;
ALTER SEQUENCE user_seq
  RESTART WITH 1;

INSERT INTO users (name, login, password)
VALUES ('User1', 'user1_login', 'password'),
       ('Admin1', 'admin1_login', 'password');

DELETE
FROM votes;
ALTER SEQUENCE vote_seq
  RESTART WITH 1;

INSERT INTO votes (day, decision, user_id)
VALUES ('2018-10-30', 'ACCEPT', 1),
       ('2018-10-29', 'ACCEPT', 2),
       ('2018-10-31', 'REJECT', 2);

