DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS portfolios;
DROP TABLE IF EXISTS userPortfolios;
DROP TABLE IF EXISTS users;


create table users(
    username varchar_ignorecase(50) PRIMARY KEY,
    password varchar(60) not null,
    enabled boolean not null,
    roles varchar(100),
    permissions varchar(100)
);

create table authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

create table userPortfolios(
	id bigint not null auto_increment,
	name varchar(30) not null,
	username varchar_ignorecase(50) not null,
	foreign key (username) references users(username)
);

create table portfolios ( 
	username varchar_ignorecase(50) not null,
    id bigint not null,
    symbol varchar(10) not null,
    amount decimal(19,4) not null,
    price decimal(19,4) not null,
    stamp timestamp not null,
    foreign key (username) references users(username),
   	foreign key (id) references userPortfolios(id)
);

