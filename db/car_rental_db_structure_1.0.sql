-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: car_rental
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
-- Table structure for table `branches`
--

DROP TABLE IF EXISTS `branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branches` (
                            `branch_id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(45) COLLATE utf8_bin NOT NULL,
                            `city` varchar(45) COLLATE utf8_bin NOT NULL,
                            `address` varchar(45) COLLATE utf8_bin NOT NULL,
                            `additional_info` varchar(200) COLLATE utf8_bin DEFAULT NULL,
                            PRIMARY KEY (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branches`
--

LOCK TABLES `branches` WRITE;
/*!40000 ALTER TABLE `branches` DISABLE KEYS */;
/*!40000 ALTER TABLE `branches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
                        `car_id` int NOT NULL AUTO_INCREMENT,
                        `brand` varchar(45) COLLATE utf8_bin NOT NULL,
                        `model` varchar(45) COLLATE utf8_bin NOT NULL,
                        `body_type` varchar(45) COLLATE utf8_bin NOT NULL,
                        `year` varchar(45) COLLATE utf8_bin NOT NULL,
                        `color` varchar(45) COLLATE utf8_bin NOT NULL,
                        `mileage` varchar(45) COLLATE utf8_bin NOT NULL,
                        `status` varchar(45) COLLATE utf8_bin NOT NULL DEFAULT 'AVAILABLE',
                        `seat_count` int NOT NULL,
                        PRIMARY KEY (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
                             `emp_id` int NOT NULL AUTO_INCREMENT,
                             `first_name` varchar(45) COLLATE utf8_bin NOT NULL,
                             `last_name` varchar(45) COLLATE utf8_bin NOT NULL,
                             `position` varchar(45) COLLATE utf8_bin NOT NULL,
                             `working_branch` varchar(45) COLLATE utf8_bin NOT NULL,
                             PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
                           `invoice_id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
                           PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
                                `reservation_id` int NOT NULL AUTO_INCREMENT,
                                `user` int NOT NULL,
                                `car` int NOT NULL,
                                `employee` int NOT NULL,
                                `date_from` date NOT NULL COMMENT 'reservation date',
                                `date_to` date NOT NULL COMMENT 'return date',
                                `return_branch` int NOT NULL,
                                `amount` varchar(45) COLLATE utf8_bin NOT NULL,
                                PRIMARY KEY (`reservation_id`),
                                KEY `fk_reservations_car_idx` (`car`),
                                KEY `fk_reservations_users_idx` (`user`),
                                KEY `fk_reservations_branches_idx` (`return_branch`),
                                KEY `fk_reservations_employees_idx` (`employee`),
                                CONSTRAINT `fk_reservations_branches` FOREIGN KEY (`return_branch`) REFERENCES `branches` (`branch_id`),
                                CONSTRAINT `fk_reservations_cars` FOREIGN KEY (`car`) REFERENCES `cars` (`car_id`),
                                CONSTRAINT `fk_reservations_employees` FOREIGN KEY (`employee`) REFERENCES `employees` (`emp_id`),
                                CONSTRAINT `fk_reservations_users` FOREIGN KEY (`user`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `email` varchar(45) COLLATE utf8_bin NOT NULL,
                         `first_name` varchar(45) COLLATE utf8_bin NOT NULL,
                         `last_name` varchar(45) COLLATE utf8_bin NOT NULL,
                         `password` varchar(45) COLLATE utf8_bin NOT NULL,
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-21 18:32:37
