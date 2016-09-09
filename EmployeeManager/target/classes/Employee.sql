Create DATABASE EmployeeDB;

USE EmployeeDB;

DROP TABLE IF EXISTS Employee;
CREATE TABLE Employee
(
id int NOT NULL AUTO_INCREMENT,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
email_address VARCHAR(200) NOT NULL,
phone VARCHAR(50) NULL,
dept_id int NULL,
PRIMARY KEY ( id )
);

DROP TABLE IF EXISTS Department;
CREATE TABLE Department
(
id int NOT NULL AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
PRIMARY KEY ( id )
);

INSERT into Department values (1,'Maths');
INSERT into Department values (2,'Physics');

INSERT into Employee values (1,'John','Doe','John@gmail.com','11223344',1);
INSERT into Employee values (2,'Raam','Kumar','Kumar@mail.com','4675788',1);