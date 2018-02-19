-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 18. Feb 2018 um 09:02
-- Server-Version: 10.1.10-MariaDB
-- PHP-Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `fahrradverleih`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `adresse`
--

CREATE TABLE `adresse` (
  `Straße` varchar(20) NOT NULL,
  `Ort` varchar(20) NOT NULL,
  `Hausnummer` int(11) NOT NULL,
  `PLZ` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `adresse`
--

INSERT INTO `adresse` (`Straße`, `Ort`, `Hausnummer`, `PLZ`) VALUES
('Sparbereggstraße', 'Hall in Tirol', 13, 6060),
('Robert Frey Straße', 'Wattens', 12, 6112),
('Hocheder Weg', 'Oberhofen im Inntal', 8, 6406),
('Schoberstadl', 'Reutte', 28, 6600);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `benutzer`
--

CREATE TABLE `benutzer` (
  `BenutzerID` int(11) NOT NULL,
  `Vorname` varchar(50) DEFAULT NULL,
  `Nachname` varchar(50) DEFAULT NULL,
  `PLZ` int(11) DEFAULT NULL,
  `EMail` varchar(50) DEFAULT NULL,
  `Geburtsdatum` date DEFAULT NULL,
  `Fahrradfuehrerschein` tinyint(1) DEFAULT NULL,
  `passwort` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `benutzer`
--

INSERT INTO `benutzer` (`BenutzerID`, `Vorname`, `Nachname`, `PLZ`, `EMail`, `Geburtsdatum`, `Fahrradfuehrerschein`, `passwort`) VALUES
(1, 'Verena', 'Gurtner', 6406, 'verena.gu@aon.at', '1999-05-10', 0, 'password'),
(2, 'Sara', 'Hindelang', 6600, 'sari.hindelang@gmail.com', '1998-12-14', 0, 'password'),
(3, 'David', 'Meyer', 6112, 'david.meyer@students.htlinn.ac.at', NULL, 1, 'password'),
(5, 'Thomas', 'Kerber', 6060, 'thomas.kerber@students.htlinn.ac.at', NULL, 1, 'password');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `fahrrad`
--

CREATE TABLE `fahrrad` (
  `RadID` int(11) NOT NULL,
  `Marke` varchar(50) DEFAULT NULL,
  `Art` varchar(50) DEFAULT NULL,
  `Farbe` varchar(50) DEFAULT NULL,
  `Zoll` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `fahrrad`
--

INSERT INTO `fahrrad` (`RadID`, `Marke`, `Art`, `Farbe`, `Zoll`) VALUES
(1, 'Trek', 'Jungendrad', 'weiß-türkis', 26),
(2, 'Trek', 'Alltagsfahrrad', 'schwarz', 29),
(3, 'bergamont', 'Mountainbike', 'grün', 29),
(4, 'bmc', 'Mountainbike', 'gelb', 29);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `verleih`
--

CREATE TABLE `verleih` (
  `BenutzerID` int(11) DEFAULT NULL,
  `RadID` int(11) NOT NULL DEFAULT '0',
  `DatumAUS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DatumZURUECK` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`PLZ`);

--
-- Indizes für die Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  ADD PRIMARY KEY (`BenutzerID`),
  ADD KEY `PLZ` (`PLZ`);

--
-- Indizes für die Tabelle `fahrrad`
--
ALTER TABLE `fahrrad`
  ADD PRIMARY KEY (`RadID`);

--
-- Indizes für die Tabelle `verleih`
--
ALTER TABLE `verleih`
  ADD PRIMARY KEY (`RadID`,`DatumAUS`),
  ADD KEY `BenutzerID` (`BenutzerID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  MODIFY `BenutzerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT für Tabelle `fahrrad`
--
ALTER TABLE `fahrrad`
  MODIFY `RadID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  ADD CONSTRAINT `benutzer_ibfk_1` FOREIGN KEY (`PLZ`) REFERENCES `adresse` (`PLZ`);

--
-- Constraints der Tabelle `verleih`
--
ALTER TABLE `verleih`
  ADD CONSTRAINT `verleih_ibfk_1` FOREIGN KEY (`BenutzerID`) REFERENCES `benutzer` (`BenutzerID`),
  ADD CONSTRAINT `verleih_ibfk_2` FOREIGN KEY (`RadID`) REFERENCES `fahrrad` (`RadID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
