DELETE
FROM users;
ALTER SEQUENCE user_seq
  RESTART WITH 1;

INSERT INTO users (name, login, password, enabled)
VALUES ('User1', 'user1_login', '{noop}password', true),
       ('Admin1', 'admin1_login', '{noop}password', true);

DELETE
FROM user_roles;

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 1),
       ('ROLE_ADMIN', 2),
       ('ROLE_USER', 2);

DELETE
FROM events;
ALTER SEQUENCE event_seq
  RESTART WITH 1;

INSERT INTO events (name, description, status)
VALUES ('событие 1', 'описание события 1', 'IN_VOTING'),
       ('событие 2', 'описание события 2', 'IN_VOTING');

DELETE
FROM event_votings;
ALTER SEQUENCE event_voting_seq
  RESTART WITH 1;

INSERT INTO event_votings (event_id, description, status)
VALUES (1, 'Описание 1', 'NEW_VOTING'),
       (2, 'Описание 2', 'NEW_VOTING');

DELETE
FROM vote_days;
ALTER SEQUENCE vote_day_seq
  RESTART WITH 1;

INSERT INTO vote_days (event_voting_id, day)
VALUES (1, '2018-10-30'),
       (2, '2018-10-31'),
       (1, '2018-10-31');

DELETE
FROM votes;
ALTER SEQUENCE vote_seq
  RESTART WITH 1;

INSERT INTO votes (decision, user_id, vote_day_id)
VALUES ('ACCEPT', 1, 1),
       ('ACCEPT', 2, 2),
       ('REJECT', 2, 1);

DELETE
FROM event_members;
ALTER SEQUENCE event_member_seq
  RESTART WITH 1;

INSERT INTO event_members (decision, user_id, event_id)
VALUES ('ACCEPT', 1, 1),
       ('REJECT', 1, 2),
       ('REJECT', 2, 1);



