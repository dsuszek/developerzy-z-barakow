CREATE TABLE UserRoles(
id TINYINT PRIMARY KEY AUTO_INCREMENT,
name varchar(30) UNIQUE
);

CREATE TABLE Users(
id SMALLINT PRIMARY KEY AUTO_INCREMENT,
email varchar(50) UNIQUE,
password varchar(50),
roleId TINYINT,
FOREIGN KEY (roleId) REFERENCES UserRoles (id)
);

CREATE TABLE JobRoles(
id SMALLINT PRIMARY KEY AUTO_INCREMENT,
name varchar(50) UNIQUE,
description varchar(3000),
link varchar(1000)
);

