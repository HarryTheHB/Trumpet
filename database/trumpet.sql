DROP DATABASE IF EXISTS `trumpet`;
CREATE DATABASE  IF NOT EXISTS `trumpet` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `trumpet`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
  `idComment` int(11) NOT NULL AUTO_INCREMENT,
  `User_idUser` int(11) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `Status_idStatus` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`idComment`),
  -- UNIQUE KEY `User_idUser` (`User_idUser`),
  KEY `fk_Comment_User1_idx` (`User_idUser`),
  KEY `fk_Comment_Status1_idx` (`Status_idStatus`),
  CONSTRAINT `fk_Comment_Status1` FOREIGN KEY (`Status_idStatus`) REFERENCES `Status` (`idStatus`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Comment_User1` FOREIGN KEY (`User_idUser`) REFERENCES `User` (`idUser`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `idLocation` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double(10,6) DEFAULT NULL,
  `longitude` double(10,6) DEFAULT NULL,
  -- `points` POINT NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idLocation`)
  -- SPATIAL INDEX(points)
) ENGINE=InnDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` VALUES (1,34.870000,120.400000,'JOHNS HOPKINS UNIVERSITY'),(2,64.870000,20.400000,'Shanghai Jiao Tong UNIVERSITY'),(3,35.980000, -36.700000,'Carlyle'),(4,30.980000,-39.700000,'BroadView'),(5,65.900000,23.120000,'Hopkins House');
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PersonalInfo`
--

DROP TABLE IF EXISTS `PersonalInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PersonalInfo` (
  `idPersonalInfo` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `visibility` int(11) DEFAULT '0',
  `gender` varchar(1),
  `description` varchar(500),
  PRIMARY KEY (`idPersonalInfo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PersonalInfo`
--

LOCK TABLES `PersonalInfo` WRITE;
/*!40000 ALTER TABLE `PersonalInfo` DISABLE KEYS */;
INSERT INTO `PersonalInfo` VALUES (1,'gala',22,0,'m', 'cao'),(2,'daiyang',21,1, 'm', 'ni'),(3,'fengcong',22,0,'m', 'ma'),(4,'gaoyuan',23,1,'m', 'b');
/*!40000 ALTER TABLE `PersonalInfo` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `Status`
--

DROP TABLE IF EXISTS `Status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Status` (
  `idStatus` int(11) NOT NULL AUTO_INCREMENT,
  `User_idUser` int(11),
  `Location_idLocation` int(11) NOT NULL,
  `content` varchar(500) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `coverRange` int(11) DEFAULT NULL,
  PRIMARY KEY (`idStatus`),
  UNIQUE KEY `Location_idLocation` (`Location_idLocation`),
  KEY `fk_Status_User1_idx` (`Location_idLocation`),
  KEY `fk_Status_Location1_idx` (`Location_idLocation`),
  CONSTRAINT `fk_Status_User1` FOREIGN KEY (`User_idUser`) REFERENCES `User` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Status_Location1` FOREIGN KEY (`Location_idLocation`) REFERENCES `Location` (`idLocation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Status`
--

LOCK TABLES `Status` WRITE;
/*!40000 ALTER TABLE `Status` DISABLE KEYS */;
INSERT INTO `Status` VALUES (1,5,'sucks','2013-10-22 10:43:23',50, 1);
/*!40000 ALTER TABLE `Status` ENABLE KEYS */;
UNLOCK TABLES;



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-27 20:21:16
