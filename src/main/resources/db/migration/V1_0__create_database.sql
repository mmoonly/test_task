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

CREATE TABLE IF NOT EXISTS cities(
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
name varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS profiles(
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
name varchar(20) NOT NULL,
surname varchar(20)NOT NULL,
city_id INT NOT NULL,
FOREIGN KEY (city_id) REFERENCES cities(id)
);

CREATE TABLE IF NOT EXISTS number_plates(
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
region int NOT NULL  check(region >= 0 and region < 8),
series varchar(2) NOT NULL,
value varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS cars(
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
brand varchar(20) NOT NULL,
number_plate_id INT NOT NULL,
FOREIGN KEY (number_plate_id) REFERENCES number_plates(id)
);

CREATE TABLE IF NOT EXISTS profiles_cars
(
   profile_id INT,
   car_id INT,
   PRIMARY KEY (profile_id,car_id),
   FOREIGN KEY (profile_id) REFERENCES profiles(id),
   FOREIGN KEY (car_id) REFERENCES cars(id)
);