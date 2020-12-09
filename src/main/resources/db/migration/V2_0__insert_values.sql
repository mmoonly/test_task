INSERT INTO roles (name)
VALUES
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO cities (name)
VALUES
('Minsk'),
('Grodno'),
('Vitebsk');


INSERT INTO profiles (name,surname,city_id)
VALUES
('Valentin','Sidorevich',1),
('Dima','Petrovich',2),
('Alexey','Valeriev',3);


INSERT INTO number_plates (region,series,value)
VALUES
(7,'AO','7184'),
(5,'AM','3712'),
(3,'AI','9832'),
(7,'AT','6523'),
(7,'AM','1563'),
(7,'AT','6423');

INSERT INTO cars (brand,number_plate_id)
VALUES
('Audi',1),
('Skoda',2),
('BMW',3),
('Skoda',4),
('Skoda',5),
('Audi',6);


INSERT INTO profiles_cars (profile_id,car_id)
VALUES
(1,1),
(1,2),
(2,2),
(3,1),
(3,2),
(3,3),
(3,4),
(3,5),
(3,6);