-- -----------------------------------------------------
-- lab2 var3 by Aleksey Krazhevskiy
-- -----------------------------------------------------

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Place`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Place` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Place` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Event` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Event` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `duration` VARCHAR(45) NULL,
  `id_responsible` INT NULL,
  `Id_place` INT NOT NULL DEFAULT (1),
  `Status` ENUM('Проводится', 'Отложено', 'Отменено', 'Запланировано'),
  PRIMARY KEY (`id`, `Id_place`),
  INDEX `fk_Event_Place_idx` (`Id_place` ASC) VISIBLE,
  CONSTRAINT `fk_Event_Place`
    FOREIGN KEY (`Id_place`)
    REFERENCES `mydb`.`Place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  check ( start_date >= CURDATE() and end_date > Event.start_date ))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Organization` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Organization` (
  `idOrganization` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idOrganization`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Participant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Participant` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Participant` (
  `idParticipant` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `Event_id` INT NOT NULL,
  `Event_Id_place` INT NOT NULL,
  `id_responsible` INT NOT NULL,
  `Organization_idOrganization` INT NOT NULL,
  PRIMARY KEY (`idParticipant`, `Event_id`, `Event_Id_place`),
  INDEX `fk_Participant_Event1_idx` (`Event_id` ASC, `Event_Id_place` ASC) VISIBLE,
  INDEX `fk_Participant_Participant1_idx` (`id_responsible` ASC) VISIBLE,
  INDEX `fk_Participant_Organization1_idx` (`Organization_idOrganization` ASC) VISIBLE,
  CONSTRAINT `fk_Participant_Event1`
    FOREIGN KEY (`Event_id` , `Event_Id_place`)
    REFERENCES `mydb`.`Event` (`id` , `Id_place`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Participant_Participant1`
    FOREIGN KEY (`id_responsible`)
    REFERENCES `mydb`.`Participant` (`idParticipant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Participant_Organization1`
    FOREIGN KEY (`Organization_idOrganization`)
    REFERENCES `mydb`.`Organization` (`idOrganization`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;