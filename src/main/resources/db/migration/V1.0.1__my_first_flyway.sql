create table access_log (
    id integer primary key autoincrement,
    request_start_at timestamp not null,
    proc_time integer,
    url varchar
);
