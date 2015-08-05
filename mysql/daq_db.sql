-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema daq_db
-- -----------------------------------------------------
-- Schema de una base de datos standard para utilizada en la adquisición de datos  para multiples nodos.

-- -----------------------------------------------------
-- Schema daq_db
--
-- Schema de una base de datos standard para utilizada en la adquisición de datos  para multiples nodos.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `daq_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `daq_db` ;

-- -----------------------------------------------------
-- Table `daq_db`.`session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `daq_db`.`session` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` VARCHAR(45) NOT NULL COMMENT 'name of session',
  `init` DATETIME NULL COMMENT 'init of session',
  `end` DATETIME NULL COMMENT 'End of session',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daq_db`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `daq_db`.`tag` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT 'tag name',
  `data_type` VARCHAR(10) NOT NULL COMMENT 'Data type. Example: DI,DO, AO,AI, Streaming, etc.',
  `protocol` VARCHAR(10) NOT NULL COMMENT 'protocol used to get data.',
  `session_id` INT NULL COMMENT '',
  `config` MEDIUMTEXT NULL COMMENT 'Tag configuration should be saved as json in database.',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_tag_session1_idx` (`session_id` ASC)  COMMENT '',
  CONSTRAINT `fk_tag_session1`
    FOREIGN KEY (`session_id`)
    REFERENCES `daq_db`.`session` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daq_db`.`analog_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `daq_db`.`analog_data` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `time` DATETIME NOT NULL COMMENT 'tiempo en el que fue adquirido o enviado el dato',
  `value` FLOAT ZEROFILL NULL COMMENT '',
  `tag` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `tag`)  COMMENT '',
  INDEX `fk_data_tag_idx` (`tag` ASC)  COMMENT '',
  CONSTRAINT `fk_data_tag`
    FOREIGN KEY (`tag`)
    REFERENCES `daq_db`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daq_db`.`digital_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `daq_db`.`digital_data` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `time` DATETIME NOT NULL COMMENT 'Tiempo en el que fue adquirido o enviado el dato',
  `value` TINYINT(1) NULL COMMENT '',
  `tag` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `tag`)  COMMENT '',
  INDEX `fk_data_tag_idx` (`tag` ASC)  COMMENT '',
  CONSTRAINT `fk_data_tag0`
    FOREIGN KEY (`tag`)
    REFERENCES `daq_db`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daq_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `daq_db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(45) NOT NULL COMMENT 'used for easy identification',
  `password` VARCHAR(45) NULL COMMENT 'password',
  `firstname` VARCHAR(45) NULL COMMENT '',
  `lastname` VARCHAR(45) NULL COMMENT '',
  `email` VARCHAR(45) NULL COMMENT '',
  `phone` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daq_db`.`session_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `daq_db`.`session_has_user` (
  `session_id` INT NOT NULL COMMENT '',
  `user_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`session_id`, `user_id`)  COMMENT '',
  INDEX `fk_session_has_user_user1_idx` (`user_id` ASC)  COMMENT '',
  INDEX `fk_session_has_user_session1_idx` (`session_id` ASC)  COMMENT '',
  CONSTRAINT `fk_session_has_user_session1`
    FOREIGN KEY (`session_id`)
    REFERENCES `daq_db`.`session` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_session_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `daq_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
