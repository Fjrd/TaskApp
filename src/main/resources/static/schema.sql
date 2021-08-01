drop table if exists ACCOUNT;
drop table if exists ACCOUNT_ROLES;
drop table if exists TASK;

create table ACCOUNT
(
    ID binary auto_increment,
    NAME VARCHAR(255) not null,
    PASSWORD VARCHAR(255) not null
);

alter table ACCOUNT add constraint ACCOUNT_PK primary key (ID);


create table ACCOUNT_ROLES
(
    ACCOUNT_ID binary not null,
    ROLES VARCHAR(255) not null,
    constraint ACCOUNT_ROLES_ACCOUNT_ID_FK
        foreign key (ACCOUNT_ID) references ACCOUNT (ID)
);

create table TASK
(
    ID binary auto_increment,
    STATUS VARCHAR(255) not null,
    TEXT VARCHAR(255) not null,
    AUTHOR_ID binary,
    constraint TASK_PK
        primary key (ID),
    constraint TASK_ACCOUNT_ID_FK
        foreign key (ID) references ACCOUNT (ID)
);
