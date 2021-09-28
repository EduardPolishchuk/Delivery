DROP TABLE IF EXISTS receipt;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS parcel;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS tariff;
DROP TABLE IF EXISTS role;

create table city
(
    id        bigint auto_increment,
    name      varchar(45) not null,
    name_uk   varchar(45) not null,
    latitude  float       not null,
    longitude float       not null,
    constraint id_UNIQUE
        unique (id)
);

alter table city
    add primary key (id);

create table parcel
(
    parcel_id bigint auto_increment,
    length    float unsigned not null,
    width     float unsigned not null,
    height    float unsigned not null,
    weight    float unsigned not null,
    type      varchar(45)    not null,
    constraint id_UNIQUE
        unique (parcel_id)
);

alter table parcel
    add primary key (parcel_id);

create table role
(
    id        int          not null
        primary key,
    role_name varchar(255) not null
);

create table tariff
(
    tariff_id           bigint auto_increment,
    uah_per_mm_length   float           null,
    uah_per_mm_width    float           null,
    uah_per_mm_height   float           null,
    uah_per_km_distance float           null,
    uah_per_kg_weight   float           null,
    additional          float default 0 null,
    constraint tariff_id_uindex
        unique (tariff_id)
);

alter table tariff
    add primary key (tariff_id);

create table user
(
    user_id    bigint auto_increment,
    login      varchar(16)           not null,
    last_name  varchar(32)           not null,
    first_name varchar(32)           not null,
    email      varchar(255)          not null,
    password   varchar(32)           not null,
    role       int                   not null,
    balance    decimal(20) default 0 not null,
    constraint id_UNIQUE
        unique (user_id),
    constraint role_id
        foreign key (role) references role (id)
);

create index role_id_idx
    on user (role);

alter table user
    add primary key (user_id);

create table `order`
(
    id             bigint auto_increment,
    receiving_date date        null,
    request_date   date        null,
    parcel_id      bigint      not null,
    user_sender    bigint      not null,
    city_from      bigint      not null,
    city_to        bigint      not null,
    order_status   varchar(45) null,
    constraint id_UNIQUE
        unique (id),
    constraint city_from
        foreign key (city_from) references city (id),
    constraint city_to
        foreign key (city_to) references city (id),
    constraint parcel_id
        foreign key (parcel_id) references parcel (parcel_id),
    constraint user_sender
        foreign key (user_sender) references user (user_id)
);

create index city_from_idx
    on `order` (city_from);

create index city_to_idx
    on `order` (city_to);

create index parcel_id_idx
    on `order` (parcel_id);

create index user_sender_idx
    on `order` (user_sender);

alter table `order`
    add primary key (id);

create table receipt
(
    id       bigint auto_increment
        primary key,
    price    decimal(20) unsigned not null,
    paid     tinyint default 0    null,
    order_id bigint               not null,
    constraint fk_receipt_order1
        foreign key (order_id) references `order` (id)
);

create index fk_receipt_order1_idx
    on receipt (order_id);
	
	
	INSERT INTO role (id, role_name) VALUES (1,'user');
INSERT INTO role (id, role_name) VALUES (2,'manager');
INSERT INTO user(login, email, password, balance, last_name, first_name, role) VALUES ('manager','admin@gmail.com','1',0,'Ivanov','Ivan', 1);
INSERT INTO user(login, email, password, balance, last_name, first_name, role) VALUES ('user','qwe@gmail.com','1',0,'Foster','Mary',2);
INSERT INTO user(login, email, password, balance, last_name, first_name, role) VALUES ('qweqwe','qweqwe@gmail.com','1',0,'Williams','Bob',2);
INSERT INTO user(login, email, password, balance, last_name, first_name, role) VALUES ('qwerty','qwerty@gmail.com','1',0,'Weaver','Roby',2);
INSERT INTO user(login, email, password, balance, last_name, first_name, role) VALUES ('ytrewq','ytrewq@gmail.com','1',0,'Petrov','Petr',2);
INSERT INTO user(login, email, password, balance, last_name, first_name, role) VALUES ('user','user@gmail.com','1',0,'Alekseev','Aleksei',2);

INSERT INTO city(name, name_uk, longitude, latitude) VALUES('Vinnytsia', 'Вінниця', 28.467975, 49.2320162);
INSERT INTO city(name, name_uk, longitude, latitude) VALUES('Kiew', 'Києв', 30.5238, 50.45466);
INSERT INTO city(name, name_uk, longitude, latitude) VALUES('Odesa', 'Одеса', 30.732564, 46.484658);
INSERT INTO city(name, name_uk, longitude, latitude) VALUES('Kharkiv', 'Харків', 36.272266, 49.991426);
INSERT INTO city(name, name_uk, longitude, latitude) VALUES('Lviv', 'Львів', 24.031592, 49.841952);
INSERT INTO city(name, name_uk, longitude, latitude) VALUES('Sumy', 'Суми', 34.798386, 50.907001);


INSERT INTO tariff (uah_per_mm_length, uah_per_mm_width, uah_per_mm_height, uah_per_km_distance, uah_per_kg_weight, additional)
VALUES (0.1, 0.1,0.1,0.1,0.1, 10)