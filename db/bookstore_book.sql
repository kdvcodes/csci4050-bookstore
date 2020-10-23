-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bookISBN` int NOT NULL,
  `bookName` varchar(150) DEFAULT NULL,
  `bookAuthor` varchar(60) DEFAULT NULL,
  `bookYear` varchar(4) DEFAULT NULL,
  `bookDesc` varchar(1000) DEFAULT NULL,
  `bookPrice` double DEFAULT NULL,
  `bookCoverImg` varchar(45) DEFAULT NULL,
  `bookGenre` varchar(45) DEFAULT NULL,
  `bookAvailability` int DEFAULT NULL,
  `bookStock` int DEFAULT NULL,
  PRIMARY KEY (`bookISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (6227306,'Jab, Jab, Jab, Right Hook: How to Tell Your Story in a Noisy Social World','Gary Vaynerchuk','2013','New York Times bestselling author and social media expert Gary Vaynerchuk shares hard-won advice on how to connect with customers and beat the competition. A mash-up of the best elements of Crush It! and The Thank You Economy with a fresh spin, Jab, Jab, Jab, Right Hook is a blueprint to social media marketing strategies that really works.						\r\n								 ',25,'6227306.jpg','fiction',1,54),(439064872,'Harry Potter and the Chamber of Secrets','J.K. Rowling','1998','A mysterious elf tells Harry to expect trouble during his second year at Hogwarts, but nothing can prepare him for trees that fight back, flying cars, spiders that talk and deadly warnings written in blood on the walls of the school.',10,'0439064872.jpg','fiction',1,43),(439136369,'Harry Potter and the Prisoner Of Azkaban','J.K. Rowling','1999','The book follows Harry Potter, a young wizard, in his third year at Hogwarts School of Witchcraft and Wizardry. Along with friends Ronald Weasley and Hermione Granger, Harry investigates Sirius Black, an escaped prisoner from Azkaban, the wizard prison, believed to be one of Lord Voldemort\'s old allies.',11,'0439136369.jpg','fiction',1,32),(439139600,'Harry Potter and the Goblet of Fire','J.K. Rowling','2000','Harry Potter and the Goblet of Fire is a fantasy book written by British author J. K. Rowling and the fourth novel in the Harry Potter series. It follows Harry Potter, a wizard in his fourth year at Hogwarts School of Witchcraft and Wizardry, and the mystery surrounding the entry of Harry\'s name into the Triwizard Tournament, in which he is forced to compete.',12,'0439139600.jpg','fiction',1,8),(439358078,'Harry Potter and the Order of the Phoenix','J.K. Rowling','2003','Harry Potter and the Order of the Phoenix is a fantasy novel written by British author J. K. Rowling and the fifth novel in the Harry Potter series. It follows Harry Potter\'s struggles through his fifth year at Hogwarts School of Witchcraft and Wizardry, including the surreptitious return of the antagonist Lord Voldemort, O.W.L. exams, and an obstructive Ministry of Magic.',11,'0439358078.jpg','fiction',1,39),(439708184,'Harry Potter and the Sorcerer\'s Stone','J.K. Rowling','1998','Harry Potter has no idea how famous he is. That\'s because he\'s being raised by his miserable aunt and uncle who are terrified Harry will learn that he\'s really a wizard, just as his parents were. But everything changes when Harry is summoned to attend an infamous school for wizards, and he begins to discover some clues about his illustrious birthright. From the surprising way he is greeted by a lovable giant, to the unique curriculum and colorful faculty at his unusual school, Harry finds himself drawn deep inside a mystical world he never knew existed and closer to his own noble destiny.',8,'0439708184.jpg','fiction',1,56),(439785960,'Harry Potter and the Half-Blood Prince','J.K. Rowling','2005','Harry Potter and the Half-Blood Prince is a fantasy novel written by British author J.K. Rowling and the sixth and penultimate novel in the Harry Potter series. Set during Harry Potter\'s sixth year at Hogwarts, the novel explores the past of Harry\'s nemesis, Lord Voldemort, and Harry\'s preparations for the final battle against Voldemort alongside his headmaster and mentor Albus Dumbledore.',12,'0439785960.jpg','fiction',1,23),(545139708,'Harry Potter and the Deathly Hallows','J.K. Rowling','2007','Harry Potter and the Deathly Hallows is a fantasy novel written by British author J. K. Rowling and the seventh and final novel of the Harry Potter series. The novel chronicles the events directly following Harry Potter and the Half-Blood Prince (2005) and the final confrontation between the wizards Harry Potter and Lord Voldemort.',15,'0545139708.jpg','fiction',1,28),(545271908,'The Sea of Monsters','Rick Riordan','2006','This is the second novel in the Percy Jackson & the Olympians series and the sequel to The Lightning Thief. This book chronicles the adventures of thirteen-year-old demigod Percy Jackson as he and his friends rescue his satyr friend Grover from the Cyclops Polyphemus and save Camp Half-Blood from a Titan\'s attack by bringing the Golden Fleece to cure Thalia\'s poisoned pine tree.',17,'0545271908.jpg','fiction',1,37),(786838655,'The Lightning Thief','Rick Riordan','2005','The Lightning Thief is a 2005 American fantasy-adventure novel based on Greek mythology, the first young adult novel written by Rick Riordan in the Percy Jackson & the Olympians series. It won the Adult Library Services Association Best Books for Young Adults, among other awards. It was adapted into a film named Percy Jackson & the Olympians: The Lightning Thief released in the United States on February 12, 2010.',10,'786838655.jpg','fiction',1,10),(1423101456,'The Titan’s Curse','Rick Riordan','2007','This is the third novel in the Percy Jackson & the Olympians series and the sequel to The Sea of Monsters. It is about the adventures of the 14-year-old demigod Percy Jackson as he and his friends go on a dangerous quest to rescue his 14-year-old demigod friend Annabeth Chase and the Greek goddess Artemis, who have both been kidnapped.',18,'1423101456.jpg','fiction',1,45),(1423101464,'The Battle of the Labyrinth','Rick Riordan','2008','The book follows the adventures of modern-day fifteen-year-old demigod Percy Jackson, the son of a mortal woman and the Greek god Poseidon. Percy and his friends Annabeth Chase, Grover Underwood, Rachel Dare and Tyson attempt to stop Luke Castellan and his army from invading Camp Half-Blood through Daedalus\'s labyrinth by trying to prevent the Ariadne\'s string from falling into his hands.',19,'1423101464.jpg','ficiton',1,15),(1423101472,'The Last Olympian','Rick Riordan','2009','It is the fifth and final novel of the Percy Jackson & the Olympians series and serves as the direct sequel to The Battle of the Labyrinth. The Last Olympian revolves around the demigod Percy Jackson as he leads his friends in a last stand to protect Mount Olympus.',20,'1423101472.jpg','fiction',1,47);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-26 12:36:00
