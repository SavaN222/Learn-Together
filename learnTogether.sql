-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: learn_together
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `value` bit(1) DEFAULT NULL,
  `question_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKplq0bjdsn3i0eml0s28ap87ap` (`question_id`),
  CONSTRAINT `FKplq0bjdsn3i0eml0s28ap87ap` FOREIGN KEY (`question_id`) REFERENCES `quiz_question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'4',_binary '',1),(2,'3',NULL,1),(3,'2',NULL,1),(4,'1',NULL,1),(5,'10',NULL,2),(6,'3',NULL,2),(7,'25',_binary '',2),(8,'1',NULL,2),(9,'3.14',_binary '',3),(10,'4.14',NULL,3),(11,'5.14',NULL,3),(12,'2.14',NULL,3),(33,'Michael Jordan',NULL,9),(34,'Kobe Bryant',_binary '',9),(35,'Brian Scalabrine',NULL,9),(36,'LeBron James',NULL,9),(37,'4',NULL,10),(38,'8',NULL,10),(39,'10',NULL,10),(40,'12',_binary '',10),(41,'David Robinson',_binary '',11),(42,'Russel Westbrook',NULL,11),(43,'Hakeem Olajuwon',NULL,11),(44,'Michael Jordan',NULL,11),(45,'4',_binary '',12),(46,'5',NULL,12),(47,'1',NULL,12),(48,'3',NULL,12),(49,'1',NULL,13),(50,'2',NULL,13),(51,'3',NULL,13),(52,'25',_binary '',13);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'BEOGRAD'),(2,'BOR'),(3,'CACAK'),(4,'JAGODINA'),(5,'KOSOVSKA MITROVICA'),(6,'KRAGUJEVAC'),(7,'KRALJEVO'),(8,'LEPOSAVIC'),(9,'LESKOVAC'),(10,'NIS'),(11,'NOVI PAZAR'),(12,'NOVI SAD'),(13,'SOMBOR'),(14,'SUBOTICA'),(15,'UZICE'),(16,'VRANJE'),(17,'VRNJACKA BANJA'),(18,'ZRENJANIN'),(19,'ZUBIN POTOK'),(20,'ZVECAN'),(21,'ALEKSINAC'),(22,'ARANDJELOVAC'),(23,'BLACE'),(24,'CUPRIJA'),(25,'GNJILANE'),(26,'KIKINDA'),(27,'KRUSEVAC'),(28,'PIROT'),(29,'PROKUPLJE'),(30,'SABAC'),(31,'SREMSKA MITROVICA'),(32,'TRSTENIK'),(33,'VALJEVO'),(34,'VRSAC'),(35,'BACKA TOPOLA'),(36,'POZAREVAC'),(37,'SREMSKA KAMENICA'),(38,'MLADENOVAC'),(39,'SREMSKI KARLOVCI'),(40,'SVILAJNAC'),(41,'PANCEVO'),(42,'ZAJECAR'),(43,'ZEMUN');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_post`
--

DROP TABLE IF EXISTS `comment_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext COLLATE utf8mb4_general_ci,
  `edited` bit(1) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh53mlyacf2ky34mmj4r20htum` (`student_id`),
  KEY `FK46o4sw5i0ykgcca4gfbr63l9q` (`post_id`),
  CONSTRAINT `FK46o4sw5i0ykgcca4gfbr63l9q` FOREIGN KEY (`post_id`) REFERENCES `post_university` (`id`),
  CONSTRAINT `FKh53mlyacf2ky34mmj4r20htum` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_post`
--

LOCK TABLES `comment_post` WRITE;
/*!40000 ALTER TABLE `comment_post` DISABLE KEYS */;
INSERT INTO `comment_post` VALUES (5,'Wow amazing',_binary '\0','UNSEEN',10,8);
/*!40000 ALTER TABLE `comment_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_study`
--

DROP TABLE IF EXISTS `comment_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_study` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext COLLATE utf8mb4_general_ci,
  `edited` bit(1) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `question_study_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqacfdd17doj9fhwtiq4bdjpt4` (`student_id`),
  KEY `FKrot8kaurggdcpuwesvki7kpws` (`question_study_id`),
  CONSTRAINT `FKqacfdd17doj9fhwtiq4bdjpt4` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKrot8kaurggdcpuwesvki7kpws` FOREIGN KEY (`question_study_id`) REFERENCES `question_study` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_study`
--

LOCK TABLES `comment_study` WRITE;
/*!40000 ALTER TABLE `comment_study` DISABLE KEYS */;
INSERT INTO `comment_study` VALUES (18,'Did you check kupujemprodajem?',_binary '\0','SEEN',2,16),(19,'No, thank you. ?',_binary '\0','UNSEEN',12,16),(20,'UpWork',_binary '\0','UNSEEN',12,17);
/*!40000 ALTER TABLE `comment_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `action_user_id` int DEFAULT NULL,
  `student_higher_id` int DEFAULT NULL,
  `student_lower_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtnefesxwqhacwxero1edyid95` (`action_user_id`),
  KEY `FKtcfeaw8c7m7sjfxgtl612uppp` (`student_higher_id`),
  KEY `FKs32lvtgcytvvqc282mypkitsd` (`student_lower_id`),
  CONSTRAINT `FKs32lvtgcytvvqc282mypkitsd` FOREIGN KEY (`student_lower_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FKtcfeaw8c7m7sjfxgtl612uppp` FOREIGN KEY (`student_higher_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FKtnefesxwqhacwxero1edyid95` FOREIGN KEY (`action_user_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,'ACCEPT',1,2,1),(8,'ACCEPT',3,3,2),(14,'ACCEPT',2,12,2),(15,'ACCEPT',10,12,10),(16,'ACCEPT',2,10,2);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_post`
--

DROP TABLE IF EXISTS `like_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9hks3w5yx5oiy8fq5ud4avoxs` (`student_id`),
  KEY `FK1uohn32m0ys3wrfn73k1w4xqy` (`post_id`),
  CONSTRAINT `FK1uohn32m0ys3wrfn73k1w4xqy` FOREIGN KEY (`post_id`) REFERENCES `post_university` (`id`),
  CONSTRAINT `FK9hks3w5yx5oiy8fq5ud4avoxs` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_post`
--

LOCK TABLES `like_post` WRITE;
/*!40000 ALTER TABLE `like_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_study`
--

DROP TABLE IF EXISTS `like_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_study` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `question_study_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp7q57x6l0di8v8t30ck2nempu` (`student_id`),
  KEY `FKi84um5l5rayh1smpsyepvq73m` (`question_study_id`),
  CONSTRAINT `FKi84um5l5rayh1smpsyepvq73m` FOREIGN KEY (`question_study_id`) REFERENCES `question_study` (`id`),
  CONSTRAINT `FKp7q57x6l0di8v8t30ck2nempu` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_study`
--

LOCK TABLES `like_study` WRITE;
/*!40000 ALTER TABLE `like_study` DISABLE KEYS */;
INSERT INTO `like_study` VALUES (13,2,16),(14,12,17);
/*!40000 ALTER TABLE `like_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_university`
--

DROP TABLE IF EXISTS `post_university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_university` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` longtext COLLATE utf8mb4_general_ci,
  `edited` bit(1) DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `university_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkp886u9eruoy04wxx0g7buukh` (`student_id`),
  KEY `FK5p0fd035v4ney5ydmxolsg3la` (`university_id`),
  CONSTRAINT `FK5p0fd035v4ney5ydmxolsg3la` FOREIGN KEY (`university_id`) REFERENCES `university` (`id`),
  CONSTRAINT `FKkp886u9eruoy04wxx0g7buukh` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_university`
--

LOCK TABLES `post_university` WRITE;
/*!40000 ALTER TABLE `post_university` DISABLE KEYS */;
INSERT INTO `post_university` VALUES (8,'This is only visible for RAF students',_binary '\0',2,78);
/*!40000 ALTER TABLE `post_university` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_study`
--

DROP TABLE IF EXISTS `question_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_study` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` longtext COLLATE utf8mb4_general_ci,
  `edited` bit(1) DEFAULT NULL,
  `title` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `study_field_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK744mdlmf9sesxtbchdlw9tr1j` (`student_id`),
  KEY `FKdh0ifshu6gjwc8m63wmb70nyy` (`study_field_id`),
  CONSTRAINT `FK744mdlmf9sesxtbchdlw9tr1j` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKdh0ifshu6gjwc8m63wmb70nyy` FOREIGN KEY (`study_field_id`) REFERENCES `study_field` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_study`
--

LOCK TABLES `question_study` WRITE;
/*!40000 ALTER TABLE `question_study` DISABLE KEYS */;
INSERT INTO `question_study` VALUES (16,'Can someone give me a list of best computer up to 500euros? ?',_binary '','Best laptops for 500e',12,25),(17,'Which is the best website for freelancing?',_binary '\0','Freelance???',2,25);
/*!40000 ALTER TABLE `question_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6awm9kbqny41efdoaol63uc4y` (`student_id`),
  CONSTRAINT `FK6awm9kbqny41efdoaol63uc4y` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (1,'Math quiz',1),(5,'NBA QUIZ',4),(7,'MATH 2',2);
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_question`
--

DROP TABLE IF EXISTS `quiz_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `quiz_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdtynvfjgh6e7fd8l0wk37nrpc` (`quiz_id`),
  CONSTRAINT `FKdtynvfjgh6e7fd8l0wk37nrpc` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_question`
--

LOCK TABLES `quiz_question` WRITE;
/*!40000 ALTER TABLE `quiz_question` DISABLE KEYS */;
INSERT INTO `quiz_question` VALUES (1,'2 + 2 =',1),(2,'5 * 5 =',1),(3,'Number PI is:',1),(9,'Which player has nickname \"Black Mamba\"',5),(10,'How many NBA franchises have never won a championship?',5),(11,'Last player to get quadruple double?',5),(12,'2 + 2',7),(13,'5 * 5',7);
/*!40000 ALTER TABLE `quiz_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `profile_pic` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `study_field_id` int DEFAULT NULL,
  `university_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKru964th5p2qpd72at1v1ft9y5` (`study_field_id`),
  KEY `FK157t7rer269uuhfphq1mcd7y9` (`university_id`),
  CONSTRAINT `FK157t7rer269uuhfphq1mcd7y9` FOREIGN KEY (`university_id`) REFERENCES `university` (`id`),
  CONSTRAINT `FKru964th5p2qpd72at1v1ft9y5` FOREIGN KEY (`study_field_id`) REFERENCES `study_field` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Basketball Player ?\r\nFront-End developer ?‍?','nakaradasava@gmail.com',_binary '','Male','$2a$10$FFcsmAUJokVoDZzUwclqJuaUH5b8FSo2k8iVcnLiFgiZZfXs9RZj6','/images/profile_pictures/male.png','student','John',25,78),(2,'Back-End developer ?‍?','savanakarada22@gmail.com',_binary '','Male','$2a$10$tBgwiwl6HtuP6qgm.RVpG.0t4qKrkMry2e5aD5QBiRMFc5EpIJBdi','/images/user-photos/2/IMG_20200708_114736_521.jpg','student','Sava',25,78),(3,NULL,'tester@gmail.com',_binary '','Female','$2a$10$Xc5lNRMYKj5qL6GH2Vn62.la2ogRT11FJlj8J1sQwn/cWTmzDHGb6','/images/profile_pictures/female.png','student','tester',25,78),(4,NULL,'test1508@gmail.com',_binary '','Male','$2a$10$u7pHlDfpWjuhSHG7kL0qlO80Z57JqvOt9z4Wy7yKwDSk/8PqNt3Qm','/images/profile_pictures/male.png','student','Test',38,8),(8,NULL,'savanakarada1508@gmail.com',_binary '','Male','$2a$10$XJ3lJpVbaKdrGjP8XEOYvuisq8FUtfiSp0ejiqgxRqjgQRY2aEfHC','/images/profile_pictures/male.png','student','SavaN1508',30,156),(9,'','jessepickman321@gmail.com',_binary '','Male','$2a$10$V7gAX.SwlQRbedUL7Glpc.hR/FHDiY.fykjy.g0cvQZNyeLroMODG','/images/user-photos/9/lockscreen.jpg','student','Jesse',30,156),(10,NULL,'maletester@gmail.com',_binary '','Male','$2a$10$Yqo3shOngnLN2KgYiLNvue5C9o27.epMwCUPyJDxYzUcgBuEctwIS','/images/profile_pictures/male.png','student','male_tester',25,78),(11,NULL,'femaletester@gmail.com',_binary '','Female','$2a$10$1f.MSia1ZWe45L93RgY4CukClQyoa.cPBDZP.5CJs1iotV0nQUWmm','/images/profile_pictures/female.png','student','female_tester',35,78),(12,'','gahahv@hhshshs.com',_binary '','Female','$2a$10$xlx8POFuXj5DTjl/VuNvoufRIokqVOufKZWOmtu/6bZE71QmvTkHa','/images/user-photos/12/svgA9715694077794288.png','student','Emilija',11,40);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_like_post`
--

DROP TABLE IF EXISTS `student_like_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_like_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ieqt8tlb5jqw75bn19x0ljqe` (`student_id`),
  KEY `FK5t3x477hkxyf4ep1kxmox9n1e` (`post_id`),
  CONSTRAINT `FK5t3x477hkxyf4ep1kxmox9n1e` FOREIGN KEY (`post_id`) REFERENCES `student_post` (`id`),
  CONSTRAINT `FK8ieqt8tlb5jqw75bn19x0ljqe` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_like_post`
--

LOCK TABLES `student_like_post` WRITE;
/*!40000 ALTER TABLE `student_like_post` DISABLE KEYS */;
INSERT INTO `student_like_post` VALUES (7,2,8),(8,12,8);
/*!40000 ALTER TABLE `student_like_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_post`
--

DROP TABLE IF EXISTS `student_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` longtext COLLATE utf8mb4_general_ci,
  `edited` bit(1) DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2fobyd1nj9883q075u46tnw32` (`student_id`),
  CONSTRAINT `FK2fobyd1nj9883q075u46tnw32` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_post`
--

LOCK TABLES `student_post` WRITE;
/*!40000 ALTER TABLE `student_post` DISABLE KEYS */;
INSERT INTO `student_post` VALUES (8,'Hello everybody!!!',_binary '\0',10),(9,'Did you check my NBA quiz??\r\nhttp://localhost:8080/quiz/5/question/?questionId=0',_binary '\0',2);
/*!40000 ALTER TABLE `student_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_post_comment`
--

DROP TABLE IF EXISTS `student_post_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_post_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext COLLATE utf8mb4_general_ci,
  `edited` bit(1) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `student_post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5i3wxn521f0oit5r9u423vjte` (`student_id`),
  KEY `FKvuuexlatt9r4ytc49iwcubab` (`student_post_id`),
  CONSTRAINT `FK5i3wxn521f0oit5r9u423vjte` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKvuuexlatt9r4ytc49iwcubab` FOREIGN KEY (`student_post_id`) REFERENCES `student_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_post_comment`
--

LOCK TABLES `student_post_comment` WRITE;
/*!40000 ALTER TABLE `student_post_comment` DISABLE KEYS */;
INSERT INTO `student_post_comment` VALUES (5,'Hi!!',_binary '','UNSEEN',2,8),(6,'Hii!',_binary '\0','UNSEEN',12,8),(7,'to hard man!!!',_binary '\0','UNSEEN',10,9);
/*!40000 ALTER TABLE `student_post_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study_field`
--

DROP TABLE IF EXISTS `study_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_field` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_field`
--

LOCK TABLES `study_field` WRITE;
/*!40000 ALTER TABLE `study_field` DISABLE KEYS */;
INSERT INTO `study_field` VALUES (1,'Anthropology'),(2,'Archaeology'),(3,'History'),(4,'Linguistics and languages'),(5,'Philosophy'),(6,'Religion'),(7,'Culinary arts'),(8,'Literature'),(9,'Performing arts'),(10,'Visual arts'),(11,'Economics'),(12,'Geography'),(13,'Area studies'),(14,'Ethnic and cultural studies'),(15,'Gender and sexuality studies'),(16,'Organizational studies'),(17,'Political science'),(18,'Psychology'),(19,'Sociology'),(20,'Biology'),(21,'Chemistry'),(22,'Earth sciences'),(23,'Physics'),(24,'Astronomy'),(25,'Computer sciences'),(26,'Logic'),(27,'Pure mathematics'),(28,'Applied mathematics'),(29,'Systems science'),(30,'Agriculture'),(31,'Architecture and design'),(32,'Business'),(33,'Divinity'),(34,'Education'),(35,'Engineering and technology'),(36,'Environmental studies and forestry'),(37,'Family and consumer science'),(38,'Human physical performance and recreation'),(39,'Journalism, media studies and communication'),(40,'Law'),(41,'Library and museum studies'),(42,'Medicine'),(43,'Military sciences'),(44,'Public administration'),(45,'Social work'),(46,'Transportation');
/*!40000 ALTER TABLE `study_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `confirmation_token` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1j9myk0fjdutkio0tf3mne3u9` (`student_id`),
  CONSTRAINT `FK1j9myk0fjdutkio0tf3mne3u9` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `university` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `short_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `city_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK70cg9iket7hgqy26vs3d652uw` (`city_id`),
  CONSTRAINT `FK70cg9iket7hgqy26vs3d652uw` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
INSERT INTO `university` VALUES (1,'Fakultet za fizičku hemiju','FFH','www.ffh.bg.ac.rs',1),(2,'Matematički fakultet','MATF','www.matf.bg.ac.rs',1),(3,'Ekonomski fakultet','EKOF','www.ekof.bg.ac.rs',1),(4,'Arhitektonski fakultet','ARH-BG','http://www.arh.bg.ac.rs/en/',1),(5,'Fakultet za specijalnu edukaciju i rehabilitaciju','FASPER','http://www.fasper.bg.ac.rs/',1),(6,'Fakultet muzičke umetnosti','FMU','https://www.fmu.bg.ac.rs/en/',1),(7,'Fizički fakultet','FF-BG','http://www.ff.bg.ac.rs/',1),(8,'Fakultet sporta i fizičkog vaspitanja','DIF','http://www.dif.bg.ac.rs/',1),(9,'Filološki fakultet','FIL','http://www.fil.bg.ac.rs/',1),(10,'Građevinski fakultet','GRF','https://www.grf.bg.ac.rs/home',1),(11,'Medicinski fakultet','MED-BG','http://med.bg.ac.rs/?page_id=14465&lang=en',1),(12,'Saobraćajni fakultet','SF-BG','https://www.sf.bg.ac.rs/index.php/sr-rs/',1),(13,'Biološki fakultet','BIO-BG','https://www.bio.bg.ac.rs/',1),(14,'Fakultet veterinarske medicine','VET-BG','http://www.vet.bg.ac.rs/en',1),(15,'Fakultet organizacionih nauka','FON','http://www.fon.bg.ac.rs/',1),(16,'Filozofski fakultet','F-BG','http://www.f.bg.ac.rs/',1),(17,'Hemijski fakultet','CHEM-BG','https://www.chem.bg.ac.rs/',1),(18,'Pravni fakultet','IUS-BG','http://www.ius.bg.ac.rs/',1),(19,'Stomatološki fakultet','STOMF','https://www.stomf.bg.ac.rs/',1),(20,'Učiteljski fakultet','UF-BG','http://www.uf.bg.ac.rs/',1),(21,'Vojna akademija','VA-BG','http://www.va.mod.gov.rs/',1),(22,'Fakultet dramskih umetnosti','FDU-BG','https://fdu.bg.ac.rs/',1),(23,'Fakultet političkih nauka','FPN','https://www.fpn.bg.ac.rs/',1),(24,'Mašinski fakultet','MAS-BG','https://www.mas.bg.ac.rs/',1),(25,'Kriminalističko policijska akademija','KPA','https://www.kpu.edu.rs/cms/',1),(26,'Pravoslavni bogoslovski fakultet','BFSPC','https://bfspc.bg.ac.rs/en/',1),(27,'Poljoprivredni fakultet','AGRIF-BG','http://www.agrif.bg.ac.rs/',1),(28,'Šumarski fakultet','SFB','http://www.sfb.bg.ac.rs/',1),(29,'Vojnomedicinska akademija','VMA','http://www.vma.mod.gov.rs/',1),(30,'Elektrotehnički fakultet','ETF','https://www.etf.bg.ac.rs/#gsc.tab=0',1),(31,'Fakultet likovnih umetnosti','FLU','https://flu.bg.ac.rs/',1),(32,'Fakultet primenjenih umetnosti','FPU','https://www.fpu.bg.ac.rs/',1),(33,'Fakultet bezbednosti','FB','www.fb.bg.ac.rs',1),(34,'Farmaceutski fakultet','PHAR-BG','http://www.pharmacy.bg.ac.rs/en/',1),(35,'Geografski fakultet','GEF','http://www.gef.bg.ac.rs/en/',1),(36,'Rudarsko-geološki fakultet','RGF','https://rgf.bg.ac.rs/',1),(37,'Tehnološko-metalurški fakultet','TMF','http://www.tmf.bg.ac.rs/en#gsc.tab=0',1),(38,'Fakultet za primenjeni menadžment, ekonomiju i finansije',NULL,'https://mef.edu.rs/',1),(39,'Fakultet savremenih umetnosti','MEF','www.fsu.edu.rs',1),(40,'Singidunum','Sing-BG','http://eng.singidunum.ac.rs/',1),(41,'Visoka škola modernog biznisa','MBS','https://mbs.edu.rs/',1),(42,'Fakultet za projektni i inovacioni menadžment','PMC','http://pmc.edu.rs/',1),(43,'Akademija umetnosti',NULL,NULL,1),(44,'Beogradska bankarska akademija',NULL,NULL,1),(45,'Fakultet digitalnih umetnosti',NULL,NULL,1),(46,'Fakultet informacionih tehnologija – Metropolitan univerzitet',NULL,NULL,1),(47,'Fakultet informacionih tehnologija – Alfa univerzitet',NULL,NULL,1),(48,'Fakultet za pravo, javnu upravu i bezbednost',NULL,NULL,1),(49,'Fakultet za ekologiju i zaštitu životne sredine',NULL,NULL,1),(50,'Fakultet za ekonomiju i političke nauke',NULL,NULL,1),(51,'Fakultet za ekonomiju, finansije i administraciju FEFA',NULL,NULL,1),(52,'Fakultet za evropski biznis i marketing',NULL,NULL,1),(53,'Fakultet za graditeljski menadžment',NULL,NULL,1),(54,'Fakultet za informatiku i računarstvo',NULL,NULL,1),(55,'Fakultet za inženjerski internacionalni menadžment',NULL,NULL,1),(56,'Fakultet za fizičku kulturu i menadžment u sportu',NULL,NULL,1),(57,'Fakultet za kompjuterske nauke',NULL,NULL,1),(58,'Fakultet za kulturu i medije',NULL,NULL,1),(59,'Fakultet za medije i komunikacije – FMK',NULL,NULL,1),(60,'Tehnički fakultet',NULL,NULL,1),(61,'Fakultet za menadžment FM',NULL,NULL,1),(62,'Fakultet za menadžment u sportu',NULL,NULL,1),(63,'Fakultet za menadžment nekretnina',NULL,NULL,1),(64,'Fakultet za međunarodnu ekonomiju',NULL,NULL,1),(65,'Fakultet za poslovne studije',NULL,NULL,1),(66,'Fakultet za poslovne studije i pravo',NULL,NULL,1),(67,'Fakultet za preduzetnički biznis',NULL,NULL,1),(68,'Fakultet za primenjenu ekologiju',NULL,NULL,1),(69,'Fakultet za strateški i operativni menadžment',NULL,NULL,1),(70,'Fakultet za sport',NULL,NULL,1),(71,'Fakultet za strane jezike',NULL,NULL,1),(72,'Fakultet za turistički i hotelijerski menadžment',NULL,NULL,1),(73,'Fakultet za trgovinu i bankarstvo',NULL,NULL,1),(74,'Fakultet za umetnost i dizajn',NULL,NULL,1),(75,'NOVA akademija umetnosti',NULL,NULL,1),(76,'Poslovni fakultet',NULL,NULL,1),(77,'Pravni fakultet',NULL,NULL,1),(78,'Računarski fakultet','RAF','https://raf.edu.rs/',1),(79,'SAE Institut',NULL,NULL,1),(80,'Visoka škola strukovnih studija',NULL,NULL,1),(81,'Akademija poslovnih strukovnih studija',NULL,NULL,1),(82,'Tehnikum Taurunum – Visoka inženjerska škola strukovnih studija',NULL,NULL,1),(83,'Visoka škola elektrotehnike i računarstva strukovnih studija',NULL,NULL,1),(84,'Visoka škola – Akademija Srpske Pravoslavne Crkve za umetnost i konservaciju',NULL,NULL,1),(85,'Visoka škola strukovnih studija za informacione i komunikacione tehnologije',NULL,NULL,1),(86,'Visoka građevinsko–geodetska škola strukovnih studija',NULL,NULL,1),(87,'Visoka turistička škola strukovnih studija',NULL,NULL,1),(88,'Visoka tehnička škola strukovnih studija  ',NULL,NULL,1),(89,'Visoka hotelijerska škola strukovnih studija',NULL,NULL,1),(90,'Visoka škola likovnih i primenjenih umetnosti strukovnih studija',NULL,NULL,1),(91,'Visoka zdravstvena škola strukovnih studija',NULL,NULL,1),(92,'Visoka železnička škola strukovnih studija',NULL,NULL,1),(93,'Visoka tekstilna strukovna škola za dizajn, tehnologiju i menadžment',NULL,NULL,1),(94,'Visoka medicinska škola strukovnih studija “Milutin Milanković”',NULL,NULL,1),(95,'Visoka zdravstveno-sanitarna škola strukovnih studija',NULL,NULL,1),(96,'Visoka škola strukovnih studija za informacione tehnologije',NULL,NULL,1),(97,'Visoka strukovna škola za preduzetništvo',NULL,NULL,1),(98,'Visoka škola strukovnih studija – Sportska akademija',NULL,NULL,1),(99,'Institut za Estetiku i Kozmetologiju “LE PRESTIGE”',NULL,NULL,1),(100,'Visoka brodarska škola akademskih studija',NULL,NULL,1),(101,'Aquatonale Beauty Academy',NULL,NULL,1),(102,'Akademija fudbala',NULL,NULL,1),(103,'Akademija mode i dizajna',NULL,NULL,1),(104,'Akademija za diplomatiju i bezbednost',NULL,NULL,1),(105,'English School of Business',NULL,NULL,1),(106,'Mod`Art International',NULL,NULL,1),(107,'Visoka poslovna škola – Megatrend',NULL,NULL,1),(108,'Visoka škola za ekonomiju i upravu',NULL,NULL,1),(109,'Visoka škola za poslovnu ekonomiju i preduzetništvo',NULL,NULL,1),(110,'Visoka sportska i zdravstvena škola',NULL,NULL,1),(111,'Visoka strukovna škola za propagandu i odnose sa javnošću',NULL,NULL,1),(112,'Tehnološki fakultet',NULL,NULL,12),(113,'Prirodno-matematički fakultet',NULL,NULL,12),(114,'Akademija umetnosti',NULL,NULL,12),(115,'Fakultet sporta i fizičkog vaspitanja',NULL,NULL,12),(116,'Fakultet tehničkih nauka',NULL,NULL,12),(117,'Filozofski fakultet',NULL,NULL,12),(118,'Ekonomski fakultet',NULL,NULL,12),(119,'Medicinski fakultet',NULL,NULL,12),(120,'Poljoprivredni fakultet',NULL,NULL,12),(121,'Pravni fakultet',NULL,NULL,12),(122,'Fakultet za ekonomiju i inženjerski menadžment',NULL,NULL,12),(123,'Pravni fakultet za privredu i pravosuđe',NULL,NULL,12),(124,'Farmaceutski fakultet',NULL,NULL,12),(125,'Fakultet za evropske pravno-političke studije',NULL,NULL,12),(126,'Fakultet za pravne i poslovne studije',NULL,NULL,12),(127,'Univerzitet Singidunum',NULL,NULL,12),(128,'Visoka poslovna škola strukovnih studija',NULL,NULL,12),(129,'Visoka škola strukovnih studija za obrazovanje vaspitača',NULL,NULL,12),(130,'Visoka poslovna škola strukovnih studija',NULL,NULL,12),(131,'Visoka škola strukovnih studija za obrazovanje vaspitača',NULL,NULL,12),(132,'Tehnički fakultet u Boru',NULL,NULL,2),(133,'Fakultet tehničkih nauka',NULL,NULL,3),(134,'Agronomski Fakultet',NULL,NULL,3),(135,'Visoka škola tehničkih strukovnih studija',NULL,NULL,3),(136,'Visoka poslovna škola strukovnih studija',NULL,NULL,3),(137,'Fakultet pedagoških nauka',NULL,NULL,4),(138,'Fakultet pedagoških nauka',NULL,NULL,7),(139,'Učiteljski fakultet u Prizrenu',NULL,NULL,8),(140,'Fakultet za sport i fizičko vaspitanje',NULL,NULL,8),(141,'Visoka ekonomska škola strukovnih studija u Peći sa privremenim sedištem u Leposaviću',NULL,NULL,8),(142,'Visoka tehnička škola strukovnih studija iz Uroševca sa privremenim sedištem u Leposaviću',NULL,NULL,8),(143,'Tehnološki fakultet',NULL,NULL,9),(144,'Visoka tehnološko umetnička strukovna školat',NULL,NULL,9),(145,'Visoka poslovna škola strukovnih studija',NULL,NULL,9),(146,'Pedagoški fakultet',NULL,NULL,13),(147,'Ekonomski fakultet',NULL,NULL,14),(148,'Građevinski fakultet',NULL,NULL,14),(149,'Učiteljski fakultet na mađarskom nastavnom jeziku',NULL,NULL,14),(150,'Pedagoški fakultet',NULL,NULL,15),(151,'Pedagoški fakultet',NULL,NULL,16),(152,'Fakultet za hotelijerstvo i turizam',NULL,NULL,17),(153,'Tehnički Fakultet Mihajlo pupin',NULL,NULL,18),(154,'Poljoprivredni fakultet',NULL,NULL,19),(155,'Fakultet umetnosti',NULL,NULL,20),(156,'Fakultet za biofarming',NULL,NULL,35),(157,'Fakultet za poslovne studije',NULL,NULL,36),(158,'Fakultet za poslovno industrijski menadžment',NULL,NULL,38),(159,'Stomatološki fakultet',NULL,NULL,41),(160,'Fakultet za menadžment',NULL,NULL,39),(161,'Fakultet ekološke poljoprivrede',NULL,NULL,40),(162,'Poslovni fakultet',NULL,NULL,33),(163,'Fakultet za poslovnu ekonomiju',NULL,NULL,33),(164,'Fakultet za poslovne studije',NULL,NULL,34),(165,'Fakultet za menadžment',NULL,NULL,43),(166,'Fakultet zaštite životne sredine',NULL,NULL,37),(167,'Fakultet za uslužni biznis',NULL,NULL,37),(168,'Fakultet za digitalnu produkciju',NULL,NULL,37),(169,'Fakultet za primenjenu bezbednost',NULL,NULL,37),(170,'Fakultet poslovne ekonomije',NULL,NULL,37),(171,'Akademija klasičnog slikarstva',NULL,NULL,37),(172,'Fakultet informacionih tehnologija',NULL,NULL,37),(173,'Fakultet tehničkih nauka',NULL,NULL,5),(174,'Ekonomski fakultet',NULL,NULL,5),(175,'Medicinski fakultet',NULL,NULL,5),(176,'Pravni fakultet',NULL,NULL,5),(177,'Prirodno-matematički fakultet',NULL,NULL,5),(178,'Filozofski fakultet',NULL,NULL,5),(179,'Departman za filozofske nauke',NULL,NULL,11),(180,'Departman za filološke nauke',NULL,NULL,11),(181,'Departman za hemijsko-tehnološke nauke',NULL,NULL,11),(182,'Departman za biomedicinske nauke',NULL,NULL,11),(183,'Departman za umetnost',NULL,NULL,11),(184,'Departman za pravne nauke',NULL,NULL,11),(185,'Departman za matematičke nauke',NULL,NULL,11),(186,'Departman za ekonomske nauke',NULL,NULL,11),(187,'Departman za tehničke nauke',NULL,NULL,11),(188,'Departman za filološke nauke',NULL,NULL,11),(189,'Departman za pedagoško-psihološke nauke',NULL,NULL,11),(190,'Departman za ekonomske nauke',NULL,NULL,11),(191,'Departman za pravne nauke',NULL,NULL,11),(192,'Departman za računarske nauke',NULL,NULL,11),(193,'Departman za umetnost',NULL,NULL,11),(194,'Pravni fakultet',NULL,NULL,6),(195,'Ekonomski fakultet',NULL,NULL,6),(196,'Fakultet inženjerskih nauka',NULL,NULL,6),(197,'Fakultet medicinskih nauka',NULL,NULL,6),(198,'Filološko-umetnički fakultet',NULL,NULL,6),(199,'Prirodno-matematički fakultet',NULL,NULL,6),(200,'Ekonomski fakultet',NULL,NULL,10),(201,'Građevinsko–arhitektonski fakultet',NULL,NULL,10),(202,'Elektronski fakultet',NULL,NULL,10),(203,'Fakultet umetnosti',NULL,NULL,10),(204,'Fakultet zaštite na radu',NULL,NULL,10),(205,'Fakultet sporta i fizičkog vaspitanja',NULL,NULL,10),(206,'Filozofski fakultet',NULL,NULL,10),(207,'Mašinski fakultet',NULL,NULL,10),(208,'Mašinski fakultet',NULL,NULL,10),(209,'Pravni fakultet',NULL,NULL,10),(210,'Prirodno–matematički fakultet',NULL,NULL,10),(211,'Fakultet za pravne i poslovne studije “Dr Lazar Vrkatić”',NULL,NULL,10),(212,'Pravni fakultet za privredu i pravosuđe',NULL,NULL,10),(213,'Univerzitet Singidunum – Centar u Nišu',NULL,NULL,10);
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-29 21:14:09
