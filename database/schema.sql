drop schema if exists todolist;

create schema todolist;

use todolist;

create table user(
    user_id varchar(8) not null,
    username varchar(128) not null,
    name varchar(128),

    primary key(username)
);

create table task(
    username varchar(128) not null,
    task_id int auto_increment,
    description varchar(255),
    priority enum('1','2','3'),
    due_date date,
    
    primary key(task_id),

    constraint fk_username
        foreign key(username)
        references user(username)
);

