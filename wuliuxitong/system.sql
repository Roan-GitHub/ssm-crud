/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50024
Source Host           : localhost:3306
Source Database       : system

Target Server Type    : MYSQL
Target Server Version : 50024
File Encoding         : 65001

Date: 2018-11-15 21:32:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment,
  `username` char(255) NOT NULL,
  `password` char(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456');
INSERT INTO `admin` VALUES ('21', 'admin', '000000');

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(11) NOT NULL auto_increment,
  `capcity` float NOT NULL,
  `start` char(255) NOT NULL,
  `info` char(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('1', '300', '广东广州', '大货车', '2018-06-01 21:45:53');
INSERT INTO `car` VALUES ('2', '150', '北京', '中货车', '2018-06-02 21:46:28');
INSERT INTO `car` VALUES ('3', '80', '上海', '小货车', '2018-06-03 00:00:00');

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL auto_increment,
  `name` char(255) NOT NULL,
  `address` char(255) NOT NULL,
  `phone` char(255) NOT NULL,
  `linkman` char(255) NOT NULL,
  `info` char(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '犟驴服装公司', '广东广州', '1237485', '赵一', '服装大公司');
INSERT INTO `company` VALUES ('2', '健康药业', '广东珠海', '4567894', '刘二', '药业大公司');
INSERT INTO `company` VALUES ('3', '幸福粮仓', '北京', '7965461', '钱三', '粮食大公司');
INSERT INTO `company` VALUES ('4', '美满科技有限公司', '上海', '8979', '孙四', '电子产品大公司');

-- ----------------------------
-- Table structure for `dynamic_info`
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_info`;
CREATE TABLE `dynamic_info` (
  `id` int(11) NOT NULL auto_increment,
  `title` char(255) NOT NULL,
  `date` datetime NOT NULL,
  `info` char(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamic_info
-- ----------------------------
INSERT INTO `dynamic_info` VALUES ('1', '车辆紧缺', '2018-02-07 21:12:27', '缺车时期不宜继续发货');
INSERT INTO `dynamic_info` VALUES ('2', '长途停运', '2018-01-09 21:15:19', '塞车中1');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL auto_increment,
  `type` char(255) NOT NULL,
  `name` char(255) NOT NULL,
  `amount` char(255) NOT NULL,
  `start` char(255) NOT NULL,
  `end` char(255) NOT NULL,
  `date` datetime NOT NULL,
  `tran_type` char(255) NOT NULL,
  `linkman` char(255) NOT NULL,
  `phone` char(255) NOT NULL,
  `time` datetime NOT NULL,
  `info` char(255) NOT NULL,
  `people` char(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '服装', '卫衣', '3000', '广东广州', '湖南长沙', '2018-06-01 00:00:00', '慢运', '王二', '123', '2018-06-02 21:35:16', '运输中', '罗三');
INSERT INTO `goods` VALUES ('2', '药品', '板蓝根', '5000', '广东珠海', '北京', '2018-06-03 00:00:00', '快运', '卢可', '456', '2018-06-04 21:36:45', '药品运输', '黄可以');

-- ----------------------------
-- Table structure for `logistics_knowledge`
-- ----------------------------
DROP TABLE IF EXISTS `logistics_knowledge`;
CREATE TABLE `logistics_knowledge` (
  `id` int(11) NOT NULL auto_increment,
  `title` char(255) NOT NULL,
  `date` datetime NOT NULL,
  `info` char(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logistics_knowledge
-- ----------------------------
INSERT INTO `logistics_knowledge` VALUES ('1', '什么是第三物流？', '2018-01-01 21:22:17', '第三方物流是指生产经营企业为集中精力搞好主业，把原来属于自己处理的物流活动，以合同方式委托给专业物流服务企业，同时通过信息系统与物流企业保持密切联系，以达到对物流全程管理控制的一种物流运作与管理方式。');
INSERT INTO `logistics_knowledge` VALUES ('2', '什么是大票零担', '2010-06-20 21:22:56', '大票零担的货主主要有三类，生产制造企业、三方物流、一二级批发商。他们发货有三个典型特点，一是发货区域广，二是发货数量大，三是有计划性发货');
INSERT INTO `logistics_knowledge` VALUES ('3', '物流和快递的区别', '2018-02-06 21:24:10', '物流和快递有很大区别，物流通常是指对于较大件物品的运输，主要服务对象为企业，快递则相反，运输物件通常为小物件，主要服务对象为个人。通常100公斤以上的货物选择快递运输费用会非常高。物流作业虽然在时效上稍逊色于快递，但安全性高、运费实惠。国内物流业仍然处在快速发展阶段，物流公司主要有分为两种类型：专线、三方。');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL auto_increment,
  `title` char(255) NOT NULL,
  `date` datetime NOT NULL,
  `info` char(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '“新零售•新物流•新冷链”高峰论坛隆重举行，开启中原物流发展新征程！！！', '2018-03-13 21:25:41', '本次论坛活动由河南省制冷学会、河南省物流学会主办，河南牧业经济学院承办，中国物通网、晶链通平台公司、郑州海名汇博会展策划有限公司等协办，河南牧业经济学院物流与电商学院院长，河南制冷学会冷链物流工作委员会主任曹献存、副院长于晓胜、中国物通网，中国食品招商网CEO贾信河等众多行业专家和企业家，以及媒体代表、各界嘉宾上百人参会，共话河南物流发展新征程。');
INSERT INTO `notice` VALUES ('2', '中国物通网“智慧物流商学院”正式揭牌，开创智慧物流人才培养新模式', '2018-03-28 21:26:04', '随着物流行业信息化转型升级步伐的加快和智慧物流概念的深入，各种物流“黑科技”大家或许已见惯不惊，但智慧物流人才培养模式的“黑科技”却是一个新鲜事，它开创了行业复合应用型人才培养模式的先河，具有重要的应用意义');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` char(255) NOT NULL,
  `password` char(255) NOT NULL,
  `sex` char(255) NOT NULL,
  `phone` char(255) NOT NULL,
  `email` char(255) NOT NULL,
  `question` char(255) NOT NULL,
  `answer` char(255) NOT NULL,
  `register` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('5', 'liu', '123', '男', '13642337609', '123@qq.con', '123', '123', '2018-06-25 00:00:00');
