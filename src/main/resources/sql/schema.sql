
-- 用户 start
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
-- 用户 end

-- 组织 start
CREATE SEQUENCE t_org_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
create table t_org(
	id int default nextval('t_org_id_seq') not null,
    parent_id int not null,
	org_name varchar(255) not null,
	org_code varchar(255) not null,
	primary key (id)
);
-- 组织 end

-- 角色 start
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
-- 角色 end

-- 菜单 start
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
-- 菜单 end

-- 用户组织关联
create table t_user_org(
    user_id int not null,
    org_id int not null,
    primary key (user_id, org_id)
);

-- 用户角色关联
create table t_user_role(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id)
);

-- 角色菜单关联
create table t_role_menu(
    role_id int not null,
    menu_id int not null,
    primary key (role_id, menu_id)
);

