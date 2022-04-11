CREATE TABLE users
(
    id        INTEGER
        CONSTRAINT users_pk PRIMARY KEY AUTOINCREMENT NOT NULL,
    firstname VARCHAR(30)                             NOT NULL,
    lastname  VARCHAR(30)                             NOT NULL,
    username  VARCHAR(30)                             NOT NULL,
    email     VARCHAR(40)                             NOT NULL,
    password  VARCHAR(60)                             NOT NULL

);

CREATE TABLE tags
(
    id      INTEGER
        CONSTRAINT tags_pk PRIMARY KEY AUTOINCREMENT NOT NULL,
    message VARCHAR(30)                              NOT NULL

);

CREATE TABLE workouts
(
    id          INTEGER
        CONSTRAINT workouts_pk PRIMARY KEY AUTOINCREMENT NOT NULL,
    title       VARCHAR(40)                              NOT NULL,
    rating      REAL DEFAULT 0 NOT NULL,
    description VARCHAR(1000)                            NOT NULL
);

INSERT INTO tags (message)
VALUES ('arms workout'),
       ('arms & chest'),
       ('back'),
       ('biceps'),
       ('buttocks'),
       ('calves'),
       ('cardio'),
       ('chest'),
       ('crossfit workout'),
       ('chest&back'),
       ('legs'),
       ('lightweight'),
       ('press'),
       ('press & legs'),
       ('quadriceps'),
       ('shoulders'),
       ('stretching'),
       ('triceps');

CREATE TABLE workouts_tags
(
    id          INTEGER
        CONSTRAINT workouts_tags_pk PRIMARY KEY AUTOINCREMENT NOT NULL,
    workouts_id INTEGER,
    tags_id     INTEGER,
    FOREIGN KEY (workouts_id) REFERENCES workouts (id),
    FOREIGN KEY (tags_id) REFERENCES tags (id)
);

select *
from users;