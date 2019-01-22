show databases ;
create database if not exists Restaurant;
use Restaurant;

CREATE TABLE Menu(

  item_code VARCHAR(10) NOT NULL PRIMARY KEY ,
  description VARCHAR(20) NOT NULL ,
  unit_price INT NOT NULL ,
  meal_type VARCHAR(20)

);

SHOW TABLES ;
DESC Menu;

CREATE TABLE Employee(

  employee_id VARCHAR(10) NOT NULL PRIMARY KEY ,
  employee_name VARCHAR(20) NOT NULL ,
  address VARCHAR(50) NOT NULL ,
  age INT NOT NULL ,
  mobile VARCHAR(10) NOT NULL ,
  job VARCHAR(20) NOT NULL,
  gender VARCHAR(10) NOT NULL
);

CREATE TABLE ResturantTable(

  table_number VARCHAR(10) NOT NULL PRIMARY KEY ,
  table_name VARCHAR(20) NOT NULL ,
  seats INT,
  status VARCHAR(10)
);

CREATE TABLE `Order`(

  order_id VARCHAR(10) NOT NULL PRIMARY KEY ,
  date DATE NOT NULL ,
  table_number  VARCHAR(10),
  employee_id VARCHAR(10),
  dining VARCHAR(20),
  CONSTRAINT FOREIGN KEY (table_number) REFERENCES restauranttable (table_number),
  CONSTRAINT FOREIGN KEY (employee_id) REFERENCES Employee (employee_id)

);

DROP TABLE Payment;

CREATE TABLE OrderDetail(

  order_id VARCHAR(10) NOT NULL,
  item_code VARCHAR(10) NOT NULL ,
  qty INT,
  unit_price INT,
  CONSTRAINT FOREIGN KEY (order_id) REFERENCES `Order`(order_id),
  CONSTRAINT FOREIGN KEY (item_code) REFERENCES Menu (item_code),
  CONSTRAINT PRIMARY KEY (order_id,item_code)

);

CREATE TABLE Payment(

  payment_id VARCHAR(10) NOT NULL PRIMARY KEY ,
  order_id VARCHAR(10) NOT NULL ,
  payment_method VARCHAR(10) NOT NULL ,
  total INT,
  CONSTRAINT FOREIGN KEY (order_id) REFERENCES `Order` (order_id)
);