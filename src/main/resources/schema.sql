create table buildings (
  id identity,
  name varchar(30) not null,
  address varchar(50) not null,
  numFloors INTEGER
);
create table rooms (
  id IDENTITY,
  buildingId INTEGER,
  floorId INTEGER
);