DELETE
FROM user_roles;

DELETE
FROM votes;
ALTER SEQUENCE vote_seq
  RESTART WITH 1;

DELETE
FROM event_days;
ALTER SEQUENCE event_day_seq
  RESTART WITH 1;

DELETE
FROM users;
ALTER SEQUENCE user_seq
  RESTART WITH 1;

INSERT INTO users (name, login, password, enabled)
VALUES ('User1', 'user1_login', 'password', true),
       ('Admin1', 'admin1_login', 'password', true);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

DELETE
FROM events;
ALTER SEQUENCE event_seq
  RESTART WITH 1;

INSERT INTO events (name, description)
VALUES ('событие 1', 'описание события 1'),
       ('событие 2', 'описание события 2');

INSERT INTO event_days (day, event_id)
VALUES ('2018-10-30', 1),
       ('2018-10-31', 2);

INSERT INTO votes (decision, user_id, event_day_id)
VALUES ('ACCEPT', 1, 1),
       ('ACCEPT', 2, 2),
       ('REJECT', 2, 1);



