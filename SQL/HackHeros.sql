CREATE TABLE `users` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(80) UNIQUE NOT NULL,
  `email` varchar(75) UNIQUE NOT NULL,
  `hash_pass` varchar(128) NOT NULL,
  `streak` int NOT NULL DEFAULT 0,
  `experience` int NOT NULL DEFAULT 0,
  `preferred_category_id` int
);

CREATE TABLE `categories` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(60) UNIQUE NOT NULL,
  `description` varchar(320)
);

CREATE TABLE `questions` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `question` varchar(220) UNIQUE NOT NULL,
  `answer_a` varchar(100) NOT NULL,
  `answer_b` varchar(100) NOT NULL,
  `answer_c` varchar(100),
  `answer_d` varchar(100),
  `correct_answer` char(1) NOT NULL,
  `exp_gain` int NOT NULL DEFAULT 10,
  `lesson_id` int NOT NULL
);

CREATE TABLE `lesson` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(60) UNIQUE NOT NULL,
  `description` varchar(320),
  `category_id` integer NOT NULL
);

CREATE TABLE `passed_lesson` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `lesson_id` integer NOT NULL,
  `completed_at` timestamp NOT NULL,
  `correct_answers` integer NOT NULL DEFAULT 0
);

CREATE TABLE `education_materials` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(120) UNIQUE,
  `lesson_id` integer NOT NULL
);

CREATE TABLE `paragraphs` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `paragraph_number` int NOT NULL,
  `header` varchar(60) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `material_id` integer NOT NULL
);

ALTER TABLE `users` ADD FOREIGN KEY (`preferred_category_id`) REFERENCES `categories` (`id`);

ALTER TABLE `lesson` ADD FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

ALTER TABLE `questions` ADD FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`);

ALTER TABLE `passed_lesson` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `passed_lesson` ADD FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`);

ALTER TABLE `education_materials` ADD FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`);

ALTER TABLE `paragraphs` ADD FOREIGN KEY (`material_id`) REFERENCES `education_materials` (`id`);
