/*
 Navicat Premium Data Transfer

 Source Server         : aliyun-yuansj
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 101.37.70.202:3306
 Source Schema         : db_shiro

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 08/10/2021 18:23:14
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `parent_id`   bigint unsigned DEFAULT NULL COMMENT '父级主键',
    `code`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编码',
    `name`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
    `icon`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
    `type`        tinyint unsigned DEFAULT NULL COMMENT '1 菜单，2按钮，3链接，4表单',
    `url`         varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'url地址',
    `id_deleted`  tinyint unsigned DEFAULT NULL,
    `create_time` datetime                                DEFAULT NULL,
    `update_time` datetime                                DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `code`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色编码',
    `name`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
    `mark`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
    `is_deleted`  tinyint unsigned DEFAULT NULL,
    `create_time` datetime                                DEFAULT NULL,
    `update_time` datetime                                DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sys_role_module_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module_ref`;
CREATE TABLE `sys_role_module_ref`
(
    `id`        bigint unsigned NOT NULL AUTO_INCREMENT,
    `role_id`   bigint unsigned DEFAULT NULL,
    `module_id` bigint unsigned DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户主键',
    `username`    varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
    `password`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录密码',
    `secret_key`  varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '盐值，尼玛密钥',
    `is_locked`   tinyint unsigned DEFAULT NULL,
    `nickname`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
    `is_deleted`  tinyint unsigned DEFAULT NULL COMMENT '是否被删除',
    `create_time` datetime                                DEFAULT NULL,
    `update_time` datetime                                DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sys_user_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_ref`;
CREATE TABLE `sys_user_role_ref`
(
    `id`      bigint unsigned NOT NULL AUTO_INCREMENT,
    `user_id` bigint unsigned DEFAULT NULL,
    `role_id` bigint unsigned DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET
FOREIGN_KEY_CHECKS = 1;
