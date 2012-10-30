DROP TABLE IF EXISTS Schedule;
DROP TABLE IF EXISTS Tuition;
DROP TABLE IF EXISTS MathunakiUser;

CREATE TABLE Schedule (
    schedule_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tuition_id BIGINT NOT NULL,
    start_date DATETIME NOT NULL,
    duration INT NOT NULL,
    description VARCHAR(255)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Tuition (
    tuition_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    user_level VARCHAR(20) NOT NULL,
    description VARCHAR(255),
    resource BLOB
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE MathunakiUser (
    user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(30),
    phone_number VARCHAR(10),
    phone_number_parent VARCHAR(10),
    phone_number_2 VARCHAR(10),
    information VARCHAR(255),
    status TINYINT(1) NOT NULL,
    price DOUBLE PRECISION NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE Schedule
ADD CONSTRAINT FK_SCHEDULE_TUITION FOREIGN KEY (tuition_id) REFERENCES Tuition(tuition_id),
ADD INDEX IDX_SCHEDULE_TUITION (tuition_id);

ALTER TABLE Tuition
ADD CONSTRAINT FK_TUITION_USER FOREIGN KEY (user_id) REFERENCES MathunakiUser(user_id),
ADD INDEX IDX_TUITION_USER (user_id);