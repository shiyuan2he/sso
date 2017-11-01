/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.216.129-root
 Source Server Type    : MariaDB
 Source Server Version : 100117
 Source Host           : 192.168.216.129:3306
 Source Schema         : sso

 Target Server Type    : MariaDB
 Target Server Version : 100117
 File Encoding         : 65001

 Date: 01/11/2017 18:05:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sso_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sso_user`;
CREATE TABLE `t_sso_user` (
  `id` bigint(19) NOT NULL DEFAULT '0' COMMENT '主键',
  `user_code` varchar(10) DEFAULT '' COMMENT '用户编码',
  `user_name` varchar(19) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(19) DEFAULT NULL COMMENT '用户密码',
  `password_encryption_type` varchar(10) DEFAULT NULL COMMENT '密码加密类型',
  `mobile` bigint(11) DEFAULT '0' COMMENT '用户手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='统一用户表';;

-- ----------------------------
-- Records of t_sso_user
-- ----------------------------
BEGIN;
INSERT INTO `t_sso_user` VALUES (111111111, '', NULL, '123', 'base64', 11111111111);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
