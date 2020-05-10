/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.67-community-nt : Database - library_management
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`library_management` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `library_management`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `user type` varchar(100) NOT NULL,
  `mobile` int(100) default NULL,
  `email` varchar(100) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `admin` */

insert  into `admin`(`admin_name`,`password`,`user type`,`mobile`,`email`) values ('saloni','saloni123','admin',NULL,NULL),('mahak','1234','admin',12345678,'sssss');

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `author` */

insert  into `author`(`id`,`name`) values (1,'ASD'),(2,'FWEFWE'),(3,'BALAGURU SWAMI'),(4,'GFGGFVV');

/*Table structure for table `book_table` */

DROP TABLE IF EXISTS `book_table`;

CREATE TABLE `book_table` (
  `bookid` int(7) NOT NULL auto_increment,
  `title` varchar(100) NOT NULL,
  `author` varchar(100) default NULL,
  `subject` varchar(100) default NULL,
  `category` varchar(100) default NULL,
  `publisher` varchar(100) default NULL,
  `isbn` int(10) default NULL,
  `edition` int(10) default NULL,
  `price` int(10) default NULL,
  `quantity` int(10) default NULL,
  PRIMARY KEY  (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=5445325 DEFAULT CHARSET=latin1;

/*Data for the table `book_table` */

insert  into `book_table`(`bookid`,`title`,`author`,`subject`,`category`,`publisher`,`isbn`,`edition`,`price`,`quantity`) values (139299,'c progrmming','BALAGURU SWAMI','TMP','GCFFXG','C#',4567,2019,233,5),(351671,'learn to code','FWEFWE','JAVA','PROGRAMMING','DFDGSFDS',7689,2018,22,20),(483277,'jquery','ASD','DESIGNING','SCRIPTING2','EERT',6789,1200,6000,0),(582445,'html','FWEFWE','C++','SCRIPTING','EERT',5833,2019,200,12),(685282,'learn to design','FWEFWE','C#','SCRIPTING2','EERT',4590,2016,200,20),(927755,'learning java','BALAGURU SWAMI','JAVA','PROGRAMMING','VUE',4567,2018,200,12);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `category` */

insert  into `category`(`id`,`name`) values (1,'PROGRAMMING'),(2,'SCRIPTING'),(3,'SCRIPTING2'),(4,'GCFFXG');

/*Table structure for table `issue` */

DROP TABLE IF EXISTS `issue`;

CREATE TABLE `issue` (
  `bookid` int(10) NOT NULL,
  `issue_date` date default NULL,
  `status` varchar(20) default NULL,
  `submit_date` date default NULL,
  `username` varchar(100) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `issue` */

insert  into `issue`(`bookid`,`issue_date`,`status`,`submit_date`,`username`) values (483277,'2018-12-22','sumitted','2018-12-31','ds'),(483277,'2018-12-22','sumitted','2018-12-31','ds'),(483277,'2018-12-22','sumitted','2018-12-31','ds'),(274253,NULL,'sumitted',NULL,'raj'),(139299,NULL,'sumitted','2018-12-22',NULL),(274253,NULL,'not submitted',NULL,NULL),(483277,NULL,'not submitted',NULL,NULL),(139299,NULL,'sumitted','2018-12-22','chavi'),(139299,NULL,'sumitted','2018-12-22','chavi'),(139299,'2018-12-22','sumitted','2018-12-22','chavi'),(685282,'2018-12-22','sumitted','2018-12-22','raj');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) default NULL,
  `usertype` varchar(100) default NULL,
  `id` int(10) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`username`,`password`,`usertype`,`id`) values ('chavi','123456','student',36659287),('ds','sal','student',29111111),('raj','3456','faculty',97375124);

/*Table structure for table `publisher` */

DROP TABLE IF EXISTS `publisher`;

CREATE TABLE `publisher` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `publisher` */

insert  into `publisher`(`id`,`name`) values (1,'TMP'),(2,'EERT'),(3,'VUE'),(4,'DFDGSFDS');

/*Table structure for table `registeration` */

DROP TABLE IF EXISTS `registeration`;

CREATE TABLE `registeration` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `mobile` varchar(15) default NULL,
  `email` varchar(100) default NULL,
  `username` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97375125 DEFAULT CHARSET=latin1;

/*Data for the table `registeration` */

insert  into `registeration`(`id`,`name`,`mobile`,`email`,`username`) values (29111111,'sal','1234567','sassssaa','ds'),(36659287,'chavi','12309879','saass','chavi'),(72214667,'dfgd','67678','hjbbjb','ds'),(88913337,'dfd','787','jbj','ds'),(97375124,'rajesj','78787878','sss','raj');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `subject` */

insert  into `subject`(`id`,`name`) values (1,'C'),(2,'C++'),(3,'JAVA'),(4,'DESIGNING'),(5,'C#'),(6,'FDFD');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
