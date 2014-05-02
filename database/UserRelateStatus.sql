DROP TABLE IF EXISTS `User_relate_Status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_relate_Status` (
  `User_idUser` int(11) NOT NULL,
  `Status_idStatus` int(11) NOT NULL,
  `subscribe` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`User_idUser`,`Status_idStatus`),
  KEY `fk_User_has_Status1_Status1_idx` (`Status_idStatus`),
  KEY `fk_User_has_Status1_User1_idx` (`User_idUser`),
  CONSTRAINT `fk_User_has_Status1_Status1` FOREIGN KEY (`Status_idStatus`) REFERENCES `Status` (`idStatus`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_User_has_Status1_User1` FOREIGN KEY (`User_idUser`) REFERENCES `User` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_relate_Status`
--

LOCK TABLES `User_relate_Status` WRITE;
/*!40000 ALTER TABLE `User_relate_Status` DISABLE KEYS */;
/*!40000 ALTER TABLE `User_relate_Status` ENABLE KEYS */;
UNLOCK TABLES;