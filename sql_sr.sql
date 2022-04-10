create table users
(
    id INTEGER CONSTRAINT users_pk PRIMARY KEY AUTOINCREMENT,
    firstname VARCHAR(30),
    lastname  VARCHAR(30),
    username  VARCHAR(30),
    email     VARCHAR(40),
    password  VARCHAR(60)

);
INSERT INTO users (firstname, lastname, username, email, password)
VALUES ('ivan','ivanov','vanya','ivan@mail.ru','12345678');

create table tags
(
    id INTEGER CONSTRAINT tags_pk PRIMARY KEY AUTOINCREMENT,
    message VARCHAR(30)


);
INSERT INTO tags (message)
VALUES ('Biceps');
INSERT INTO tags (message)
VALUES ('Triceps');
INSERT INTO tags (message)
VALUES ('Back');
INSERT INTO tags (message)
VALUES ('quadriceps');
INSERT INTO tags (message)
VALUES ('calves');
INSERT INTO tags (message)
VALUES ('chest');
INSERT INTO tags (message)
VALUES ('press');
INSERT INTO tags (message)
VALUES ('armsworkout');
INSERT INTO tags (message)
VALUES ('chest&back');
INSERT INTO tags (message)
VALUES ('legs');
INSERT INTO tags (message)
VALUES ('cardio');
INSERT INTO tags (message)
VALUES ('press&legs');
INSERT INTO tags (message)
VALUES ('arms&chest');
INSERT INTO tags (message)
VALUES ('stretching');
INSERT INTO tags (message)
VALUES ('crossfitworkout');
INSERT INTO tags (message)
VALUES ('lightweight');
INSERT INTO tags (message)
VALUES ('buttocks');



create table workouts
(
    id INTEGER CONSTRAINT workouts_pk PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(40),
    rating REAL DEFAULT 0,
    description VARCHAR(1000)
);
INSERT INTO workouts (title, description, rating) VALUES
('exampleWorkout','do hard, do hard, do hard',3.4);

select * from users;
