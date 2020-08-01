-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema private_announcement_systemdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `private_announcement_systemdb` DEFAULT CHARACTER SET utf8 ;
USE `private_announcement_systemdb` ;

-- -----------------------------------------------------
-- Table `private_announcement_systemdb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `private_announcement_systemdb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(120) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phoneNumber` VARCHAR(45) NULL DEFAULT NULL,
  `rating` DOUBLE NULL DEFAULT '0',
  `region` VARCHAR(70) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8;
INSERT INTO private_announcement_systemdb.user(userName, password,email,role) VALUES("Admin", "$2a$10$F1ztHb6L2CSk6xVMEN9f/.jPDEj.q3xbMldxG1qwN4.XDKGq1Ba0y", "admin@admin.com","ADMIN");


-- -----------------------------------------------------
-- Table `private_announcement_systemdb`.`announcement_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `private_announcement_systemdb`.`announcement_category` (
  `idannouncement_category` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idannouncement_category`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `private_announcement_systemdb`.`announcement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `private_announcement_systemdb`.`announcement` (
  `idannouncement` INT NOT NULL AUTO_INCREMENT,
  `header` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `isTop` TINYINT NOT NULL DEFAULT '0',
  `creationDate` DATETIME NOT NULL,
  `paymentDate` DATETIME NULL DEFAULT NULL,
  `isActive` TINYINT NOT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `announcement_category_id` INT NOT NULL,
  PRIMARY KEY (`idannouncement`),
  CONSTRAINT `fk_announcement_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `private_announcement_systemdb`.`user` (`id`),
  CONSTRAINT `FKnqw3rjmnqe1uf02nk9k0jctkg`
    FOREIGN KEY (`announcement_category_id`)
    REFERENCES `private_announcement_systemdb`.`announcement_category` (`idannouncement_category`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `private_announcement_systemdb`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `private_announcement_systemdb`.`comment` (
  `idcomment` INT NOT NULL AUTO_INCREMENT,
  `comment_text` VARCHAR(200) NOT NULL,
  `publication_date` DATETIME NULL DEFAULT NULL,
  `announcement_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`idcomment`),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `private_announcement_systemdb`.`user` (`id`),
  CONSTRAINT `FKeesiagm3hwe8l0oosrb17cjyn`
    FOREIGN KEY (`announcement_id`)
    REFERENCES `private_announcement_systemdb`.`announcement` (`idannouncement`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `private_announcement_systemdb`.`credit_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `private_announcement_systemdb`.`credit_card` (
  `idcredit_card` INT NOT NULL AUTO_INCREMENT,
  `cvv_code` VARCHAR(45) NOT NULL,
  `valid_thru` VARCHAR(45) NOT NULL,
  `owner_name` VARCHAR(45) NOT NULL,
  `owner_surname` VARCHAR(45) NOT NULL,
  `card_number` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`idcredit_card`),
  CONSTRAINT `FKh4wi9724muee2kp2c4ku1yia2`
    FOREIGN KEY (`user_id`)
    REFERENCES `private_announcement_systemdb`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `private_announcement_systemdb`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `private_announcement_systemdb`.`message` (
  `idmessage` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(500) NOT NULL,
  `publication_date` DATETIME NULL DEFAULT NULL,
  `user_id_from` INT NOT NULL,
  PRIMARY KEY (`idmessage`),
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`user_id_from`)
    REFERENCES `private_announcement_systemdb`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `private_announcement_systemdb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `private_announcement_systemdb`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(245) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;
INSERT INTO private_announcement_systemdb.role(name) VALUES("ROLE_USER");
INSERT INTO private_announcement_systemdb.role(name) VALUES("ROLE_ADMIN");

-- -----------------------------------------------------
-- Table `private_announcement_systemdb`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `private_announcement_systemdb`.`user_role` (
  `user_id` INT NOT NULL,
  `roles_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `roles_id`),
  INDEX `FKeog8p06nu33ihk13roqnrp1y6` (`roles_id` ASC) VISIBLE,
  CONSTRAINT `FKeog8p06nu33ihk13roqnrp1y6`
    FOREIGN KEY (`roles_id`)
    REFERENCES `private_announcement_systemdb`.`role` (`id`),
  CONSTRAINT `FKs51iigign85r46j35s8npjs02`
    FOREIGN KEY (`user_id`)
    REFERENCES `private_announcement_systemdb`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO private_announcement_systemdb.user_role(user_id,roles_id) VALUES(1,1);
INSERT INTO private_announcement_systemdb.user_role(user_id,roles_id) VALUES(1,2);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
