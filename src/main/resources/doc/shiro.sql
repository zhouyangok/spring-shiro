/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2015-08-06 11:09:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `code` varchar(64) NOT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `seq` tinyint(1) NOT NULL DEFAULT '0',
  `createdate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('1', 'JAVA快速开发框架', '地址', '01', 'icon-company', null, '0', '2014-02-19 01:00:00');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `seq` tinyint(1) NOT NULL DEFAULT '0',
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `resourcetype` tinyint(1) NOT NULL DEFAULT '0',
  `createdate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=216 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '系统管理', '', '系统管理', 'icon-company', null, '7', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('11', '资源管理', '/resource/manager', '资源管理', 'icon-folder', '1', '1', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('111', '列表', '/resource/treeGrid', '资源列表', 'icon-btn', '11', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('112', '添加', '/resource/add', '资源添加', 'icon-btn', '11', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('113', '编辑', '/resource/edit', '资源编辑', 'icon-btn', '11', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('114', '删除', '/resource/delete', '资源删除', 'icon-btn', '11', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('12', '角色管理', '/role/manager', '角色管理', 'icon-folder', '1', '2', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('121', '列表', '/role/dataGrid', '角色列表', 'icon-btn', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('122', '添加', '/role/add', '角色添加', 'icon-btn', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('123', '编辑', '/role/edit', '角色编辑', 'icon-btn', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('124', '删除', '/role/delete', '角色删除', 'icon-btn', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('125', '授权', '/role/grant', '角色授权', 'icon-btn', '12', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('13', '用户管理', '/user/manager', '用户管理', 'icon-folder', '1', '3', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('131', '列表', '/user/dataGrid', '用户列表', 'icon-btn', '13', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('132', '添加', '/user/add', '用户添加', 'icon-btn', '13', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('133', '编辑', '/user/edit', '用户编辑', 'icon-btn', '13', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('134', '删除', '/user/delete', '用户删除', 'icon-btn', '13', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('135', '查看', '/user/view', '用户查看', 'icon-btn', '13', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('14', '部门管理', '/organization/manager', '部门管理', 'icon-folder', '1', '4', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('141', '列表', '/organization/treeGrid', '用户列表', 'icon-btn', '14', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('142', '添加', '/organization/add', '部门添加', 'icon-btn', '14', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('143', '编辑', '/organization/edit', '部门编辑', 'icon-btn', '14', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('144', '删除', '/organization/delete', '部门删除', 'icon-btn', '14', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('145', '查看', '/organization/view', '部门查看', 'icon-btn', '14', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('2', '代码演示', '', '代码演示', 'icon-company', null, '6', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('21', 'DEMO管理', '/demo/manager', 'DEMO管理', 'icon-folder', '2', '1', '0', '0', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('211', '列表', '/demo/dataGrid', '列表', 'icon-btn', '21', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('212', '添加', '/demo/add', '添加', 'icon-btn', '21', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('213', '编辑', '/demo/edit', '编辑', 'icon-btn', '21', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('214', '删除', '/demo/delete', '删除', 'icon-btn', '21', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('215', '查看', '/demo/view', '查看', 'icon-btn', '21', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `resource` VALUES ('22', 'EASYUI', 'http://www.jeasyui.com', 'EASYUI', 'icon-folder', '2', '1', '0', '0', '2014-02-19 01:00:00');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `seq` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '0', '超级管理员，拥有全部权限');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `role_id` smallint(5) NOT NULL,
  `resource_id` smallint(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='角色资源';

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1', '1');
INSERT INTO `role_resource` VALUES ('2', '1', '11');
INSERT INTO `role_resource` VALUES ('3', '1', '111');
INSERT INTO `role_resource` VALUES ('4', '1', '112');
INSERT INTO `role_resource` VALUES ('5', '1', '113');
INSERT INTO `role_resource` VALUES ('6', '1', '114');
INSERT INTO `role_resource` VALUES ('7', '1', '12');
INSERT INTO `role_resource` VALUES ('8', '1', '121');
INSERT INTO `role_resource` VALUES ('9', '1', '122');
INSERT INTO `role_resource` VALUES ('10', '1', '123');
INSERT INTO `role_resource` VALUES ('11', '1', '124');
INSERT INTO `role_resource` VALUES ('12', '1', '125');
INSERT INTO `role_resource` VALUES ('13', '1', '13');
INSERT INTO `role_resource` VALUES ('14', '1', '131');
INSERT INTO `role_resource` VALUES ('15', '1', '132');
INSERT INTO `role_resource` VALUES ('16', '1', '133');
INSERT INTO `role_resource` VALUES ('17', '1', '134');
INSERT INTO `role_resource` VALUES ('18', '1', '135');
INSERT INTO `role_resource` VALUES ('19', '1', '14');
INSERT INTO `role_resource` VALUES ('20', '1', '141');
INSERT INTO `role_resource` VALUES ('21', '1', '142');
INSERT INTO `role_resource` VALUES ('22', '1', '143');
INSERT INTO `role_resource` VALUES ('23', '1', '144');
INSERT INTO `role_resource` VALUES ('24', '1', '145');
INSERT INTO `role_resource` VALUES ('25', '1', '2');
INSERT INTO `role_resource` VALUES ('26', '1', '21');
INSERT INTO `role_resource` VALUES ('27', '1', '211');
INSERT INTO `role_resource` VALUES ('28', '1', '212');
INSERT INTO `role_resource` VALUES ('29', '1', '213');
INSERT INTO `role_resource` VALUES ('30', '1', '214');
INSERT INTO `role_resource` VALUES ('31', '1', '215');
INSERT INTO `role_resource` VALUES ('32', '1', '22');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `sex` tinyint(1) NOT NULL DEFAULT '0',
  `age` tinyint(1) NOT NULL DEFAULT '0',
  `usertype` tinyint(1) NOT NULL DEFAULT '0',
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `organization_id` int(11) NOT NULL DEFAULT '0',
  `createdate` datetime NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '超级管理员', '21232f297a57a5a743894a0e4a801fc3', '0', '18', '0', '0', '1', '2012-06-04 01:00:00', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(5) NOT NULL,
  `role_id` smallint(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
