DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `PersonalInfo_idPersonalInfo` int(11) DEFAULT NULL,
  -- `Location_idLocation` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  -- `gender` varchar(1) DEFAULT NULL,
  -- `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `PersonalInfo_idPersonalInfo` (`PersonalInfo_idPersonalInfo`),
  -- UNIQUE KEY `PersonalInfo_idPersonalInfo_2` (`PersonalInfo_idPersonalInfo`),
  -- UNIQUE KEY `Location_idLocation` (`Location_idLocation`),
  -- UNIQUE KEY `PersonalInfo_idPersonalInfo_3` (`PersonalInfo_idPersonalInfo`),
  -- UNIQUE KEY `Location_idLocation_2` (`Location_idLocation`),
  KEY `fk_User_PersonalInfo1_idx` (`PersonalInfo_idPersonalInfo`),
  -- KEY `fk_User_Location1_idx` (`Location_idLocation`),
  -- CONSTRAINT `fk_User_Location1` FOREIGN KEY (`Location_idLocation`) REFERENCES `Location` (`idLocation`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_PersonalInfo` FOREIGN KEY (`PersonalInfo_idPersonalInfo`) REFERENCES `PersonalInfo` (`idPersonalInfo`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
-- INSERT INTO `User` VALUES (1,1,'lhuang33@jhu.edu','123'),(2,2,'sb@jhu.edu','111'),(3,3,'nimabi@jhu.edu','ddd');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;