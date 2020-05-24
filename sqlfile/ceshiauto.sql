/*
 Navicat Premium Data Transfer

 Source Server         : cdb-6z1kjh7u.bj.tencentcdb.com
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-6z1kjh7u.bj.tencentcdb.com:10217
 Source Schema         : ceshiauto

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 24/05/2020 21:01:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('cloud-pc', 'system-server', '$2a$10$.bH97sMQcXt8Kdl3XMal7.VzpPArag1kajLb0jRRq4ksSq4/7pRsS', 'ALL,SYSTEM_API', 'authorization_code,password,implicit,client_credentials,refresh_token', 'http://cy925.top/', '', 10000, NULL, NULL, 'true');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限 ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父权限 ID (0为顶级菜单)',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权标识符',
  `index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `type` int(2) NOT NULL DEFAULT 1 COMMENT '类型(1菜单，2按钮)',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标类型',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `sort` int(3) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (11, 0, '系统首页', 'sys:index', 'dashboard', 1, 'el-icon-lx-home', '', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 1);
INSERT INTO `sys_permission` VALUES (17, 0, '基础表格', 'sys:manage', 'table', 1, 'el-icon-lx-cascades', NULL, '2023-08-08 11:11:11', '2023-08-09 15:26:28', 2);
INSERT INTO `sys_permission` VALUES (19, 17, '列表', 'sys:user:list', '', 2, '', '员工列表', '2023-08-08 11:11:11', '2023-08-08 11:11:11', 0);
INSERT INTO `sys_permission` VALUES (20, 17, '新增', 'sys:user:add', '', 2, '', '新增用户', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (21, 17, '修改', 'sys:user:edit', '', 2, '', '修改用户', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (22, 17, '删除', 'sys:user:delete', '', 2, '', '删除用户', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (23, 17, '目录结构', 'sys:index', '/devguide/structure', 1, 'ios-paper', NULL, '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (24, 23, '列表', 'sys:role:list', NULL, 2, NULL, '角色列表', '2023-08-08 11:11:11', '2023-08-08 11:11:11', 0);
INSERT INTO `sys_permission` VALUES (25, 23, '新增', 'sys:role:add', '', 2, '', '新增角色', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (26, 23, '修改', 'sys:role:edit', '', 2, '', '修改角色', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (27, 23, '删除', 'sys:role:delete', '', 2, '', '删除角色', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (28, 17, '权限管理', 'sys:permission', '/permission', 2, 'fa fa-cog', NULL, '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (29, 28, '列表', 'sys:permission:list', NULL, 2, NULL, '权限列表', '2023-08-08 11:11:11', '2023-08-08 11:11:11', 0);
INSERT INTO `sys_permission` VALUES (30, 28, '新增', 'sys:permission:add', '', 2, NULL, '新增权限', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (31, 28, '修改', 'sys:permission:edit', '', 2, NULL, '修改权限', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (32, 28, '删除', 'sys:permission:delete', '', 2, '', '删除权限', '2023-08-08 11:11:11', '2023-08-09 15:26:28', 0);
INSERT INTO `sys_permission` VALUES (33, 17, '开发规范', 'sys:index', '/devguide/standard', 1, 'ios-notifications-outline', '1', '2020-04-27 17:18:36', '2020-04-27 17:18:36', 0);
INSERT INTO `sys_permission` VALUES (34, 17, '代码模板设置', 'sys:index', '/devguide/codeTemplate', 1, 'md-lock', '1', '2020-04-27 17:19:11', '2020-04-27 17:19:11', 0);
INSERT INTO `sys_permission` VALUES (36, 0, 'tab选项卡', 'sys:index', 'tabs', 1, 'el-icon-lx-copy', NULL, '2020-05-14 15:25:41', '2020-05-14 15:25:41', 3);
INSERT INTO `sys_permission` VALUES (37, 0, '表单相关', 'sys:index', '3', 1, 'el-icon-lx-calendar', NULL, '2020-05-14 15:27:18', '2020-05-14 15:27:18', 4);
INSERT INTO `sys_permission` VALUES (38, 37, '基本表单', 'sys:index', 'form', 1, NULL, NULL, '2020-05-14 15:27:35', '2020-05-14 15:27:35', 1);
INSERT INTO `sys_permission` VALUES (39, 37, '三级菜单', 'sys:index', '3-2', 1, NULL, NULL, '2020-05-14 15:28:46', '2020-05-14 15:28:46', 2);
INSERT INTO `sys_permission` VALUES (40, 39, '富文本编辑器', 'sys:index', 'editor', 1, NULL, NULL, '2020-05-14 15:30:45', '2020-05-14 15:30:45', 1);
INSERT INTO `sys_permission` VALUES (41, 39, 'markdown编辑器', 'sys:index', 'markdown', 1, NULL, NULL, '2020-05-14 15:31:11', '2020-05-14 15:31:11', 2);
INSERT INTO `sys_permission` VALUES (42, 37, '文件上传', 'sys:index', 'upload', 1, NULL, NULL, '2020-05-14 15:31:44', '2020-05-14 15:31:44', 3);
INSERT INTO `sys_permission` VALUES (43, 0, '自定义图标', 'sys:index', 'icon', 1, 'el-icon-lx-emoji', NULL, '2020-05-14 15:32:12', '2020-05-14 15:32:12', 4);
INSERT INTO `sys_permission` VALUES (44, 0, '系统配置', 'sys:index', '11', 1, 'el-icon-s-tools', '', '2020-05-21 13:04:10', '2020-05-21 13:04:13', 20);
INSERT INTO `sys_permission` VALUES (45, 44, '菜单管理', 'sys:menu', 'menu', 1, 'el-icon-menu', '', '2020-05-21 13:05:46', '2020-05-21 13:05:48', 3);
INSERT INTO `sys_permission` VALUES (50, 17, '驱蚊器', '112', '11', 2, NULL, '1221', '2020-05-22 02:20:23', '2020-05-22 15:20:22', 1221);
INSERT INTO `sys_permission` VALUES (52, 17, '3333', '33', '333', 1, NULL, '33', '2020-05-22 02:21:16', '2020-05-22 15:21:15', 33);
INSERT INTO `sys_permission` VALUES (60, 45, '新增', 'sys:menu:add', '', 2, NULL, '菜单管理新增', '2020-05-23 00:25:50', '2020-05-23 13:25:49', 1);
INSERT INTO `sys_permission` VALUES (61, 45, '编辑', 'sys:menu:edit', '', 2, NULL, '菜单编辑', '2020-05-23 00:30:45', '2020-05-23 13:30:43', 2);
INSERT INTO `sys_permission` VALUES (62, 45, '删除', 'sys:menu:del', '', 2, NULL, '菜单删除', '2020-05-23 00:31:21', '2020-05-23 13:31:20', 3);
INSERT INTO `sys_permission` VALUES (63, 44, '角色管理', 'sys:role', 'role', 1, NULL, '角色管理', '2020-05-23 00:39:52', '2020-05-23 13:36:05', 2);
INSERT INTO `sys_permission` VALUES (64, 44, '用户管理', 'sys:user:index', 'user', 1, NULL, '用户管理', '2020-05-24 01:20:23', '2020-05-24 14:20:20', 3);
INSERT INTO `sys_permission` VALUES (65, 63, '新增', 'sys:role:add', '', 2, NULL, '角色新增', '2020-05-24 03:52:36', '2020-05-24 16:52:34', 1);
INSERT INTO `sys_permission` VALUES (66, 63, '编辑', 'sys:role:edit', '', 2, NULL, '角色编辑', '2020-05-24 03:53:21', '2020-05-24 16:53:18', 2);
INSERT INTO `sys_permission` VALUES (67, 63, '删除', 'sys:role:del', '', 2, NULL, '角色删除', '2020-05-24 03:53:42', '2020-05-24 16:53:40', 3);
INSERT INTO `sys_permission` VALUES (68, 64, '新增', 'sys:user:add', '', 2, NULL, '用户新增', '2020-05-24 03:54:10', '2020-05-24 16:54:07', 1);
INSERT INTO `sys_permission` VALUES (69, 64, '编辑', 'sys:user:edit', '', 2, NULL, '用户修改', '2020-05-24 03:54:32', '2020-05-24 16:54:29', 2);
INSERT INTO `sys_permission` VALUES (70, 64, '删除', 'sys:user:del', '', 2, NULL, '用户删除', '2020-05-24 03:54:58', '2020-05-24 16:54:55', 3);
INSERT INTO `sys_permission` VALUES (71, 64, '锁定账户', 'sys:user:switch', '', 2, NULL, '锁定账户', '2020-05-24 03:55:40', '2020-05-24 16:55:37', 4);
INSERT INTO `sys_permission` VALUES (72, 45, '列表', 'sys:menu:list', '', 2, NULL, '列表', '2020-05-24 04:05:03', '2020-05-24 17:05:00', 0);
INSERT INTO `sys_permission` VALUES (73, 63, '列表', 'sys:role:list', '', 2, NULL, '列表', '2020-05-24 04:05:27', '2020-05-24 17:05:25', 0);
INSERT INTO `sys_permission` VALUES (74, 64, '列表', 'sys:user:list', '', 2, NULL, '列表', '2020-05-24 04:05:47', '2020-05-24 17:05:45', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色 ID',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色说明',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (9, '超级管理员', '拥有所有的权限', '2023-08-08 11:11:11', '2020-05-24 04:06:48');
INSERT INTO `sys_role` VALUES (10, '普通管理员', '拥有查看权限', '2023-08-11 11:11:11', '2020-05-24 04:06:53');
INSERT INTO `sys_role` VALUES (17, '技术人员', '11', '2020-05-23 03:06:43', '2020-05-23 04:26:50');
INSERT INTO `sys_role` VALUES (22, '技术人员', '11', '2020-05-23 03:06:43', '2020-05-23 04:26:50');
INSERT INTO `sys_role` VALUES (24, '技术人员', '11', '2020-05-23 03:06:43', '2020-05-23 04:26:50');
INSERT INTO `sys_role` VALUES (26, '技术人员', '11', '2020-05-23 03:06:43', '2020-05-23 04:26:50');
INSERT INTO `sys_role` VALUES (27, '技术人员', '11', '2020-05-23 03:06:43', '2020-05-23 04:26:50');
INSERT INTO `sys_role` VALUES (28, '技术人员', '11', '2020-05-23 03:06:43', '2020-05-23 04:26:50');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 321 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (295, 9, 11);
INSERT INTO `sys_role_permission` VALUES (296, 9, 44);
INSERT INTO `sys_role_permission` VALUES (297, 9, 63);
INSERT INTO `sys_role_permission` VALUES (298, 9, 73);
INSERT INTO `sys_role_permission` VALUES (299, 9, 65);
INSERT INTO `sys_role_permission` VALUES (300, 9, 66);
INSERT INTO `sys_role_permission` VALUES (301, 9, 67);
INSERT INTO `sys_role_permission` VALUES (302, 9, 45);
INSERT INTO `sys_role_permission` VALUES (303, 9, 72);
INSERT INTO `sys_role_permission` VALUES (304, 9, 60);
INSERT INTO `sys_role_permission` VALUES (305, 9, 61);
INSERT INTO `sys_role_permission` VALUES (306, 9, 62);
INSERT INTO `sys_role_permission` VALUES (307, 9, 64);
INSERT INTO `sys_role_permission` VALUES (308, 9, 74);
INSERT INTO `sys_role_permission` VALUES (309, 9, 68);
INSERT INTO `sys_role_permission` VALUES (310, 9, 69);
INSERT INTO `sys_role_permission` VALUES (311, 9, 70);
INSERT INTO `sys_role_permission` VALUES (312, 9, 71);
INSERT INTO `sys_role_permission` VALUES (313, 10, 44);
INSERT INTO `sys_role_permission` VALUES (314, 10, 63);
INSERT INTO `sys_role_permission` VALUES (315, 10, 45);
INSERT INTO `sys_role_permission` VALUES (316, 10, 64);
INSERT INTO `sys_role_permission` VALUES (317, 10, 11);
INSERT INTO `sys_role_permission` VALUES (318, 10, 73);
INSERT INTO `sys_role_permission` VALUES (319, 10, 72);
INSERT INTO `sys_role_permission` VALUES (320, 10, 74);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储, admin/1234',
  `is_account_non_expired` int(2) NULL DEFAULT 1 COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int(2) NULL DEFAULT 1 COMMENT '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int(2) NULL DEFAULT 1 COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int(2) NULL DEFAULT 1 COMMENT '帐户是否可用(1 可用，0 删除用户)',
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `mobile`(`mobile`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (9, 'admin', '$2a$10$rDkPvvAFV8kqwvKJzwlRv.i.q.wz1w1pz0SFsHn/55jNeZFQv/eCm', 1, 1, 1, 1, '超级管理员', '13731323132', 'mengxuegu888@163.com', '2023-08-08 11:11:11', '2020-05-24 03:22:27');
INSERT INTO `sys_user` VALUES (10, 'test', '$2a$10$rDkPvvAFV8kqwvKJzwlRv.i.q.wz1w1pz0SFsHn/55jNeZFQv/eCm', 1, 0, 1, 0, '测试', '16888886666', 'test11@qq.com', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `sys_user` VALUES (11, 'test2', '$2a$10$efNLpAYr6jI7pJILAitiKeKTZb9h1Q7VhLqT6/IXf06Z/UvUqJkg.', 1, 1, 1, 1, '袁帅', '17602230056', '1728939967@qq.com', '2020-05-24 03:30:25', '2020-05-24 03:59:24');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (3, 9, 9);
INSERT INTO `sys_user_role` VALUES (6, 11, 10);

SET FOREIGN_KEY_CHECKS = 1;
