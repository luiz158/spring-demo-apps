/*
SQLyog Enterprise v8.63 
MySQL - 5.1.30-community : Database - linkshare
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`linkshare` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `linkshare`;

/*Table structure for table `comments` */

DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `message` longtext NOT NULL,
  `link_id` int(11) NOT NULL,
  `posted_by` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FKDC17DDF48E95F26F` (`posted_by`),
  KEY `FKDC17DDF422EF3904` (`link_id`),
  CONSTRAINT `FKDC17DDF422EF3904` FOREIGN KEY (`link_id`) REFERENCES `links` (`link_id`),
  CONSTRAINT `FKDC17DDF48E95F26F` FOREIGN KEY (`posted_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `comments` */

insert  into `comments`(`comment_id`,`created_on`,`message`,`link_id`,`posted_by`) values (1,'2012-07-15 00:00:00','Test Comment',1,2);

/*Table structure for table `links` */

DROP TABLE IF EXISTS `links`;

CREATE TABLE `links` (
  `link_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `description` longtext,
  `title` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `posted_by` int(11) NOT NULL,
  PRIMARY KEY (`link_id`),
  KEY `FK6234FB98E95F26F` (`posted_by`),
  CONSTRAINT `FK6234FB98E95F26F` FOREIGN KEY (`posted_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `links` */

insert  into `links`(`link_id`,`created_on`,`description`,`title`,`url`,`posted_by`) values (1,'2012-07-15 00:00:00','Test link Description.','Test Link','www.google.com',1);

/*Table structure for table `links_tags` */

DROP TABLE IF EXISTS `links_tags`;

CREATE TABLE `links_tags` (
  `link_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  KEY `FK67B788DF22EF3904` (`link_id`),
  KEY `FK67B788DF61138B0` (`tag_id`),
  CONSTRAINT `FK67B788DF61138B0` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`tag_id`),
  CONSTRAINT `FK67B788DF22EF3904` FOREIGN KEY (`link_id`) REFERENCES `links` (`link_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `links_tags` */

insert  into `links_tags`(`link_id`,`tag_id`) values (1,1);
insert  into `links_tags`(`link_id`,`tag_id`) values (1,2);

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

insert  into `roles`(`role_id`,`description`,`role_name`) values (1,'Administrator','ROLE_ADMIN');
insert  into `roles`(`role_id`,`description`,`role_name`) values (2,'Normal User','ROLE_USER');
insert  into `roles`(`role_id`,`description`,`role_name`) values (3,'Anonymous User','ROLE_ANONYMOUS');

/*Table structure for table `tags` */

DROP TABLE IF EXISTS `tags`;

CREATE TABLE `tags` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `tags` */

insert  into `tags`(`tag_id`,`label`,`value`) values (1,'Java','java');
insert  into `tags`(`tag_id`,`label`,`value`) values (2,'Spring','spring');
insert  into `tags`(`tag_id`,`label`,`value`) values (3,'Hibernate','hibernate');
insert  into `tags`(`tag_id`,`label`,`value`) values (4,'JPA','jpa');
insert  into `tags`(`tag_id`,`label`,`value`) values (5,'jQuery','jquery');

/*Table structure for table `user_preferences` */

DROP TABLE IF EXISTS `user_preferences`;

CREATE TABLE `user_preferences` (
  `preference_id` int(11) NOT NULL AUTO_INCREMENT,
  `subscribe_to_daily_link_feed` tinyint(1) NOT NULL,
  PRIMARY KEY (`preference_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `user_preferences` */

insert  into `user_preferences`(`preference_id`,`subscribe_to_daily_link_feed`) values (1,1);
insert  into `user_preferences`(`preference_id`,`subscribe_to_daily_link_feed`) values (2,1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `disabled` tinyint(1) NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `pwd_rest_token` varchar(255) DEFAULT NULL,
  `pwd_rest_token_timeout` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `preferences_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_id` (`email_id`),
  UNIQUE KEY `username` (`username`),
  KEY `FK4D495E862677225` (`preferences_id`),
  CONSTRAINT `FK4D495E862677225` FOREIGN KEY (`preferences_id`) REFERENCES `user_preferences` (`preference_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`user_id`,`disabled`,`email_id`,`password`,`pwd_rest_token`,`pwd_rest_token_timeout`,`username`,`preferences_id`) values (1,0,'sivaprasadreddy.k@gmail.com','sivaprasad',NULL,NULL,'sivaprasadreddy.k',1);
insert  into `users`(`user_id`,`disabled`,`email_id`,`password`,`pwd_rest_token`,`pwd_rest_token_timeout`,`username`,`preferences_id`) values (2,0,'nullpointer','nullpointer4j@gmail.com',NULL,NULL,'nullpointer4j',2);

/*Table structure for table `users_roles` */

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKF6CCD9C66A759784` (`role_id`),
  KEY `FKF6CCD9C6E20E927` (`user_id`),
  CONSTRAINT `FKF6CCD9C6E20E927` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKF6CCD9C66A759784` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users_roles` */

insert  into `users_roles`(`user_id`,`role_id`) values (1,1);
insert  into `users_roles`(`user_id`,`role_id`) values (1,2);
insert  into `users_roles`(`user_id`,`role_id`) values (2,2);

/*Table structure for table `votes` */

DROP TABLE IF EXISTS `votes`;

CREATE TABLE `votes` (
  `link_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `vote_type` char(1) NOT NULL,
  PRIMARY KEY (`link_id`,`user_id`),
  KEY `FK6B30AC9E20E927` (`user_id`),
  KEY `FK6B30AC922EF3904` (`link_id`),
  CONSTRAINT `FK6B30AC922EF3904` FOREIGN KEY (`link_id`) REFERENCES `links` (`link_id`),
  CONSTRAINT `FK6B30AC9E20E927` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `votes` */

insert  into `votes`(`link_id`,`user_id`,`vote_type`) values (1,1,'U');
insert  into `votes`(`link_id`,`user_id`,`vote_type`) values (1,2,'D');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
