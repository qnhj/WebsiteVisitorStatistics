-- 数据存储表
/*==============================================================*/
/* Table: msg                                               */
/*==============================================================*/
create table msg
(
    uuid           char(32) not null,
    time           char(30),
    protocol       char(20),
    remoteAddr     char(20),
    remotePort     integer,
    method         char(10),
    locale         char(20),
    remoteUser     char(50),
    queryString    char(100),
    osFamily       char(30),
    osName         char(100),
    uaName         char(30),
    uaFamily       char(30),
    browserVersion char(30),
    deviceType     char(50),
    type           char(30),
    remarks        char(200),
    mark           boolean,
    markRemarks    char(200),
    admin          char(50),
    url            char(200),
    primary key (uuid)
);


-- 管理员账户表
/*==============================================================*/
/* Table: user                                                 */
/*==============================================================*/
create table user
(
    userUuid    char(32) not null,
    userName    char(50),
    userPw      char(50),
    admin       int,
    userRemarks char(200),
    userIndex   int,
    primary key (userUuid)
);


-- 管理员登录记录
/*==============================================================*/
/* Table: userLogin                                             */
/*==============================================================*/
create table userLogin
(
    loginUuid    char(32) not null,
    userName     char(50),
    loginTime    char(30),
    loginIp      char(20),
    loginRemarks char(200),
    msgUuid      char(32),
    primary key (loginUuid)
);
