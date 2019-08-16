drop table IF EXISTS user;

create TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);

INSERT INTO user (first_name, last_name, username, password) VALUES
  ('Aliko', 'Dangote', 'user', 'user');

drop table if exists operation_system;

create table operation_system (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    version VARCHAR(10) NOT NULL,
    manufacturer VARCHAR(20) NOT NULL,
    release_date datetime NULL
);

INSERT INTO operation_system (name, version, manufacturer, release_date) VALUES
  ('Windows 10', '2600', 'Microsoft', now()),
  ('iOS', '12', 'Apple', now()),
  ('Red Hat', '8', 'IBM', now());

