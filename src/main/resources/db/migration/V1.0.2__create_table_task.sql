create table task (
    id integer primary key autoincrement,
    task varchar,
    create_at timestamp not null,
    update_at timestamp not null
);
