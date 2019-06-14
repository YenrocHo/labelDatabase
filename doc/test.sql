/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-05-25 00:41:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_datas
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_datas`;
CREATE TABLE `t_sys_datas` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `file_path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件表存储表';

-- ----------------------------
-- Records of t_sys_datas
-- ----------------------------
INSERT INTO `t_sys_datas` VALUES ('493164859534344192', 'D:/profile/57e6ed81bc836d122fcff4f86e0cebbc.jpg');
INSERT INTO `t_sys_datas` VALUES ('493191568597975040', 'D:/profile/499885e31867b77550deac0cb4596c22.jpg');
INSERT INTO `t_sys_datas` VALUES ('493194407776878592', 'D:/profile/304ed8bf6257c6b3a77e7f367e916b96.jpg');
INSERT INTO `t_sys_datas` VALUES ('493195419333951488', 'D:/profile/49c7c2126ea04b65c2164f0aff6c3279.jpg');
INSERT INTO `t_sys_datas` VALUES ('493195646874943488', 'D:/profile/b336f90e57ea73cc286e266103033c74.jpg');

-- ----------------------------
-- Table structure for t_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file`;
CREATE TABLE `t_sys_file` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片名字',
  `create_user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名字',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件信息表';

-- Records of t_sys_file
-- ----------------------------
INSERT INTO `t_sys_file` VALUES ('493105775934177280', '水电费2', '1', 'admin', '2018-09-22 17:03:25', '1', 'admin', '2018-09-22 21:01:09');
INSERT INTO `t_sys_file` VALUES ('493191574256091136', '阿达达', '1', 'admin', '2018-09-22 22:47:14', null, null, null);
-- ----------------------------

DROP TABLE IF EXISTS `t_sys_tag`;
CREATE TABLE `t_sys_tag` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `stage` varchar(255) DEFAULT NULL COMMENT '阶段',
  `variety` varchar(255) DEFAULT NULL COMMENT '种类',
  `product_Name` varchar(255) DEFAULT NULL COMMENT '产品',
  `expiration` varchar(255) DEFAULT NULL COMMENT '保质期',
  `items` varchar(255) DEFAULT NULL COMMENT '项目点',
  `print_User` varchar(255) DEFAULT NULL COMMENT '打印人',
  `print_Time` datetime DEFAULT NULL COMMENT '打印时间',
  `end_Time` datetime DEFAULT NULL COMMENT '过期时间',
  `put_Time` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='标签管理';

DROP TABLE IF EXISTS `t_sys_stage`;
CREATE TABLE `t_sys_stage` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `stage` varchar(255) DEFAULT NULL COMMENT '阶段',
  `name` varchar(255) DEFAULT NULL COMMENT '中文名',
  `english_name` varchar(255) DEFAULT NULL COMMENT '英文名',
  `create_Time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_Time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='制作阶段';

DROP TABLE IF EXISTS `t_sys_items`;
CREATE TABLE `t_sys_items` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `items` varchar(255) DEFAULT NULL COMMENT '项目点名称',
  `name` varchar(255) DEFAULT NULL COMMENT '中文名',
  `english_name` varchar(255) DEFAULT NULL COMMENT '英文名',
  `create_Time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_Time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目点';


DROP TABLE IF EXISTS `t_sys_food`;
CREATE TABLE `t_sys_food` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `food_name` varchar(255) DEFAULT NULL COMMENT '食品种类',
  `name` varchar(255) DEFAULT NULL COMMENT '中文名',
  `english_name` varchar(255) DEFAULT NULL COMMENT '英文名',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `create_Time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_Time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='食品种类';

DROP TABLE IF EXISTS `t_sys_food_picture`;
CREATE TABLE `t_sys_food_picture` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `food_id` varchar(255) DEFAULT NULL COMMENT '食品种类Id',
  `data_id` varchar(255) DEFAULT NULL COMMENT '图片id',
  `create_Time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_Time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='食品图片';

DROP TABLE IF EXISTS `t_sys_learn_file`;
CREATE TABLE `t_sys_learn_file` (
  `file_id` varchar(255) NOT NULL COMMENT 'fileId',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件上传表';

DROP TABLE IF EXISTS `t_sys_product`;
CREATE TABLE `t_sys_product` (
  `product_id` varchar(255) NOT NULL COMMENT '产品主键',
  `name` varchar(255) NOT NULL COMMENT '产品名称',
  `c_name` varchar(255) DEFAULT NULL COMMENT '产品中文名',
  `e_name` varchar(255) DEFAULT NULL COMMENT '产品英文名',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_sys_store`;
CREATE TABLE `t_sys_store` (
  `id` varchar(255) NOT NULL COMMENT '存储主键',
  `condition` varchar(255) NOT NULL COMMENT '存储名称',
  `c_condition` varchar(255) DEFAULT NULL COMMENT '存储中文名',
  `e_condition` varchar(255) DEFAULT NULL COMMENT '存储英文名',
  `temperature` varchar(255) DEFAULT NULL COMMENT '存储',
  `status` int(11) DEFAULT NULL COMMENT '存储状态',
  `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_file_data
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file_data`;
CREATE TABLE `t_sys_file_data` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `data_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `file_id` varchar(255) COLLATE utf8_bin DEFAULT '文件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件数据外键绑定表';

-- ----------------------------
-- Records of t_sys_file_data
-- ----------------------------
INSERT INTO `t_sys_file_data` VALUES ('493105048578949120', '493105005788659712', '493105048578949120');
INSERT INTO `t_sys_file_data` VALUES ('493164878349991936', '493164859534344192', '493105775934177280');
INSERT INTO `t_sys_file_data` VALUES ('493191574256091136', '493191568597975040', '493191574256091136');
INSERT INTO `t_sys_file_data` VALUES ('493191574256091137', '493164859534344192', '493191574256091136');
INSERT INTO `t_sys_file_data` VALUES ('493195660292521984', '493195646874943488', '493195660292521984');

-- ----------------------------
-- Table structure for t_sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_oper_log`;
CREATE TABLE `t_sys_oper_log` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '方法',
  `oper_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作人',
  `oper_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'url',
  `oper_param` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '参数',
  `error_msg` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `oper_time` date DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日志记录表';

-- ----------------------------
-- Records of t_sys_oper_log
-- ----------------------------
INSERT INTO `t_sys_oper_log` VALUES ('495981627277574144', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-09-30');
INSERT INTO `t_sys_oper_log` VALUES ('496130829131448320', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-01');
INSERT INTO `t_sys_oper_log` VALUES ('496130881971290112', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-01');
INSERT INTO `t_sys_oper_log` VALUES ('496131511678926848', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-01');
INSERT INTO `t_sys_oper_log` VALUES ('496133762661220352', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-01');
INSERT INTO `t_sys_oper_log` VALUES ('496801222838190080', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-02');
INSERT INTO `t_sys_oper_log` VALUES ('496801238482944000', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-02');
INSERT INTO `t_sys_oper_log` VALUES ('496802305908146176', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-02');
INSERT INTO `t_sys_oper_log` VALUES ('496804902987628544', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-02');
INSERT INTO `t_sys_oper_log` VALUES ('496805167543353344', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-02');
INSERT INTO `t_sys_oper_log` VALUES ('496806436433559552', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2018-10-02');
INSERT INTO `t_sys_oper_log` VALUES ('548521545325084672', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-22');
INSERT INTO `t_sys_oper_log` VALUES ('548522078878302208', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-22');
INSERT INTO `t_sys_oper_log` VALUES ('548522095005401088', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-22');
INSERT INTO `t_sys_oper_log` VALUES ('548522402275917824', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-22');
INSERT INTO `t_sys_oper_log` VALUES ('548522413080444928', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-22');
INSERT INTO `t_sys_oper_log` VALUES ('548522419581616128', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-22');
INSERT INTO `t_sys_oper_log` VALUES ('548522847354486784', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-22');
INSERT INTO `t_sys_oper_log` VALUES ('548527944973156352', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-22');
INSERT INTO `t_sys_oper_log` VALUES ('548527983720136704', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-22');
INSERT INTO `t_sys_oper_log` VALUES ('550320803963469824', '用户集合查询', 'cn.com.jetshine.datav.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-02-27');
INSERT INTO `t_sys_oper_log` VALUES ('554024819377569792', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-03-09');
INSERT INTO `t_sys_oper_log` VALUES ('572829171173031936', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-04-30');
INSERT INTO `t_sys_oper_log` VALUES ('572829178798276608', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-04-30');
INSERT INTO `t_sys_oper_log` VALUES ('572829190399721472', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-04-30');
INSERT INTO `t_sys_oper_log` VALUES ('572946409100148736', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572946451189989376', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572946453597519872', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572949769186443264', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572949826359001088', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572949834143629312', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572950139371520000', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572950429306978304', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572950438211485696', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572950443378868224', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572952192667877376', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572952194916024320', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572952199684947968', '用户集合查询', 'cn.com.jetshine.user.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572957590665822208', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572957612786581504', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572959611405991936', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572959625121366016', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572960399230500864', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572960403772932096', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572960405828141056', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572960407648468992', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572960408986451968', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572960409661734912', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572960410341212160', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572968038253461504', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572968047648702464', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572968061154361344', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572968071300382720', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572968085766537216', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572970121207742464', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572970169140248576', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572970186114596864', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '///UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981002880679936', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981031699742720', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981121122304000', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981123202678784', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981127552172032', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981129179561984', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981136943218688', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981830395887616', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981835462606848', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572981892177985536', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572984108251086848', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572984472446697472', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572984639782649856', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572985386804969472', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/springboot/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572985407378030592', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/springboot/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572985417922510848', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/springboot/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572985907766886400', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572985925638815744', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572985934518157312', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572990439833993216', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/demo/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('572990457194217472', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/demo/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-01');
INSERT INTO `t_sys_oper_log` VALUES ('574746434637660160', '用户集合查询', 'cn.com.jetshine.ywzspt.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-05');
INSERT INTO `t_sys_oper_log` VALUES ('574746459027537920', '用户集合查询', 'cn.com.jetshine.ywzspt.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-05');
INSERT INTO `t_sys_oper_log` VALUES ('575103573847703552', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-06');
INSERT INTO `t_sys_oper_log` VALUES ('575103582882234368', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-06');
INSERT INTO `t_sys_oper_log` VALUES ('575103584832585728', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-06');
INSERT INTO `t_sys_oper_log` VALUES ('575103588733288448', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-06');
INSERT INTO `t_sys_oper_log` VALUES ('575106362099367936', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-06');
INSERT INTO `t_sys_oper_log` VALUES ('575106383096053760', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-06');
INSERT INTO `t_sys_oper_log` VALUES ('575106403916578816', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '//UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-06');
INSERT INTO `t_sys_oper_log` VALUES ('575114555802255360', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575114613012561920', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575114624546897920', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575114634210574336', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575114676153614336', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575114684047294464', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575114707627671552', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575125179835875328', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575125181685563392', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575125191626063872', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575125198718631936', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575125200337633280', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575125208873041920', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('575126190109491200', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-07');
INSERT INTO `t_sys_oper_log` VALUES ('576600087799529472', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-11');
INSERT INTO `t_sys_oper_log` VALUES ('578214488637964288', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-15');
INSERT INTO `t_sys_oper_log` VALUES ('578215933424697344', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-15');
INSERT INTO `t_sys_oper_log` VALUES ('578295115655675904', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-15');
INSERT INTO `t_sys_oper_log` VALUES ('578334014285283328', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-15');
INSERT INTO `t_sys_oper_log` VALUES ('578334057562112000', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-15');
INSERT INTO `t_sys_oper_log` VALUES ('581539999485263872', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-24');
INSERT INTO `t_sys_oper_log` VALUES ('581641202755960832', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-25');
INSERT INTO `t_sys_oper_log` VALUES ('581642561861451776', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-25');
INSERT INTO `t_sys_oper_log` VALUES ('581642571026006016', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-25');
INSERT INTO `t_sys_oper_log` VALUES ('581642590676320256', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-25');
INSERT INTO `t_sys_oper_log` VALUES ('581642605851312128', '用户集合查询', 'com.fc.test.controller.admin.UserController.list()', 'admin', '/UserController/list', '{\"pageSize\":[\"10\"],\"pageNum\":[\"1\"]}', null, '2019-05-25');

-- ----------------------------
-- Table structure for t_sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission_role`;
CREATE TABLE `t_sys_permission_role` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(255) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限中间表';

-- ----------------------------
-- Records of t_sys_permission_role
-- ----------------------------
INSERT INTO `t_sys_permission_role` VALUES ('0d419824-3182-4455-a003-e31bb715a100', '488305788310257664', '14');
INSERT INTO `t_sys_permission_role` VALUES ('1', '488243256161730560', '27');
INSERT INTO `t_sys_permission_role` VALUES ('14060d9e-f469-457f-b4e8-9d7c7629f3a8', '488243256161730560', '586146059751784448');
INSERT INTO `t_sys_permission_role` VALUES ('14c710cc-bbc4-440f-9bfb-dfaba965f727', '488305788310257664', '575853607149109248');
INSERT INTO `t_sys_permission_role` VALUES ('18d78238-7c4f-4b52-9f16-e76b2ea24aeb', '488289006124007424', '333');
INSERT INTO `t_sys_permission_role` VALUES ('1c0fe9b3-062e-4f63-80aa-4ece532595b8', '488243256161730560', '4');
INSERT INTO `t_sys_permission_role` VALUES ('1e4a34fa-183d-405b-b44c-56b479664192', '488305788310257664', '5766007011074375683');
INSERT INTO `t_sys_permission_role` VALUES ('1e9da378-1b26-45bc-88e5-5e1a2bc3e51f', '488305788310257664', '5766007011074375682');
INSERT INTO `t_sys_permission_role` VALUES ('1eab9974-9b3c-406c-8100-f5623ffd6da4', '488243256161730560', '11');
INSERT INTO `t_sys_permission_role` VALUES ('204598c7-9708-4536-b28e-92a4e5da9b8d', '488305788310257664', '5766007011074375684');
INSERT INTO `t_sys_permission_role` VALUES ('20fb8ae7-b95a-4474-9e37-62192d9ae619', '488305788310257664', '9');
INSERT INTO `t_sys_permission_role` VALUES ('26c8f474-4411-4acf-827c-0a9eeb1032cb', '488243256161730560', '16');
INSERT INTO `t_sys_permission_role` VALUES ('287460a3-f7dc-4f67-a370-54df839b46bb', '488289006124007424', '15');
INSERT INTO `t_sys_permission_role` VALUES ('2b0946a6-ffe7-492d-9b4f-4005d1dfb70d', '488289006124007424', '111');
INSERT INTO `t_sys_permission_role` VALUES ('2d4df3b3-52bd-467a-97cd-e9441c059d5e', '488243256161730560', '586145757657038848');
INSERT INTO `t_sys_permission_role` VALUES ('30704f4e-b6cf-4010-86fc-0bf356c27fa8', '488289006124007424', '10');
INSERT INTO `t_sys_permission_role` VALUES ('31633e59-cb83-436f-89ce-9626dd3531ac', '488305788310257664', '575852089792528384');
INSERT INTO `t_sys_permission_role` VALUES ('378edca7-b8cd-4741-af44-55fe2b83dd17', '488289006124007424', '1');
INSERT INTO `t_sys_permission_role` VALUES ('38098ee8-34f1-4d26-963f-549eb8cddae8', '488243256161730560', '584032374602334208');
INSERT INTO `t_sys_permission_role` VALUES ('393521ad-97b4-4ea5-9d50-e80024429567', '488243256161730560', '9');
INSERT INTO `t_sys_permission_role` VALUES ('3f400f65-ef0a-446e-8ba8-7a3b59ce3c91', '488243256161730560', '496782496638173184');
INSERT INTO `t_sys_permission_role` VALUES ('4593aaae-42e7-4877-ac9a-939e80e37d05', '488289006124007424', '4');
INSERT INTO `t_sys_permission_role` VALUES ('4961f095-b7b3-49bd-bb9f-077b1b83b2b0', '488243256161730560', '585050382246346752');
INSERT INTO `t_sys_permission_role` VALUES ('49e24731-35b5-4d04-9ee1-8b162ce8003c', '488243256161730560', '586146568323727360');
INSERT INTO `t_sys_permission_role` VALUES ('4a306a99-b71d-4156-b6b1-1fd91a5eb482', '488243256161730560', '23');
INSERT INTO `t_sys_permission_role` VALUES ('4afa8efc-f4dd-45d8-a7e3-ca06fd18b829', '488305788310257664', '496805860547231744');
INSERT INTO `t_sys_permission_role` VALUES ('4dc4c717-228b-45c9-b022-c9e4205f4708', '488305788310257664', '18');
INSERT INTO `t_sys_permission_role` VALUES ('4fbeca28-1e05-441c-98c2-69e9be6128c5', '488243256161730560', '13');
INSERT INTO `t_sys_permission_role` VALUES ('50059c69-164d-45ec-b6e6-4a5401777421', '488305788310257664', '5766007011074375681');
INSERT INTO `t_sys_permission_role` VALUES ('526b84df-71a8-45cf-8952-ef12774d1b1e', '488243256161730560', '585940351957598208');
INSERT INTO `t_sys_permission_role` VALUES ('536ff514-ac3c-48b2-a4d8-fb4bbce5e470', '488305788310257664', '5');
INSERT INTO `t_sys_permission_role` VALUES ('615eb5fa-8b26-44e2-a8aa-baa7df4989b9', '488305788310257664', '12');
INSERT INTO `t_sys_permission_role` VALUES ('61c99b57-63c2-4e78-91ac-9ddfaf1b06c5', '488243256161730560', '585051069143318528');
INSERT INTO `t_sys_permission_role` VALUES ('664f100d-8c2a-44b2-87eb-b19eab16052f', '488243256161730560', '586146275997515776');
INSERT INTO `t_sys_permission_role` VALUES ('6ab07923-acb9-4243-a535-e5c0b5aa63f5', '488243256161730560', '584032225905868800');
INSERT INTO `t_sys_permission_role` VALUES ('6c4217c0-d3b2-48f6-b9cb-a1c3df78263b', '488243256161730560', '585051732246003712');
INSERT INTO `t_sys_permission_role` VALUES ('6d5144e9-f91c-4235-92f6-8db71d1febe0', '488243256161730560', '15');
INSERT INTO `t_sys_permission_role` VALUES ('71dcae3a-f18e-4997-b966-518575497d4d', '488305788310257664', '10');
INSERT INTO `t_sys_permission_role` VALUES ('72278064-2a41-46cb-829a-1d18e6990998', '488305788310257664', '19');
INSERT INTO `t_sys_permission_role` VALUES ('7393c0f7-db0b-4841-a9cc-54263e27d5b1', '488243256161730560', '586146763983814656');
INSERT INTO `t_sys_permission_role` VALUES ('748a5e74-cf65-47da-aa56-560471d3f641', '488243256161730560', '585099096482643968');
INSERT INTO `t_sys_permission_role` VALUES ('7a2c8752-2439-4e67-9318-eabe577dc1b9', '488243256161730560', '19');
INSERT INTO `t_sys_permission_role` VALUES ('7ed9ed30-87b7-4e93-ae3d-6d62ec5da890', '488243256161730560', '18');
INSERT INTO `t_sys_permission_role` VALUES ('7f40ce1b-6c36-4b61-a99f-ec8197e03cef', '488243256161730560', '14');
INSERT INTO `t_sys_permission_role` VALUES ('815c7b31-7d82-4d43-9683-8c4168237da3', '488305788310257664', '486690002869157888');
INSERT INTO `t_sys_permission_role` VALUES ('83a1171b-ba52-433e-82e6-07526658fe7e', '488305788310257664', '576600701107437568');
INSERT INTO `t_sys_permission_role` VALUES ('840933c6-b0ab-4629-845d-b19d73a0d75b', '488305788310257664', '21');
INSERT INTO `t_sys_permission_role` VALUES ('85845cb3-9a7a-41a2-986d-02ac10420492', '488305788310257664', '1');
INSERT INTO `t_sys_permission_role` VALUES ('866f76a4-2a08-46fd-a6f8-a63b063f149c', '488243256161730560', '585518674798968832');
INSERT INTO `t_sys_permission_role` VALUES ('8707a733-05f7-43ae-97b7-10babf414783', '488243256161730560', '22');
INSERT INTO `t_sys_permission_role` VALUES ('8746b5f2-6858-48a8-bae4-568f0ded8fd5', '488305788310257664', '23');
INSERT INTO `t_sys_permission_role` VALUES ('8967fff4-8019-4aba-98cc-50d374c4bfd6', '488305788310257664', '11');
INSERT INTO `t_sys_permission_role` VALUES ('8c9c2a91-26e3-4400-9d7f-4ed1e009a1f8', '488305788310257664', '7');
INSERT INTO `t_sys_permission_role` VALUES ('94e94f2e-6ac3-4ace-87d9-227a6894054f', '488243256161730560', '585050590191550464');
INSERT INTO `t_sys_permission_role` VALUES ('9780fd13-2055-4d10-a674-6dcd969250f6', '488243256161730560', '12');
INSERT INTO `t_sys_permission_role` VALUES ('98c307b3-fc2a-4ec2-8e06-e4e9ab962c1d', '488243256161730560', '24');
INSERT INTO `t_sys_permission_role` VALUES ('9a1d7545-21ea-4a45-ba8e-de92e959e35c', '488243256161730560', '20');
INSERT INTO `t_sys_permission_role` VALUES ('9fd8ba61-4677-4fb0-801f-925a153746b4', '488243256161730560', '7');
INSERT INTO `t_sys_permission_role` VALUES ('a2b2cf43-77a8-4aed-8cb0-dcda1a89dcce', '488243256161730560', '6');
INSERT INTO `t_sys_permission_role` VALUES ('a4622fec-dffc-4c96-8a56-3c8d27584d9a', '488305788310257664', '20');
INSERT INTO `t_sys_permission_role` VALUES ('a89ccffd-1dec-4052-b49e-a07341e13b90', '488305788310257664', '496782496638173184');
INSERT INTO `t_sys_permission_role` VALUES ('a8a6b92f-f263-484f-8a3c-a3a801376a17', '488305788310257664', '6');
INSERT INTO `t_sys_permission_role` VALUES ('a8ef1920-8fa6-4167-90d8-281516db8b7f', '488243256161730560', '5');
INSERT INTO `t_sys_permission_role` VALUES ('a931f970-2a69-42d9-a426-0a97c4977147', '488289006124007424', '9');
INSERT INTO `t_sys_permission_role` VALUES ('adde3507-1392-42af-9a58-e2d44b028f17', '488243256161730560', '486690002869157888');
INSERT INTO `t_sys_permission_role` VALUES ('af83f7de-691c-4913-9649-7e01e46cdc82', '488243256161730560', '17');
INSERT INTO `t_sys_permission_role` VALUES ('b141a6c5-1bc5-4e54-b80b-f56f47515400', '488305788310257664', '8');
INSERT INTO `t_sys_permission_role` VALUES ('c5c998d0-aa42-4106-af58-2f50d0afe03c', '488289006124007424', '5');
INSERT INTO `t_sys_permission_role` VALUES ('c76e15d9-8b7a-477a-8c9e-139280a3a381', '488243256161730560', '1');
INSERT INTO `t_sys_permission_role` VALUES ('cda18f82-aeae-429b-acd0-c00a94e81297', '488243256161730560', '8');
INSERT INTO `t_sys_permission_role` VALUES ('ce6877e4-c467-4077-a8c9-7fccdd976122', '488305788310257664', '4');
INSERT INTO `t_sys_permission_role` VALUES ('d4d174e2-5e83-49b0-b658-c56b76a3ab05', '488305788310257664', '22');
INSERT INTO `t_sys_permission_role` VALUES ('d8c9517d-aaaa-43c9-b108-b367c692c094', '488305788310257664', '496803374054768640');
INSERT INTO `t_sys_permission_role` VALUES ('db460904-b746-4a95-85e3-eb0170d71aa8', '488243256161730560', '585425759993069568');
INSERT INTO `t_sys_permission_role` VALUES ('ded3451c-9dce-4212-afed-d369380411e2', '488305788310257664', '17');
INSERT INTO `t_sys_permission_role` VALUES ('e6d33929-1c05-4c54-b594-2ba0ab7312fb', '488289006124007424', '14');
INSERT INTO `t_sys_permission_role` VALUES ('e83a90f3-f2a0-4d0b-817b-56711880f970', '488243256161730560', '26');
INSERT INTO `t_sys_permission_role` VALUES ('e84b3495-079a-4922-bd65-ce88ebdfe287', '488243256161730560', '585498681881395200');
INSERT INTO `t_sys_permission_role` VALUES ('e894b510-ae28-4873-a22a-8b5d672444fa', '488243256161730560', '585124540586131456');
INSERT INTO `t_sys_permission_role` VALUES ('e9b44699-760b-4a7e-8232-704b26484e72', '488243256161730560', '25');
INSERT INTO `t_sys_permission_role` VALUES ('ec718882-8dbe-45cf-ba6c-c08a8162fa9c', '488243256161730560', '10');
INSERT INTO `t_sys_permission_role` VALUES ('ed152ba2-68ca-469d-acb1-b2dcd3535063', '488243256161730560', '21');
INSERT INTO `t_sys_permission_role` VALUES ('f31f64ad-0752-4bfd-8732-8c250722a44a', '488305788310257664', '13');
INSERT INTO `t_sys_permission_role` VALUES ('f42af1bf-a892-40f6-98cb-d21b746986bb', '488305788310257664', '15');
INSERT INTO `t_sys_permission_role` VALUES ('f9eaa18f-7e35-40e0-84c7-235ce6873a14', '488305788310257664', '575851658483859456');
INSERT INTO `t_sys_permission_role` VALUES ('fe03a878-8400-4b5b-8829-a990b14cb3f2', '488289006124007424', '222');
INSERT INTO `t_sys_permission_role` VALUES ('ff422257-5bec-43f2-87f1-8a6294f9b3b1', '488305788310257664', '16');

-- ----------------------------
-- Table structure for t_sys_premission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_premission`;
CREATE TABLE `t_sys_premission` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `descripion` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) DEFAULT NULL COMMENT '授权链接',
  `pid` varchar(255) DEFAULT NULL COMMENT '父节点id',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_sys_premission
-- ----------------------------
INSERT INTO `t_sys_premission` VALUES ('1', '首页', '首页', null, '0', null, '0', 'fa fa-home', '1');
INSERT INTO `t_sys_premission` VALUES ('10', '角色集合', '角色集合', '/RoleController/list', '9', 'system:role:list', '2', '', null);
INSERT INTO `t_sys_premission` VALUES ('11', '角色添加', '角色添加', '/RoleController/add', '9', 'system:role:add', '2', 'entypo-plus-squared', null);
INSERT INTO `t_sys_premission` VALUES ('12', '角色删除', '角色删除', '/RoleController/remove', '9', 'system:role:remove', '2', 'entypo-trash', null);
INSERT INTO `t_sys_premission` VALUES ('13', '角色修改', '角色修改', '/RoleController/edit', '9', 'system:role:edit', '2', 'fa fa-wrench', null);
INSERT INTO `t_sys_premission` VALUES ('14', '权限展示', '权限展示', '/PremissionController/view', '496782496638173184', 'system:premission:view', '1', 'fa fa-key', null);
INSERT INTO `t_sys_premission` VALUES ('15', '权限集合', '权限集合', '/PremissionController/list', '14', 'system:premission:list', '2', '', null);
INSERT INTO `t_sys_premission` VALUES ('16', '权限添加', '权限添加', '/PremissionController/add', '14', 'system:premission:add', '2', 'entypo-plus-squared', null);
INSERT INTO `t_sys_premission` VALUES ('17', '权限删除', '权限删除', '/PremissionController/remove', '14', 'system:premission:remove', '2', 'entypo-trash', null);
INSERT INTO `t_sys_premission` VALUES ('18', '权限修改', '权限修改', '/PremissionController/edit', '14', 'system:premission:edit', '2', 'fa fa-wrench', null);
INSERT INTO `t_sys_premission` VALUES ('19', '文件展示', '文件展示', '/FileController/view', '496782496638173184', 'system:file:view', '1', 'fa fa-file-image-o', null);
INSERT INTO `t_sys_premission` VALUES ('20', '文件添加', '文件添加', '/FileController/add', '19', 'system:file:add', '2', 'entypo-plus-squared', null);
INSERT INTO `t_sys_premission` VALUES ('21', '文件删除', '文件删除', '/FileController/remove', '19', 'system:file:remove', '2', 'entypo-trash', null);
INSERT INTO `t_sys_premission` VALUES ('22', '文件修改', '文件修改', '/FileController/edit', '19', 'system:file:edit', '2', 'fa fa-wrench', null);
INSERT INTO `t_sys_premission` VALUES ('23', '文件集合', '文件集合', '/FileController/list', '19', 'system:file:list', '2', '', null);
INSERT INTO `t_sys_premission` VALUES ('24', '产品管理', '产品管理', '/ProductController/view', '585050382246346752', 'system:product:view', '1', 'fa fa-smile-o', '3');
INSERT INTO `t_sys_premission` VALUES ('25', '产品列表', '产品列表', '/ProductController/list', '24', 'system:product:list', '2', 'fa fa-gear', '3');
INSERT INTO `t_sys_premission` VALUES ('26', '产品添加', '产品添加', '/ProductController/add', '24', 'system:product:add', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('27', '产品删除', '产品删除', '/ProductController/remove', '24', 'system:product:remove', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('4', '用户管理', '用户展示', '/UserController/view', '496782496638173184', 'system:user:view', '1', 'icon icon-user', '1');
INSERT INTO `t_sys_premission` VALUES ('486690002869157888', '用户密码修改', '用户密码修改', '/UserController/editPwd', '4', 'system:user:editPwd', '2', 'entypo-tools', '3');
INSERT INTO `t_sys_premission` VALUES ('496126970468237312', '日志展示', '日志管理', '/LogController/view', '496124944220946432', 'system:log:view', '1', 'fa-bitbucket', null);
INSERT INTO `t_sys_premission` VALUES ('496127240363311104', '日志删除', '日志删除', '/LogController/remove', '496126970468237312', 'system:log:remove', '2', 'entypo-trash', null);
INSERT INTO `t_sys_premission` VALUES ('496127794879660032', '日志集合', '日志集合', '/LogController/list', '496126970468237312', 'system:log:list', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('496782496638173184', '系统设置', '系统设置', null, '1', null, '0', 'fa fa-gear', '3');
INSERT INTO `t_sys_premission` VALUES ('5', '用户集合', '用户集合', '/UserController/list', '4', 'system:user:list', '2', '', null);
INSERT INTO `t_sys_premission` VALUES ('575853607149109248', '代码集合', '代码集合', '/generatorController/list', '575852089792528384', 'system:generator:list', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('581541547099553792', 'druid监控', '/druid/', '/druid/', '496782496638173184', 'user:list', '1', 'fa fa-line-chart', null);
INSERT INTO `t_sys_premission` VALUES ('584032225905868800', '标签管理', '标签管理', '/TagController/view', '496782496638173184', 'system:tag:view', '1', 'fa fa-bandcamp', null);
INSERT INTO `t_sys_premission` VALUES ('584032374602334208', '标签列表', '', '/TagController/list', '584032225905868800', 'system:tag:list', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('585050382246346752', '流程管理', '流程管理', null, '1', null, '0', 'fa fa-window-restore', '3');
INSERT INTO `t_sys_premission` VALUES ('585050590191550464', '制作阶段', '制作阶段', '/StageController/view', '585050382246346752', 'system:stage:view', '1', 'fa fa-superpowers', null);
INSERT INTO `t_sys_premission` VALUES ('585051069143318528', '食品种类', '食品种类', '/FoodController/view', '585050382246346752', 'system:food:view', '1', 'fa fa-smile-o', '1');
INSERT INTO `t_sys_premission` VALUES ('585051732246003712', '储存条件', '', '/EmployeesController/listq', '585050382246346752', 'system:user:viewq', '1', 'fa fa-television', null);
INSERT INTO `t_sys_premission` VALUES ('585099096482643968', '阶段列表', '阶段列表', '/StageController/list', '585050590191550464', 'system:stage:list', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('585124540586131456', '阶段新增', '阶段新增', '/StageController/add', '585050590191550464', 'system:stage:add', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('585425759993069568', '阶段修改', '阶段修改', '/StageController/edit', '585050590191550464', 'system:stage:edit', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('585498681881395200', '食品列表', '食品列表', '/FoodController/list', '585051069143318528', 'system:food:list', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('585518674798968832', '食品添加', '食品添加', '/FoodController/add', '585051069143318528', 'system:food:add', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('585940351957598208', '删除阶段', '删除阶段', '/StageController/remove', '585050590191550464', 'system:stage:remove', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('586145757657038848', '项目点管理', '项目点管理', '/ItemsController/view', '585050382246346752', 'system:items:view', '1', 'fa fa-book', null);
INSERT INTO `t_sys_premission` VALUES ('586146059751784448', '项目点列表', '项目点列表', '/ItemsController/list', '586145757657038848', 'system:items:list', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('586146275997515776', '项目点添加', '项目点添加', '/ItemsController/add', '586145757657038848', 'system:items:add', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('586146568323727360', '项目点删除', '项目点删除', '/ItemsController/remove', '586145757657038848', 'system:items:remove', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('586146763983814656', '项目点编辑', '项目点编辑', '/ItemsController/edit', '586145757657038848', 'system:items:edit', '2', null, null);
INSERT INTO `t_sys_premission` VALUES ('6', '用户添加', '用户添加', '/UserController/add', '4', 'system:user:add', '2', 'entypo-plus-squared', null);
INSERT INTO `t_sys_premission` VALUES ('7', '用户删除', '用户删除', '/UserController/remove', '4', 'system:user:remove', '2', 'entypo-trash', null);
INSERT INTO `t_sys_premission` VALUES ('8', '用户修改', '用户修改', '/UserController/edit', '4', 'system:user:edit', '2', 'fa fa-wrench', null);
INSERT INTO `t_sys_premission` VALUES ('9', '角色管理', '角色展示', '/RoleController/view', '496782496638173184', 'system:role:view', '1', 'fa fa-group', null);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('488243256161730560', '管理员');
INSERT INTO `t_sys_role` VALUES ('488289006124007424', '用户');
INSERT INTO `t_sys_role` VALUES ('488305788310257664', '能修改用户密码角色');

-- ----------------------------
-- Table structure for t_sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_user`;
CREATE TABLE `t_sys_role_user` (
  `id` varchar(255) NOT NULL,
  `sys_user_id` varchar(255) DEFAULT NULL COMMENT 'ç”¨æˆ·id',
  `sys_role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- ----------------------------
-- Records of t_sys_role_user
-- ----------------------------
INSERT INTO `t_sys_role_user` VALUES ('2', '2', '2');
INSERT INTO `t_sys_role_user` VALUES ('488203259031322624', '487424004592762880', '488203230455529472');
INSERT INTO `t_sys_role_user` VALUES ('488243438727200768', '1', '488243256161730560');
INSERT INTO `t_sys_role_user` VALUES ('488305827225010176', '488294747442511872', '488289006124007424');
INSERT INTO `t_sys_role_user` VALUES ('488305827485057024', '488294747442511872', '488305788310257664');
INSERT INTO `t_sys_role_user` VALUES ('488857903675998208', '488857903675998208', '488289006124007424');
INSERT INTO `t_sys_role_user` VALUES ('575114609501929472', '575114609501929472', '488243256161730560');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3');
INSERT INTO `t_sys_user` VALUES ('488294747442511872', 'fuce', 'e10adc3949ba59abbe56e057f20f883e');

/*
    code by liyuan
    2019/6/5 10:59
*/
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_product
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_product`;
CREATE TABLE `t_sys_product`  (
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品主键',
  `c_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品中文名',
  `e_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品英文名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;