DROP TABLE IF EXISTS score;
CREATE TABLE score
(
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    username               VARCHAR(55) NOT NULL,
    points                  DECIMAL (55) NOT NULL,
    date                  TIMESTAMP NOT NULL
);




DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(55)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  BOOLEAN      NOT NULL
);

INSERT INTO user (username, password, enabled)
VALUES ('mary', '$2a$10$6n5MSOo87/wZ7tdXgi6tq.UUsQFMAu56DbpARHfnFtGMiHhjeDgFq', true),   -- password
       ('vinesh', '$2a$10$nT76r851fofA55wJO6QA1OJM7wZmnUVUfvv15FoRl1keZ77EkEYBO', true), -- pass
       ('thanos', '$2a$10$AW5G1nlMzXAXNsxXYDbdgO02vSRxgzcoGa54kImLH5c7KDV1XlaaO', true); -- pass2


DROP TABLE IF EXISTS authority;
CREATE TABLE authority
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(55)  NOT NULL,
    type VARCHAR(55) NOT NULL
);
INSERT INTO authority (username, type)
VALUES ('mary', 'ADMIN'),
       ('vinesh', 'SUPERUSER'),
       ('thanos', 'USER');
