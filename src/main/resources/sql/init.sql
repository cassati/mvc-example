CREATE SEQUENCE t_user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
create table t_user(
	id int default nextval('t_user_id_seq') not null,
	name varchar(255) not null,
	username varchar(255) not null,
	primary key (id)
);

CREATE SEQUENCE t_role_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
create table t_role(
	id int default nextval('t_role_id_seq') not null,
	role_name varchar(255) not null,
	role_code varchar(255) not null,
	primary key (id)
);

CREATE SEQUENCE t_menu_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
create table t_menu(
	id int default nextval('t_menu_id_seq') not null,
	menu_name varchar(255) not null,
	menu_code varchar(255) not null,
	permission varchar(255) null,
	primary key (id)
);