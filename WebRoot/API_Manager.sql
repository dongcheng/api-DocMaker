/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50146
 Source Host           : localhost
 Source Database       : API_Manager

 Target Server Type    : MySQL
 Target Server Version : 50146
 File Encoding         : utf-8

 Date: 03/02/2014 17:05:06 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `T_Function`
-- ----------------------------
DROP TABLE IF EXISTS `T_Function`;
CREATE TABLE `T_Function` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `httpMethod` varchar(300) DEFAULT NULL,
  `isLogin` int(2) NOT NULL,
  `params` varchar(1000) DEFAULT NULL,
  `mID` int(32) DEFAULT NULL,
  `createDate` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `T_Module`
-- ----------------------------
DROP TABLE IF EXISTS `T_Module`;
CREATE TABLE `T_Module` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `baseUrl` varchar(200) DEFAULT NULL,
  `descStr` varchar(1000) DEFAULT NULL,
  `createDate` varchar(64) DEFAULT NULL,
  `pID` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `T_Project`
-- ----------------------------
DROP TABLE IF EXISTS `T_Project`;
CREATE TABLE `T_Project` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `baseUrl` varchar(1000) DEFAULT NULL,
  `descStr` varchar(1000) DEFAULT NULL,
  `createDate` varchar(64) DEFAULT NULL,
  `version` varchar(32) DEFAULT '1.0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

