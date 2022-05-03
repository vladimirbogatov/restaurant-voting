INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}userPassword'),
       ('Admin', 'admin@gmail.com', '{noop}adminPassword');

INSERT INTO restaurants (name)
VALUES ('Прага'),
       ('Рыба&Мясо'),
       ('Ocean Basket');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO dishes (restaurant_id, name, price)
VALUES (3, 'Морс', 95),
       (3, 'Кулебяка', 500.55),
       (3, 'Борщ', 100);

INSERT INTO votes (user_id, restaurant_id, date)
VALUES (2, 3, '2022-04-30'),
       (1, 2, '2022-04-30'),
       (2, 1, '2022-05-01'),
       (1, 1, '2022-05-01'),
       (2, 2, '2022-05-02'),
       (1, 3, '2022-05-02');