drop table PG560_USER;

create table PG560_USER(
	id integer generated by default as identity primary key,
	username varchar(30) not null, 
	name varchar(60) not null,
	city varchar(30) not null,
	country varchar(30) not null
	);

insert into PG560_USER(username, name, city, country) values( 'tonnyg', 'Tonny Gundersen', 'Oslo', 'Norway');


