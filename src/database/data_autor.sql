-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 24. Jul 2016 um 14:54
-- Server-Version: 10.1.13-MariaDB
-- PHP-Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `bookstore`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `autor`
--

CREATE TABLE `autor` (
  `aid` int(11) NOT NULL,
  `vorname` varchar(64) DEFAULT NULL,
  `nachname` varchar(64) DEFAULT NULL,
  `geburtsdatum` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `autor`
--

INSERT INTO `autor` (`aid`, `vorname`, `nachname`, `geburtsdatum`) VALUES
(1, 'Joe', 'Abercrombie', '1974-12-31'),
(2, 'George Raymond Richard', 'Martin ', '1948-09-20'),
(3, 'John Ronald Reuel', 'Tolkien', '1892-01-03'),
(4, 'Stephen', 'King', '1947-09-21'),
(5, 'Bram', 'Stoker', '1847-11-08'),
(6, 'Philip Kindred', 'Dick', '1928-12-16'),
(7, 'Orson Scott', 'Card', '1951-08-24'),
(8, 'Douglas', 'Adams', '1952-03-11'),
(9, 'John', 'Grisham', '1955-02-08'),
(10, 'Tom', 'Clancy', '1947-04-12'),
(11, 'Ayn', 'Rand', '1905-02-02');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`aid`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `autor`
--
ALTER TABLE `autor`
  MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
