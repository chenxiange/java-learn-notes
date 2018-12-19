create table t_user(
	id int primary key auto_increment,
	mobile varchar(20) not null,
	password varchar(32) not null,
	gmt_create datetime not null,
	gmt_modify datetime not null
);