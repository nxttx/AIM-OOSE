-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Gegenereerd op: 26 mrt 2021 om 15:14
-- Serverversie: 10.4.10-MariaDB
-- PHP-versie: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spotitube`
--
CREATE DATABASE IF NOT EXISTS `spotitube` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `spotitube`;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `playlist`
--

DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist` (
  `id` int(11) NOT NULL,
  `Naam` varchar(50) NOT NULL,
  `Eigenaar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `playlist`
--

INSERT INTO `playlist` (`id`, `Naam`, `Eigenaar`) VALUES
(10, 'Pop', 1),
(11, 'Rock', 1),
(15, 'House', 2),
(16, 'Death metal', 1);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `track`
--

DROP TABLE IF EXISTS `track`;
CREATE TABLE `track` (
  `id` int(11) NOT NULL,
  `Performer` varchar(75) NOT NULL,
  `Title` varchar(75) NOT NULL,
  `Duration` int(11) NOT NULL,
  `Album` varchar(75) DEFAULT NULL,
  `PublicationDate` date DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `playcount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `track`
--

INSERT INTO `track` (`id`, `Performer`, `Title`, `Duration`, `Album`, `PublicationDate`, `Description`, `playcount`) VALUES
(1, 'U2', 'Song for someone', 350, 'The cost', NULL, NULL, NULL),
(2, 'The Frames', 'The cost', 423, 'The cost', '2006-03-19', 'Title song from the Album The Cost', 37),
(3, 'Lisa Hannigan', 'Ocean and a rock', 337, 'Sea sew', NULL, NULL, NULL),
(4, 'Britney Spears', 'Gimme More', 251, 'Blackout', '2007-10-25', NULL, 76),
(5, 'Fergie, Ludacris', 'Glamorous', 246, 'The Dutchess', '2006-01-01', NULL, 69),
(6, 'Mis-Teeq', 'Scandalous - U.S. Radio Edit', 238, 'Mis-Teeq', '2004-07-13', NULL, 57),
(7, 'Robyn, Kleerup', 'With Every Heartbeat', 255, 'Robyn', '2005-04-27', NULL, 44),
(8, 'Rihanna', 'Only Girl (In The World)', 235, 'Loud', '2010-11-16', NULL, 54),
(9, 'Jane Child', 'Don\'t Wanna Fall in Love', 247, 'Jane Child', NULL, NULL, 51),
(10, 'Kelly Rowland', 'Work - Freemasons Club Mix', 637, 'Work (Remix Bundle)', '2008-03-25', NULL, 28),
(11, 'Daryl Hall & John Oates', 'Adult Education', 323, 'Rock \'N Soul, Part 1 (Expanded Edition)', NULL, NULL, 50),
(12, 'Pet Shop Boys', 'West End Girls - 2001 Remaster', 245, 'Please: Further Listening 1984-1986', '1986-03-24', NULL, 43),
(13, 'Wham!', 'Everything She Wants', 302, 'Make It Big', '1984-10-23', NULL, 65),
(14, 'All Saints', 'Pure Shores', 268, 'The Beach (Original Motion Picture Soundtrack)', '2000-02-01', NULL, 0),
(15, 'Corona', 'The Rhythm of the Night', 264, 'Disco 90\'s', '2008-03-16', NULL, 58),
(16, 'N Joi', 'Anthem', 198, '90s 100 Hits', '2014-11-28', NULL, 37),
(17, 'Stardust', 'Music Sounds Better With You - 12\" Club Mix', 235, 'Total Music: Dance Classics Vol. 1', '2010-01-01', NULL, 0),
(18, 'Modjo', 'Lady (Hear Me Tonight)', 274, 'Vintage Ibiza Classics', '1905-07-05', NULL, 0),
(19, 'Amerie', '1 Thing', 238, 'Touch', NULL, NULL, 61),
(20, 'The Blow Monkeys, Kym Mazelle', 'Wait (feat. Kym Mazelle)', 189, 'Whoops! There Goes the Neighbourhood', '1989-09-26', NULL, 27),
(21, 'Sly Fox', 'Let\'s Go All The Way', 239, 'Lost Hits Of The 80\'s', '2012-01-01', NULL, 43),
(22, 'Taylor Dayne', 'Tell It to My Heart', 220, 'Tell It To My Heart', '1988-01-19', NULL, 0),
(23, 'Living In A Box', 'Living in a Box', 186, 'Living in a Box', '1987-05-09', NULL, 50),
(24, 'INXS', 'New Sensation', 219, 'Kick', NULL, NULL, 55),
(25, 'Bobby Brown', 'On Our Own', 294, 'Ghostbusters II', '1989-06-16', NULL, 49),
(26, 'Bronski Beat', 'Smalltown Boy', 303, 'The Age Of Consent', NULL, NULL, 0),
(27, 'Naked Eyes', 'Promises, Promises - Single Edit', 225, 'Best Of', '1991-04-19', NULL, 42),
(28, 'Sneaker Pimps', '6 Underground - Nellee Hooper Mix', 234, 'Becoming X', NULL, NULL, 0),
(29, 'Backstreet Boys', 'I Want It That Way', 213, 'The Essential Backstreet Boys', '2013-09-26', NULL, 0),
(30, 'Jamiroquai', 'Alright - Remastered', 263, 'Travelling Without Moving', '1996-09-09', NULL, 0),
(31, 'Morcheeba', 'Tape Loop', 262, 'Who Can You Trust? / Beats & B-Sides', '1996-09-20', NULL, 40),
(32, 'Moloko', 'The Time Is Now', 318, 'Things to Make and Do', '2000-10-31', NULL, 55),
(33, 'Gorillaz', 'Feel Good Inc.', 222, 'Demon Days', '2005-05-23', NULL, 81),
(34, 'Robbie Williams, Kylie Minogue', 'Kids', 260, 'Light Years', '2000-09-25', NULL, 49),
(35, 'Dirty Vegas', 'Days Go By', 432, 'Dirty Vegas', '2002-06-04', NULL, 0),
(36, 'Cassie', 'Me & U', 192, 'Cassie (U.S. Version)', '2006-08-07', NULL, 72),
(37, 'Maroon 5, Christina Aguilera', 'Moves Like Jagger - Studio Recording From The Voice Performance', 201, '20 #1\'s: Party Hits', '2016-12-30', NULL, 28),
(38, 'M.I.A.', 'Bad Girls', 227, 'Matangi', '2013-01-01', NULL, 68),
(39, 'M83', 'Midnight City', 241, 'Hurry Up, We\'re Dreaming', NULL, NULL, 72),
(40, 'Lady Gaga', 'Applause', 212, 'ARTPOP', '2013-01-01', NULL, 0),
(41, 'Mike Posner, Gigamesh', 'Cooler Than Me - Single Mix', 213, '31 Minutes to Takeoff', '2010-08-09', NULL, 73),
(42, 'Lorde', 'Tennis Court', 198, 'Pure Heroine', '2013-09-27', NULL, 61),
(43, 'Black Eyed Peas', 'Meet Me Halfway', 284, 'THE E.N.D. (THE ENERGY NEVER DIES)', '2009-01-01', NULL, 66),
(44, 'Real Life', 'Send Me An Angel', 236, 'Heartland', '1983-01-01', NULL, 56);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `track_in_playlist`
--

DROP TABLE IF EXISTS `track_in_playlist`;
CREATE TABLE `track_in_playlist` (
  `id` int(11) NOT NULL,
  `Playlist_id` int(11) NOT NULL,
  `Track_id` int(11) NOT NULL,
  `OfflineAvailable` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `track_in_playlist`
--

INSERT INTO `track_in_playlist` (`id`, `Playlist_id`, `Track_id`, `OfflineAvailable`) VALUES
(11, 10, 1, 0),
(12, 10, 4, 0),
(13, 10, 5, 0),
(14, 10, 7, 0),
(15, 11, 13, 0),
(16, 11, 33, 0),
(17, 10, 2, 1);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `UserPassword` varchar(255) NOT NULL,
  `Token` varchar(64) DEFAULT NULL,
  `dateOfToken` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `users`
--

INSERT INTO `users` (`id`, `Username`, `UserPassword`, `Token`, `dateOfToken`) VALUES
(1, 'RobertBoudewijn', 'Pass', '1616515808998', '0000-00-00'),
(2, 'meron', 'MySuperSecretPassword12341', '1616515774503', NULL);

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_playlist_user` (`Eigenaar`);

--
-- Indexen voor tabel `track`
--
ALTER TABLE `track`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `track_in_playlist`
--
ALTER TABLE `track_in_playlist`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Playlist_id` (`Playlist_id`,`Track_id`),
  ADD KEY `fk_track` (`Track_id`);

--
-- Indexen voor tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `playlist`
--
ALTER TABLE `playlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT voor een tabel `track`
--
ALTER TABLE `track`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT voor een tabel `track_in_playlist`
--
ALTER TABLE `track_in_playlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT voor een tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `FK_playlist_user` FOREIGN KEY (`Eigenaar`) REFERENCES `users` (`id`);

--
-- Beperkingen voor tabel `track_in_playlist`
--
ALTER TABLE `track_in_playlist`
  ADD CONSTRAINT `fk_Playlist_id` FOREIGN KEY (`Playlist_id`) REFERENCES `playlist` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_track` FOREIGN KEY (`Track_id`) REFERENCES `track` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
