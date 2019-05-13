-- Lav vagtplan table med kunder og medarbejder i som foreign keys
-- Vagtplan skal have id, tid og dato

CREATE DATABASE IF NOT EXISTS SCS_db;
USE SCS_db;

CREATE TABLE brugere
(
id  INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
username 	VARCHAR(30) NOT NULL,
password 	VARCHAR(30) NOT NULL,
status 		VARCHAR(10) NOT NULL DEFAULT 'user'
);

INSERT INTO brugere VALUES
(DEFAULT, 'Admin', 'Admin', 'Admin'),

(DEFAULT, 'Kagebageren', '1234', DEFAULT),
(DEFAULT, 'Hansen31', '1234', DEFAULT),
(DEFAULT, 'Krammermanden', '1234', DEFAULT),
(DEFAULT, 'Sindzz01', '1234', DEFAULT),
(DEFAULT, 'Brormanden', '1234', DEFAULT);

CREATE TABLE kunder
(
id 				INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
firma_navn 		VARCHAR(30) NOT NULL,
kontaktperson 	VARCHAR(30) NOT NULL,
telefon 		VARCHAR(12) NOT NULL,
email 			VARCHAR(50) NOT NULL,
CVR 			VARCHAR(10)
);

INSERT INTO kunder VALUES
(DEFAULT, 'Skohjørnet', 'Hans Christensen', '20201919', 'hansen@sko.dk', '87654321'),
(DEFAULT, 'Hyggekrogen', 'Miklas Narrov', '25251515', 'MI@Hyg.dk', '38959301'),
(DEFAULT, 'Kongburger', 'Snu Satan', '33336119', 'haha@ha.dk', '83421521'),
(DEFAULT, 'WeAr', 'Jens Andersen', '43153919', 'JA@wear.dk', '84235121'),
(DEFAULT, 'qtOutfit', 'Hildur Jensen', '25151239', 'HJ@qt.dk', '63412321');

CREATE TABLE medarbejder
(
id				INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
fornavn			VARCHAR(15) NOT NULL,
efternavn		VARCHAR(15) NOT NULL,
ansættelsesdato	DATE NOT NULL,
telefon			VARCHAR(10) NOT NULL,
email			VARCHAR(30),
cpr				VARCHAR(15) NOT NULL,
løn				DECIMAL(10.2) NOT NULL DEFAULT '20000.00'
);

INSERT INTO medarbejder VALUES
(DEFAULT, 'Svend', 'Petersen', '2015-05-05', '50502929', 'Svend@hotmail.dk', '200590-9597', DEFAULT),
(DEFAULT, 'Jens', 'Ibsen', '2018-10-10', '45402929', 'Jeib@hotmail.dk', '100791-9595', DEFAULT),
(DEFAULT, 'Mads', 'Direksen', '2017-07-03', '65342929', 'Direk@gmail.dk', '200590-9597', DEFAULT),
(DEFAULT, 'Henrik', 'Vardik', '2014-01-10', '32342929', 'Vardikkeren@gmail.dk', '200790-2595', DEFAULT),
(DEFAULT, 'Hildur', 'Bentsen', '2011-12-15', '25602929', 'Hidu@hotmail.dk', '200590-5596', DEFAULT);