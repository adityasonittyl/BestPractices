/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.67-community-nt : Database - libmgmt1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`libmgmt1` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `libmgmt1`;

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `author` */

insert  into `author`(`id`,`name`) values (1,'KAITHY SIERRA'),(2,'KHALID'),(3,'MUGAL'),(4,'HERBERT SHILDTH'),(5,'YASHWANT KANITKAR'),(6,'KORTH'),(9,'DIETAL');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(10) NOT NULL,
  `title` varchar(100) default NULL,
  `author` varchar(100) default NULL,
  `subject` varchar(100) default NULL,
  `category` varchar(100) default NULL,
  `publication` varchar(100) default NULL,
  `isbn` varchar(50) default NULL,
  `price` int(10) default NULL,
  `quantity` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `book` */

insert  into `book`(`id`,`title`,`author`,`subject`,`category`,`publication`,`isbn`,`price`,`quantity`) values (5896788,'programming in java','HERBERT SHILDTH','JAVA','PROGRAMMING LANGUAGE','TMH','23232',450,10),(6966763,'programmin in cpp','YASHWANT KANITKAR','CPP','PROGRAMMING LANGUAGE','TMH','3434',350,10);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `category` */

insert  into `category`(`id`,`name`) values (1,'PROGRAMMING LANGUAGE'),(2,'GK');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `id` int(10) NOT NULL auto_increment,
  `username` varchar(100) default NULL,
  `password` varchar(100) default NULL,
  `usertype` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`id`,`username`,`password`,`usertype`) values (1,'abhishek','ak123','admin'),(2,'tarun','tk123','user');

/*Table structure for table `publication` */

DROP TABLE IF EXISTS `publication`;

CREATE TABLE `publication` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `publication` */

insert  into `publication`(`id`,`name`) values (1,'TMH'),(2,'BPB'),(3,'VUE'),(4,'GENIUS');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `subject` */

insert  into `subject`(`id`,`name`) values (1,'JAVA'),(2,'C'),(3,'CPP'),(4,'TOC'),(5,'ITC'),(6,'SE');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
