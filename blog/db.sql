CREATE DATABASE test;
USE test;

CREATE TABLE `test`.`user` (
  `id` INT NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `token` VARCHAR(250) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `test`.`post` (
  `id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `body` VARCHAR(300) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`));