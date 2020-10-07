/*
 Navicat Premium Data Transfer

 Source Server         : mariadb-dev-bpjstk
 Source Server Type    : MariaDB
 Source Server Version : 100140
 Source Host           : 172.28.208.22:3306
 Source Schema         : website_new

 Target Server Type    : MariaDB
 Target Server Version : 100140
 File Encoding         : 65001

 Date: 07/10/2020 10:00:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryType` char(1) DEFAULT NULL,
  `categoryTitle` varchar(100) DEFAULT NULL,
  `categoryShortDescription` varchar(255) DEFAULT NULL,
  `categoryLongDescription` text,
  `active` char(1) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `langId` varchar(10) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `userRekam` int(11) DEFAULT NULL,
  `userUbah` int(11) DEFAULT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `newsId` int(11) NOT NULL AUTO_INCREMENT,
  `newsCategoryId` int(11) DEFAULT NULL,
  `newsTitle` varchar(255) DEFAULT NULL,
  `newsMetaData` varchar(255) DEFAULT NULL,
  `newsShortContent` varchar(255) DEFAULT NULL,
  `newsLongContent` text,
  `newsAuthor` varchar(100) DEFAULT NULL,
  `imagePathUrl` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `langId` varchar(5) DEFAULT NULL,
  `active` char(1) DEFAULT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  `userRekam` int(11) DEFAULT NULL,
  `userUbah` int(11) DEFAULT NULL,
  PRIMARY KEY (`newsId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `userType` char(1) DEFAULT NULL,
  `fullName` varchar(100) DEFAULT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  `userRekam` int(11) DEFAULT NULL,
  `userUbah` int(11) DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `lastLoginIp` varchar(20) DEFAULT NULL,
  `lastLoginAgent` varchar(50) DEFAULT NULL,
  `active` char(1) DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
