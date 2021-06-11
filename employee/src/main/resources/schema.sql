CREATE SCHEMA IF NOT EXISTS employee AUTHORIZATION sa;
DROP TABLE IF EXISTS employee.insurance_card_details;
DROP TABLE IF EXISTS employee.employee_details;
DROP TABLE IF EXISTS employee.email_queue;
DROP TABLE IF EXISTS employee.email_template;
create table employee.employee_details(
employee_id varchar(100) not null,
employee_name varchar(100) not null ,
dob DATE,
designation  varchar(50) not null,
date_of_joining DATE DEFAULT now(),
work_location varchar(100),
mobile varchar(20),
email varchar(50),
date_of_leaving DATE,
addtional_details varchar(500),
employee_status varchar(50),
custom_field varchar(100),
custom_field2 varchar(10),
custom_field3 DATE,
 primary key(employee_id)
);

create table employee.insurance_card_details(
card_id varchar(100) not null,
employee_id varchar(100) not null,
dependent_name varchar(50) not null,
relation_to_employee varchar(100) not null,
dob DATE,
activation_date DATE,
addtional_details varchar(500),
custom_field varchar(100),
custom_field2 varchar(10),
custom_field3 DATE,
card_status varchar(10),
expiration_date DATE,
primary key(card_id),
foreign key(employee_id) references employee.employee_details(employee_id)
);

create table employee.email_queue(
email_id varchar(100) not null,
email_template varchar(100),
from_address varchar(100) not null,
to_address varchar(100) not null,
email_subject varchar(200) not null,
email_body varchar(10000) not null,
email_status varchar(20) not null,
created_on_local DATE,
custom_field varchar(100),
custom_field2 varchar(10),
custom_field3 DATE,
primary key(email_id)
);


create table employee.email_template(
email_template_id varchar(100) not null,
email_template varchar(100),
template varchar(10000),
email_subject varchar(1000),
primary key(email_template_id)
);