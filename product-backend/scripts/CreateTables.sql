CREATE TABLE IF NOT EXISTS UserRoles (
id TINYINT PRIMARY KEY AUTO_INCREMENT,
name varchar(30) UNIQUE
);

CREATE TABLE IF NOT EXISTS Users (
id SMALLINT PRIMARY KEY AUTO_INCREMENT,
email varchar(50) UNIQUE,
password varchar(80),
roleId TINYINT,
FOREIGN KEY (roleId) REFERENCES UserRoles (id)
);

<<<<<<< HEAD
CREATE TABLE IF NOT EXISTS JobRoles (
id SMALLINT PRIMARY KEY AUTO_INCREMENT,
name varchar(50) UNIQUE,
description varchar(3000),
link varchar(1000),
FOREIGN KEY (id) REFERENCES Bands (id)
);

CREATE TABLE Token (
id SMALLINT PRIMARY KEY AUTO_INCREMENT,
email varchar(50) NOT NULL,
token varchar(64) NOT NULL,
expiry DATETIME NOT NULL,
FOREIGN KEY (email) REFERENCES Users(email)
=======
CREATE TABLE IF NOT EXISTS Bands (
id SMALLINT PRIMARY KEY AUTO_INCREMENT,
name varchar(50) UNIQUE NOT NULL,
level TINYINT NOT NULL
>>>>>>> 77c95c4ad538432ab215d032f6def25e058915de
);

CREATE TABLE IF NOT EXISTS Capabilities (
id smallint NOT NULL AUTO_INCREMENT,
capabilityName varchar(50),
leadName varchar(50),
capabilityLeadPicture LONGTEXT,
message varchar(3000),
PRIMARY KEY(id)
);

<<<<<<< HEAD
CREATE TABLE IF NOT EXISTS Bands (
 id SMALLINT PRIMARY KEY AUTO_INCREMENT,
 name varchar(50) UNIQUE NOT NULL,
 level TINYINT NOT NULL
 );

=======
CREATE TABLE IF NOT EXISTS JobRoles (
id SMALLINT PRIMARY KEY AUTO_INCREMENT,
name varchar(50) UNIQUE,
description varchar(3000),
link varchar(1000),
bandId SMALLINT,
capabilityId SMALLINT,
FOREIGN KEY (bandId) REFERENCES Bands (id),
FOREIGN KEY (capabilityId) REFERENCES Capabilities (id)
);
>>>>>>> 77c95c4ad538432ab215d032f6def25e058915de
