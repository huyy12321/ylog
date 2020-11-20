-- auto-generated definition
create table ylog
(
    id              bigint auto_increment
        primary key,
    user_id         bigint       null,
    create_time     datetime     null,
    controller_name varchar(50)  null comment '访问的controller名字',
    path            varchar(255) null comment '访问路径',
    ip              varchar(50)  null comment '访问的ip',
    type            varchar(50)  null comment '操作类型',
    data            text         null comment '请求的参数',
    msg             text         null comment '返回的信息',
    code            varchar(10)  null comment '返回的响应码',
    use_time        int  null comment '接口耗时，单位毫秒'
);

