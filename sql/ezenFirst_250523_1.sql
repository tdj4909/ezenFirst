-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: database-2.ctcam64yo4u8.ap-northeast-2.rds.amazonaws.com    Database: nowon
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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

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
  `product_id` int unsigned NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `fk_cart_member1_idx` (`member_id`),
  KEY `fk_cart_menu1_idx` (`product_id`),
  CONSTRAINT `fk_cart_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `fk_cart_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,1,4,8),(2,12,1,4,7),(3,1,1,4,8),(4,1,1,4,7),(5,1,1,4,8),(6,1,1,4,7),(7,1,1,4,7),(8,1,1,4,8),(9,4,1,4,8),(10,7,1,4,7),(11,6,1,4,7),(12,9,1,4,7),(13,12,1,4,7),(14,11,1,4,8),(15,7,1,4,7),(16,3,1,4,8),(17,5,1,4,7),(18,2,1,4,7),(19,1,1,4,8),(20,1,1,4,8),(21,3,1,4,7),(22,4,1,4,8),(23,3,1,4,8),(24,2,1,4,7),(25,2,1,4,8),(26,3,1,4,7),(27,1,0,4,8),(28,1,0,4,7),(29,2,0,4,9);
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
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_used` tinyint(1) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `codegroup_id` int unsigned NOT NULL,
  PRIMARY KEY (`code_id`),
  UNIQUE KEY `seq_UNIQUE` (`code_id`),
  KEY `fk_code_codegroup1_idx` (`codegroup_id`),
  CONSTRAINT `fk_code_codegroup1` FOREIGN KEY (`codegroup_id`) REFERENCES `codegroup` (`codegroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
INSERT INTO `code` VALUES (1,'SKT','2025-05-14 12:12:30',NULL,1,0,1),(2,'KT','2025-05-14 12:12:38',NULL,1,0,1),(3,'LGT','2025-05-14 12:12:49',NULL,1,0,1),(7,'버거','2025-05-18 20:56:23',NULL,1,0,2),(8,'피자','2025-05-18 20:56:36','2025-05-23 08:42:52',1,0,2),(9,'음료','2025-05-18 20:56:48',NULL,1,0,2),(10,'남성','2025-05-23 07:50:50',NULL,1,0,15),(11,'여성','2025-05-23 07:50:55',NULL,1,0,15),(12,'공개안함','2025-05-23 07:51:04','2025-05-23 07:51:19',1,0,15),(13,'주문 접수','2025-05-23 07:51:44',NULL,1,0,16),(14,'준비중','2025-05-23 07:51:56',NULL,1,0,16),(15,'주문 완료','2025-05-23 07:52:13',NULL,1,0,16),(16,'주문 취소','2025-05-23 07:52:22',NULL,1,0,16);
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
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_used` tinyint(1) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`codegroup_id`),
  UNIQUE KEY `seq_UNIQUE` (`codegroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `codegroup`
--

LOCK TABLES `codegroup` WRITE;
/*!40000 ALTER TABLE `codegroup` DISABLE KEYS */;
INSERT INTO `codegroup` VALUES (1,'통신사','2025-05-14 12:12:13','2025-05-23 07:48:22',1,0),(2,'메뉴 종류','2025-05-18 20:54:56','2025-05-23 07:47:48',1,0),(15,'성별','2025-05-23 07:48:44','2025-05-23 08:21:35',1,0),(16,'주문 상태','2025-05-23 07:49:04','2025-05-23 08:21:30',1,0);
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
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `uuid_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ext` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `size` bigint NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (12,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/0b805faa-f7b7-459b-83dd-3d74fcd03e99_burger-cjt.png','burger-cjt.png','0b805faa-f7b7-459b-83dd-3d74fcd03e99.png','png',278311,'2025-05-20 07:13:05',NULL,0),(13,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/320f6523-e42e-41ae-8ea6-02ee9cbb45e4_bacon-cheese-burger.jpg','bacon-cheese-burger.jpg','320f6523-e42e-41ae-8ea6-02ee9cbb45e4.jpg','jpg',51658,'2025-05-20 07:15:21',NULL,0),(14,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/32d6e7aa-bc4a-43fe-b850-76f739011e49_','','32d6e7aa-bc4a-43fe-b850-76f739011e49.','',0,'2025-05-20 07:18:07',NULL,0),(15,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/ed221a0b-7200-4338-aa4a-dda209aff060_bacon-cheese-burger.jpg','bacon-cheese-burger.jpg','ed221a0b-7200-4338-aa4a-dda209aff060.jpg','jpg',51658,'2025-05-20 07:31:10',NULL,0),(16,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/465454c2-1c0d-4110-8174-2dad4a3e3fee_double-cheese-burger.jpg','double-cheese-burger.jpg','465454c2-1c0d-4110-8174-2dad4a3e3fee.jpg','jpg',847576,'2025-05-20 07:32:41',NULL,0),(17,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/162c3192-12af-4ff7-adf0-95dc4b4e18e9_margherita-pizza.jpg','margherita-pizza.jpg','162c3192-12af-4ff7-adf0-95dc4b4e18e9.jpg','jpg',912911,'2025-05-23 08:43:35',NULL,0),(18,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/03434b79-a9c5-4600-8e8b-53f1704d74a9_mushroom-cheese-burger.jpg','mushroom-cheese-burger.jpg','03434b79-a9c5-4600-8e8b-53f1704d74a9.jpg','jpg',676038,'2025-05-23 08:44:48',NULL,0),(19,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/529cb8d8-1215-49ac-b6a6-119421b6c7a5_mushroom-fresh-burger.jpg','mushroom-fresh-burger.jpg','529cb8d8-1215-49ac-b6a6-119421b6c7a5.jpg','jpg',46380,'2025-05-23 08:45:23',NULL,0),(20,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/bbdd7b32-2e90-48ad-bcb2-45d71dd49510_pepperoni-pizza.jpg','pepperoni-pizza.jpg','bbdd7b32-2e90-48ad-bcb2-45d71dd49510.jpg','jpg',96672,'2025-05-23 08:46:09',NULL,0),(21,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/a0fba373-558d-4c1d-8e58-497c6eb9a65a_burger-j.png','burger-j.png','a0fba373-558d-4c1d-8e58-497c6eb9a65a.png','png',315760,'2025-05-23 08:46:58',NULL,0);
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
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` int NOT NULL,
  `birthday` date NOT NULL,
  `mobile_carrier` int NOT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `seq_UNIQUE` (`member_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'1','$2a$10$Ow7pJKY9BXpM8oQX1O6Avuto.R2BzZiY1XrHG.RnirQnzBVUNaDHK','1',1,'1901-01-01',1,'1','2025-05-14 14:02:25',NULL,0),(2,'2','$2a$10$PDBMvK3ZVQEFfJD1cErFg.jHSxQruGn3WVlyGIHN45112yJW1xvg2','2',2,'1902-01-01',2,'2','2025-05-14 14:16:39',NULL,0),(3,'3','$2a$10$2zoNcsKxTASt.3x4mgakd.flhEADF5PDYhrelkZ/ubpXqFC57njjy','이름3',1,'2025-05-12',1,'333','2025-05-20 02:54:53',NULL,0),(4,'1@1','$2a$10$Dtp6DXGKCkdNloHlgB6ElO1rnQYJjR/TOmSd6FmF1k5KxiT7yUkiW','이름33',1,'2025-04-09',1,'111','2025-05-20 02:55:35','2025-05-20 08:40:32',0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
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
  `product_id` int unsigned NOT NULL,
  PRIMARY KEY (`order_detail_id`),
  UNIQUE KEY `seq_UNIQUE` (`order_detail_id`),
  KEY `fk_order_item_menu1_idx` (`product_id`),
  KEY `fk_order_detail_order_master1_idx` (`order_master_id`),
  CONSTRAINT `fk_order_detail_order_master1` FOREIGN KEY (`order_master_id`) REFERENCES `order_master` (`order_master_id`),
  CONSTRAINT `fk_order_item_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,11,189400,2,8),(2,7,189400,2,7),(3,5,77000,3,7),(4,3,77000,3,8),(5,2,8200,4,7),(6,1,12000,4,8),(7,3,8200,5,7),(8,1,12000,5,8),(9,4,12000,6,8),(10,2,8200,7,7),(11,3,12000,7,8),(12,3,8200,8,7),(13,2,12000,8,8);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_master`
--

LOCK TABLES `order_master` WRITE;
/*!40000 ALTER TABLE `order_master` DISABLE KEYS */;
INSERT INTO `order_master` VALUES (2,0,189400,'2025-05-22 05:03:14',NULL,0,4),(3,0,77000,'2025-05-22 05:20:49',NULL,0,4),(4,1,28400,'2025-05-22 05:22:41',NULL,0,4),(5,0,36600,'2025-05-23 03:47:37',NULL,0,4),(6,0,48000,'2025-05-23 03:54:00','2025-05-23 06:21:30',1,4),(7,0,52400,'2025-05-23 04:04:16','2025-05-23 06:21:41',1,4),(8,1,48600,'2025-05-23 04:07:07',NULL,0,4);
/*!40000 ALTER TABLE `order_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` int NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int NOT NULL,
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `calories` int NOT NULL,
  `sugars` int NOT NULL,
  `protein` int NOT NULL,
  `fat` int NOT NULL,
  `sodium` int NOT NULL,
  `stock` int NOT NULL,
  `order_count` int unsigned NOT NULL,
  `rating` decimal(3,2) DEFAULT NULL,
  `is_recommand` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `file_id` int unsigned NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `seq_UNIQUE` (`product_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_menu_file1_idx` (`file_id`),
  CONSTRAINT `fk_menu_file1` FOREIGN KEY (`file_id`) REFERENCES `file` (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (7,7,'베이컨치즈버거',8200,'베이컨치즈버거',600,60,60,60,600,100,0,2.00,1,'2025-05-20 07:15:21','2025-05-23 07:23:12',0,15),(8,7,'더블치즈버거',12000,'더블치즈버거',1000,10,10,10,100,100,0,4.00,1,'2025-05-20 07:32:41','2025-05-23 06:48:33',0,16),(9,8,'마르게리타',18000,'마르게리타',1200,100,20,20,1200,200,0,NULL,1,'2025-05-23 08:43:35',NULL,0,17),(10,7,'머쉬룸치즈버거',11200,'머쉬룸치즈버거',1100,80,80,80,800,112,0,NULL,1,'2025-05-23 08:44:48',NULL,0,18),(11,7,'머쉬룸프레쉬버거',9900,'',900,90,90,90,900,99,0,NULL,0,'2025-05-23 08:45:23',NULL,0,19),(12,8,'페퍼로니피자',19000,'',1900,190,90,90,1900,190,0,NULL,1,'2025-05-23 08:46:09',NULL,0,20),(13,7,'징거버거',8000,'',800,800,80,80,800,80,0,NULL,1,'2025-05-23 08:46:58',NULL,0,21);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `member_id` int unsigned NOT NULL,
  `product_id` int unsigned NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `seq_UNIQUE` (`review_id`),
  KEY `fk_review_member1_idx` (`member_id`),
  KEY `fk_review_product1_idx` (`product_id`),
  CONSTRAINT `fk_review_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `fk_review_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (2,3,'ㅁㄴㅇㅁㄴㄻㄴㄹ','2025-05-23 06:41:09',NULL,0,4,8),(3,5,'ㄴㅇㄹㄴㅇㅎㄴㅇㅎ','2025-05-23 06:43:53',NULL,0,4,8),(4,5,'리뷰3','2025-05-23 06:48:24',NULL,0,4,8),(5,1,'1','2025-05-23 06:48:33',NULL,0,4,8),(6,2,'1111111111111111111111111111111111111111111111111111111111111111111111111111111111','2025-05-23 07:23:12',NULL,0,4,7);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-23 17:47:56
