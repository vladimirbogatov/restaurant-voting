DELETE
FROM restaurants;
DELETE
FROM dishes;
DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM votes;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', 'admin password');

INSERT INTO restaurants (name)
VALUES ('Прага'),
       ('Рыба&Мясо'),
       ('Ocean Basket');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO dishes (restaurant_id, name, price)
VALUES (100002, 'Морс', 95),
       (100002, 'Кулебяка', 500.55),
       (100002, 'Борщ', 100);

/*
INSERT INTO votes (user_id, restaurant_id)
VALUES (100000, 100002),
       (100021, 100006);
*/
