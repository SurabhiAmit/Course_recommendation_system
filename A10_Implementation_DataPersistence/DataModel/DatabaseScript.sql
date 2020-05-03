SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema A10db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `A10db` DEFAULT CHARACTER SET utf8 ;
USE `A10db` ;

-- -----------------------------------------------------
-- Table `A10db`.`DegreeProgram`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`DegreeProgram` (
  `name` VARCHAR(255) NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `A10db`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`Student` (
  `uusid` BIGINT NOT NULL,
  `studentName` VARCHAR(255) NOT NULL,
  `homeAddress` VARCHAR(255) NOT NULL,
  `phoneNumber` VARCHAR(10) NULL,
  `DegreeProgram_id` INT NOT NULL,
  PRIMARY KEY (`uusid`),
  CONSTRAINT `fk_Student_DegreeProgram1`
    FOREIGN KEY (`DegreeProgram_id`)
    REFERENCES `A10db`.`DegreeProgram` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Student_DegreeProgram1_idx` ON `A10db`.`Student` (`DegreeProgram_id` ASC);


-- -----------------------------------------------------
-- Table `A10db`.`Course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`Course` (
  `courseid` BIGINT NOT NULL,
  `courseTitle` VARCHAR(100) NOT NULL,
  `courseDescription` VARCHAR(255) NOT NULL,
  `courseCost` DECIMAL NOT NULL,
  PRIMARY KEY (`courseid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `A10db`.`AcademicRecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`AcademicRecord` (
  `term` INT NOT NULL,
  `year` INT NOT NULL,
  `uusid` BIGINT NOT NULL,
  `courseid` BIGINT NOT NULL,
  `grade` VARCHAR(2) NOT NULL,
  CONSTRAINT `fk_AcademicRecord_Student1`
    FOREIGN KEY (`uusid`)
    REFERENCES `A10db`.`Student` (`uusid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AcademicRecord_Course1`
    FOREIGN KEY (`courseid`)
    REFERENCES `A10db`.`Course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_AcademicRecord_Student1_idx` ON `A10db`.`AcademicRecord` (`uusid` ASC);

CREATE INDEX `fk_AcademicRecord_Course1_idx` ON `A10db`.`AcademicRecord` (`courseid` ASC);


-- -----------------------------------------------------
-- Table `A10db`.`Instructor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`Instructor` (
  `uuiid` BIGINT NOT NULL,
  `instructorName` VARCHAR(255) NOT NULL,
  `emailAddress` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`uuiid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `A10db`.`CourseOffering`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`CourseOffering` (
  `id` INT NOT NULL,
  `courseid` BIGINT NOT NULL,
  `uuiid` BIGINT NOT NULL,
  `term` INT NOT NULL,
  `year` INT NOT NULL,
  `totalstudents` INT NOT NULL,
  PRIMARY KEY (`id`, `courseid`),
  CONSTRAINT `fk_CourseOffering_Course1`
    FOREIGN KEY (`courseid`)
    REFERENCES `A10db`.`Course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CourseOffering_Instructor1`
    FOREIGN KEY (`uuiid`)
    REFERENCES `A10db`.`Instructor` (`uuiid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_CourseOffering_Course1_idx` ON `A10db`.`CourseOffering` (`courseid` ASC);

CREATE INDEX `fk_CourseOffering_Instructor1_idx` ON `A10db`.`CourseOffering` (`uuiid` ASC);


-- -----------------------------------------------------
-- Table `A10db`.`InstructorOfficeHours`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`InstructorOfficeHours` (
  `id` INT NOT NULL,
  `uuiid` BIGINT NOT NULL,
  `officeHour` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_InstructorOfficeHours_Instructor`
    FOREIGN KEY (`uuiid`)
    REFERENCES `A10db`.`Instructor` (`uuiid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_InstructorOfficeHours_Instructor_idx` ON `A10db`.`InstructorOfficeHours` (`uuiid` ASC);


-- -----------------------------------------------------
-- Table `A10db`.`CoursePrerequisites`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`CoursePrerequisites` (
  `prerequisiteid` INT NOT NULL,
  `id` INT NOT NULL,
  `courseid` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_CoursePrerequisites_Course1`
    FOREIGN KEY (`courseid`)
    REFERENCES `A10db`.`Course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_CoursePrerequisites_Course1_idx` ON `A10db`.`CoursePrerequisites` (`courseid` ASC);


-- -----------------------------------------------------
-- Table `A10db`.`CourseCatalog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`CourseCatalog` (
  `id` INT NOT NULL,
  `term` INT NOT NULL,
  `year` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `A10db`.`CourseCatalogCourseList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`CourseCatalogCourseList` (
  `id` INT NOT NULL,
  `coursecatalogid` INT NOT NULL,
  `courseid` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_CourseCatalogCourseList_CourseCatalog1`
    FOREIGN KEY (`coursecatalogid`)
    REFERENCES `A10db`.`CourseCatalog` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CourseCatalogCourseList_Course1`
    FOREIGN KEY (`courseid`)
    REFERENCES `A10db`.`Course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_CourseCatalogCourseList_CourseCatalog1_idx` ON `A10db`.`CourseCatalogCourseList` (`coursecatalogid` ASC);

CREATE INDEX `fk_CourseCatalogCourseList_Course1_idx` ON `A10db`.`CourseCatalogCourseList` (`courseid` ASC);


-- -----------------------------------------------------
-- Table `A10db`.`DegreeProgramCourses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`DegreeProgramCourses` (
  `id` INT NOT NULL,
  `DegreeProgram_id` INT NOT NULL,
  `courseid` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_DegreeProgramCourses_DegreeProgram1`
    FOREIGN KEY (`DegreeProgram_id`)
    REFERENCES `A10db`.`DegreeProgram` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DegreeProgramCourses_Course1`
    FOREIGN KEY (`courseid`)
    REFERENCES `A10db`.`Course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_DegreeProgramCourses_DegreeProgram1_idx` ON `A10db`.`DegreeProgramCourses` (`DegreeProgram_id` ASC);

CREATE INDEX `fk_DegreeProgramCourses_Course1_idx` ON `A10db`.`DegreeProgramCourses` (`courseid` ASC);


-- -----------------------------------------------------
-- Table `A10db`.`ApplicationState`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `A10db`.`ApplicationState` (
  `id` INT NOT NULL,
  `currentmode` INT NOT NULL,
  `currentlyProcessingTerm` INT NOT NULL,
  `currentlyProcessingFile` VARCHAR(100) NOT NULL,
  `simulationstep` INT NOT NULL,
  `filesToProcess` VARCHAR(255) NOT NULL,
  `filesProcessed` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
