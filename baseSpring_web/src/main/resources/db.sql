create database bank;
use bank;

#
create table accounts(
                        accountid int primary key auto_increment,
                        balance numeric(10,2) check ( balance>0 )
);
insert into accounts(balance) values (1000);
insert into accounts(balance) values (1);
insert into accounts(balance) values (0);

CREATE TABLE oprecord (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      accountid INT REFERENCES accounts(accountid),
                      opmoney NUMERIC(10,2),
                      optime DATETIME,
                      optype ENUM('deposit', 'withdraw', 'transfer') NOT NULL,
                      transferid INT
);
drop table oprecord;

select * from accounts;  # 账户
select * from oprecord;  # 流水记录
update accounts set email='3108542443@qq.com' ;
update accounts set email='2921310632@qq.com' ;

# 测试存钱
update accounts set balance=balance+100 where accountid=1;
insert into oprecord(accountid,opmoney,optime,optype,transferid)
    values (1,100,now(),'deposit',null);

delete from oprecord;