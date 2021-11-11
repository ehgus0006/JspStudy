show databases;


create table member (
	id varchar(50) not null primary key,
	passwd varchar(16) not null,
	name varchar(16) not null,
	reg_date datetime not null
);

select * from member;

alter table member add (address varchar(100) not null , tel varchar(20) not null);

desc member;

insert into member values('kingdora@dragon.com','1234', '김개동', now(), '서울시', '010-2222-1111');
insert into member values('kingdora@aaa.com','1234', '김도현', now(), '경기도', '010-2222-1111');

delete from member;

select * from member;

alter table member modify passwd varchar(60) not null;

desc member;

