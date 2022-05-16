INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO restaurants (name)
VALUES ('Прага'),
       ('Рыба&Мясо'),
       ('Ocean Basket');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO dishes (restaurant_id, name, price)
VALUES (1, 'Морс', 9500),
       (1, 'Кулебяка', 50055),
       (1, 'Борщ', 10000);

INSERT INTO votes (user_id, restaurant_id, date)
VALUES (2, 3, '2022-04-30'),
       (1, 2, '2022-04-30'),
       (2, 1, '2022-05-01'),
       (1, 1, '2022-05-01'),
       (2, 2, '2022-05-02');
