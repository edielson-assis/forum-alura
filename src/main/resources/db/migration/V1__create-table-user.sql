create table user(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(100) not null,

    primary key(id)
);