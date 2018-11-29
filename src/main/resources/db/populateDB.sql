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

INSERT INTO users (name, login, password)
VALUES ('User1', 'user1_login', 'password'),
       ('Admin1', 'admin1_login', 'password');

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



