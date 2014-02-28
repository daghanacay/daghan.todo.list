SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `TodoListDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `TodoListDB` ;

-- -----------------------------------------------------
-- Table `TodoListDB`.`LOGIN_TABLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TodoListDB`.`LOGIN_TABLE` ;

CREATE TABLE IF NOT EXISTS `TodoListDB`.`LOGIN_TABLE` (
  `idLOGIN_TABLE` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT TRUE,
  PRIMARY KEY (`idLOGIN_TABLE`),
  UNIQUE INDEX `idLOGIN_TABLE_UNIQUE` (`idLOGIN_TABLE` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TodoListDB`.`USER_DATA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TodoListDB`.`USER_DATA` ;

CREATE TABLE IF NOT EXISTS `TodoListDB`.`USER_DATA` (
  `idUSER_DATA` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `fk_LOGIN_TABLE_id` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`idUSER_DATA`, `fk_LOGIN_TABLE_id`),
  UNIQUE INDEX `idUSER_DATA_UNIQUE` (`idUSER_DATA` ASC),
  INDEX `fk_USER_DATA_LOGIN_TABLE1_idx` (`fk_LOGIN_TABLE_id` ASC),
  CONSTRAINT `fk_USER_DATA_LOGIN_TABLE1`
    FOREIGN KEY (`fk_LOGIN_TABLE_id`)
    REFERENCES `TodoListDB`.`LOGIN_TABLE` (`idLOGIN_TABLE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TodoListDB`.`TODO_ITEMS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TodoListDB`.`TODO_ITEMS` ;

CREATE TABLE IF NOT EXISTS `TodoListDB`.`TODO_ITEMS` (
  `idTODO_ITEMS` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `detail` VARCHAR(45) NULL,
  `priority` INT NULL,
  `entered` DATETIME NULL,
  `due` DATETIME NULL,
  `fk_USER_DATA_id` INT NOT NULL,
  PRIMARY KEY (`idTODO_ITEMS`, `fk_USER_DATA_id`),
  UNIQUE INDEX `idTODO_ITEMS_UNIQUE` (`idTODO_ITEMS` ASC),
  INDEX `fk_TODO_ITEMS_USER_DATA1_idx` (`fk_USER_DATA_id` ASC),
  CONSTRAINT `fk_TODO_ITEMS_USER_DATA1`
    FOREIGN KEY (`fk_USER_DATA_id`)
    REFERENCES `TodoListDB`.`USER_DATA` (`idUSER_DATA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TodoListDB`.`USER_ROLES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TodoListDB`.`USER_ROLES` ;

CREATE TABLE IF NOT EXISTS `TodoListDB`.`USER_ROLES` (
  `idUSER_ROLES` INT NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(45) NULL,
  `fk_LOGIN_TABLE_id` INT NOT NULL,
  PRIMARY KEY (`idUSER_ROLES`, `fk_LOGIN_TABLE_id`),
  INDEX `fk_USER_ROLES_LOGIN_TABLE1_idx` (`fk_LOGIN_TABLE_id` ASC),
  CONSTRAINT `fk_USER_ROLES_LOGIN_TABLE1`
    FOREIGN KEY (`fk_LOGIN_TABLE_id`)
    REFERENCES `TodoListDB`.`LOGIN_TABLE` (`idLOGIN_TABLE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `TodoListDB`.`LOGIN_TABLE`
-- -----------------------------------------------------
START TRANSACTION;
USE `TodoListDB`;
INSERT INTO `TodoListDB`.`LOGIN_TABLE` (`idLOGIN_TABLE`, `login`, `password`, `enabled`) VALUES (1, 'daghan@cba.com', 'daghan', TRUE);
INSERT INTO `TodoListDB`.`LOGIN_TABLE` (`idLOGIN_TABLE`, `login`, `password`, `enabled`) VALUES (2, 'dilara@bcal.com', 'dilara', TRUE);
INSERT INTO `TodoListDB`.`LOGIN_TABLE` (`idLOGIN_TABLE`, `login`, `password`, `enabled`) VALUES (3, 'tayfun@abc.com', 'tayfun', TRUE);

COMMIT;


-- -----------------------------------------------------
-- Data for table `TodoListDB`.`USER_DATA`
-- -----------------------------------------------------
START TRANSACTION;
USE `TodoListDB`;
INSERT INTO `TodoListDB`.`USER_DATA` (`idUSER_DATA`, `first_name`, `last_name`, `fk_LOGIN_TABLE_id`) VALUES (1, 'Daghan', 'Acay', 1);
INSERT INTO `TodoListDB`.`USER_DATA` (`idUSER_DATA`, `first_name`, `last_name`, `fk_LOGIN_TABLE_id`) VALUES (2, 'Dilara', 'Tuna', 2);
INSERT INTO `TodoListDB`.`USER_DATA` (`idUSER_DATA`, `first_name`, `last_name`, `fk_LOGIN_TABLE_id`) VALUES (3, 'Tayfun', 'Tuna', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `TodoListDB`.`TODO_ITEMS`
-- -----------------------------------------------------
START TRANSACTION;
USE `TodoListDB`;
INSERT INTO `TodoListDB`.`TODO_ITEMS` (`idTODO_ITEMS`, `detail`, `priority`, `entered`, `due`, `fk_USER_DATA_id`) VALUES (1, 'first todo for daghan priority 1', 1, '2013-11-06 08:43:44', '2013-11-06 08:43:44', 1);
INSERT INTO `TodoListDB`.`TODO_ITEMS` (`idTODO_ITEMS`, `detail`, `priority`, `entered`, `due`, `fk_USER_DATA_id`) VALUES (2, 'second todo for daghan priority 2', 2, '2013-11-06 08:43:44', '2013-11-06 08:43:44', 1);
INSERT INTO `TodoListDB`.`TODO_ITEMS` (`idTODO_ITEMS`, `detail`, `priority`, `entered`, `due`, `fk_USER_DATA_id`) VALUES (3, 'first todo for tayfun priority 1', 1, '2013-11-06 08:43:44', '2013-11-06 08:43:44', 3);
INSERT INTO `TodoListDB`.`TODO_ITEMS` (`idTODO_ITEMS`, `detail`, `priority`, `entered`, `due`, `fk_USER_DATA_id`) VALUES (4, 'second todo for tayfun priority 2', 2, '2013-11-06 08:43:44', '2013-11-06 08:43:44', 3);
INSERT INTO `TodoListDB`.`TODO_ITEMS` (`idTODO_ITEMS`, `detail`, `priority`, `entered`, `due`, `fk_USER_DATA_id`) VALUES (5, 'third todo for daghan priority 3', 3, '2013-11-06 08:43:44', '2013-11-06 08:43:44', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `TodoListDB`.`USER_ROLES`
-- -----------------------------------------------------
START TRANSACTION;
USE `TodoListDB`;
INSERT INTO `TodoListDB`.`USER_ROLES` (`idUSER_ROLES`, `authority`, `fk_LOGIN_TABLE_id`) VALUES (1, 'AUTH', 1);
INSERT INTO `TodoListDB`.`USER_ROLES` (`idUSER_ROLES`, `authority`, `fk_LOGIN_TABLE_id`) VALUES (2, 'AUTH', 2);
INSERT INTO `TodoListDB`.`USER_ROLES` (`idUSER_ROLES`, `authority`, `fk_LOGIN_TABLE_id`) VALUES (3, 'AUTH', 3);

COMMIT;

