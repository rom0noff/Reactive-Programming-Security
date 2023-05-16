create table if not exists hello_user(
  id        bigserial primary key ,
  username   varchar(50) not null unique ,
  password   varchar(100),
  roles      varchar(50)
);
-------------------------------------------------------
-- SELECT *
-- FROM pg_timezone_names;
-- SHOW timezone;
-- set TIMEZONE = +5;
-- ALTER DATABASE "money-transfer-db" SET timezone TO 'Asia/Tashkent';
-- -------------------------------------------------