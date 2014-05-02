DROP TABLE IF EXISTS `Notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Notification` (
	`User_idUser` int(11) NOT NULL,
	`Status_idStatus` int(11) NOT NULL,
	`time` DATETIME DEFAULT '2000-01-01 00:00:00',
	PRIMARY KEY (`User_idUser`,`Status_idStatus`),
	KEY `fk_Notification_Status1_idx` (`Status_idStatus`),
	KEY `fk_Notification_User1_idx` (`User_idUser`),
	CONSTRAINT `fk_Notification_Status1` FOREIGN KEY (`Status_idStatus`) REFERENCES `Status` (`idStatus`) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `fk_Notification_User1` FOREIGN KEY (`User_idUser`) REFERENCES `User` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `Notification` WRITE;
/*!40000 ALTER TABLE `Notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `Notification` ENABLE KEYS */;
UNLOCK TABLES;