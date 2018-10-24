DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_seq;

CREATE SEQUENCE user_seq START 1;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  name             VARCHAR                 NOT NULL,
  login            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL
);
CREATE UNIQUE INDEX users_unique_login_idx ON users (login);
