CREATE TABLE IF NOT EXISTS roles (
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
name varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
login varchar(20) not null,
password varchar(100) not null,
role_id int not null,
FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO roles (name)
VALUES
('ROLE_ADMIN'),
('ROLE_USER');

CREATE TABLE IF NOT EXISTS cities(
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
name varchar(20) NOT NULL
);

INSERT INTO cities (name)
VALUES
('Minsk'),
('Grodno'),
('Vitebsk');

CREATE TABLE IF NOT EXISTS profiles(
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
name varchar(20) NOT NULL,
surname varchar(20)NOT NULL,
city_id INT NOT NULL,
FOREIGN KEY (city_id) REFERENCES cities(id)
);

INSERT INTO profiles (name,surname,city_id)
VALUES
('Valentin','Sidorevich',1),
('Dima','Petrovich',2),
('Alexey','Valeriev',3);

CREATE TABLE IF NOT EXISTS number_plates(
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
region int NOT NULL  check(region >= 0 and region < 8),
series varchar(2) NOT NULL,
value varchar(20) NOT NULL
);

INSERT INTO number_plates (region,series,value)
VALUES
(7,'AO','7184'),
(5,'AM','3712'),
(3,'AI','9832'),
(7,'AT','6523'),
(7,'AM','1563'),
(7,'AT','6423');

CREATE TABLE IF NOT EXISTS cars(
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
brand varchar(20) NOT NULL,
number_plate_id INT NOT NULL,
FOREIGN KEY (number_plate_id) REFERENCES number_plates(id)
);

INSERT INTO cars (brand,number_plate_id)
VALUES
('Audi',1),
('Skoda',2),
('BMW',3),
('Skoda',4),
('Skoda',5),
('Audi',6);

CREATE TABLE IF NOT EXISTS profiles_cars
(
   profile_id INT,
   car_id INT,
   PRIMARY KEY (profile_id,car_id),
   FOREIGN KEY (profile_id) REFERENCES profiles(id),
   FOREIGN KEY (car_id) REFERENCES cars(id)
);

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