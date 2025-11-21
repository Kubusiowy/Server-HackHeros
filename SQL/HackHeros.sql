-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Lis 21, 2025 at 11:50 AM
-- Wersja serwera: 8.0.40
-- Wersja PHP: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `HackHeros`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `categories`
--

CREATE TABLE `categories` (
  `id` int NOT NULL,
  `name` varchar(60) NOT NULL,
  `description` varchar(320) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`) VALUES
(1, 'Cyberbezpieczeństwo', 'Podstawy bezpiecznego korzystania z internetu'),
(2, 'Media społecznościowe', 'Bezpieczeństwo na TikToku, Instagramie i innych'),
(3, 'Fake news', 'Jak rozpoznawać fałszywe informacje w sieci'),
(4, 'xdd', 'test');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `education_materials`
--

CREATE TABLE `education_materials` (
  `id` int NOT NULL,
  `title` varchar(120) DEFAULT NULL,
  `lesson_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `education_materials`
--

INSERT INTO `education_materials` (`id`, `title`, `lesson_id`) VALUES
(1, '5 zasad tworzenia hasła', 1),
(2, 'Przykłady ataków phishingowych', 2),
(3, 'Checklista bezpieczeństwa Insta', 3),
(4, 'Jak weryfikować informacje w sieci', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Form_answers`
--

CREATE TABLE `Form_answers` (
  `User_id` int NOT NULL,
  `Question1` int NOT NULL,
  `Question2` int NOT NULL,
  `Question3` int NOT NULL,
  `Question4` int NOT NULL,
  `Question5` int NOT NULL,
  `Question6` int NOT NULL,
  `Choosed_category` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lesson`
--

CREATE TABLE `lesson` (
  `id` int NOT NULL,
  `name` varchar(60) NOT NULL,
  `description` varchar(320) DEFAULT NULL,
  `category_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `lesson`
--

INSERT INTO `lesson` (`id`, `name`, `description`, `category_id`) VALUES
(1, 'Silne hasła', 'Jak tworzyć silne i unikalne hasła', 1),
(2, 'Phishing', 'Jak nie dać się złapać na fałszywe maile i strony', 1),
(3, 'Bezpieczeństwo na Instagramie', 'Ustawienia prywatności i bezpieczeństwa', 2),
(4, 'Rozpoznawanie fake newsów', 'Proste techniki sprawdzania informacji', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `paragraphs`
--

CREATE TABLE `paragraphs` (
  `id` int NOT NULL,
  `paragraph_number` int NOT NULL,
  `header` varchar(60) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `material_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `paragraphs`
--

INSERT INTO `paragraphs` (`id`, `paragraph_number`, `header`, `content`, `material_id`) VALUES
(1, 1, 'Dlaczego hasło jest ważne?', 'Hasło to pierwsza linia obrony Twojego konta. Słabe hasło = łatwe przejęcie konta.', 1),
(2, 2, 'Zasada długości', 'Dobre hasło ma minimum 12 znaków i łączy litery, cyfry oraz znaki specjalne.', 1),
(3, 1, 'Czym jest phishing?', 'Phishing to próba wyłudzenia Twoich danych, np. przez fałszywe strony logowania.', 2),
(4, 1, 'Prywatne konto', 'Ustawienie konta jako prywatne ogranicza dostęp do Twoich zdjęć i relacji.', 3),
(5, 1, 'Sprawdzaj źródło', 'Zanim udostępnisz news, sprawdź, czy pochodzi z wiarygodnego serwisu.', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `passed_lesson`
--

CREATE TABLE `passed_lesson` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `lesson_id` int NOT NULL,
  `completed_at` timestamp NOT NULL,
  `correct_answers` int NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `passed_lesson`
--

INSERT INTO `passed_lesson` (`id`, `user_id`, `lesson_id`, `completed_at`, `correct_answers`) VALUES
(1, 1, 1, '2025-11-21 08:10:00', 2),
(2, 1, 2, '2025-11-21 08:20:00', 1),
(3, 2, 4, '2025-11-21 08:25:00', 1),
(4, 1, 1, '2025-11-21 09:22:34', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `questions`
--

CREATE TABLE `questions` (
  `id` int NOT NULL,
  `question` varchar(220) NOT NULL,
  `answer_a` varchar(100) NOT NULL,
  `answer_b` varchar(100) NOT NULL,
  `answer_c` varchar(100) DEFAULT NULL,
  `answer_d` varchar(100) DEFAULT NULL,
  `correct_answer` char(1) NOT NULL,
  `exp_gain` int NOT NULL DEFAULT '10',
  `lesson_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `question`, `answer_a`, `answer_b`, `answer_c`, `answer_d`, `correct_answer`, `exp_gain`, `lesson_id`) VALUES
(1, 'Które hasło jest NAJBEZPIECZNIEJSZE?', 'kuba123', 'Haslo123!', 'M0cne_haslo!2025', 'qwerty2024', 'C', 10, 1),
(2, 'Co z poniższych jest dobrym nawykiem przy hasłach?', 'Używanie tego samego hasła wszędzie', 'Zapisywanie hasła na kartce przy monitorze', 'Włączony menedżer haseł i unikalne hasło do każdej strony', 'Podawanie hasła znajomym, którym ufasz', 'C', 10, 1),
(3, 'Dostałeś maila z linkiem do “logowania do banku”. Co robisz?', 'Klikam od razu, przecież to bank', 'Sprawdzam adres nadawcy i wpisuję adres banku ręcznie w przeglądarce', 'Podaję login i hasło, żeby mieć spokój', 'Przesyłam maila znajomym, żeby też zobaczyli', 'B', 10, 2),
(4, 'Co pomoże Ci zwiększyć bezpieczeństwo na Instagramie?', 'Udostępnianie lokalizacji przy każdym zdjęciu', 'Ustawienie konta jako publiczne', 'Silne hasło + włączona dwuskładnikowa weryfikacja (2FA)', 'Logowanie na swoje konto na obcych komputerach', 'C', 10, 3),
(5, 'Widzisz sensacyjnego newsa, który pojawia się tylko w jednym dziwnym serwisie. Co robisz?', 'Od razu udostępniam, żeby inni też zobaczyli', 'Sprawdzam w kilku różnych źródłach, zanim uwierzę', 'Wierzę, jeśli zgadza się z moją opinią', 'Piszę w komentarzu, że “na pewno to prawda”', 'B', 10, 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(80) NOT NULL,
  `email` varchar(75) NOT NULL,
  `hash_pass` varchar(128) NOT NULL,
  `streak` int NOT NULL DEFAULT '0',
  `experience` int NOT NULL DEFAULT '0',
  `preferred_category_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `hash_pass`, `streak`, `experience`, `preferred_category_id`) VALUES
(1, 'kuba', 'kuba@example.com', 'hashed_password_1', 3, 120, 1),
(2, 'anna', 'anna@example.com', 'hashed_password_2', 1, 40, 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indeksy dla tabeli `education_materials`
--
ALTER TABLE `education_materials`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`),
  ADD KEY `lesson_id` (`lesson_id`);

--
-- Indeksy dla tabeli `Form_answers`
--
ALTER TABLE `Form_answers`
  ADD PRIMARY KEY (`User_id`),
  ADD KEY `Choosed_category` (`Choosed_category`);

--
-- Indeksy dla tabeli `lesson`
--
ALTER TABLE `lesson`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `category_id` (`category_id`);

--
-- Indeksy dla tabeli `paragraphs`
--
ALTER TABLE `paragraphs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `material_id` (`material_id`);

--
-- Indeksy dla tabeli `passed_lesson`
--
ALTER TABLE `passed_lesson`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `lesson_id` (`lesson_id`);

--
-- Indeksy dla tabeli `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `question` (`question`),
  ADD KEY `lesson_id` (`lesson_id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `preferred_category_id` (`preferred_category_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `education_materials`
--
ALTER TABLE `education_materials`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `lesson`
--
ALTER TABLE `lesson`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `paragraphs`
--
ALTER TABLE `paragraphs`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `passed_lesson`
--
ALTER TABLE `passed_lesson`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `education_materials`
--
ALTER TABLE `education_materials`
  ADD CONSTRAINT `education_materials_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`);

--
-- Constraints for table `Form_answers`
--
ALTER TABLE `Form_answers`
  ADD CONSTRAINT `form_answers_ibfk_1` FOREIGN KEY (`Choosed_category`) REFERENCES `categories` (`id`);

--
-- Constraints for table `lesson`
--
ALTER TABLE `lesson`
  ADD CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `paragraphs`
--
ALTER TABLE `paragraphs`
  ADD CONSTRAINT `paragraphs_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `education_materials` (`id`);

--
-- Constraints for table `passed_lesson`
--
ALTER TABLE `passed_lesson`
  ADD CONSTRAINT `passed_lesson_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `passed_lesson_ibfk_2` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`);

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`preferred_category_id`) REFERENCES `categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
