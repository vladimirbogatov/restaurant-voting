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

INSERT INTO users (id, name, email, password)
VALUES (100020, 'User', 'user@yandex.ru', '{noop}password'),
       (100021, 'Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO restaurants (id, name)
VALUES (100005, 'Прага'),
       (100006, 'Рыба&Мясо'),
       (100007, 'Ocean Basket');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100020),
       ('ADMIN', 100021),
       ('USER', 100021);

INSERT INTO dishes (id, restaurant_id, name, price)
VALUES (100002, 100005, 'Морс', 95),
       (100003, 100005, 'Кулебяка', 500.55),
       (100004, 100005, 'Борщ', 100);

INSERT INTO votes (id, user_id, restaurant_id)
VALUES (100010, 100020, 100005),
       (100011, 100021, 100006);
