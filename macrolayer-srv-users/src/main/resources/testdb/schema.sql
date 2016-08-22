drop table T_USER if exists;

create table T_USER (ID bigint identity primary key,
					NUMBER varchar(9) not null,
					NAME varchar(50) not null, 
					SURNAME1 varchar(50) not null, 
					SURNAME2 varchar(50), 
					unique(NUMBER));
