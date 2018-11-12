DROP TABLE IF EXISTS votes;
DROP SEQUENCE IF EXISTS vote_seq;
DROP TABLE IF EXISTS event_days;
DROP SEQUENCE IF EXISTS event_day_seq;
DROP TABLE IF EXISTS events;
DROP SEQUENCE IF EXISTS event_seq;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_seq;

CREATE SEQUENCE user_seq
  START 1;

CREATE TABLE users
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  name     VARCHAR NOT NULL,
  login    VARCHAR NOT NULL,
  password VARCHAR NOT NULL
);
CREATE UNIQUE INDEX users_unique_login_idx
  ON users (login);

CREATE SEQUENCE vote_seq
  START 1;

CREATE TABLE votes
(
  id           INTEGER PRIMARY KEY DEFAULT nextval('vote_seq'),
  day          DATE    NOT NULL,
  decision     VARCHAR NOT NULL,
  user_id      INTEGER NOT NULL,
  event_day_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_user_event_idx
  ON votes (user_id, event_day_id);

CREATE SEQUENCE event_seq
  START 1;

CREATE TABLE events
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('event_seq'),
  name        VARCHAR NOT NULL,
  description VARCHAR
);

CREATE SEQUENCE event_day_seq
  START 1;

CREATE TABLE event_days
(
  id  INTEGER PRIMARY KEY DEFAULT nextval('event_day_seq'),
  day DATE NOT NULL
);
