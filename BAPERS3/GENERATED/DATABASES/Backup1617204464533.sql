-- MySQL dump 10.13  Distrib 8.0.23, for macos10.15 (x86_64)
--
-- Host: localhost    Database: Bapers
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `accountdetails`
--

DROP TABLE IF EXISTS `accountdetails`;
/*!50001 DROP VIEW IF EXISTS `accountdetails`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `accountdetails` AS SELECT 
 1 AS `AccountNumber`,
 1 AS `Address`,
 1 AS `PhoneNumber`,
 1 AS `AccountStatus`,
 1 AS `ContactName`,
 1 AS `CustomerName`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `CustomerAccounts`
--

DROP TABLE IF EXISTS `CustomerAccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CustomerAccounts` (
  `AccountNumber` int NOT NULL AUTO_INCREMENT,
  `AccountStatus` varchar(255) NOT NULL,
  `PhoneNumber` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `EmailAddress` varchar(255) NOT NULL,
  `CustomerName` varchar(255) NOT NULL,
  `ContactName` varchar(255) NOT NULL,
  `IsArchived` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`AccountNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerAccounts`
--

LOCK TABLES `CustomerAccounts` WRITE;
/*!40000 ALTER TABLE `CustomerAccounts` DISABLE KEYS */;
INSERT INTO `CustomerAccounts` VALUES (1,'non-valued','123','addres','email@mail.com','bob','bbob',0);
/*!40000 ALTER TABLE `CustomerAccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FixedDiscount`
--

DROP TABLE IF EXISTS `FixedDiscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FixedDiscount` (
  `DiscountID` int NOT NULL AUTO_INCREMENT,
  `AccountNumber` int NOT NULL,
  `FlatDiscountRate` int NOT NULL,
  PRIMARY KEY (`DiscountID`,`AccountNumber`),
  UNIQUE KEY `DiscountID` (`DiscountID`),
  UNIQUE KEY `AccountNumber` (`AccountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FixedDiscount`
--

LOCK TABLES `FixedDiscount` WRITE;
/*!40000 ALTER TABLE `FixedDiscount` DISABLE KEYS */;
/*!40000 ALTER TABLE `FixedDiscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FlexibleDiscount`
--

DROP TABLE IF EXISTS `FlexibleDiscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FlexibleDiscount` (
  `DiscountID` int NOT NULL AUTO_INCREMENT,
  `BandNumber` int NOT NULL,
  `AccountNumber` int NOT NULL,
  `VolumeLowerBound` int NOT NULL,
  `VolumeUpperBound` int NOT NULL,
  `FlexibleDiscountRate` int NOT NULL,
  PRIMARY KEY (`DiscountID`,`BandNumber`,`AccountNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FlexibleDiscount`
--

LOCK TABLES `FlexibleDiscount` WRITE;
/*!40000 ALTER TABLE `FlexibleDiscount` DISABLE KEYS */;
INSERT INTO `FlexibleDiscount` VALUES (1,1,1,0,1000,0),(1,2,1,1000,2000,1),(1,3,1,2000,100000000,2),(2,1,1,0,1000,0),(2,2,1,1000,2000,1),(2,3,1,2000,100000000,2);
/*!40000 ALTER TABLE `FlexibleDiscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `individualperformancereport`
--

DROP TABLE IF EXISTS `individualperformancereport`;
/*!50001 DROP VIEW IF EXISTS `individualperformancereport`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `individualperformancereport` AS SELECT 
 1 AS `Name`,
 1 AS `Tasks IDs`,
 1 AS `Department`,
 1 AS `Date/Start time`,
 1 AS `Time taken`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `individualperformancereport1`
--

DROP TABLE IF EXISTS `individualperformancereport1`;
/*!50001 DROP VIEW IF EXISTS `individualperformancereport1`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `individualperformancereport1` AS SELECT 
 1 AS `TimeSpent`,
 1 AS `EmployeeCompletedBy`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `individualperformancereport2`
--

DROP TABLE IF EXISTS `individualperformancereport2`;
/*!50001 DROP VIEW IF EXISTS `individualperformancereport2`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `individualperformancereport2` AS SELECT 
 1 AS `Total time on chosen date`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!50001 DROP VIEW IF EXISTS `invoice`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `invoice` AS SELECT 
 1 AS `TaskID`,
 1 AS `AccountNumber`,
 1 AS `AccountStatus`,
 1 AS `PhoneNumber`,
 1 AS `ContactName`,
 1 AS `CustomerName`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Jobs`
--

DROP TABLE IF EXISTS `Jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Jobs` (
  `JobID` int NOT NULL AUTO_INCREMENT,
  `AccountNumber` int NOT NULL DEFAULT '0',
  `NumberOfTasks` int NOT NULL DEFAULT '0',
  `DateOfJob` varchar(255) NOT NULL DEFAULT 'UNSPECIFIED',
  `JobDeadline` varchar(255) NOT NULL DEFAULT 'UNSPECIFIED',
  `JobUrgency` varchar(255) NOT NULL DEFAULT 'UNSPECIFIED',
  `Price` float NOT NULL DEFAULT '0',
  `TasksCompleted` int NOT NULL DEFAULT '0',
  `IsCompleted` tinyint(1) NOT NULL DEFAULT '0',
  `IsArchived` int NOT NULL DEFAULT '0',
  `IsLate` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`JobID`,`AccountNumber`),
  UNIQUE KEY `JobID` (`JobID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Jobs`
--

LOCK TABLES `Jobs` WRITE;
/*!40000 ALTER TABLE `Jobs` DISABLE KEYS */;
INSERT INTO `Jobs` VALUES (1,1,0,'2020-09-09','2020-09-09 20:00','normal',0,0,0,0,0),(2,1,3,'Wed Mar 31 16:25:52 BST 2021','Wed Mar 31 19:25:52 BST 2021','vurgent',15,0,0,0,0);
/*!40000 ALTER TABLE `Jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `jobsheet`
--

DROP TABLE IF EXISTS `jobsheet`;
/*!50001 DROP VIEW IF EXISTS `jobsheet`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `jobsheet` AS SELECT 
 1 AS `Account number`,
 1 AS `CustomerName`,
 1 AS `ContactName`,
 1 AS `Job`,
 1 AS `Price, £`,
 1 AS `Task`,
 1 AS `Department`,
 1 AS `Start time/Date`,
 1 AS `Time Taken`,
 1 AS `EmployeeCompletedBy`,
 1 AS `JobTaskID`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Payments`
--

DROP TABLE IF EXISTS `Payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Payments` (
  `JobID` int NOT NULL,
  `AccountNumber` int NOT NULL,
  `Price` float NOT NULL,
  `DiscountedPrice` float NOT NULL,
  `DateOfPayment` varchar(255) NOT NULL,
  `PaymentType` varchar(255) NOT NULL,
  `ExpiryDate` int DEFAULT NULL,
  `CardholderName` varchar(255) DEFAULT NULL,
  `CardType` varchar(255) DEFAULT NULL,
  `Last4Digits` int DEFAULT NULL,
  `CVVC` int DEFAULT NULL,
  PRIMARY KEY (`JobID`,`AccountNumber`),
  UNIQUE KEY `JobID` (`JobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payments`
--

LOCK TABLES `Payments` WRITE;
/*!40000 ALTER TABLE `Payments` DISABLE KEYS */;
INSERT INTO `Payments` VALUES (1,1,30,25,'today today today','today today today',1,'card','cardtype',1245,241);
/*!40000 ALTER TABLE `Payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TaskInAJob`
--

DROP TABLE IF EXISTS `TaskInAJob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TaskInAJob` (
  `JobTaskID` int NOT NULL AUTO_INCREMENT,
  `JobID` int NOT NULL,
  `TaskID` int NOT NULL,
  `AccountNumber` int NOT NULL,
  `TaskStartTime` varchar(255) NOT NULL DEFAULT 'UNSPECIFIED',
  `EmployeeCompletedBy` varchar(255) DEFAULT 'INCOMPLETE',
  `ShiftCompleted` int DEFAULT '0',
  `JobUrgency` varchar(255) NOT NULL DEFAULT 'UNSPECIFIED',
  `TaskDeadline` varchar(255) NOT NULL DEFAULT 'UNSPECIFIED',
  `ActualDuration` int DEFAULT '0',
  `isCompleted` tinyint(1) DEFAULT '0',
  `IsArchived` int NOT NULL DEFAULT '0',
  `StartTimeMillis` varchar(255) DEFAULT 'UNSET',
  PRIMARY KEY (`JobTaskID`,`JobID`,`TaskID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TaskInAJob`
--

LOCK TABLES `TaskInAJob` WRITE;
/*!40000 ALTER TABLE `TaskInAJob` DISABLE KEYS */;
INSERT INTO `TaskInAJob` VALUES (1,1,1,1,'2020-09-09 12:00:12','Marina Scott',1,'normal','2000-09-09',30,0,0,'UNSET'),(2,1,1,1,'2020-09-09 12:00:12','Marina Scott',1,'normal','2000-09-09',30,0,0,'UNSET'),(3,1,1,1,'2020-09-09 11:00:12','John Nash',1,'normal','2000-09-09',30,0,0,'UNSET'),(4,1,1,1,'2020-09-09 12:00:12','Peter',1,'normal','2000-09-09',30,1,0,'UNSET'),(5,1,1,1,'2020-09-09 12:00:12','Peter',1,'normal','2000-09-09',30,1,0,'UNSET'),(6,1,1,1,'2020-09-09 11:00:12','John Nash',1,'normal','2000-09-09',30,1,0,'UNSET'),(7,2,0,1,'UNSPECIFIED','INCOMPLETE',0,'vurgent','UNSPECIFIED',0,0,0,'UNSET'),(8,2,0,1,'UNSPECIFIED','INCOMPLETE',0,'vurgent','UNSPECIFIED',0,0,0,'UNSET'),(9,2,0,1,'UNSPECIFIED','INCOMPLETE',0,'vurgent','UNSPECIFIED',0,0,0,'UNSET');
/*!40000 ALTER TABLE `TaskInAJob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tasks`
--

DROP TABLE IF EXISTS `Tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tasks` (
  `TaskID` int NOT NULL AUTO_INCREMENT,
  `TaskLocation` varchar(255) NOT NULL,
  `TaskPrice` float NOT NULL,
  `TaskDescription` varchar(255) NOT NULL,
  `PredictedDuration` int NOT NULL,
  `IsArchived` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`TaskID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tasks`
--

LOCK TABLES `Tasks` WRITE;
/*!40000 ALTER TABLE `Tasks` DISABLE KEYS */;
INSERT INTO `Tasks` VALUES (1,'Copy Room',5,'Use of large copy camera',30,0),(2,'Devlopmental area',50,'Black and white film processing',60,0);
/*!40000 ALTER TABLE `Tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserAccounts`
--

DROP TABLE IF EXISTS `UserAccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserAccounts` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(55) NOT NULL,
  `RoleName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `StaffName` varchar(255) NOT NULL,
  PRIMARY KEY (`EmployeeID`,`Username`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserAccounts`
--

LOCK TABLES `UserAccounts` WRITE;
/*!40000 ALTER TABLE `UserAccounts` DISABLE KEYS */;
INSERT INTO `UserAccounts` VALUES (1,'om','Office Manager','om','name');
/*!40000 ALTER TABLE `UserAccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VariableDiscount`
--

DROP TABLE IF EXISTS `VariableDiscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `VariableDiscount` (
  `DiscountID` int NOT NULL AUTO_INCREMENT,
  `TaskID` int NOT NULL,
  `AccountNumber` int NOT NULL,
  `TaskDiscount` int NOT NULL,
  PRIMARY KEY (`DiscountID`,`TaskID`,`AccountNumber`),
  UNIQUE KEY `TaskID` (`TaskID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VariableDiscount`
--

LOCK TABLES `VariableDiscount` WRITE;
/*!40000 ALTER TABLE `VariableDiscount` DISABLE KEYS */;
/*!40000 ALTER TABLE `VariableDiscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `accountdetails`
--

/*!50001 DROP VIEW IF EXISTS `accountdetails`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jaroviadb`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `accountdetails` AS select `customeraccounts`.`AccountNumber` AS `AccountNumber`,`customeraccounts`.`Address` AS `Address`,`customeraccounts`.`PhoneNumber` AS `PhoneNumber`,`customeraccounts`.`AccountStatus` AS `AccountStatus`,`customeraccounts`.`ContactName` AS `ContactName`,`customeraccounts`.`CustomerName` AS `CustomerName` from `customeraccounts` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `individualperformancereport`
--

/*!50001 DROP VIEW IF EXISTS `individualperformancereport`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jaroviadb`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `individualperformancereport` AS select `TIJ`.`EmployeeCompletedBy` AS `Name`,`T`.`TaskID` AS `Tasks IDs`,`T`.`TaskLocation` AS `Department`,`TIJ`.`TaskStartTime` AS `Date/Start time`,`TIJ`.`ActualDuration` AS `Time taken` from (`taskinajob` `TIJ` join `tasks` `T`) where ((`TIJ`.`TaskID` = `T`.`TaskID`) and (`TIJ`.`TaskStartTime` like '%') and (`TIJ`.`isCompleted` = 1)) order by `TIJ`.`EmployeeCompletedBy` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `individualperformancereport1`
--

/*!50001 DROP VIEW IF EXISTS `individualperformancereport1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jaroviadb`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `individualperformancereport1` AS select sum(`TIJ`.`ActualDuration`) AS `TimeSpent`,`TIJ`.`EmployeeCompletedBy` AS `EmployeeCompletedBy` from (`taskinajob` `TIJ` join `tasks` `T`) where ((`TIJ`.`TaskID` = `T`.`TaskID`) and (`TIJ`.`TaskStartTime` like '%') and (`TIJ`.`isCompleted` = 1)) group by `TIJ`.`EmployeeCompletedBy` order by `TIJ`.`EmployeeCompletedBy` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `individualperformancereport2`
--

/*!50001 DROP VIEW IF EXISTS `individualperformancereport2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jaroviadb`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `individualperformancereport2` AS select sum(`TIJ`.`ActualDuration`) AS `Total time on chosen date` from (`taskinajob` `TIJ` join `tasks` `T`) where ((`TIJ`.`TaskStartTime` like '%') and (`TIJ`.`isCompleted` = 1)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `invoice`
--

/*!50001 DROP VIEW IF EXISTS `invoice`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jaroviadb`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `invoice` AS select `TIJ`.`TaskID` AS `TaskID`,`CA`.`AccountNumber` AS `AccountNumber`,`CA`.`AccountStatus` AS `AccountStatus`,`CA`.`PhoneNumber` AS `PhoneNumber`,`CA`.`ContactName` AS `ContactName`,`CA`.`CustomerName` AS `CustomerName` from (`taskinajob` `TIJ` join `customeraccounts` `CA`) where (`TIJ`.`AccountNumber` = `CA`.`AccountNumber`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `jobsheet`
--

/*!50001 DROP VIEW IF EXISTS `jobsheet`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`jaroviadb`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `jobsheet` AS select `CA`.`AccountNumber` AS `Account number`,`CA`.`CustomerName` AS `CustomerName`,`CA`.`ContactName` AS `ContactName`,`J`.`JobID` AS `Job`,`T`.`TaskPrice` AS `Price, £`,`T`.`TaskID` AS `Task`,`T`.`TaskLocation` AS `Department`,`TIJ`.`TaskStartTime` AS `Start time/Date`,`TIJ`.`ActualDuration` AS `Time Taken`,`TIJ`.`EmployeeCompletedBy` AS `EmployeeCompletedBy`,`TIJ`.`JobTaskID` AS `JobTaskID` from (((`taskinajob` `TIJ` join `jobs` `J`) join `tasks` `T`) join `customeraccounts` `CA`) where ((`TIJ`.`isCompleted` = 1) and (`CA`.`AccountNumber` like '%') and (`TIJ`.`TaskStartTime` like '%%') and (`J`.`JobID` = `TIJ`.`JobID`) and (`T`.`TaskID` = `TIJ`.`TaskID`) and (`J`.`AccountNumber` = `CA`.`AccountNumber`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-31 16:27:44
