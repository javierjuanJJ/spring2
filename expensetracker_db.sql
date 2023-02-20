create table IF NOT EXISTS users
(
    user_id    integer primary key not null,
    first_name varchar(20)         not null,
    last_name  varchar(20)         not null,
    email      varchar(30)         not null,
    password   text                not null
);

create table IF NOT EXISTS categories
(
    category_id integer primary key not null,
    user_id     integer             not null,
    title       varchar(20)         not null,
    description varchar(50)         not null
);
alter table categories
    add constraint  cat_users_fk
        foreign key (user_id) references users (user_id);

create table IF NOT EXISTS transactions
(
    transaction_id   integer primary key not null,
    category_id      integer             not null,
    user_id          integer             not null,
    amount           numeric(10, 2)      not null,
    note             varchar(50)         not null,
    transaction_date bigint              not null
);
alter table transactions
    add constraint trans_cat_fk
        foreign key (category_id) references categories (category_id);
alter table transactions
    add constraint trans_users_fk
        foreign key (user_id) references users (user_id);

create sequence et_users_seq increment 1 start 1;
create sequence et_categories_seq increment 1 start 1;
create sequence et_transactions_seq increment 1 start 1000;