DELETE
FROM dishes;
DELETE
FROM restaurants;
DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants (id, name)
VALUES (100005, 'Прага'),
       (100006, 'Рыба&Мясо'),
       (100007, 'Ocean Basket');

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO dishes (restaurant_id, name, price)
VALUES (100005,'Морс', 95.00),
       (100005,'Кулебяка', 500.55),
       (100005,'Борщ', 100),
       (100006,'Стейк', 800.10),
       (100006,'Рыба на пару', 400.55),
       (100006,'Чай', 50.05),
       (100007,'Жульен', 150),
       (100007,'Креветки', 350),
       (100007,'Палтус гриль', 700.80);