DELETE FROM users;
ALTER SEQUENCE user_seq RESTART WITH 1;

INSERT INTO users (name, login, password) VALUES
  ('User1', 'user1_login', 'password'),
  ('Admin1', 'admin1_login', 'password');

