DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_seq;
DROP TABLE IF EXISTS votes;
DROP SEQUENCE IF EXISTS vote_seq;

CREATE SEQUENCE user_seq START 1;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  name             VARCHAR                 NOT NULL,
  login            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL
);
CREATE UNIQUE INDEX users_unique_login_idx ON users (login);

CREATE SEQUENCE vote_seq START 1;

CREATE TABLE votes
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('vote_seq'),
  day              DATE                    NOT NULL,
  decision         VARCHAR                 NOT NULL,
  user_id     INTEGER   NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
