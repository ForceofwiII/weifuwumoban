CREATE TABLE sku_images (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '图片ID',
#                                sku_id BIGINT NOT NULL COMMENT 'SKU ID',
                            type varchar(10) NOT NULL COMMENT '图片类型',
                            url VARCHAR(500) NOT NULL COMMENT '图片路径或URL',
                            name VARCHAR(255) NOT NULL COMMENT '图片名称',
                            description TEXT COMMENT '图片描述',
                            tags VARCHAR(255) COMMENT '图片标签, json数组',
                            format varchar(10) NOT NULL COMMENT '图片格式',
                            user_name VARCHAR(255) NOT NULL COMMENT '上传用户名',
                            sort_order INT DEFAULT 0 COMMENT '图片顺序',
                            create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            is_delete tinyint DEFAULT 0 COMMENT '逻辑删除',
                            INDEX idx_username (user_name) COMMENT '用户名索引',
                            INDEX idx_name (name) COMMENT '图片名索引',
                            INDEX idx_type (type) COMMENT '图片类型索引',
                            INDEX idx_tags (tags) COMMENT '图片标签索引'
) comment 'sku图片表' collate =utf8mb4_unicode_ci;


create table user
(
    id             bigint auto_increment comment 'id'
        primary key,
    user_name      varchar(256)                           not null comment '账号',
    email          varchar(256)                           null comment '邮箱',
    phone          varchar(20)                            null comment '手机号',
    login_type     varchar(256)                           not null comment '登录类型：account/phone/email/wechat',
    third_party_id varchar(256)                           null comment '第三方id',
    password       varchar(512)                           not null comment '密码',
    nick_name      varchar(256)                           null comment '用户昵称',
    user_avatar    varchar(1024)                          null comment '用户头像',
    user_profile   varchar(512)                           null comment '用户简介',
    user_role      varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    edit_time      datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    create_time    datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete      tinyint      default 0                 not null comment '是否删除'
)
    comment '用户' collate = utf8mb4_unicode_ci;

create index nickName
    on user (nick_name);


create table user_member
(
    id              bigint auto_increment comment '主键id'
        primary key,
    user_id         bigint                             not null comment '用户ID，关联user表',
    userAccount     varchar(256)                       not null comment '账号',
    member_level_id bigint                             not null comment '会员等级ID，关联ums_member_level表',
    valid_from      datetime                           not null comment '会员开始时间',
    valid_to        datetime                           not null comment '会员到期时间',
    status          tinyint  default 1                 not null comment '会员状态[1->有效；0->过期]',
    create_time     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted      tinyint  default 0                 not null comment '逻辑删除标记[0->未删除；1->已删除]',
    constraint user_id
        unique (user_id, member_level_id)


)
    comment '用户会员表';

create index idx_status
    on user_member (status);

create index idx_user_id
    on user_member (user_id);

create index member_level_id
    on user_member (member_level_id);


create table ums_member_level
(
    id                      bigint auto_increment comment 'id'
        primary key,
    name                    varchar(100)             null comment '等级名称',
    growth_point            int                      null comment '等级需要的成长值',
    default_status          tinyint                  null comment '是否为默认等级[0->不是；1->是]',
    free_freight_point      decimal(18, 4)           null comment '免运费标准',
    comment_growth_point    int                      null comment '每次评价获取的成长值',
    priviledge_free_freight tinyint                  null comment '是否有免邮特权',
    priviledge_member_price tinyint                  null comment '是否有会员价格特权',
    priviledge_birthday     tinyint                  null comment '是否有生日特权',
    note                    varchar(255)             null comment '备注',
    createTime              datetime default (now()) null,
    updateTime              datetime default (now()) null,
    is_delete               int      default 0       null,
    constraint name
        unique (name)
)
    comment '会员等级';
