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
  `seq` int unsigned NOT NULL AUTO_INCREMENT,
  `quantity` int unsigned NOT NULL,
  `cartDelNy` tinyint(1) NOT NULL,
  `member_seq` int unsigned NOT NULL,
  `menu_seq` int unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `fk_cart_member1_idx` (`member_seq`),
  KEY `fk_cart_menu1_idx` (`menu_seq`),
  CONSTRAINT `fk_cart_member1` FOREIGN KEY (`member_seq`) REFERENCES `user` (`seq`),
  CONSTRAINT `fk_cart_menu1` FOREIGN KEY (`menu_seq`) REFERENCES `menu` (`seq`)
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
  `seq` int unsigned NOT NULL AUTO_INCREMENT,
  `codeCd` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `codeNm` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `codeUse` tinyint(1) NOT NULL,
  `codeRegDateTime` datetime DEFAULT NULL,
  `codeModDateTime` datetime DEFAULT NULL,
  `codeDelNy` tinyint(1) NOT NULL,
  `codeGroup_seq` int unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `seq_UNIQUE` (`seq`),
  KEY `fk_code_codeGroup1_idx` (`codeGroup_seq`),
  CONSTRAINT `fk_code_codeGroup1` FOREIGN KEY (`codeGroup_seq`) REFERENCES `codegroup` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
INSERT INTO `code` VALUES (1,NULL,'SKT',1,NULL,NULL,0,1),(2,NULL,'KT',1,NULL,NULL,0,1),(3,NULL,'LG',1,NULL,NULL,0,1),(4,NULL,'다음',1,NULL,NULL,0,2),(5,NULL,'구글',1,NULL,NULL,0,2),(6,NULL,'네이버',1,NULL,NULL,0,2),(7,NULL,'다음',1,NULL,NULL,0,3),(8,NULL,'구글',1,NULL,NULL,0,3),(9,NULL,'SKT알뜰폰',1,NULL,NULL,1,1),(10,'','구글',1,NULL,NULL,0,1),(11,'111','구글111222',0,NULL,NULL,0,1),(12,NULL,'1',1,NULL,NULL,1,2),(13,NULL,'1',1,NULL,NULL,1,1),(15,'','ㅁㅁㅁ',1,'2025-03-31 10:12:39','2025-03-31 10:15:45',0,4),(16,'','ㅌㅌ',1,'2025-03-31 10:16:02','2025-03-31 10:16:07',1,11),(17,NULL,'ㅍㅍㅍㅍㅍㅍㅍㅍㅍㅍㅍ',1,'2025-03-31 10:37:52',NULL,1,5),(18,NULL,'a',1,'2025-03-31 14:27:02',NULL,1,4),(19,'','ㅊ',1,'2025-03-31 14:36:21','2025-04-07 10:23:15',1,5),(20,'','ㅁㄴㄻㄴㄹ',1,'2025-03-31 14:54:46','2025-03-31 15:31:47',1,5),(21,NULL,'ㅁㄴㄹ    ㄻㄴㄹ',1,'2025-03-31 14:54:55',NULL,1,5),(22,'','aaaaaaa',1,'2025-03-31 16:20:29','2025-04-01 11:11:59',1,4),(23,NULL,'ㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔ',1,'2025-04-01 09:35:42',NULL,1,34),(24,NULL,'ㅁ',1,'2025-04-09 12:05:09',NULL,1,34),(25,NULL,'ㅁ',1,'2025-04-09 12:05:14',NULL,1,34),(26,NULL,'ㅁ',1,'2025-04-09 12:05:19',NULL,1,3),(27,NULL,'ㅁ',1,'2025-04-09 12:05:24',NULL,1,2),(28,NULL,'ㅁ',1,'2025-04-09 12:05:29',NULL,1,34),(29,NULL,'ㅁ',1,'2025-04-09 12:05:35',NULL,1,34),(30,NULL,'ㅁ',1,'2025-04-09 12:05:40',NULL,1,34),(31,NULL,'ㅁ',1,'2025-04-09 12:05:44',NULL,1,34);
/*!40000 ALTER TABLE `code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `codegroup`
--

DROP TABLE IF EXISTS `codegroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `codegroup` (
  `seq` int unsigned NOT NULL AUTO_INCREMENT,
  `codeGroupCd` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `codeGroupNm` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `codeGroupUse` tinyint(1) NOT NULL,
  `codeGroupRegDateTime` datetime DEFAULT NULL,
  `codeGroupModDateTime` datetime DEFAULT NULL,
  `codeGroupDelNy` tinyint(1) NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `seq_UNIQUE` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `codegroup`
--

LOCK TABLES `codegroup` WRITE;
/*!40000 ALTER TABLE `codegroup` DISABLE KEYS */;
INSERT INTO `codegroup` VALUES (1,NULL,'통신사',1,NULL,NULL,0),(2,NULL,'작품.222',0,NULL,NULL,0),(3,'임시 코드','작품.333',1,NULL,NULL,0),(4,NULL,'작품.444',1,NULL,NULL,1),(5,'22222','등록.111111222',1,NULL,NULL,1),(6,'','등록.111333',1,NULL,'2025-03-31 14:08:47',1),(7,NULL,'',1,NULL,NULL,1),(9,'','이름.222222222',1,NULL,NULL,1),(10,'','이름.333333333333',0,NULL,NULL,1),(11,'1','이름.44444',0,NULL,NULL,0),(12,'ㅁ','ㅁ',1,NULL,NULL,1),(13,'ㄴ','ㄴ',1,NULL,NULL,1),(14,'5555','5555',1,NULL,NULL,1),(15,'6666','6666',1,NULL,NULL,1),(16,'6','6',1,NULL,NULL,1),(17,NULL,'7',1,NULL,NULL,1),(18,NULL,'8',1,NULL,NULL,1),(19,NULL,'9',1,NULL,NULL,1),(20,NULL,'9',1,NULL,NULL,1),(21,NULL,'1',1,NULL,NULL,1),(22,NULL,'2',1,NULL,NULL,1),(23,'','ㅡㅡㅡㅡ',1,'2025-03-28 16:29:38','2025-03-31 16:25:45',1),(24,'','z',1,'2025-03-28 17:45:18','2025-03-31 14:08:36',1),(25,NULL,'',1,'2025-03-31 13:48:23',NULL,1),(31,NULL,'',1,'2025-03-31 13:55:42',NULL,1),(33,'','',1,'2025-03-31 14:06:35','2025-03-31 14:07:11',1),(34,'','ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ',1,'2025-04-01 09:35:32','2025-04-01 11:11:30',1),(35,NULL,'uuuuuu',1,'2025-04-01 11:11:39',NULL,1),(36,'','등록.100',1,'2025-04-16 14:27:06','2025-04-16 14:27:11',1),(37,NULL,'등록.101',1,'2025-04-16 14:43:47',NULL,0),(38,NULL,'등록.102',1,'2025-04-16 14:44:05',NULL,0),(39,NULL,'등록1001',1,'2025-04-16 15:00:20','2025-04-16 15:00:57',1);
/*!40000 ALTER TABLE `codegroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fileuploaded`
--

DROP TABLE IF EXISTS `fileuploaded`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fileuploaded` (
  `seq` int unsigned NOT NULL AUTO_INCREMENT,
  `type` int NOT NULL,
  `sort` int NOT NULL,
  `path` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `originalName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uuidName` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ext` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `size` bigint NOT NULL,
  `delNy` tinyint(1) NOT NULL,
  `regDateTime` datetime NOT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fileuploaded`
--

LOCK TABLES `fileuploaded` WRITE;
/*!40000 ALTER TABLE `fileuploaded` DISABLE KEYS */;
INSERT INTO `fileuploaded` VALUES (1,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/669046a4-b0e4-4a4a-bddf-b746cb10211d_%EB%A9%94%EC%9D%BC%ED%85%8C%EC%8A%A4%ED%8A%B8.PNG','메일테스트.PNG','669046a4-b0e4-4a4a-bddf-b746cb10211d.PNG','PNG',34415,0,'2025-04-22 13:04:46'),(2,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/e5ac8512-0573-43a3-ba8d-49b762ca5196_%EB%A9%94%EC%9D%BC%ED%85%8C%EC%8A%A4%ED%8A%B8.PNG','메일테스트.PNG','e5ac8512-0573-43a3-ba8d-49b762ca5196.PNG','PNG',34415,0,'2025-04-22 13:08:08'),(3,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/09533ac1-f4c3-484f-b9c8-c5725141e0d0_%EB%A9%94%EC%9D%BC%ED%85%8C%EC%8A%A4%ED%8A%B8.PNG','메일테스트.PNG','09533ac1-f4c3-484f-b9c8-c5725141e0d0.PNG','PNG',34415,0,'2025-04-22 13:54:13'),(4,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/3d0e87f0-8fc6-4382-9018-5f5368bb52cf_HTML5_logo_and_wordmark.svg.png','HTML5_logo_and_wordmark.svg.png','3d0e87f0-8fc6-4382-9018-5f5368bb52cf.png','png',30317,0,'2025-04-22 15:54:59'),(5,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/d69134d0-7155-4f5a-bce0-07bce3aae87c_png-clipart-javascript-programmer-node-js-web-application-markup-language-angle-text.png','png-clipart-javascript-programmer-node-js-web-application-markup-language-angle-text.png','d69134d0-7155-4f5a-bce0-07bce3aae87c.png','png',6427,0,'2025-04-22 15:59:45'),(6,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/669d48fc-04b0-487e-a460-e561cb935eac_HTML5_logo_and_wordmark.svg.png','HTML5_logo_and_wordmark.svg.png','669d48fc-04b0-487e-a460-e561cb935eac.png','png',30317,0,'2025-04-22 16:02:32'),(7,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/357a9ef0-3aca-4843-a0c6-40c7f2d3ac22_HTML5_logo_and_wordmark.svg.png','HTML5_logo_and_wordmark.svg.png','357a9ef0-3aca-4843-a0c6-40c7f2d3ac22.png','png',30317,0,'2025-04-22 16:03:04'),(8,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/41ad5004-2e9d-4276-862d-962c9c7ad373_%EB%A9%94%EC%9D%BC%ED%85%8C%EC%8A%A4%ED%8A%B8.PNG','메일테스트.PNG','41ad5004-2e9d-4276-862d-962c9c7ad373.PNG','PNG',34415,0,'2025-04-22 16:03:54'),(9,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/c79235d0-1151-49ad-ab4e-e12ff659aaf7_CSS3_logo_and_wordmark.svg.png','CSS3_logo_and_wordmark.svg.png','c79235d0-1151-49ad-ab4e-e12ff659aaf7.png','png',27886,0,'2025-04-22 16:07:21'),(10,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/8bea237e-aea7-409c-833f-8ad4fea35568_%EB%A9%94%EC%9D%BC%ED%85%8C%EC%8A%A4%ED%8A%B8.PNG','메일테스트.PNG','8bea237e-aea7-409c-833f-8ad4fea35568.PNG','PNG',34415,0,'2025-04-22 16:10:30'),(11,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/4ad0d656-dd21-42e5-8c13-bd6c36100cf0_margherita-pizza.jpg','margherita-pizza.jpg','4ad0d656-dd21-42e5-8c13-bd6c36100cf0.jpg','jpg',912911,0,'2025-04-22 16:33:36'),(12,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/3ca78c86-9745-4158-a758-babc2e482dba_double-cheese-burger.jpg','double-cheese-burger.jpg','3ca78c86-9745-4158-a758-babc2e482dba.jpg','jpg',847576,0,'2025-04-22 16:36:02'),(13,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/00f3f233-2e0e-4ae4-a82f-23253b347dbd_mushroom-cheese-burger.jpg','mushroom-cheese-burger.jpg','00f3f233-2e0e-4ae4-a82f-23253b347dbd.jpg','jpg',676038,0,'2025-04-22 16:38:32'),(14,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/bc3cd81d-b53c-40ae-b41b-f45973354fda_pepperoni-pizza.jpg','pepperoni-pizza.jpg','bc3cd81d-b53c-40ae-b41b-f45973354fda.jpg','jpg',96672,0,'2025-04-22 16:47:11'),(15,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/b992d573-d388-4e78-bb90-80dbc784782a_bacon-cheese-burger.jpg','bacon-cheese-burger.jpg','b992d573-d388-4e78-bb90-80dbc784782a.jpg','jpg',51658,0,'2025-04-22 16:50:41'),(16,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/35dda954-783e-4df3-9361-0bc10d07c7bd_mushroom-fresh-burger.jpg','mushroom-fresh-burger.jpg','35dda954-783e-4df3-9361-0bc10d07c7bd.jpg','jpg',46380,0,'2025-04-22 16:53:51'),(17,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/e076c8d1-4e8a-43de-bc01-9ec520b1cbe6_featur-2.jpg','featur-2.jpg','e076c8d1-4e8a-43de-bc01-9ec520b1cbe6.jpg','jpg',32809,0,'2025-04-22 16:55:16'),(18,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/92e50325-7c3b-4087-9b61-da87fe9d5997_featur-3.jpg','featur-3.jpg','92e50325-7c3b-4087-9b61-da87fe9d5997.jpg','jpg',47885,0,'2025-04-22 16:55:50'),(19,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/e0d85fa2-e993-43c4-ab91-e5ef7d8e8c07_fruite-item-3.jpg','fruite-item-3.jpg','e0d85fa2-e993-43c4-ab91-e5ef7d8e8c07.jpg','jpg',26051,0,'2025-04-22 16:56:36'),(20,1,1,'https://nowonbucket.s3.ap-northeast-2.amazonaws.com/productImg/797bafb4-48d7-4f3c-b811-4d428e3a3eed_vegetable-item-3.png','vegetable-item-3.png','797bafb4-48d7-4f3c-b811-4d428e3a3eed.png','png',56672,0,'2025-04-22 16:57:12');
/*!40000 ALTER TABLE `fileuploaded` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `seq` int unsigned NOT NULL AUTO_INCREMENT,
  `menuNm` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `menuPrice` int NOT NULL,
  `menuDescription` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `calories` int NOT NULL,
  `sugars` int NOT NULL,
  `protein` int NOT NULL,
  `fat` int NOT NULL,
  `sodium` int NOT NULL,
  `menuType` int NOT NULL,
  `recommand` tinyint(1) NOT NULL,
  `menuNew` tinyint(1) DEFAULT NULL,
  `menuStock` int NOT NULL,
  `menuOrderCnt` int DEFAULT NULL,
  `menuRating` int DEFAULT NULL,
  `menuRegDateTime` datetime DEFAULT NULL,
  `menuModDateTime` datetime DEFAULT NULL,
  `menuDelNy` tinyint(1) NOT NULL,
  `fileUploaded_seq` int unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `seq_UNIQUE` (`seq`),
  KEY `fk_code_fileUploaded1_idx` (`fileUploaded_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'피자',10000,'설명@@@@@',300,10,10,10,10,1,1,1,20,NULL,NULL,NULL,NULL,1,0),(2,'1',1,'1',1,1,1,1,1,3,1,1,1,NULL,NULL,NULL,NULL,1,0),(3,'11',11,'11311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311311',11,11,11,11,11,1,1,0,11,NULL,NULL,NULL,NULL,1,0),(4,'2',2,'2',2,2,2,2,2,4,0,0,2,NULL,NULL,NULL,NULL,1,0),(5,'6',6,'6',6,6,6,6,6,3,1,1,6,NULL,NULL,NULL,NULL,1,0),(6,'66',6,'6',6,6666,6,6,66,1,1,1,66,NULL,NULL,NULL,NULL,1,0),(7,'44444444444444444',1,'1',1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,1,0),(8,'1',1,'1',1,1,1,1,1,2,1,1,1,NULL,NULL,NULL,NULL,1,0),(9,'33333333333333333333',1,'1',1,1,1,1,1,1,1,1,1,NULL,NULL,NULL,NULL,1,0),(10,'1',1,'1',1,1,1,1,1,2,1,1,1,NULL,NULL,NULL,NULL,1,0),(11,'2',2,'2',2,2,2,2,2,2,1,1,2,NULL,NULL,NULL,NULL,1,0),(12,'adfasdas',3131,'',21312,3,123,13,131,3,1,1,123123123,NULL,NULL,NULL,NULL,1,0),(14,'마르게리타',18000,'00000',1000,1,2,3,4,2,1,1,100,NULL,NULL,NULL,NULL,0,11),(15,'더블치즈버거',9000,'더블치즈버거',1000,2,3,4,5,2,0,1,50,NULL,NULL,'2025-04-09 11:23:40',NULL,0,12),(16,'머쉬룸치즈버거',11000,'머쉬룸치즈버거',800,10,20,30,400,1,1,1,100,NULL,NULL,'2025-04-16 14:29:42',NULL,0,13),(17,'페퍼로니 피자',19000,'페퍼로니 피자',2300,50,50,50,50,2,1,1,100,NULL,NULL,'2025-04-16 15:10:51',NULL,0,14),(18,'베이컨치즈버거',10000,'베이컨치즈버거',1300,100,100,100,100,2,1,0,300,NULL,NULL,'2025-04-16 15:12:37',NULL,0,15),(19,'머쉬룸프레쉬버거',9800,'머쉬룸프레쉬버거',900,10,10,10,10,2,1,1,101,NULL,NULL,'2025-04-22 13:08:08',NULL,0,16),(20,'딸기',20000,'딸기',2000,20,20,20,20,1,1,1,2000,NULL,NULL,'2025-04-22 13:54:13',NULL,0,17),(21,'브로콜리',3000,'브로콜리',300,30,30,30,3,3,1,1,3000,NULL,NULL,'2025-04-22 16:03:04',NULL,0,18),(22,'바나나',400000,'바나나',400,40,40,40,40,3,1,1,400,NULL,NULL,'2025-04-22 16:07:21',NULL,0,20);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordermenu`
--

DROP TABLE IF EXISTS `ordermenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordermenu` (
  `seq` int unsigned NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `orders_seq` int unsigned NOT NULL,
  `menu_seq` int unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `seq_UNIQUE` (`seq`),
  KEY `fk_orderMenu_order1_idx` (`orders_seq`),
  KEY `fk_orderMenu_menu1_idx` (`menu_seq`),
  CONSTRAINT `fk_orderMenu_menu1` FOREIGN KEY (`menu_seq`) REFERENCES `menu` (`seq`),
  CONSTRAINT `fk_orderMenu_order1` FOREIGN KEY (`orders_seq`) REFERENCES `orders` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordermenu`
--

LOCK TABLES `ordermenu` WRITE;
/*!40000 ALTER TABLE `ordermenu` DISABLE KEYS */;
INSERT INTO `ordermenu` VALUES (3,2,2,2),(4,2,2,3),(5,2,2,3),(6,2,2,4),(7,2,2,1),(8,2,20,1),(9,2,20,2),(10,2,20,3),(11,2,20,4),(12,2,20,5),(13,2,20,6),(14,1,26,20),(15,1,26,20),(16,3,26,20),(17,1,32,20),(18,1,32,20),(19,1,32,20),(20,3,32,20),(21,1,32,20);
/*!40000 ALTER TABLE `ordermenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `seq` int unsigned NOT NULL AUTO_INCREMENT,
  `ordersDate` datetime NOT NULL,
  `ordersStatus` int NOT NULL,
  `odTotalPrice` int NOT NULL DEFAULT '0',
  `ordersDelNy` tinyint(1) NOT NULL,
  `user_seq` int unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `seq_UNIQUE` (`seq`),
  KEY `fk_order_user1_idx` (`user_seq`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'2010-10-10 00:00:00',0,0,1,1),(3,'2000-10-10 00:00:00',1,0,0,1),(4,'2000-10-10 00:00:00',1,0,0,1),(5,'2000-10-10 00:00:00',1,0,0,1),(6,'2000-10-10 00:00:00',1,0,0,1),(8,'2000-10-10 00:00:00',1,0,0,1),(9,'2000-10-10 00:00:00',1,0,0,1),(10,'2000-10-10 00:00:00',1,0,0,1),(11,'2000-10-10 00:00:00',1,0,1,1),(12,'2000-10-10 00:00:00',2,0,0,1),(13,'2000-10-10 00:00:00',2,0,0,1),(14,'2000-10-10 00:00:00',1,0,0,1),(15,'2000-10-10 00:00:00',3,0,0,1),(16,'2000-10-10 00:00:00',2,0,0,1),(17,'2000-10-10 00:00:00',0,0,0,1),(20,'2000-10-10 00:00:00',0,0,0,1),(21,'2025-04-21 14:37:26',1,0,0,1),(22,'2025-04-23 11:30:34',1,0,0,20),(23,'2025-04-23 11:33:42',1,0,0,20),(24,'2025-04-23 11:36:04',1,0,0,20),(25,'2025-04-23 11:40:55',1,0,0,20),(26,'2025-04-23 11:58:05',1,0,0,20),(27,'2025-04-24 16:53:40',1,0,0,20),(28,'2025-04-24 17:09:06',1,0,0,20),(29,'2025-04-24 17:11:52',1,0,0,20),(30,'2025-04-24 17:12:42',1,0,0,20),(31,'2025-04-24 17:27:01',1,400000,0,20),(32,'2025-04-24 17:27:15',1,462400,0,20);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `seq` int unsigned NOT NULL AUTO_INCREMENT,
  `reviewDate` date NOT NULL,
  `reviewRate` int NOT NULL,
  `reviewDescription` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `reviewDelNy` tinyint(1) NOT NULL,
  `user_seq` int unsigned NOT NULL,
  `menu_seq` int unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `seq_UNIQUE` (`seq`),
  KEY `fk_review_user1_idx` (`user_seq`),
  KEY `fk_review_menu1_idx` (`menu_seq`),
  CONSTRAINT `fk_review_menu1` FOREIGN KEY (`menu_seq`) REFERENCES `menu` (`seq`),
  CONSTRAINT `fk_review_user1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'2000-10-10',5,'설명~~~~~~~~~~~~~~~~~~~',1,1,1),(3,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(4,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(5,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(6,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(7,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(8,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(9,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(10,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(11,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(12,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(13,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(14,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(15,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',0,1,1),(16,'2000-10-10',4,'설명~~~~~~~~~~~~~~~~~~~',1,1,1),(17,'2025-04-17',3,'리뷰 내용123123',0,1,2),(18,'2025-04-17',3,'리뷰 내용123123',0,1,14),(19,'2025-04-17',3,'리뷰 내용 567567',0,2,14),(20,'2025-04-18',5,'리뷰테스트100',0,20,15),(21,'2025-04-18',1,'리뷰테스트101',0,20,15),(22,'2025-04-21',4,'리뷰 테스트 101',0,20,18),(23,'2025-04-21',5,'리뷰테스트102',0,20,18),(24,'2025-04-22',5,'바나나 리뷰 테스트 100',0,20,22),(25,'2025-04-22',1,'바나나 리뷰 테스트 200',0,20,22);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `seq` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nickname` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastNm` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `firstNm` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` int NOT NULL,
  `userDob` date DEFAULT NULL,
  `mobileCarrier` int NOT NULL,
  `phone` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `userDelNy` tinyint(1) NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `seq_UNIQUE` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'111@gmail.com','111','111','1','11',1,NULL,1,'010-0000-0000',0,0),(2,'2','2','2','2','2',1,NULL,2,'010-2222-2222',0,0),(3,'3','3','3','3','3',3,NULL,3,'3',0,0),(4,'qwe@naver.com','qwe','qwe','qwe1','qwe2',1,NULL,1,'010-7894-7894',0,0),(5,'asd@gmail.com','asd','asd','홍','길동',2,NULL,9,'010-1122-3344',0,0),(6,'zzzzz','zzzzz','zzzzz','z','zz',2,NULL,1,'zzzzz',1,0),(7,'xxxxx','xxxxx','xxxxx','x','xx',3,NULL,2,'xxxxx',0,1),(8,'ccccc','ccccc','ccccc','c','ccc',1,NULL,3,'ccccc',0,1),(9,'1','1','1','1','1',1,NULL,1,'1',0,1),(10,'xxxxxxx','z','z','z','z',2,NULL,2,'z',1,1),(11,'d','d','d','d','d',3,NULL,3,'d',1,1),(12,'이메일','','닉네임','성','이름',2,NULL,3,'1',1,0),(13,'sample@example.com','12345678','임시 닉네임','홍','길동',1,'2025-03-06',1,'010-1234-5678',1,0),(14,'qqq','qq','qq','qq','qq',1,NULL,2,'qq',0,1),(15,'asdasfAS@ASFafasf','asfasfasfas','asfasf','asfasf','asfasf',3,'2025-04-21',10,'asfasfasf',0,0),(16,'sdf','sadf','fsadf','dfasd','sdfsa',2,'2025-04-01',10,'asdfsadf',0,0),(18,'afsdfasd','asdfasdf','sadfsadfas','sdfasdf','sadfsdf',1,'2025-04-11',3,'safdsaf',0,0),(19,'2','2','2','2','2',1,'1902-01-02',9,'2',0,0),(20,'ServiceAdmin@gmail.com','$2a$10$WBXkTTLo1BrmJinG5n8a/uvbVpHXiQUXGG8wrSxkFa0wS9bDdVlB6','닉네임','성','이름',1,'2025-04-08',2,'010-1234-1234',0,0),(21,'a','$2a$10$W95l/5Db0Wkgjp0Wub7.FesnQI3yQ6GyJRM4JkVC39BTFwhIEEoHy','a','a','a',1,'2025-04-09',1,'0',0,0),(22,'b','$2a$10$4OB6b94Xrej8k.zrfUMpiOMUpy2nZlYlV/yjpi0yvUopJpB2tq8pG','b','b','b',1,'2025-04-08',1,'1',0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-28 11:02:13
