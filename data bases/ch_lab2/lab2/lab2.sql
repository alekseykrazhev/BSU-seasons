-- lab2 by Aleksey Krazhevskiy

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
-- Table `mydb`.`SPECIALITY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`SPECIALITY` ;

CREATE TABLE IF NOT EXISTS `mydb`.`SPECIALITY` (
  `Code_Spec` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(45) NULL,
  PRIMARY KEY (`Code_Spec`),
  UNIQUE INDEX `Code_Spec_UNIQUE` (`Code_Spec` ASC) VISIBLE)
AUTO_INCREMENT=1401,
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DOCTOR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`DOCTOR` ;

CREATE TABLE IF NOT EXISTS `mydb`.`DOCTOR` (
  `ID_Doctor` INT NOT NULL,
  `Surname` VARCHAR(45) NULL,
  `Name` VARCHAR(45) NULL,
  `Middle_Name` VARCHAR(45) NULL,
  `Doctor_Spec` INT NOT NULL,
  PRIMARY KEY (`ID_Doctor`),
  CONSTRAINT `fk_DOCTOR_SPECIALITY`
    FOREIGN KEY (`Doctor_Spec`)
    REFERENCES `mydb`.`SPECIALITY` (`Code_Spec`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PATIENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PATIENT` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PATIENT` (
  `N_Card` INT NOT NULL,
  `Surname` VARCHAR(45) NULL,
  `Name` VARCHAR(45) NULL,
  `Middle_Name` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `Phone` VARCHAR(45) NULL,
  PRIMARY KEY (`N_Card`),
  UNIQUE INDEX `N_Card_UNIQUE` (`N_Card` ASC) VISIBLE,
  CHECK ( REGEXP_LIKE(Phone, '^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$')))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SERVICES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`SERVICES` ;

CREATE TABLE IF NOT EXISTS `mydb`.`SERVICES` (
  `Code_Srv` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(45) NULL,
  `Price` FLOAT NULL,
  PRIMARY KEY (`Code_Srv`),
  UNIQUE INDEX `Code_Srv_UNIQUE` (`Code_Srv` ASC) VISIBLE,
  CHECK ( Price <= 1000.0 ))
AUTO_INCREMENT=110,
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`VISIT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`VISIT` ;

CREATE TABLE IF NOT EXISTS `mydb`.`VISIT` (
  `ID_Doctor` INT NOT NULL,
  `ID_Pacient` INT NOT NULL,
  `Date_Visit` DATE NULL,
  `N_Visit` INT NOT NULL,
  `SERVICES_Code_Srv` INT NOT NULL,
  PRIMARY KEY (`N_Visit`, `ID_Doctor`),
  INDEX `fk_VISIT_SERVICES1_idx` (`SERVICES_Code_Srv` ASC) VISIBLE,
  CONSTRAINT `fk_VISIT_PATIENT1`
    FOREIGN KEY (`ID_Pacient`)
    REFERENCES `mydb`.`PATIENT` (`N_Card`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VISIT_DOCTOR1`
    FOREIGN KEY (`ID_Doctor`)
    REFERENCES `mydb`.`DOCTOR` (`ID_Doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VISIT_SERVICES1`
    FOREIGN KEY (`SERVICES_Code_Srv`)
    REFERENCES `mydb`.`SERVICES` (`Code_Srv`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CHECK ( Date_Visit <= '2022/12/31' and Date_Visit >= '2022/01/01'))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;