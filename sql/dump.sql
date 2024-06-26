CREATE DATABASE  IF NOT EXISTS `webmarket` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `webmarket`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: webmarket
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `amministratore`
--

DROP TABLE IF EXISTS `amministratore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amministratore` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amministratore`
--

LOCK TABLES `amministratore` WRITE;
/*!40000 ALTER TABLE `amministratore` DISABLE KEYS */;
INSERT INTO `amministratore` VALUES (1,'admin@gmail.com','282db4a4425f50237e7df29d56988825f15dd8b34fa74af54e650ce0fd8897a82dff0b952017a3a88a62f5f1b0e0e467',1),(2,'universale@gmail.com','bf6d14d1110d651f1bb8c4f2a15c62709691e4389ee393e3f6ac56c92a441e1a3243df2dc3a495106bd30573176c5975',1);
/*!40000 ALTER TABLE `amministratore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caratteristica`
--

DROP TABLE IF EXISTS `caratteristica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caratteristica` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `unita_di_misura` varchar(255) NOT NULL,
  `ID_categoria_nipote` int unsigned NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `ID_categoria_nipote` (`ID_categoria_nipote`),
  CONSTRAINT `caratteristica_ibfk_1` FOREIGN KEY (`ID_categoria_nipote`) REFERENCES `categorianipote` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caratteristica`
--

LOCK TABLES `caratteristica` WRITE;
/*!40000 ALTER TABLE `caratteristica` DISABLE KEYS */;
INSERT INTO `caratteristica` VALUES (1,'RAM','GB',1,1),(2,'RAM','GB',2,1),(3,'CPU','GHz',1,1),(4,'CPU','GHz',2,1),(5,'Lunghezza','cm',3,1),(6,'Peso','kg',4,1),(7,'Lunghezza cavo','m',4,1),(8,'Peso','kg',5,1),(9,'Lunghezza cavo','m',5,1),(10,'Pagine','pagine',6,1),(11,'Colore','',7,1);
/*!40000 ALTER TABLE `caratteristica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriafiglio`
--

DROP TABLE IF EXISTS `categoriafiglio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoriafiglio` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `ID_categoria_padre` int unsigned NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `ID_categoria_padre` (`ID_categoria_padre`),
  CONSTRAINT `categoriafiglio_ibfk_1` FOREIGN KEY (`ID_categoria_padre`) REFERENCES `categoriapadre` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriafiglio`
--

LOCK TABLES `categoriafiglio` WRITE;
/*!40000 ALTER TABLE `categoriafiglio` DISABLE KEYS */;
INSERT INTO `categoriafiglio` VALUES (1,'Computer',2,1),(2,'Accessori',2,1),(3,'Mobili',3,1),(4,'Cancelleria',1,1);
/*!40000 ALTER TABLE `categoriafiglio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorianipote`
--

DROP TABLE IF EXISTS `categorianipote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorianipote` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `ID_categoria_figlio` int unsigned NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `ID_categoria_figlio` (`ID_categoria_figlio`),
  CONSTRAINT `categorianipote_ibfk_1` FOREIGN KEY (`ID_categoria_figlio`) REFERENCES `categoriafiglio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorianipote`
--

LOCK TABLES `categorianipote` WRITE;
/*!40000 ALTER TABLE `categorianipote` DISABLE KEYS */;
INSERT INTO `categorianipote` VALUES (1,'PC Desktop',1,1),(2,'Notebook',1,1),(3,'Scrivania',3,1),(4,'Cuffie',2,1),(5,'Mouse',2,1),(6,'Quaderno',4,1),(7,'Penna',4,1);
/*!40000 ALTER TABLE `categorianipote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriapadre`
--

DROP TABLE IF EXISTS `categoriapadre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoriapadre` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriapadre`
--

LOCK TABLES `categoriapadre` WRITE;
/*!40000 ALTER TABLE `categoriapadre` DISABLE KEYS */;
INSERT INTO `categoriapadre` VALUES (1,'Modulistica',1),(2,'Informatica',1),(3,'Mobilio',1);
/*!40000 ALTER TABLE `categoriapadre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chiude`
--

DROP TABLE IF EXISTS `chiude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chiude` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `ID_ordine` int unsigned NOT NULL,
  `ID_ordinante` int unsigned NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `ID_ordine` (`ID_ordine`),
  KEY `ID_ordinante` (`ID_ordinante`),
  CONSTRAINT `chiude_ibfk_1` FOREIGN KEY (`ID_ordine`) REFERENCES `ordine` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chiude_ibfk_2` FOREIGN KEY (`ID_ordinante`) REFERENCES `ordinante` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chiude`
--

LOCK TABLES `chiude` WRITE;
/*!40000 ALTER TABLE `chiude` DISABLE KEYS */;
INSERT INTO `chiude` VALUES (1,2,1,1);
/*!40000 ALTER TABLE `chiude` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composta`
--

DROP TABLE IF EXISTS `composta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composta` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `valore` varchar(255) NOT NULL,
  `ID_richiesta` int unsigned NOT NULL,
  `ID_caratteristica` int unsigned NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `ID_richiesta` (`ID_richiesta`),
  KEY `ID_caratteristica` (`ID_caratteristica`),
  CONSTRAINT `composta_ibfk_1` FOREIGN KEY (`ID_richiesta`) REFERENCES `richiesta` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `composta_ibfk_2` FOREIGN KEY (`ID_caratteristica`) REFERENCES `caratteristica` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composta`
--

LOCK TABLES `composta` WRITE;
/*!40000 ALTER TABLE `composta` DISABLE KEYS */;
INSERT INTO `composta` VALUES (1,'32',1,2,1),(2,'100',2,5,1),(3,'5',1,4,1),(4,'3.5',3,7,1),(5,'0.4',3,6,1);
/*!40000 ALTER TABLE `composta` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `rimuovi_richiesta_senza_composta` AFTER DELETE ON `composta` FOR EACH ROW BEGIN
    DECLARE richiesta_count INT;

    SELECT COUNT(*)
    INTO richiesta_count
    FROM composta
    WHERE ID_richiesta = OLD.ID_richiesta;

    IF richiesta_count = 0 THEN
        DELETE FROM richiesta
        WHERE ID = OLD.ID_richiesta;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `ordinante`
--

DROP TABLE IF EXISTS `ordinante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordinante` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ID_ufficio` int unsigned NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`),
  KEY `ID_ufficio` (`ID_ufficio`),
  CONSTRAINT `ordinante_ibfk_1` FOREIGN KEY (`ID_ufficio`) REFERENCES `ufficio` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordinante`
--

LOCK TABLES `ordinante` WRITE;
/*!40000 ALTER TABLE `ordinante` DISABLE KEYS */;
INSERT INTO `ordinante` VALUES (1,'universale@gmail.com','bf6d14d1110d651f1bb8c4f2a15c62709691e4389ee393e3f6ac56c92a441e1a3243df2dc3a495106bd30573176c5975',3,1),(2,'michaelpiccirilli3@gmail.com','78bcd4a8fdf004fbc369061a84b5cd31312da76008934816ebeb74ea3afd23f66375dffde3b91b8872ca20ed36a7b071',3,1),(3,'paoloccigiacomo@gmail.com','c93af3c4d15d0614f28aa3c0a3f929b01f0bbafc0dcf39608f5934e200b0094b1712e362953461c3b39fba751aae358b',1,1);
/*!40000 ALTER TABLE `ordinante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `stato_consegna` enum('Presa in carico','In consegna','Consegnato') NOT NULL,
  `feedback` enum('Accettato','Respinto perchè non conforme','Respinto perchè non funzionante') DEFAULT NULL,
  `data_di_consegna` timestamp NULL DEFAULT NULL,
  `ID_tecnico_ordini` int unsigned DEFAULT NULL,
  `ID_proposta` int unsigned NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `ID_tecnico_ordini` (`ID_tecnico_ordini`),
  KEY `ID_proposta` (`ID_proposta`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`ID_tecnico_ordini`) REFERENCES `tecnicoordini` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`ID_proposta`) REFERENCES `proposta` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,'Presa in carico',NULL,NULL,1,3,1),(2,'Consegnato','Accettato','2021-06-11 22:00:00',1,4,1);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `aggiorna_data_consegna` BEFORE UPDATE ON `ordine` FOR EACH ROW BEGIN
    IF NEW.stato_consegna = 'Consegnato' AND NEW.data_di_consegna IS NULL THEN
        SET NEW.data_di_consegna = CURRENT_TIMESTAMP;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `controlla_inserimento_feedback` BEFORE UPDATE ON `ordine` FOR EACH ROW BEGIN
    IF NEW.stato_consegna != 'Consegnato' AND NEW.feedback IS NOT NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Inserimento feedback non consentito per questo stato di consegna.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `inserisci_ordine_chiuso` AFTER UPDATE ON `ordine` FOR EACH ROW BEGIN
    DECLARE ID_ordinante INT UNSIGNED;

    SET ID_ordinante = (SELECT o.ID
                        FROM ordinante o
                                 JOIN richiesta r ON o.ID = r.ID_ordinante
                                 JOIN richiestapresaincarico r2 ON r.ID = r2.ID_richiesta
                                 JOIN proposta p on r2.ID = p.ID_richiesta_presa_in_carico
                                 JOIN ordine o2 ON p.ID = o2.ID_proposta
                        WHERE o2.ID = NEW.ID);
    IF NEW.stato_consegna = 'Consegnato' THEN
        INSERT INTO chiude(ID_ordine, ID_ordinante)
        VALUES (NEW.ID, ID_ordinante);
    END IF;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `proposta`
--

DROP TABLE IF EXISTS `proposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proposta` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `codice_prodotto` varchar(255) NOT NULL,
  `produttore` varchar(255) NOT NULL,
  `note` text,
  `prezzo` decimal(10,2) NOT NULL,
  `nome_prodotto` varchar(255) NOT NULL,
  `URL` varchar(2048) NOT NULL,
  `stato_proposta` enum('In attesa','Accettato','Rifiutato') NOT NULL,
  `motivazione` text,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  `ID_richiesta_presa_in_carico` int unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `codice_prodotto` (`codice_prodotto`),
  KEY `ID_richiesta_presa_in_carico` (`ID_richiesta_presa_in_carico`),
  CONSTRAINT `proposta_ibfk_1` FOREIGN KEY (`ID_richiesta_presa_in_carico`) REFERENCES `richiestapresaincarico` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proposta`
--

LOCK TABLES `proposta` WRITE;
/*!40000 ALTER TABLE `proposta` DISABLE KEYS */;
INSERT INTO `proposta` VALUES (1,'8fu9eurw9e8ur8u38hc3m','HP','Pc economico ma plasticoso',273.50,'Pixel Intel N4500','https://www.amazon.it/HP-255-G8-portatile-DDR4-SDRAM/','Rifiutato','Non ha abbastanza RAM, non mi piace il materiale',1,1),(2,'229f8ej382j8je9sua0s9','Honor','Pc molto leggero e di un colore blu elettrico',699.99,'Honor MagicBook 14','https://www.honor.com/it/laptops/honor-magicbook-14/','In attesa',NULL,1,1),(3,'34209348nc2034823048c','MALM','Scrivania con 2 cassetti molto ampii',159.99,'Scrivania Bianca MALM','https://www.ikea.com/it/it/p/malm-scrivania-bianco-60214159/','Accettato',NULL,1,2),(4,'2387fsn9d87fsd8f7379n','ASUS','Cuffie da gaming ASUS',109.99,'ASUS Delta S Core','https://rog.asus.com/it/headsets-audio/headsets/3-5mm-headsets/rog-delta-s-core-model/','Accettato',NULL,1,3);
/*!40000 ALTER TABLE `proposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `richiesta`
--

DROP TABLE IF EXISTS `richiesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `richiesta` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `codice_richiesta` varchar(10) NOT NULL,
  `note` text,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ID_ordinante` int unsigned NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `codice_richiesta` (`codice_richiesta`),
  KEY `ID_ordinante` (`ID_ordinante`),
  CONSTRAINT `richiesta_ibfk_1` FOREIGN KEY (`ID_ordinante`) REFERENCES `ordinante` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `richiesta`
--

LOCK TABLES `richiesta` WRITE;
/*!40000 ALTER TABLE `richiesta` DISABLE KEYS */;
INSERT INTO `richiesta` VALUES (1,'7592783840','Voglio un Notebook per poter programmare in ufficio','2024-06-26 19:54:01',1,1),(2,'7683845728','Voglio una scrivania più larga ','2024-06-26 19:54:01',1,1),(3,'7834723777','Voglio un paio di cuffie da usare per le videochiamate','2024-06-26 19:54:01',1,1);
/*!40000 ALTER TABLE `richiesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `richiestapresaincarico`
--

DROP TABLE IF EXISTS `richiestapresaincarico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `richiestapresaincarico` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `ID_richiesta` int unsigned NOT NULL,
  `ID_tecnico_preventivi` int unsigned NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `ID_richiesta` (`ID_richiesta`),
  KEY `ID_tecnico_preventivi` (`ID_tecnico_preventivi`),
  CONSTRAINT `richiestapresaincarico_ibfk_1` FOREIGN KEY (`ID_richiesta`) REFERENCES `richiesta` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `richiestapresaincarico_ibfk_2` FOREIGN KEY (`ID_tecnico_preventivi`) REFERENCES `tecnicopreventivi` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `richiestapresaincarico`
--

LOCK TABLES `richiestapresaincarico` WRITE;
/*!40000 ALTER TABLE `richiestapresaincarico` DISABLE KEYS */;
INSERT INTO `richiestapresaincarico` VALUES (1,1,1,1),(2,2,2,1),(3,3,1,1);
/*!40000 ALTER TABLE `richiestapresaincarico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnicoordini`
--

DROP TABLE IF EXISTS `tecnicoordini`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnicoordini` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnicoordini`
--

LOCK TABLES `tecnicoordini` WRITE;
/*!40000 ALTER TABLE `tecnicoordini` DISABLE KEYS */;
INSERT INTO `tecnicoordini` VALUES (1,'universale@gmail.com','bf6d14d1110d651f1bb8c4f2a15c62709691e4389ee393e3f6ac56c92a441e1a3243df2dc3a495106bd30573176c5975',1),(2,'techord1@gmail.com','86dfa250e8da2a2add194605ef10dd450692f9706c01e7b352d5f13f410ccf179252694deeecff8e71a416b668abb288',1),(3,'techord2@gmail.com','e1333ca4aa5ee5963c9b8a38bc62024fb9c9636e42d01e4f0fb85f59b94d31256a8d2dd9c3f2aab686345bbebf637f6c',1);
/*!40000 ALTER TABLE `tecnicoordini` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnicopreventivi`
--

DROP TABLE IF EXISTS `tecnicopreventivi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnicopreventivi` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnicopreventivi`
--

LOCK TABLES `tecnicopreventivi` WRITE;
/*!40000 ALTER TABLE `tecnicopreventivi` DISABLE KEYS */;
INSERT INTO `tecnicopreventivi` VALUES (1,'universale@gmail.com','bf6d14d1110d651f1bb8c4f2a15c62709691e4389ee393e3f6ac56c92a441e1a3243df2dc3a495106bd30573176c5975',1),(2,'techprev1@gmail.com','19bc1dfbdcdf7d8ddbb8f6b7c12112192b7cc3569eaed68dab0720fd3a93fd7211951aa804aa499d33163944eec1312b',1),(3,'techprev2@gmail.com','e942d4bea001c6c035cf6783f17d8d8b0ab0c5dffee23ff48bd6d157b325079c7a7f3a003fefb42b04cfe5b89123f7ae',1);
/*!40000 ALTER TABLE `tecnicopreventivi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ufficio`
--

DROP TABLE IF EXISTS `ufficio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ufficio` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `sede` varchar(255) NOT NULL,
  `numero` int NOT NULL,
  `piano` int NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `citta` varchar(255) NOT NULL,
  `version` bigint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ufficio`
--

LOCK TABLES `ufficio` WRITE;
/*!40000 ALTER TABLE `ufficio` DISABLE KEYS */;
INSERT INTO `ufficio` VALUES (1,'Via Roma',12,1,'0746229912','Milano',1),(2,'Via Roma',23,2,'0746758396','Roma',1),(3,'Via Castello',11,1,'0861997534','L\'aquila',1);
/*!40000 ALTER TABLE `ufficio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'webmarket'
--

--
-- Dumping routines for database 'webmarket'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-26 21:55:33
