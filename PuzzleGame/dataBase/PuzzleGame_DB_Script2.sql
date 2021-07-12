-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema PuzzleGame
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema PuzzleGame
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PuzzleGame` DEFAULT CHARACTER SET utf8 ;
USE `PuzzleGame` ;

-- -----------------------------------------------------
-- Table `PuzzleGame`.`Player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PuzzleGame`.`Player` (
  `player_id` INT NOT NULL AUTO_INCREMENT,
  `player_firstName` VARCHAR(50) NOT NULL,
  `player_lastName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`player_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PuzzleGame`.`Game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PuzzleGame`.`Game` (
  `game_id` INT NOT NULL AUTO_INCREMENT,
  `game_time` INT NOT NULL,
  `game_date` DATE NOT NULL,
  `game_category` VARCHAR(50) NOT NULL,
  `Player_player_id` INT NOT NULL,
  PRIMARY KEY (`game_id`),
  INDEX `fk_Game_Player1_idx` (`Player_player_id` ASC) VISIBLE,
  CONSTRAINT `fk_Game_Player1`
    FOREIGN KEY (`Player_player_id`)
    REFERENCES `PuzzleGame`.`Player` (`player_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PuzzleGame`.`Move`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PuzzleGame`.`Move` (
  `move_id` INT NOT NULL AUTO_INCREMENT,
  `move_position` VARCHAR(45) NOT NULL,
  `Game_game_id` INT NOT NULL,
  PRIMARY KEY (`move_id`),
  INDEX `fk_Move_Game_idx` (`Game_game_id` ASC) VISIBLE,
  CONSTRAINT `fk_Move_Game`
    FOREIGN KEY (`Game_game_id`)
    REFERENCES `PuzzleGame`.`Game` (`game_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

##CRIACAO DE VIEWS 
CREATE VIEW playerStats_OrdBy_Name
AS
SELECT 
	p.player_firstName AS firstName,
    p.player_lastName AS lastName,
    g.game_date AS date,
    g.game_id AS game,
    g.game_category AS category,
	g.game_time AS time
FROM Player p
INNER JOIN Game g
ON p.player_id = g.game_id   
ORDER BY p.player_firstName,player_lastName ; 


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
