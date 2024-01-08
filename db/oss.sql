drop table if exists "public"."oss";
create table "public"."oss"
(
    "id"            int8 NOT NULL,
    "path"          varchar(256),
    "uuid_name"     varchar(256),
    "original_name" varchar(256),
    "creator"       int8,
    "create_date"   timestamp(6)
);

insert into "public"."oss"(id, path, uuid_name, original_name, creator, create_date)
values (1, '/Users/shutongzhang/Desktop/uploadtest/', 'test_01', 'test_01', 1067246875800000001, now()),
       (2, '/Users/shutongzhang/Desktop/uploadtest/', 'test_02', 'test_02', 1067246875800000002, now()),
       (3, '/Users/shutongzhang/Desktop/uploadtest/', 'test_03', 'test_03', 1067246875800000003, now()),
       (4, '/Users/shutongzhang/Desktop/uploadtest/', 'test_04', 'test_04', 1067246875800000004, now()),
       (5, '/Users/shutongzhang/Desktop/uploadtest/', 'test_05', 'test_05', 1067246875800000005, now()),
       (6, '/Users/shutongzhang/Desktop/uploadtest/', 'test_06', 'test_06', 1067246875800000006, now());