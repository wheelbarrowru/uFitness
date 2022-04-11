create table users
(
    id        INTEGER
        CONSTRAINT users_pk PRIMARY KEY AUTOINCREMENT NOT NULL,
    firstname VARCHAR(30)                             NOT NULL,
    lastname  VARCHAR(30)                             NOT NULL,
    username  VARCHAR(30)                             NOT NULL,
    email     VARCHAR(40)                             NOT NULL,
    password  VARCHAR(60)                             NOT NULL

);

create table tags
(
    id      INTEGER
        CONSTRAINT tags_pk PRIMARY KEY AUTOINCREMENT NOT NULL,
    message VARCHAR(30)                              NOT NULL

);

create table workouts
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



INSERT INTO workouts (title, description, rating)
VALUES ('exampleWorkout', 'do hard, do hard, do hard', 3.4),
       ('Cardio training with press', '1)пкм»10
30 сек махи гантели (12-15кг)
2)пкм»10
10 колени к груди на турнике
3)1 круг:
1км бег
2км гребля
100 ккал байк (велик)', 0),
       --cardio, press
       ('Training for big arms', '1)пкм»10
4-5 подтягиваний
2)пкм»10
7 приседаний с гирей 16кг
3)5 кругов:
10 берпи
10 ккал гребля
10 махов гири 12кг
10 ккал гребля
1 мин планка', 0),
       -- arms, press, biceps, triceps
       ('Way to good press', '1)пкм»10
30 сек протяжка
2)пкм»10
30 сек sit up + жим гантели
3)4 круга:
20 поочерёдные книжки
30 разворотов
30 касаний носков
20 свечка
1 мин (30 сек пятнашки/30 сек планка)', 0),
       -- press, back
       ('legs and arms', '1)пкм»10
30 сек приседания с гирей 12кг на груди(можно гантель)
2)пкм»10
20 сек отжиманий от пола
3)3 круга:
20 ккал гребля
20 махов гири 12кг
20 зашагивания на коробку с гирей 12 кг
1 мин планка', 0),
       --legs, triceps
       ('Great workout for legs', '4 круга:' ||
                                  '10 минут беговая дорожка;
4 подхода: 10 повторений жим ногами лежа;
4 подхода выпады вперед 10 повторений;
4 подхода по 10 раз на каждую ногу подъем на носок;
3 подхода по 10 раз приседания с собственным весом;
10 минут беговая', 0);
-- quadriceps, colves



select * from users;
