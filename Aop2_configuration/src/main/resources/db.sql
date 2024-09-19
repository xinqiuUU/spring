select * from bank;
drop table bank;

create table bank(
    id int primary key auto_increment,
    balance int
);

insert into bank(balance) values(10000);
insert into bank(balance) values(99);
