create table access_log (
    id bigint unsigned primary key auto_increment,
    request_start_at timestamp not null,
    proc_time int,
    url varchar(10000)
);
