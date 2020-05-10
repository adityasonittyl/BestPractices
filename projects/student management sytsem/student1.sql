/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.67-community-nt : Database - student1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`student1` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `student1`;

/*Table structure for table `branch` */

DROP TABLE IF EXISTS `branch`;

CREATE TABLE `branch` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `branch` */

insert  into `branch`(`id`,`name`) values (1,'IT'),(2,'CS'),(3,'EC'),(4,'MEC'),(5,'EE'),(6,'CIVIL'),(7,'ARCH.');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `course` */

insert  into `course`(`id`,`name`) values (1,'BAC'),(2,'MCA'),(3,'BTECH'),(4,'MTECH'),(5,'BBA'),(6,'MBA');

/*Table structure for table `faculty_registration` */

DROP TABLE IF EXISTS `faculty_registration`;

CREATE TABLE `faculty_registration` (
  `empid` varchar(25) NOT NULL,
  `name` varchar(50) default NULL,
  `dob` varchar(12) default NULL,
  `mobile` varchar(14) default NULL,
  `email` varchar(40) default NULL,
  `dateofjoining` varchar(15) default NULL,
  `address` varchar(150) default NULL,
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `faculty_registration` */

insert  into `faculty_registration`(`empid`,`name`,`dob`,`mobile`,`email`,`dateofjoining`,`address`,`password`) values ('123','abhishek','00/00/0000','9090909090','a@gmail.com','00-00-0000','jaipur','faculty123');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` int(10) NOT NULL auto_increment,
  `date` varchar(15) default NULL,
  `subject` varchar(50) default NULL,
  `notice` varchar(300) default NULL,
  `branch` int(10) default NULL,
  `uploaded_by` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `notice` */

/*Table structure for table `student_registration` */

DROP TABLE IF EXISTS `student_registration`;

CREATE TABLE `student_registration` (
  `enrollment` varchar(25) NOT NULL,
  `name` varchar(50) default NULL,
  `roll` varchar(15) default NULL,
  `dob` varchar(12) default NULL,
  `gender` varchar(10) default NULL,
  `mobile` varchar(14) default NULL,
  `email` varchar(40) default NULL,
  `branch` varchar(10) default NULL,
  `course` varchar(10) default NULL,
  `sem` int(5) default NULL,
  `dateofjoining` varchar(15) default NULL,
  `address` varchar(150) default NULL,
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`enrollment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `student_registration` */

insert  into `student_registration`(`enrollment`,`name`,`roll`,`dob`,`gender`,`mobile`,`email`,`branch`,`course`,`sem`,`dateofjoining`,`address`,`password`) values ('00/44444','abhishek','1','3/12/2010','Male','9090909090','abhishek.roadaheadtech@gmail.com','IT','BTECH',1,'1/1/2010','vikas marg,jaipur	','rat123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
