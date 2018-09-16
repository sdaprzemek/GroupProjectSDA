CREATE TABLE IF NOT EXISTS CLIENT (
  client_id INT(11) not null auto_increment,
  name     VARCHAR(50),
  last_name VARCHAR(50),
  phone    VARCHAR(50),
  PRIMARY KEY (client_id)
);
CREATE TABLE IF NOT EXISTS CAR (
  car_id    INT(11) not null auto_increment,
  mark     VARCHAR(50),
  type     VARCHAR(50),
  year     VARCHAR(50),
  vin      VARCHAR(50),
  color    VARCHAR(50),
  client_id INT(11),
  worker_id INT(11),
  PRIMARY KEY (car_id)
);
ALTER TABLE CAR
  ADD FOREIGN KEY (client_id)
REFERENCES CLIENT (client_id);
CREATE TABLE IF NOT EXISTS WORKER (
  worker_id INT(11) NOT NULL auto_increment,
  name     VARCHAR(50),
  last_name VARCHAR(50),
  position VARCHAR(50),
  PRIMARY KEY (worker_id)
);
CREATE TABLE IF NOT EXISTS WORK_CAR (
  worker_id INT(11),
  car_id    INT(11)
);
ALTER TABLE WORK_CAR
  ADD FOREIGN KEY (car_id)
REFERENCES CAR (car_id) ON DELETE CASCADE;
ALTER TABLE WORK_CAR
  ADD FOREIGN KEY (worker_id)
REFERENCES WORKER (worker_id);
