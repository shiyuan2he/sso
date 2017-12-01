
DROP TABLE IF EXISTS `t_sso_user`;
CREATE TABLE `t_sso_user` (
  `id` bigint(19) NOT NULL PRIMARY KEY DEFAULT '0' COMMENT '主键',
  `user_code` varchar(10) DEFAULT '' UNIQUE COMMENT '用户编码',
  `user_name` varchar(19) DEFAULT NULL UNIQUE COMMENT '用户名称',
  `password` varchar(19) DEFAULT NULL COMMENT '用户密码',
  `password_encryption_type` varchar(10) DEFAULT NULL COMMENT '密码加密类型',
  `mobile` bigint(11) DEFAULT '0' UNIQUE COMMENT '用户手机号',
  email VARCHAR(30) DEFAULT null UNIQUE comment '邮箱',
  sex tinyint(1) DEFAULT 0 COMMENT '性别：0 保密，1男2女',
  remark VARCHAR(100) DEFAULT null COMMENT '备注' ,
  source VARCHAR(20) DEFAULT NULL COMMENT '用户来源',
  is_del TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除 0：启用 1：禁用',
  creater BIGINT(19) NOT NULL DEFAULT 0 COMMENT '创建者',
  create_time TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  updater BIGINT(19) DEFAULT 0 COMMENT '更新者',
  update_time TIMESTAMP DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='统一用户表';
COMMIT;
insert into t_sso_user(id,mobile,create_time,creater) values (0,11111111111,now(),0);

DROP table IF EXISTS t_sso_role;
create table t_sso_role(
  id BIGINT(19) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键id',
  role_name VARCHAR(20) NOT NULL UNIQUE DEFAULT '' COMMENT '角色名称',
  role_description VARCHAR(20) NOT NULL DEFAULT '' COMMENT '角色描述',
  creater BIGINT(19) NOT NULL DEFAULT 0 COMMENT '创建者',
  create_time TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  updater BIGINT(19) DEFAULT 0 COMMENT '更新者',
  update_time TIMESTAMP DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  FOREIGN KEY(creater) REFERENCES t_sso_user(id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '用户角色表';
commit;
insert into t_sso_role
(role_name, role_description, creater, create_time)
values
  ('超级管理员','最大权限',0,now()),
  ('普通管理员','中等权限',0,now()),
  ('一般用户','少量权限',0,now())
;

DROP TABLE IF EXISTS t_sso_user_role;
create table t_sso_user_role(
  id BIGINT(19) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
  user_id BIGINT(19) NOT NULL DEFAULT 0 COMMENT '关联用户表主键',
  role_id BIGINT(19) NOT NULL DEFAULT 0 COMMENT '关联角色表主键',
  FOREIGN KEY(user_id) REFERENCES t_sso_user(id),
  FOREIGN KEY(role_id) REFERENCES t_sso_role(id),
  UNIQUE KEY 'idx_uqi_userId_roleId' (user_id,role_id)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8 COMMENT '用户角色表';

insert into t_sso_user_role(user_id,role_id) values(0,1);


DROP  table IF EXISTS t_sso_permission;
create table t_sso_permission(
  id BIGINT(19) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键id',
  auth_address VARCHAR(100) NOT NULL UNIQUE DEFAULT '' COMMENT '权限地址',
  auth_description VARCHAR(100) NOT NULL DEFAULT '' COMMENT '权限描述',
  parent_id BIGINT(19) NOT NULL DEFAULT 0 COMMENT '父级id',
  auth_type VARCHAR(10) not NULL DEFAULT null COMMENT '权限类型：BUTTON:按钮 MENU:菜单',
  creater BIGINT(19) NOT NULL DEFAULT 0 COMMENT '创建者',
  create_time TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  updater BIGINT(19) DEFAULT 0 COMMENT '更新者',
  update_time TIMESTAMP DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  FOREIGN KEY(creater) REFERENCES t_sso_user(id)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8 COMMENT '用户权限表';

insert into t_sso_permission
(auth_address,auth_description,parent_id,creater,create_time)
values
  ('/sso/user/list','用户管理',0,0,now()),
  ('/sso/user/add/{id}','添加用户',0,0,now()),
  ('/sso/user/update/{id}','修改用户',0,0,now()),
  ('/sso/user/delete/{id}','删除用户',0,0,now()),
  ('/sso/role/delete/{id}','角色管理',0,0,now()),
  ('/sso/role/delete/{id}','添加角色',0,0,now()),
  ('/sso/role/delete/{id}','修改角色',0,0,now()),
  ('/sso/role/delete/{id}','删除角色',0,0,now())
;

DROP table if EXISTS t_sso_role_permission;
create table t_sso_role_permission(
  id BIGINT(19) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
  role_id BIGINT(19) NOT NULL DEFAULT 0 COMMENT '关联角色表主键',
  permission_id BIGINT(19) NOT NULL DEFAULT 0 COMMENT '关联角色表主键',
  FOREIGN KEY(role_id) references t_sso_role(id),
  FOREIGN KEY(permission_id) REFERENCES t_sso_permission(id),
  UNIQUE KEY 'idx_uqi_roleId_permissionId' (role_id,permission_id)
) ENGINE = InnoDB AUTO_INCREMENT=1 CHARSET utf8 COMMENT '角色权限表';

alter table t_sso_user add column sex TINYINT(1) NOT NULL DEFAULT 1 COMMENT '性别：1男2女' AFTER password_encryption_type;
alter table t_sso_user add column email VARCHAR(50) DEFAULT '' COMMENT '用户邮箱' AFTER mobile;
alter table t_sso_user add column remark VARCHAR(100) DEFAULT '' COMMENT '备注' AFTER source;
alter table t_sso_user add column is_del TINYINT(1) DEFAULT '0' not null COMMENT '是否删除' AFTER remark;


