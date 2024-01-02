CREATE SCHEMA IF NOT EXISTS areeba;

create table if not exists areeba."Customer"
(
    "Id"   serial not null,
    "FirstName" varchar(255),
    "FatherName" varchar(255),
    "LastName" varchar(255),
    "Address" varchar(255),
    "MobileNumber" varchar(255),
    constraint "Customer_pkey" primary key ("Id")
);