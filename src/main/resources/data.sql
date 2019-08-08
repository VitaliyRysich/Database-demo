/*
create table person
(
  id integer not null,
  name varchar (255) not null,
  location varchar (255),
  birth_date timestamp,
  primary key (id)
);
*/

insert into person
  (id, name, location, birth_date)
values
    (10001, 'Vitalii', 'Wroclaw', sysdate()),
    (10002, 'Sweta', 'Wroclaw', sysdate()),
    (10003, 'Pasza', 'Berdychiv', sysdate());