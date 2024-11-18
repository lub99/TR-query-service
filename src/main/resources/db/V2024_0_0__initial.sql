CREATE TABLE application
(
    id         VARCHAR(255) PRIMARY KEY NOT NULL,
    first_name VARCHAR(255)             NOT NULL,
    last_name  VARCHAR(255)             NOT NULL,
    club       VARCHAR(255),
    distance   VARCHAR(50)              NOT NULL
);
