/*
SQLyog Community v8.4
MySQL - 5.1.46-community : Database - mobilefirst_training
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mobilefirst_training` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mobilefirst_training`;

/*Table structure for table `accounts` */

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `accountId` varchar(15) NOT NULL,
  `userId` varchar(100) NOT NULL DEFAULT '',
  `accountType` varchar(50) NOT NULL,
  PRIMARY KEY (`accountId`),
  KEY `FK_accounts_users` (`userId`),
  CONSTRAINT `FK_accounts_users` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `accounts` */

insert  into `accounts`(`accountId`,`userId`,`accountType`) values ('12345','bjones','Checking'),('54321','bjones','Savings');

/*Table structure for table `accounttransactions` */

DROP TABLE IF EXISTS `accounttransactions`;

CREATE TABLE `accounttransactions` (
  `transactionId` char(12) NOT NULL DEFAULT '',
  `fromAccount` varchar(15) DEFAULT '',
  `toAccount` varchar(15) DEFAULT NULL,
  `transactionType` varchar(100) NOT NULL,
  `transactionDate` datetime NOT NULL,
  `transactionAmount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`transactionId`),
  KEY `FK_accounttransactions_from` (`fromAccount`),
  KEY `FK_accounttransactions_to` (`toAccount`),
  CONSTRAINT `FK_accounttransactions_from` FOREIGN KEY (`fromAccount`) REFERENCES `accounts` (`accountId`) ON DELETE CASCADE,
  CONSTRAINT `FK_accounttransactions_to` FOREIGN KEY (`toAccount`) REFERENCES `accounts` (`accountId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `accounttransactions` */

insert  into `accounttransactions`(`transactionId`,`fromAccount`,`toAccount`,`transactionType`,`transactionDate`,`transactionAmount`) values ('W06091500863','12345','54321','Funds Transfer','2009-03-11 11:08:39','180.00'),('W214122/5332','12345','54321','Funds Transfer','2009-03-04 10:35:24','150.00'),('W214122/5333',NULL,'54321','Deposit','2009-03-06 11:08:39','7500.00'),('W214122/5334',NULL,'12345','Deposit','2009-03-01 11:09:39','9050.00'),('W214122/5337','12345',NULL,'ATM Withdrawal','2009-03-07 11:09:39','130.00'),('W273218/4101',NULL,'54321','Accrued Interest','2009-03-01 11:09:39','10.08'),('W274158/5551','12345',NULL,'Check Withdrawal','2009-03-03 11:09:39','150.00');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userId` varchar(100) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`userId`,`firstName`,`lastName`,`password`) values ('bjones','Brad','Jones','bjones');

/*Table structure for table `weather` */

DROP TABLE IF EXISTS `weather`;

CREATE TABLE `weather` (
  `city` text,
  `identifier` text,
  `summary` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `weather` */

insert  into `weather`(`city`,`identifier`,`summary`) values ('New York','2459115','New York City, which is geographically the largest city in the state and most populous in the United States, is known for its history as a gateway for immigration to the United States and its status as a financial, cultural, transportation, and manufacturing center. According to the U.S. Department of Commerce, it is also a destination of choice for many foreign visitors. Both state and city were named for the 17th century Duke of York, James Stuart, future James II and VII of England and Scotland.'),('Sydney','1105779','Sydney is the largest and most populous city in Australia and the state capital of New South Wales. Sydney is located on Australia\'s south-east coast of the Tasman Sea. With an approximate population of 4.5 million in the Sydney metropolitan area the city is the largest municipality in Oceania.[5]  Inhabitants of Sydney are called Sydneysiders, comprising of a cosmopolitan and international  population of people from many places around the world.'),('San Francisco','2487956','The City and County of San Francisco is the fourth most populous city in California and the 12th most populous city in the United States, with a 2008 estimated population of 808,977.[9]  The only consolidated city-county in California,[11]  it encompasses a land area of 46.7 square miles (121 km2)[12]  on the northern end of the San Francisco Peninsula, making it the second-most densely populated large city (greater than 200,000 population) in the United States.[13]  San Francisco is anchor to the 13th-largest  metropolitan area in the country, containing 4.3 million, and is the financial, cultural, and transportation center of the larger San Francisco Bay Area, a region of 7.4 million people.[14]  For the 2009-2010 season, the San'),('Christchurch','2348327','Christchurch is the largest city  in the South Island of New Zealand, and the country\'s second-largest urban area. It lies one third of the way down the South Island\'s east coast, just north of Banks Peninsula which itself, since 2006, lies within the formal limits of Christchurch.'),('Bankok','1208341','Bangkok is the capital, largest urban area and primary city of Thailand. Known in Thai as Krung Thep Mahanakhon , meaning \'city of angeles\' for short, it was a small trading post at the mouth of the Chao Phraya River during the Ayutthaya Kingdom. It came to the forefront of Siam when it was given the status as the capital city in 1768 after the burning of Ayutthaya. However, the current Rattanakosin Kingdom did not begin until 1782 when the capital was moved across the river by Rama I after the death of King Taksin. The Rattanakosin capital is now more formally called Phra Nakhon (Thai: ??????), pertaining to the ancient boundaries in the metropolis\' core and the name Bangkok now incorporates the urban build-up since the 18th century which has its own public administration and governor.');

/* Procedure structure for procedure `getAccountTransactions` */

/*!50003 DROP PROCEDURE IF EXISTS  `getAccountTransactions` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`mobilefirst`@`localhost` PROCEDURE `getAccountTransactions`(in accountId varchar(15))
BEGIN
SELECT transactionId, fromAccount, toAccount, transactionDate, transactionAmount, transactionType
FROM accounttransactions
WHERE accounttransactions.fromAccount = accountId OR accounttransactions.toAccount = accountId
ORDER BY transactionDate DESC
LIMIT 20;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
