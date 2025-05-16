-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: nowon
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int unsigned NOT NULL AUTO_INCREMENT,
  `quantity` int unsigned NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `member_id` int unsigned NOT NULL,
  `menu_id` int unsigned NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `fk_cart_member1_idx` (`member_id`),
  KEY `fk_cart_menu1_idx` (`menu_id`),
  CONSTRAINT `fk_cart_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `fk_cart_menu1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code`
--

DROP TABLE IF EXISTS `code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `code` (
  `code_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_used` tinyint(1) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `codegroup_id` int unsigned NOT NULL,
  PRIMARY KEY (`code_id`),
  UNIQUE KEY `seq_UNIQUE` (`code_id`),
  KEY `fk_code_codegroup1_idx` (`codegroup_id`),
  CONSTRAINT `fk_code_codegroup1` FOREIGN KEY (`codegroup_id`) REFERENCES `codegroup` (`codegroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
INSERT INTO `code` VALUES (1,'SKT','2025-05-14 12:12:30',NULL,1,0,1),(2,'KT','2025-05-14 12:12:38',NULL,1,0,1),(3,'LGT','2025-05-14 12:12:49',NULL,1,0,1);
/*!40000 ALTER TABLE `code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `codegroup`
--

DROP TABLE IF EXISTS `codegroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `codegroup` (
  `codegroup_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_used` tinyint(1) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`codegroup_id`),
  UNIQUE KEY `seq_UNIQUE` (`codegroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `codegroup`
--

LOCK TABLES `codegroup` WRITE;
/*!40000 ALTER TABLE `codegroup` DISABLE KEYS */;
INSERT INTO `codegroup` VALUES (1,'통신사','2025-05-14 12:12:13',NULL,1,0);
/*!40000 ALTER TABLE `codegroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `file_id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` int NOT NULL,
  `sort` int NOT NULL,
  `path` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `original_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uuid_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ext` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `size` bigint NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` int NOT NULL,
  `birthday` date NOT NULL,
  `mobile_carrier` int NOT NULL,
  `phone` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `seq_UNIQUE` (`member_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'1','$2a$10$Ow7pJKY9BXpM8oQX1O6Avuto.R2BzZiY1XrHG.RnirQnzBVUNaDHK','1',1,'1901-01-01',1,'1','2025-05-14 14:02:25',NULL,0,0),(2,'2','$2a$10$PDBMvK3ZVQEFfJD1cErFg.jHSxQruGn3WVlyGIHN45112yJW1xvg2','2',2,'1902-01-01',2,'2','2025-05-14 14:16:39',NULL,1,0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `menu_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int NOT NULL,
  `description` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `calories` int NOT NULL,
  `sugars` int NOT NULL,
  `protein` int NOT NULL,
  `fat` int NOT NULL,
  `sodium` int NOT NULL,
  `type` int NOT NULL,
  `is_recommand` tinyint(1) NOT NULL,
  `is_new` tinyint(1) NOT NULL,
  `stock` int NOT NULL,
  `order_count` int unsigned NOT NULL,
  `rating` int DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `file_id` int unsigned NOT NULL,
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `seq_UNIQUE` (`menu_id`),
  KEY `fk_menu_file1_idx` (`file_id`),
  CONSTRAINT `fk_menu_file1` FOREIGN KEY (`file_id`) REFERENCES `file` (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_detail_id` int unsigned NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `price` int NOT NULL,
  `order_master_id` int unsigned NOT NULL,
  `menu_id` int unsigned NOT NULL,
  PRIMARY KEY (`order_detail_id`),
  UNIQUE KEY `seq_UNIQUE` (`order_detail_id`),
  KEY `fk_order_item_menu1_idx` (`menu_id`),
  KEY `fk_order_detail_order_master1_idx` (`order_master_id`),
  CONSTRAINT `fk_order_detail_order_master1` FOREIGN KEY (`order_master_id`) REFERENCES `order_master` (`order_master_id`),
  CONSTRAINT `fk_order_item_menu1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_master`
--

DROP TABLE IF EXISTS `order_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_master` (
  `order_master_id` int unsigned NOT NULL AUTO_INCREMENT,
  `status` int NOT NULL,
  `price` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `member_id` int unsigned NOT NULL,
  PRIMARY KEY (`order_master_id`),
  UNIQUE KEY `seq_UNIQUE` (`order_master_id`),
  KEY `fk_orders_member1_idx` (`member_id`),
  CONSTRAINT `fk_orders_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_master`
--

LOCK TABLES `order_master` WRITE;
/*!40000 ALTER TABLE `order_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` int unsigned NOT NULL AUTO_INCREMENT,
  `rating` int NOT NULL,
  `description` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `member_id` int unsigned NOT NULL,
  `menu_id` int unsigned NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `seq_UNIQUE` (`review_id`),
  KEY `fk_review_member1_idx` (`member_id`),
  KEY `fk_review_menu1_idx` (`menu_id`),
  CONSTRAINT `fk_review_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `fk_review_menu1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-15 10:39:17
