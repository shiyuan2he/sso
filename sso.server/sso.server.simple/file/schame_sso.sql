create DATABASE sso ;
use sso ;

CREATE TABLE t_sso_user(
  id BIGINT(19) COMMENT '用户表主键',
  user_code VARCHAR(10) COMMENT '用户编码',
  user_name VARCHAR(19) COMMENT '用户名称',
  password VARCHAR(19) COMMENT '用户登陆密码',
  password_encryption_type VARCHAR(10) COMMENT '密码加密类型',-- BASE64,AES,MD5
  mobile BIGINT(11) COMMENT '用户手机号'
) ENGINE  InnoDB;
ALTER TABLE t_sso_user COMMENT = '统一用户表';