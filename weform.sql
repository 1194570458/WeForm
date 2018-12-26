/*
Navicat MySQL Data Transfer

Source Server         : 192.168.88.129
Source Server Version : 50724
Source Host           : 192.168.88.129:3306
Source Database       : weform

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-20 10:43:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `field_detail`
-- ----------------------------
DROP TABLE IF EXISTS `field_detail`;
CREATE TABLE `field_detail` (
  `detail_id` varchar(32) NOT NULL,
  `field_id` varchar(32) NOT NULL COMMENT '字段表id',
  `master_id` varchar(32) NOT NULL COMMENT '中间表id',
  `form_data` text COMMENT '字段数据',
  PRIMARY KEY (`detail_id`),
  KEY `idx_master_id` (`master_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字段数据表';

-- ----------------------------
-- Records of field_detail
-- ----------------------------
INSERT INTO `field_detail` VALUES ('154466601309047444', '1544601352713499656', '154466601306141920', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('1544666013090540330', '1544601352713499656', '154466601306141920', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('1544666273206351043', '154460150604840793', '1544666273114102428', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('154466627320639280', '1544601352713499656', '1544666273114102428', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('154466640240415504', '1544601352713499656', '154466640228737230', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('1544666402404730400', '154460150604840793', '154466640228737230', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('1544667955230198516', '154460150604840793', '154466795518856049', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('15446679552306986', '1544601352713499656', '154466795518856049', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('1544668048863202678', '154460150604840793', '1544668048785211302', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('1544668048863207600', '1544601352713499656', '1544668048785211302', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('1544668213346238140', '154460150604840793', '1544668213267347594', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('1544668213346290521', '1544601352713499656', '1544668213267347594', 'asduadhaiudhuiashausdhasduasdhaisudhiau');
INSERT INTO `field_detail` VALUES ('1544669418090265860', '1544601352713499656', '1544669417702532799', '111');

-- ----------------------------
-- Table structure for `field_master`
-- ----------------------------
DROP TABLE IF EXISTS `field_master`;
CREATE TABLE `field_master` (
  `master_id` varchar(32) NOT NULL,
  `form_id` varchar(32) NOT NULL,
  `detail_status` tinyint(3) DEFAULT '0' COMMENT '-1已删除,0未标记,1已标记',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`master_id`),
  KEY `idx_form_id` (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字段与数据中间表';

-- ----------------------------
-- Records of field_master
-- ----------------------------
INSERT INTO `field_master` VALUES ('154466601306141920', '5454', '0', '2018-12-13 01:53:33');
INSERT INTO `field_master` VALUES ('1544666273114102428', '5454', '0', '2018-12-13 01:57:53');
INSERT INTO `field_master` VALUES ('154466640228737230', '5454', '0', '2018-12-13 02:00:02');
INSERT INTO `field_master` VALUES ('154466795518856049', '5454', '0', '2018-12-13 02:25:55');
INSERT INTO `field_master` VALUES ('1544668048785211302', '5454', '0', '2018-12-13 02:27:28');
INSERT INTO `field_master` VALUES ('1544668213267347594', '5454', '0', '2018-12-13 02:30:13');
INSERT INTO `field_master` VALUES ('1544669417702532799', '5454', '0', '2018-12-13 02:50:18');

-- ----------------------------
-- Table structure for `form_field`
-- ----------------------------
DROP TABLE IF EXISTS `form_field`;
CREATE TABLE `form_field` (
  `field_id` varchar(32) NOT NULL,
  `form_id` varchar(32) NOT NULL COMMENT '表单id',
  `field_type` varchar(16) NOT NULL COMMENT '字段类型',
  `field_class` varchar(16) NOT NULL COMMENT '字段类名',
  `field_name` varchar(16) NOT NULL COMMENT '字段名字',
  `field_attr` varchar(128) NOT NULL COMMENT '字段属性',
  `field_grade` tinyint(3) DEFAULT '0' COMMENT '排序等级 0为第一',
  `field_status` tinyint(3) DEFAULT '0' COMMENT '状态,0使用,1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`field_id`),
  KEY `idx_form_id` (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字段表';

-- ----------------------------
-- Records of form_field
-- ----------------------------
INSERT INTO `form_field` VALUES ('1544601352713499656', '123', 'a', 'input3333', 'password', 'src=\"http:xxxx.jpg\"', '0', '0', '2018-12-13 03:00:58', '2018-12-13 03:00:58');
INSERT INTO `form_field` VALUES ('154460150604840793', '123', 'input', 'input11111', 'password', 'src=\"http:xxxx.jpg\"', '0', '1', '2018-12-12 07:58:25', '2018-12-12 08:02:14');
INSERT INTO `form_field` VALUES ('1544601509493196128', '123', 'input', 'input3333', 'password', 'src=\"http:xxxx.jpg\"', '2', '0', '2018-12-12 07:58:29', '2018-12-13 03:27:58');
INSERT INTO `form_field` VALUES ('1544612555028668564', '123', 'input', 'input3333', 'password', 'src=\"http:xxxx.jpg\"', '0', '0', '2018-12-12 11:02:34', '2018-12-13 03:30:24');

-- ----------------------------
-- Table structure for `form_master`
-- ----------------------------
DROP TABLE IF EXISTS `form_master`;
CREATE TABLE `form_master` (
  `form_id` varchar(32) NOT NULL,
  `form_name` varchar(32) NOT NULL COMMENT '表单名字',
  `openid` varchar(64) NOT NULL COMMENT '用户openid',
  `form_status` tinyint(3) DEFAULT '0' COMMENT '状态,-1已删除,0未开始,1收集中,2已停止',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`form_id`),
  KEY `idx_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='表单主表';

-- ----------------------------
-- Records of form_master
-- ----------------------------
INSERT INTO `form_master` VALUES ('123', '表单2222', '56556545', '-1', '2018-12-11 17:04:44', '2018-12-11 17:19:31');
INSERT INTO `form_master` VALUES ('1544546165143172272', '表单1', '555', '0', '2018-12-11 16:36:04', '2018-12-11 16:36:04');
INSERT INTO `form_master` VALUES ('2342', '表单1', '56556545', '-1', '2018-12-11 08:53:08', '2018-12-11 09:53:09');
INSERT INTO `form_master` VALUES ('444', '444', '44', '0', '2018-12-11 08:38:43', '2018-12-11 08:38:43');
INSERT INTO `form_master` VALUES ('5454', '表单1', '56556545', '0', '2018-12-11 08:44:21', '2018-12-11 08:44:21');
