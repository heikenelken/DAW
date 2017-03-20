CREATE DATABASE  IF NOT EXISTS `wallacomic` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wallacomic`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: wallacomic
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `TB_ANUNCIO`
--

DROP TABLE IF EXISTS `TB_ANUNCIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_ANUNCIO` (
  `id` int(11) NOT NULL,
  `price` double NOT NULL,
  `comment` varchar(100) NOT NULL,
  `type` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `comic` FOREIGN KEY (`id`) REFERENCES `TB_COMIC` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user` FOREIGN KEY (`id`) REFERENCES `TB_USUARIO` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TB_COMIC`
--

DROP TABLE IF EXISTS `TB_COMIC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_COMIC` (
  `id` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `autor` varchar(100) NOT NULL,
  `dibujante` varchar(100) NOT NULL,
  `argumento` varchar(100) NOT NULL,
  `foto` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TB_CONVERSACION`
--

DROP TABLE IF EXISTS `TB_CONVERSACION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_CONVERSACION` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `comentarios` FOREIGN KEY (`id`) REFERENCES `TB_MENSAJE` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userBuyer` FOREIGN KEY (`id`) REFERENCES `TB_USUARIO` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userSeller` FOREIGN KEY (`id`) REFERENCES `TB_USUARIO` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TB_MENSAJE`
--

DROP TABLE IF EXISTS `TB_MENSAJE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_MENSAJE` (
  `id` int(11) NOT NULL,
  `message` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `userMensaje` FOREIGN KEY (`id`) REFERENCES `TB_USUARIO` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TB_USUARIO`
--

DROP TABLE IF EXISTS `TB_USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_USUARIO` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `contraseñaHash` varchar(100) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `facebook` varchar(100) NOT NULL,
  `twitter` varchar(100) NOT NULL,
  `foto` varchar(100) NOT NULL,
  `roles` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TB_VALORACION`
--

DROP TABLE IF EXISTS `TB_VALORACION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_VALORACION` (
  `id` int(11) NOT NULL,
  `comentario` varchar(100) NOT NULL,
  `numEstrellas` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `userReceive` FOREIGN KEY (`id`) REFERENCES `TB_USUARIO` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_give` FOREIGN KEY (`id`) REFERENCES `TB_USUARIO` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'wallacomic'
--

--
-- Dumping routines for database 'wallacomic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-20 22:51:11
