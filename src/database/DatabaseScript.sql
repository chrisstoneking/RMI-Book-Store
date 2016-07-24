-- Host: 127.0.0.1
-- Erstellungszeit: 24. Jul 2016 um 16:55
-- Server-Version: 10.1.13-MariaDB


--
-- Datenbank: `bookstore`
--

SET foreign_key_checks=0; 

DROP TABLE IF EXISTS autor CASCADE; 

DROP TABLE IF EXISTS buch CASCADE; 

DROP TABLE IF EXISTS kaeufer CASCADE; 

DROP TABLE IF EXISTS verfassen CASCADE; 

DROP TABLE IF EXISTS kauf CASCADE; 

SET foreign_key_checks=1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `autor`
--

CREATE TABLE autor 
  ( 
     aid          INT PRIMARY KEY auto_increment, 
     vorname      VARCHAR(64), 
     nachname     VARCHAR(64), 
     geburtsdatum DATE 
  ); 

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kaeufer`
--

CREATE TABLE kaeufer 
  ( 
     kid          INT PRIMARY KEY auto_increment, 
     vorname      VARCHAR(64), 
     nachname     VARCHAR(64), 
     geburtsdatum DATE 
  ); 

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `buch`
--

CREATE TABLE buch 
  ( 
     isb_nr       VARCHAR(32), 
     titel        VARCHAR(64), 
     preis        DECIMAL(6, 2), 
     aid          INT, 
     kategorie    VARCHAR(64),
     cover        MEDIUMBLOB,
     beschreibung TEXT
  ); 

ALTER TABLE buch 
  ADD CONSTRAINT buch_pk PRIMARY KEY (isb_nr); 

ALTER TABLE buch 
  ADD CONSTRAINT buch_aid_fk FOREIGN KEY (aid) REFERENCES autor(aid); 

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kauf`
--

CREATE TABLE kauf 
  ( 
     isb_nr VARCHAR(32), 
     kid    INTEGER 
  ); 

ALTER TABLE kauf 
  ADD CONSTRAINT kauf_pk PRIMARY KEY (isb_nr, kid); 

ALTER TABLE kauf 
  ADD CONSTRAINT kauf_isb_nr_fk FOREIGN KEY (isb_nr) REFERENCES buch(isb_nr); 

ALTER TABLE kauf 
  ADD CONSTRAINT kauf_kid_fk FOREIGN KEY (kid) REFERENCES kaeufer(kid);   