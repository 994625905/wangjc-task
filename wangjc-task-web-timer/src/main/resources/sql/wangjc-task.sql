/*
Navicat MySQL Data Transfer

Source Server         : tecent
Source Server Version : 50730
Source Host           : *******
Source Database       : wangjc-task

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2020-07-22 17:28:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `task_ssid` varchar(100) DEFAULT NULL COMMENT '唯一标识',
  `task_explain` varchar(255) DEFAULT NULL COMMENT '任务说明',
  `allow_do` tinyint(1) DEFAULT '0' COMMENT '是否允许手动触发：0不允许，1允许',
  `auto_do` tinyint(1) DEFAULT '0' COMMENT '0自动，1手动',
  `task_url` varchar(255) DEFAULT NULL COMMENT '任务链接：提供手动触发的接口',
  `task_token` varchar(255) DEFAULT NULL COMMENT '验证token',
  `task_date` varchar(255) DEFAULT NULL COMMENT '执行时间',
  `is_param` tinyint(1) DEFAULT '0' COMMENT '调用是否存在参数，0不存在，1存在（一般泛指时间跨度）',
  `create_time` bigint(10) DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT '1' COMMENT '0停机，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES ('1', '定时任务：测试执行', '3df834c5-13d6-415b-a149-ac6b56f40f41', '这是一条描述信息，测试执行', '1', '0', 'http://127.0.0.1:8092/wangjc-task-web-timer/taskHand/testRun', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwidXNlcklkIjoxfQ.2FSkOf8igXPdw-K_GA28qXoninlbD_DqD_V7smaLciE', '0 0/2 * * * ?', '1', '1589967238', '0');
INSERT INTO `t_task` VALUES ('3', '定时任务：测试终止', 'f2f8ce21-bf93-4178-8abe-c94f14d8fcd1', '这是一条描述信息，测试终止', '1', '0', 'http://127.0.0.1:8092/wangjc-task-web-timer/taskHand/testStop', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwidXNlcklkIjoxfQ.2FSkOf8igXPdw-K_GA28qXoninlbD_DqD_V7smaLciE', '0 0/2 * * * ?', '1', '1589970874', '1');
INSERT INTO `t_task` VALUES ('6', '定时任务：测试实现SchedulingConfigurer来动态注入规则', '202007221441023440001', '尝试分离出corn来，动态变更定时规则', '1', '0', 'http://127.0.0.1:8092/wangjc-task-web-timer/taskHand/testImpl', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwidXNlcklkIjoxfQ.2FSkOf8igXPdw-K_GA28qXoninlbD_DqD_V7smaLciE', '0 0/2 * * * ?', '0', '1595400062', '1');

-- ----------------------------
-- Table structure for t_task_log
-- ----------------------------
DROP TABLE IF EXISTS `t_task_log`;
CREATE TABLE `t_task_log` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `taskid` int(3) DEFAULT NULL COMMENT '定时任务id',
  `run_type` tinyint(1) DEFAULT '0' COMMENT '运行类型，0自动，1手动',
  `run_time` bigint(10) DEFAULT NULL COMMENT '运行时间（手动运行的）',
  `success` tinyint(1) DEFAULT '1' COMMENT '0失败，1成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=727 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task_log
-- ----------------------------
INSERT INTO `t_task_log` VALUES ('270', '3', '1', '1595332506', '1');
INSERT INTO `t_task_log` VALUES ('271', '3', '1', '1595332574', '1');
INSERT INTO `t_task_log` VALUES ('272', '3', '1', '1595332881', '1');
INSERT INTO `t_task_log` VALUES ('273', '1', '0', '1595377201', '1');
INSERT INTO `t_task_log` VALUES ('274', '3', '0', '1595388420', '1');
INSERT INTO `t_task_log` VALUES ('275', '1', '0', '1595388420', '1');
INSERT INTO `t_task_log` VALUES ('276', '1', '0', '1595388480', '1');
INSERT INTO `t_task_log` VALUES ('277', '3', '0', '1595388480', '1');
INSERT INTO `t_task_log` VALUES ('278', '1', '0', '1595388540', '1');
INSERT INTO `t_task_log` VALUES ('279', '3', '0', '1595388540', '1');
INSERT INTO `t_task_log` VALUES ('280', '1', '0', '1595388960', '1');
INSERT INTO `t_task_log` VALUES ('281', '1', '0', '1595389020', '1');
INSERT INTO `t_task_log` VALUES ('282', '3', '0', '1595389260', '1');
INSERT INTO `t_task_log` VALUES ('283', '3', '0', '1595389320', '1');
INSERT INTO `t_task_log` VALUES ('284', '3', '0', '1595389380', '1');
INSERT INTO `t_task_log` VALUES ('285', '1', '0', '1595389440', '1');
INSERT INTO `t_task_log` VALUES ('286', '3', '0', '1595389440', '1');
INSERT INTO `t_task_log` VALUES ('287', '3', '0', '1595389500', '1');
INSERT INTO `t_task_log` VALUES ('288', '1', '0', '1595389500', '1');
INSERT INTO `t_task_log` VALUES ('289', '3', '0', '1595389560', '1');
INSERT INTO `t_task_log` VALUES ('290', '1', '0', '1595389560', '1');
INSERT INTO `t_task_log` VALUES ('291', '3', '0', '1595389620', '1');
INSERT INTO `t_task_log` VALUES ('292', '1', '0', '1595389620', '1');
INSERT INTO `t_task_log` VALUES ('293', '1', '0', '1595389680', '1');
INSERT INTO `t_task_log` VALUES ('294', '3', '0', '1595389680', '1');
INSERT INTO `t_task_log` VALUES ('295', '3', '0', '1595389740', '1');
INSERT INTO `t_task_log` VALUES ('296', '1', '0', '1595389740', '1');
INSERT INTO `t_task_log` VALUES ('297', '3', '0', '1595389800', '1');
INSERT INTO `t_task_log` VALUES ('298', '1', '0', '1595389800', '1');
INSERT INTO `t_task_log` VALUES ('299', '3', '0', '1595389860', '1');
INSERT INTO `t_task_log` VALUES ('300', '1', '0', '1595389860', '1');
INSERT INTO `t_task_log` VALUES ('301', '1', '0', '1595389920', '1');
INSERT INTO `t_task_log` VALUES ('302', '3', '0', '1595389920', '1');
INSERT INTO `t_task_log` VALUES ('303', '3', '0', '1595389980', '1');
INSERT INTO `t_task_log` VALUES ('304', '1', '0', '1595389980', '1');
INSERT INTO `t_task_log` VALUES ('305', '1', '0', '1595390040', '1');
INSERT INTO `t_task_log` VALUES ('306', '3', '0', '1595390040', '1');
INSERT INTO `t_task_log` VALUES ('307', '1', '0', '1595390100', '1');
INSERT INTO `t_task_log` VALUES ('308', '3', '0', '1595390100', '1');
INSERT INTO `t_task_log` VALUES ('309', '3', '0', '1595390160', '1');
INSERT INTO `t_task_log` VALUES ('310', '1', '0', '1595390160', '1');
INSERT INTO `t_task_log` VALUES ('311', '3', '0', '1595390220', '1');
INSERT INTO `t_task_log` VALUES ('312', '1', '0', '1595390220', '1');
INSERT INTO `t_task_log` VALUES ('313', '3', '0', '1595390280', '1');
INSERT INTO `t_task_log` VALUES ('314', '1', '0', '1595390280', '1');
INSERT INTO `t_task_log` VALUES ('315', '3', '0', '1595390340', '1');
INSERT INTO `t_task_log` VALUES ('316', '1', '0', '1595390340', '1');
INSERT INTO `t_task_log` VALUES ('317', '1', '0', '1595390400', '1');
INSERT INTO `t_task_log` VALUES ('318', '3', '0', '1595390400', '1');
INSERT INTO `t_task_log` VALUES ('319', '3', '0', '1595390460', '1');
INSERT INTO `t_task_log` VALUES ('320', '1', '0', '1595390460', '1');
INSERT INTO `t_task_log` VALUES ('321', '1', '0', '1595390520', '1');
INSERT INTO `t_task_log` VALUES ('322', '3', '0', '1595390520', '1');
INSERT INTO `t_task_log` VALUES ('323', '3', '0', '1595390580', '1');
INSERT INTO `t_task_log` VALUES ('324', '1', '0', '1595390580', '1');
INSERT INTO `t_task_log` VALUES ('325', '1', '0', '1595390640', '1');
INSERT INTO `t_task_log` VALUES ('326', '3', '0', '1595390640', '1');
INSERT INTO `t_task_log` VALUES ('327', '3', '0', '1595390700', '1');
INSERT INTO `t_task_log` VALUES ('328', '1', '0', '1595390700', '1');
INSERT INTO `t_task_log` VALUES ('329', '1', '0', '1595390760', '1');
INSERT INTO `t_task_log` VALUES ('330', '3', '0', '1595390760', '1');
INSERT INTO `t_task_log` VALUES ('331', '1', '0', '1595390820', '1');
INSERT INTO `t_task_log` VALUES ('332', '3', '0', '1595390820', '1');
INSERT INTO `t_task_log` VALUES ('333', '3', '0', '1595390880', '1');
INSERT INTO `t_task_log` VALUES ('334', '1', '0', '1595390880', '1');
INSERT INTO `t_task_log` VALUES ('335', '3', '0', '1595390940', '1');
INSERT INTO `t_task_log` VALUES ('336', '1', '0', '1595390940', '1');
INSERT INTO `t_task_log` VALUES ('337', '3', '0', '1595391000', '1');
INSERT INTO `t_task_log` VALUES ('338', '1', '0', '1595391000', '1');
INSERT INTO `t_task_log` VALUES ('339', '3', '0', '1595391060', '1');
INSERT INTO `t_task_log` VALUES ('340', '1', '0', '1595391060', '1');
INSERT INTO `t_task_log` VALUES ('341', '3', '0', '1595391120', '1');
INSERT INTO `t_task_log` VALUES ('342', '1', '0', '1595391120', '1');
INSERT INTO `t_task_log` VALUES ('343', '3', '0', '1595391180', '1');
INSERT INTO `t_task_log` VALUES ('344', '1', '0', '1595391180', '1');
INSERT INTO `t_task_log` VALUES ('345', '1', '0', '1595391240', '1');
INSERT INTO `t_task_log` VALUES ('346', '3', '0', '1595391240', '1');
INSERT INTO `t_task_log` VALUES ('347', '3', '0', '1595391300', '1');
INSERT INTO `t_task_log` VALUES ('348', '1', '0', '1595391300', '1');
INSERT INTO `t_task_log` VALUES ('349', '3', '0', '1595391360', '1');
INSERT INTO `t_task_log` VALUES ('350', '1', '0', '1595391360', '1');
INSERT INTO `t_task_log` VALUES ('351', '1', '0', '1595391420', '1');
INSERT INTO `t_task_log` VALUES ('352', '3', '0', '1595391420', '1');
INSERT INTO `t_task_log` VALUES ('353', '1', '0', '1595391480', '1');
INSERT INTO `t_task_log` VALUES ('354', '3', '0', '1595391480', '1');
INSERT INTO `t_task_log` VALUES ('355', '3', '0', '1595391540', '1');
INSERT INTO `t_task_log` VALUES ('356', '1', '0', '1595391540', '1');
INSERT INTO `t_task_log` VALUES ('357', '1', '0', '1595391600', '1');
INSERT INTO `t_task_log` VALUES ('358', '3', '0', '1595391600', '1');
INSERT INTO `t_task_log` VALUES ('359', '3', '0', '1595391660', '1');
INSERT INTO `t_task_log` VALUES ('360', '1', '0', '1595391660', '1');
INSERT INTO `t_task_log` VALUES ('361', '1', '0', '1595391720', '1');
INSERT INTO `t_task_log` VALUES ('362', '3', '0', '1595391720', '1');
INSERT INTO `t_task_log` VALUES ('363', '1', '0', '1595391780', '1');
INSERT INTO `t_task_log` VALUES ('364', '3', '0', '1595391780', '1');
INSERT INTO `t_task_log` VALUES ('365', '1', '0', '1595391840', '1');
INSERT INTO `t_task_log` VALUES ('366', '3', '0', '1595391840', '1');
INSERT INTO `t_task_log` VALUES ('367', '1', '0', '1595391900', '1');
INSERT INTO `t_task_log` VALUES ('368', '3', '0', '1595391900', '1');
INSERT INTO `t_task_log` VALUES ('369', '1', '0', '1595391960', '1');
INSERT INTO `t_task_log` VALUES ('370', '3', '0', '1595391960', '1');
INSERT INTO `t_task_log` VALUES ('371', '1', '0', '1595392020', '1');
INSERT INTO `t_task_log` VALUES ('372', '3', '0', '1595392020', '1');
INSERT INTO `t_task_log` VALUES ('373', '1', '0', '1595392080', '1');
INSERT INTO `t_task_log` VALUES ('374', '3', '0', '1595392080', '1');
INSERT INTO `t_task_log` VALUES ('375', '1', '0', '1595392140', '1');
INSERT INTO `t_task_log` VALUES ('376', '3', '0', '1595392140', '1');
INSERT INTO `t_task_log` VALUES ('377', '3', '0', '1595392200', '1');
INSERT INTO `t_task_log` VALUES ('378', '1', '0', '1595392200', '1');
INSERT INTO `t_task_log` VALUES ('379', '1', '0', '1595392260', '1');
INSERT INTO `t_task_log` VALUES ('380', '3', '0', '1595392260', '1');
INSERT INTO `t_task_log` VALUES ('381', '3', '0', '1595392320', '1');
INSERT INTO `t_task_log` VALUES ('382', '1', '0', '1595392320', '1');
INSERT INTO `t_task_log` VALUES ('383', '1', '0', '1595392380', '1');
INSERT INTO `t_task_log` VALUES ('384', '3', '0', '1595392380', '1');
INSERT INTO `t_task_log` VALUES ('385', '3', '0', '1595392440', '1');
INSERT INTO `t_task_log` VALUES ('386', '1', '0', '1595392440', '1');
INSERT INTO `t_task_log` VALUES ('387', '1', '0', '1595392500', '1');
INSERT INTO `t_task_log` VALUES ('388', '3', '0', '1595392500', '1');
INSERT INTO `t_task_log` VALUES ('389', '3', '0', '1595392560', '1');
INSERT INTO `t_task_log` VALUES ('390', '1', '0', '1595392560', '1');
INSERT INTO `t_task_log` VALUES ('391', '1', '0', '1595392620', '1');
INSERT INTO `t_task_log` VALUES ('392', '3', '0', '1595392620', '1');
INSERT INTO `t_task_log` VALUES ('393', '3', '0', '1595392680', '1');
INSERT INTO `t_task_log` VALUES ('394', '1', '0', '1595392680', '1');
INSERT INTO `t_task_log` VALUES ('395', '3', '0', '1595392740', '1');
INSERT INTO `t_task_log` VALUES ('396', '1', '0', '1595392740', '1');
INSERT INTO `t_task_log` VALUES ('397', '1', '0', '1595392800', '1');
INSERT INTO `t_task_log` VALUES ('398', '3', '0', '1595392800', '1');
INSERT INTO `t_task_log` VALUES ('399', '1', '0', '1595392860', '1');
INSERT INTO `t_task_log` VALUES ('400', '3', '0', '1595392860', '1');
INSERT INTO `t_task_log` VALUES ('401', '3', '0', '1595392920', '1');
INSERT INTO `t_task_log` VALUES ('402', '1', '0', '1595392920', '1');
INSERT INTO `t_task_log` VALUES ('403', '1', '0', '1595392980', '1');
INSERT INTO `t_task_log` VALUES ('404', '3', '0', '1595392980', '1');
INSERT INTO `t_task_log` VALUES ('405', '3', '0', '1595393040', '1');
INSERT INTO `t_task_log` VALUES ('406', '1', '0', '1595393040', '1');
INSERT INTO `t_task_log` VALUES ('407', '3', '0', '1595393100', '1');
INSERT INTO `t_task_log` VALUES ('408', '1', '0', '1595393100', '1');
INSERT INTO `t_task_log` VALUES ('409', '1', '0', '1595393160', '1');
INSERT INTO `t_task_log` VALUES ('410', '3', '0', '1595393160', '1');
INSERT INTO `t_task_log` VALUES ('411', '3', '0', '1595393220', '1');
INSERT INTO `t_task_log` VALUES ('412', '1', '0', '1595393220', '1');
INSERT INTO `t_task_log` VALUES ('413', '1', '0', '1595393280', '1');
INSERT INTO `t_task_log` VALUES ('414', '3', '0', '1595393280', '1');
INSERT INTO `t_task_log` VALUES ('415', '3', '0', '1595393340', '1');
INSERT INTO `t_task_log` VALUES ('416', '1', '0', '1595393340', '1');
INSERT INTO `t_task_log` VALUES ('417', '1', '0', '1595393400', '1');
INSERT INTO `t_task_log` VALUES ('418', '3', '0', '1595393400', '1');
INSERT INTO `t_task_log` VALUES ('419', '3', '0', '1595393460', '1');
INSERT INTO `t_task_log` VALUES ('420', '1', '0', '1595393460', '1');
INSERT INTO `t_task_log` VALUES ('421', '1', '0', '1595393520', '1');
INSERT INTO `t_task_log` VALUES ('422', '3', '0', '1595393520', '1');
INSERT INTO `t_task_log` VALUES ('423', '1', '0', '1595393580', '1');
INSERT INTO `t_task_log` VALUES ('424', '3', '0', '1595393580', '1');
INSERT INTO `t_task_log` VALUES ('425', '3', '0', '1595393640', '1');
INSERT INTO `t_task_log` VALUES ('426', '1', '0', '1595393640', '1');
INSERT INTO `t_task_log` VALUES ('427', '1', '0', '1595393700', '1');
INSERT INTO `t_task_log` VALUES ('428', '3', '0', '1595393700', '1');
INSERT INTO `t_task_log` VALUES ('429', '3', '0', '1595393760', '1');
INSERT INTO `t_task_log` VALUES ('430', '1', '0', '1595393760', '1');
INSERT INTO `t_task_log` VALUES ('431', '1', '0', '1595393820', '1');
INSERT INTO `t_task_log` VALUES ('432', '3', '0', '1595393820', '1');
INSERT INTO `t_task_log` VALUES ('433', '1', '0', '1595393880', '1');
INSERT INTO `t_task_log` VALUES ('434', '3', '0', '1595393880', '1');
INSERT INTO `t_task_log` VALUES ('435', '3', '0', '1595393940', '1');
INSERT INTO `t_task_log` VALUES ('436', '1', '0', '1595393940', '1');
INSERT INTO `t_task_log` VALUES ('437', '1', '0', '1595394000', '1');
INSERT INTO `t_task_log` VALUES ('438', '3', '0', '1595394000', '1');
INSERT INTO `t_task_log` VALUES ('439', '1', '0', '1595394060', '1');
INSERT INTO `t_task_log` VALUES ('440', '3', '0', '1595394060', '1');
INSERT INTO `t_task_log` VALUES ('441', '3', '0', '1595394120', '1');
INSERT INTO `t_task_log` VALUES ('442', '1', '0', '1595394120', '1');
INSERT INTO `t_task_log` VALUES ('443', '1', '0', '1595394180', '1');
INSERT INTO `t_task_log` VALUES ('444', '3', '0', '1595394180', '1');
INSERT INTO `t_task_log` VALUES ('445', '3', '0', '1595394240', '1');
INSERT INTO `t_task_log` VALUES ('446', '1', '0', '1595394240', '1');
INSERT INTO `t_task_log` VALUES ('447', '1', '0', '1595394300', '1');
INSERT INTO `t_task_log` VALUES ('448', '3', '0', '1595394300', '1');
INSERT INTO `t_task_log` VALUES ('449', '3', '0', '1595394360', '1');
INSERT INTO `t_task_log` VALUES ('450', '1', '0', '1595394360', '1');
INSERT INTO `t_task_log` VALUES ('451', '1', '0', '1595394420', '1');
INSERT INTO `t_task_log` VALUES ('452', '3', '0', '1595394420', '1');
INSERT INTO `t_task_log` VALUES ('453', '1', '0', '1595394480', '1');
INSERT INTO `t_task_log` VALUES ('454', '3', '0', '1595394480', '1');
INSERT INTO `t_task_log` VALUES ('455', '3', '0', '1595394540', '1');
INSERT INTO `t_task_log` VALUES ('456', '1', '0', '1595394540', '1');
INSERT INTO `t_task_log` VALUES ('457', '3', '0', '1595394600', '1');
INSERT INTO `t_task_log` VALUES ('458', '1', '0', '1595394600', '1');
INSERT INTO `t_task_log` VALUES ('459', '1', '0', '1595394660', '1');
INSERT INTO `t_task_log` VALUES ('460', '3', '0', '1595394660', '1');
INSERT INTO `t_task_log` VALUES ('461', '1', '0', '1595394720', '1');
INSERT INTO `t_task_log` VALUES ('462', '3', '0', '1595394720', '1');
INSERT INTO `t_task_log` VALUES ('463', '3', '0', '1595394780', '1');
INSERT INTO `t_task_log` VALUES ('464', '1', '0', '1595394780', '1');
INSERT INTO `t_task_log` VALUES ('465', '1', '0', '1595394840', '1');
INSERT INTO `t_task_log` VALUES ('466', '3', '0', '1595394840', '1');
INSERT INTO `t_task_log` VALUES ('467', '1', '0', '1595394900', '1');
INSERT INTO `t_task_log` VALUES ('468', '3', '0', '1595394900', '1');
INSERT INTO `t_task_log` VALUES ('469', '3', '0', '1595394960', '1');
INSERT INTO `t_task_log` VALUES ('470', '1', '0', '1595394960', '1');
INSERT INTO `t_task_log` VALUES ('471', '1', '0', '1595395020', '1');
INSERT INTO `t_task_log` VALUES ('472', '3', '0', '1595395020', '1');
INSERT INTO `t_task_log` VALUES ('473', '1', '0', '1595395080', '1');
INSERT INTO `t_task_log` VALUES ('474', '3', '0', '1595395080', '1');
INSERT INTO `t_task_log` VALUES ('475', '1', '0', '1595395140', '1');
INSERT INTO `t_task_log` VALUES ('476', '3', '0', '1595395140', '1');
INSERT INTO `t_task_log` VALUES ('477', '3', '0', '1595395200', '1');
INSERT INTO `t_task_log` VALUES ('478', '1', '0', '1595395201', '1');
INSERT INTO `t_task_log` VALUES ('479', '3', '0', '1595395260', '1');
INSERT INTO `t_task_log` VALUES ('480', '1', '0', '1595395260', '1');
INSERT INTO `t_task_log` VALUES ('481', '1', '0', '1595395320', '1');
INSERT INTO `t_task_log` VALUES ('482', '3', '0', '1595395320', '1');
INSERT INTO `t_task_log` VALUES ('483', '3', '0', '1595395380', '1');
INSERT INTO `t_task_log` VALUES ('484', '1', '0', '1595395380', '1');
INSERT INTO `t_task_log` VALUES ('485', '3', '0', '1595395440', '1');
INSERT INTO `t_task_log` VALUES ('486', '1', '0', '1595395440', '1');
INSERT INTO `t_task_log` VALUES ('487', '1', '0', '1595395500', '1');
INSERT INTO `t_task_log` VALUES ('488', '3', '0', '1595395500', '1');
INSERT INTO `t_task_log` VALUES ('489', '1', '0', '1595395560', '1');
INSERT INTO `t_task_log` VALUES ('490', '3', '0', '1595395560', '1');
INSERT INTO `t_task_log` VALUES ('491', '3', '0', '1595395620', '1');
INSERT INTO `t_task_log` VALUES ('492', '1', '0', '1595395620', '1');
INSERT INTO `t_task_log` VALUES ('493', '1', '0', '1595395680', '1');
INSERT INTO `t_task_log` VALUES ('494', '3', '0', '1595395680', '1');
INSERT INTO `t_task_log` VALUES ('495', '1', '0', '1595395740', '1');
INSERT INTO `t_task_log` VALUES ('496', '3', '0', '1595395740', '1');
INSERT INTO `t_task_log` VALUES ('497', '3', '0', '1595395800', '1');
INSERT INTO `t_task_log` VALUES ('498', '1', '0', '1595395800', '1');
INSERT INTO `t_task_log` VALUES ('499', '3', '0', '1595395860', '1');
INSERT INTO `t_task_log` VALUES ('500', '1', '0', '1595395860', '1');
INSERT INTO `t_task_log` VALUES ('501', '3', '0', '1595395920', '1');
INSERT INTO `t_task_log` VALUES ('502', '1', '0', '1595395920', '1');
INSERT INTO `t_task_log` VALUES ('503', '1', '0', '1595395980', '1');
INSERT INTO `t_task_log` VALUES ('504', '3', '0', '1595395980', '1');
INSERT INTO `t_task_log` VALUES ('505', '1', '0', '1595396040', '1');
INSERT INTO `t_task_log` VALUES ('506', '3', '0', '1595396040', '1');
INSERT INTO `t_task_log` VALUES ('507', '1', '0', '1595396100', '1');
INSERT INTO `t_task_log` VALUES ('508', '3', '0', '1595396100', '1');
INSERT INTO `t_task_log` VALUES ('509', '3', '0', '1595396160', '1');
INSERT INTO `t_task_log` VALUES ('510', '1', '0', '1595396160', '1');
INSERT INTO `t_task_log` VALUES ('511', '3', '0', '1595396220', '1');
INSERT INTO `t_task_log` VALUES ('512', '1', '0', '1595396220', '1');
INSERT INTO `t_task_log` VALUES ('513', '1', '0', '1595396280', '1');
INSERT INTO `t_task_log` VALUES ('514', '3', '0', '1595396280', '1');
INSERT INTO `t_task_log` VALUES ('515', '1', '0', '1595396340', '1');
INSERT INTO `t_task_log` VALUES ('516', '3', '0', '1595396340', '1');
INSERT INTO `t_task_log` VALUES ('517', '3', '0', '1595396400', '1');
INSERT INTO `t_task_log` VALUES ('518', '1', '0', '1595396400', '1');
INSERT INTO `t_task_log` VALUES ('519', '3', '0', '1595396460', '1');
INSERT INTO `t_task_log` VALUES ('520', '1', '0', '1595396460', '1');
INSERT INTO `t_task_log` VALUES ('521', '3', '0', '1595396520', '1');
INSERT INTO `t_task_log` VALUES ('522', '1', '0', '1595396520', '1');
INSERT INTO `t_task_log` VALUES ('523', '3', '0', '1595396580', '1');
INSERT INTO `t_task_log` VALUES ('524', '3', '0', '1595396640', '1');
INSERT INTO `t_task_log` VALUES ('525', '3', '0', '1595396700', '1');
INSERT INTO `t_task_log` VALUES ('526', '3', '0', '1595396760', '1');
INSERT INTO `t_task_log` VALUES ('527', '3', '0', '1595396820', '1');
INSERT INTO `t_task_log` VALUES ('528', '3', '0', '1595397000', '1');
INSERT INTO `t_task_log` VALUES ('529', '1', '0', '1595397240', '1');
INSERT INTO `t_task_log` VALUES ('530', '3', '0', '1595397240', '1');
INSERT INTO `t_task_log` VALUES ('531', '1', '0', '1595397360', '1');
INSERT INTO `t_task_log` VALUES ('532', '3', '0', '1595397360', '1');
INSERT INTO `t_task_log` VALUES ('533', '1', '0', '1595397480', '1');
INSERT INTO `t_task_log` VALUES ('534', '3', '0', '1595397480', '1');
INSERT INTO `t_task_log` VALUES ('535', '3', '0', '1595397600', '1');
INSERT INTO `t_task_log` VALUES ('536', '1', '0', '1595397600', '1');
INSERT INTO `t_task_log` VALUES ('537', '3', '0', '1595397720', '1');
INSERT INTO `t_task_log` VALUES ('538', '1', '0', '1595397720', '1');
INSERT INTO `t_task_log` VALUES ('539', '3', '0', '1595398080', '1');
INSERT INTO `t_task_log` VALUES ('540', '1', '0', '1595398080', '1');
INSERT INTO `t_task_log` VALUES ('541', '3', '0', '1595398200', '1');
INSERT INTO `t_task_log` VALUES ('542', '1', '0', '1595398200', '1');
INSERT INTO `t_task_log` VALUES ('543', '1', '0', '1595398320', '1');
INSERT INTO `t_task_log` VALUES ('544', '3', '0', '1595398320', '1');
INSERT INTO `t_task_log` VALUES ('545', '3', '0', '1595398440', '1');
INSERT INTO `t_task_log` VALUES ('546', '1', '0', '1595398440', '1');
INSERT INTO `t_task_log` VALUES ('547', '3', '0', '1595398560', '1');
INSERT INTO `t_task_log` VALUES ('548', '1', '0', '1595398560', '1');
INSERT INTO `t_task_log` VALUES ('549', '1', '0', '1595398680', '1');
INSERT INTO `t_task_log` VALUES ('550', '3', '0', '1595398680', '1');
INSERT INTO `t_task_log` VALUES ('551', '3', '0', '1595398800', '1');
INSERT INTO `t_task_log` VALUES ('552', '1', '0', '1595398800', '1');
INSERT INTO `t_task_log` VALUES ('553', '3', '0', '1595399040', '1');
INSERT INTO `t_task_log` VALUES ('554', '3', '0', '1595399160', '1');
INSERT INTO `t_task_log` VALUES ('555', '3', '0', '1595399280', '1');
INSERT INTO `t_task_log` VALUES ('556', '3', '0', '1595399400', '1');
INSERT INTO `t_task_log` VALUES ('557', '3', '0', '1595399520', '1');
INSERT INTO `t_task_log` VALUES ('558', '3', '0', '1595399640', '1');
INSERT INTO `t_task_log` VALUES ('559', '3', '0', '1595399760', '1');
INSERT INTO `t_task_log` VALUES ('560', '3', '0', '1595399880', '1');
INSERT INTO `t_task_log` VALUES ('561', '3', '0', '1595400000', '1');
INSERT INTO `t_task_log` VALUES ('562', '3', '0', '1595400120', '1');
INSERT INTO `t_task_log` VALUES ('563', '3', '0', '1595400240', '1');
INSERT INTO `t_task_log` VALUES ('564', '1', '0', '1595400480', '1');
INSERT INTO `t_task_log` VALUES ('565', '3', '0', '1595400480', '1');
INSERT INTO `t_task_log` VALUES ('566', '1', '0', '1595400480', '1');
INSERT INTO `t_task_log` VALUES ('567', '1', '0', '1595400600', '1');
INSERT INTO `t_task_log` VALUES ('568', '1', '0', '1595400600', '1');
INSERT INTO `t_task_log` VALUES ('569', '3', '0', '1595400600', '1');
INSERT INTO `t_task_log` VALUES ('570', '1', '0', '1595400720', '1');
INSERT INTO `t_task_log` VALUES ('571', '3', '0', '1595400720', '1');
INSERT INTO `t_task_log` VALUES ('572', '1', '0', '1595400720', '1');
INSERT INTO `t_task_log` VALUES ('573', '3', '0', '1595403720', '1');
INSERT INTO `t_task_log` VALUES ('574', '1', '0', '1595403720', '1');
INSERT INTO `t_task_log` VALUES ('575', '1', '0', '1595403720', '1');
INSERT INTO `t_task_log` VALUES ('576', '1', '0', '1595403840', '1');
INSERT INTO `t_task_log` VALUES ('577', '3', '0', '1595403840', '1');
INSERT INTO `t_task_log` VALUES ('578', '1', '0', '1595403840', '1');
INSERT INTO `t_task_log` VALUES ('579', '1', '0', '1595403960', '1');
INSERT INTO `t_task_log` VALUES ('580', '1', '0', '1595403960', '1');
INSERT INTO `t_task_log` VALUES ('581', '3', '0', '1595403960', '1');
INSERT INTO `t_task_log` VALUES ('582', '1', '0', '1595404080', '1');
INSERT INTO `t_task_log` VALUES ('583', '1', '0', '1595404080', '1');
INSERT INTO `t_task_log` VALUES ('584', '3', '0', '1595404080', '1');
INSERT INTO `t_task_log` VALUES ('585', '1', '0', '1595404200', '1');
INSERT INTO `t_task_log` VALUES ('586', '1', '0', '1595404200', '1');
INSERT INTO `t_task_log` VALUES ('587', '3', '0', '1595404200', '1');
INSERT INTO `t_task_log` VALUES ('588', '1', '0', '1595404320', '1');
INSERT INTO `t_task_log` VALUES ('589', '1', '0', '1595404320', '1');
INSERT INTO `t_task_log` VALUES ('590', '3', '0', '1595404320', '1');
INSERT INTO `t_task_log` VALUES ('591', '1', '0', '1595404440', '1');
INSERT INTO `t_task_log` VALUES ('592', '1', '0', '1595404440', '1');
INSERT INTO `t_task_log` VALUES ('593', '3', '0', '1595404440', '1');
INSERT INTO `t_task_log` VALUES ('594', '1', '0', '1595404560', '1');
INSERT INTO `t_task_log` VALUES ('595', '1', '0', '1595404560', '1');
INSERT INTO `t_task_log` VALUES ('596', '3', '0', '1595404560', '1');
INSERT INTO `t_task_log` VALUES ('597', '3', '0', '1595404680', '1');
INSERT INTO `t_task_log` VALUES ('598', '1', '0', '1595404680', '1');
INSERT INTO `t_task_log` VALUES ('599', '1', '0', '1595404680', '1');
INSERT INTO `t_task_log` VALUES ('600', '3', '0', '1595404800', '1');
INSERT INTO `t_task_log` VALUES ('601', '1', '0', '1595404800', '1');
INSERT INTO `t_task_log` VALUES ('602', '1', '0', '1595404800', '1');
INSERT INTO `t_task_log` VALUES ('603', '1', '0', '1595404920', '1');
INSERT INTO `t_task_log` VALUES ('604', '3', '0', '1595404920', '1');
INSERT INTO `t_task_log` VALUES ('605', '1', '0', '1595404920', '1');
INSERT INTO `t_task_log` VALUES ('606', '3', '0', '1595405040', '1');
INSERT INTO `t_task_log` VALUES ('607', '1', '0', '1595405040', '1');
INSERT INTO `t_task_log` VALUES ('608', '1', '0', '1595405040', '1');
INSERT INTO `t_task_log` VALUES ('609', '1', '0', '1595405160', '1');
INSERT INTO `t_task_log` VALUES ('610', '3', '0', '1595405160', '1');
INSERT INTO `t_task_log` VALUES ('611', '1', '0', '1595405160', '1');
INSERT INTO `t_task_log` VALUES ('612', '1', '0', '1595405280', '1');
INSERT INTO `t_task_log` VALUES ('613', '1', '0', '1595405280', '1');
INSERT INTO `t_task_log` VALUES ('614', '3', '0', '1595405280', '1');
INSERT INTO `t_task_log` VALUES ('615', '1', '0', '1595405400', '1');
INSERT INTO `t_task_log` VALUES ('616', '1', '0', '1595405400', '1');
INSERT INTO `t_task_log` VALUES ('617', '3', '0', '1595405400', '1');
INSERT INTO `t_task_log` VALUES ('618', '1', '0', '1595405520', '1');
INSERT INTO `t_task_log` VALUES ('619', '3', '0', '1595405520', '1');
INSERT INTO `t_task_log` VALUES ('620', '1', '0', '1595405520', '1');
INSERT INTO `t_task_log` VALUES ('621', '1', '0', '1595405640', '1');
INSERT INTO `t_task_log` VALUES ('622', '3', '0', '1595405640', '1');
INSERT INTO `t_task_log` VALUES ('623', '1', '0', '1595405640', '1');
INSERT INTO `t_task_log` VALUES ('624', '1', '0', '1595405760', '1');
INSERT INTO `t_task_log` VALUES ('625', '1', '0', '1595405760', '1');
INSERT INTO `t_task_log` VALUES ('626', '3', '0', '1595405760', '1');
INSERT INTO `t_task_log` VALUES ('627', '1', '0', '1595405880', '1');
INSERT INTO `t_task_log` VALUES ('628', '3', '0', '1595405880', '1');
INSERT INTO `t_task_log` VALUES ('629', '1', '0', '1595405880', '1');
INSERT INTO `t_task_log` VALUES ('630', '1', '0', '1595406000', '1');
INSERT INTO `t_task_log` VALUES ('631', '3', '0', '1595406000', '1');
INSERT INTO `t_task_log` VALUES ('632', '1', '0', '1595406000', '1');
INSERT INTO `t_task_log` VALUES ('633', '1', '0', '1595406120', '1');
INSERT INTO `t_task_log` VALUES ('634', '1', '0', '1595406120', '1');
INSERT INTO `t_task_log` VALUES ('635', '3', '0', '1595406120', '1');
INSERT INTO `t_task_log` VALUES ('636', '1', '0', '1595406240', '1');
INSERT INTO `t_task_log` VALUES ('637', '1', '0', '1595406240', '1');
INSERT INTO `t_task_log` VALUES ('638', '3', '0', '1595406240', '1');
INSERT INTO `t_task_log` VALUES ('639', '1', '0', '1595406360', '1');
INSERT INTO `t_task_log` VALUES ('640', '3', '0', '1595406360', '1');
INSERT INTO `t_task_log` VALUES ('641', '1', '0', '1595406360', '1');
INSERT INTO `t_task_log` VALUES ('642', '1', '0', '1595406480', '1');
INSERT INTO `t_task_log` VALUES ('643', '1', '0', '1595406480', '1');
INSERT INTO `t_task_log` VALUES ('644', '3', '0', '1595406480', '1');
INSERT INTO `t_task_log` VALUES ('645', '1', '0', '1595406600', '1');
INSERT INTO `t_task_log` VALUES ('646', '3', '0', '1595406600', '1');
INSERT INTO `t_task_log` VALUES ('647', '1', '0', '1595406600', '1');
INSERT INTO `t_task_log` VALUES ('648', '1', '0', '1595406720', '1');
INSERT INTO `t_task_log` VALUES ('649', '3', '0', '1595406720', '1');
INSERT INTO `t_task_log` VALUES ('650', '1', '0', '1595406720', '1');
INSERT INTO `t_task_log` VALUES ('651', '1', '0', '1595406840', '1');
INSERT INTO `t_task_log` VALUES ('652', '1', '0', '1595406840', '1');
INSERT INTO `t_task_log` VALUES ('653', '3', '0', '1595406840', '1');
INSERT INTO `t_task_log` VALUES ('654', '3', '0', '1595406960', '1');
INSERT INTO `t_task_log` VALUES ('655', '1', '0', '1595406960', '1');
INSERT INTO `t_task_log` VALUES ('656', '1', '0', '1595406960', '1');
INSERT INTO `t_task_log` VALUES ('657', '1', '0', '1595407080', '1');
INSERT INTO `t_task_log` VALUES ('658', '3', '0', '1595407080', '1');
INSERT INTO `t_task_log` VALUES ('659', '1', '0', '1595407080', '1');
INSERT INTO `t_task_log` VALUES ('660', '1', '0', '1595407200', '1');
INSERT INTO `t_task_log` VALUES ('661', '1', '0', '1595407200', '1');
INSERT INTO `t_task_log` VALUES ('662', '3', '0', '1595407200', '1');
INSERT INTO `t_task_log` VALUES ('663', '1', '0', '1595407320', '1');
INSERT INTO `t_task_log` VALUES ('664', '3', '0', '1595407320', '1');
INSERT INTO `t_task_log` VALUES ('665', '1', '0', '1595407320', '1');
INSERT INTO `t_task_log` VALUES ('666', '1', '0', '1595407440', '1');
INSERT INTO `t_task_log` VALUES ('667', '1', '0', '1595407440', '1');
INSERT INTO `t_task_log` VALUES ('668', '3', '0', '1595407440', '1');
INSERT INTO `t_task_log` VALUES ('669', '3', '0', '1595407560', '1');
INSERT INTO `t_task_log` VALUES ('670', '1', '0', '1595407560', '1');
INSERT INTO `t_task_log` VALUES ('671', '1', '0', '1595407560', '1');
INSERT INTO `t_task_log` VALUES ('672', '1', '0', '1595407680', '1');
INSERT INTO `t_task_log` VALUES ('673', '1', '0', '1595407680', '1');
INSERT INTO `t_task_log` VALUES ('674', '3', '0', '1595407680', '1');
INSERT INTO `t_task_log` VALUES ('675', '1', '0', '1595407800', '1');
INSERT INTO `t_task_log` VALUES ('676', '1', '0', '1595407800', '1');
INSERT INTO `t_task_log` VALUES ('677', '3', '0', '1595407800', '1');
INSERT INTO `t_task_log` VALUES ('678', '1', '0', '1595407920', '1');
INSERT INTO `t_task_log` VALUES ('679', '1', '0', '1595407920', '1');
INSERT INTO `t_task_log` VALUES ('680', '3', '0', '1595407920', '1');
INSERT INTO `t_task_log` VALUES ('681', '3', '0', '1595408040', '1');
INSERT INTO `t_task_log` VALUES ('682', '1', '0', '1595408040', '1');
INSERT INTO `t_task_log` VALUES ('683', '1', '0', '1595408040', '1');
INSERT INTO `t_task_log` VALUES ('684', '1', '0', '1595408160', '1');
INSERT INTO `t_task_log` VALUES ('685', '1', '0', '1595408160', '1');
INSERT INTO `t_task_log` VALUES ('686', '3', '0', '1595408160', '1');
INSERT INTO `t_task_log` VALUES ('687', '1', '0', '1595408280', '1');
INSERT INTO `t_task_log` VALUES ('688', '3', '0', '1595408280', '1');
INSERT INTO `t_task_log` VALUES ('689', '1', '0', '1595408280', '1');
INSERT INTO `t_task_log` VALUES ('690', '1', '0', '1595408400', '1');
INSERT INTO `t_task_log` VALUES ('691', '1', '0', '1595408400', '1');
INSERT INTO `t_task_log` VALUES ('692', '3', '0', '1595408400', '1');
INSERT INTO `t_task_log` VALUES ('693', '1', '0', '1595408520', '1');
INSERT INTO `t_task_log` VALUES ('694', '1', '0', '1595408520', '1');
INSERT INTO `t_task_log` VALUES ('695', '3', '0', '1595408520', '1');
INSERT INTO `t_task_log` VALUES ('696', '1', '0', '1595408640', '1');
INSERT INTO `t_task_log` VALUES ('697', '1', '0', '1595408640', '1');
INSERT INTO `t_task_log` VALUES ('698', '3', '0', '1595408640', '1');
INSERT INTO `t_task_log` VALUES ('699', '1', '0', '1595408760', '1');
INSERT INTO `t_task_log` VALUES ('700', '3', '0', '1595408760', '1');
INSERT INTO `t_task_log` VALUES ('701', '1', '0', '1595408760', '1');
INSERT INTO `t_task_log` VALUES ('702', '1', '0', '1595408880', '1');
INSERT INTO `t_task_log` VALUES ('703', '1', '0', '1595408880', '1');
INSERT INTO `t_task_log` VALUES ('704', '3', '0', '1595408880', '1');
INSERT INTO `t_task_log` VALUES ('705', '3', '0', '1595409000', '1');
INSERT INTO `t_task_log` VALUES ('706', '1', '0', '1595409000', '1');
INSERT INTO `t_task_log` VALUES ('707', '1', '0', '1595409000', '1');
INSERT INTO `t_task_log` VALUES ('708', '1', '0', '1595409120', '1');
INSERT INTO `t_task_log` VALUES ('709', '1', '0', '1595409120', '1');
INSERT INTO `t_task_log` VALUES ('710', '3', '0', '1595409120', '1');
INSERT INTO `t_task_log` VALUES ('711', '1', '0', '1595409240', '1');
INSERT INTO `t_task_log` VALUES ('712', '3', '0', '1595409240', '1');
INSERT INTO `t_task_log` VALUES ('713', '1', '0', '1595409240', '1');
INSERT INTO `t_task_log` VALUES ('714', '3', '0', '1595409360', '1');
INSERT INTO `t_task_log` VALUES ('715', '1', '0', '1595409360', '1');
INSERT INTO `t_task_log` VALUES ('716', '1', '0', '1595409360', '1');
INSERT INTO `t_task_log` VALUES ('717', '1', '0', '1595409480', '1');
INSERT INTO `t_task_log` VALUES ('718', '3', '0', '1595409480', '1');
INSERT INTO `t_task_log` VALUES ('719', '1', '0', '1595409480', '1');
INSERT INTO `t_task_log` VALUES ('720', '1', '0', '1595409600', '1');
INSERT INTO `t_task_log` VALUES ('721', '1', '0', '1595409600', '1');
INSERT INTO `t_task_log` VALUES ('722', '3', '0', '1595409600', '1');
INSERT INTO `t_task_log` VALUES ('723', '3', '0', '1595409720', '1');
INSERT INTO `t_task_log` VALUES ('724', '3', '0', '1595409840', '1');
INSERT INTO `t_task_log` VALUES ('725', '3', '0', '1595409960', '1');
INSERT INTO `t_task_log` VALUES ('726', '3', '0', '1595410080', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `account` varchar(18) DEFAULT NULL,
  `password` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456');

-- ----------------------------
-- Function structure for essay_commentQuery
-- ----------------------------
DROP FUNCTION IF EXISTS `essay_commentQuery`;
DELIMITER ;;
CREATE DEFINER=``@`` FUNCTION `essay_commentQuery`(workid INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(workid AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT father_id INTO sTempChd FROM essay_comment WHERE id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT father_id INTO sTempChd FROM essay_comment WHERE id = sTempChd;
END WHILE;
RETURN sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for project_commentQuery
-- ----------------------------
DROP FUNCTION IF EXISTS `project_commentQuery`;
DELIMITER ;;
CREATE DEFINER=``@`` FUNCTION `project_commentQuery`(workId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(workId AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT father_id INTO sTempChd FROM project_comment WHERE id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT father_id INTO sTempChd FROM project_comment WHERE id = sTempChd;
END WHILE;
RETURN sTemp;
END
;;
DELIMITER ;
