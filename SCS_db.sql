CREATE DATABASE IF NOT EXISTS SCS_db;
USE SCS_db;

CREATE TABLE byer
(
postnummer INT PRIMARY KEY NOT NULL, 
bydel VARCHAR(30) NOT NULL
);

INSERT INTO byer VALUES
('2300', 'København S'),
('2620', 'Albertslund'),
('2450', 'København SV'),
('2670', 'Greve');

CREATE TABLE brugere
(
bruger_id  INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
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
kunde_id 		INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
firma_navn 		VARCHAR(30) NOT NULL,
kontaktperson 	VARCHAR(30) NOT NULL,
telefon 		VARCHAR(12) NOT NULL,
email 			VARCHAR(50) NOT NULL,
CVR 			VARCHAR(10),
adresse			VARCHAR(30) NOT NULL,
postnummer		INT			NOT NULL,

FOREIGN KEY(postnummer) REFERENCES byer(postnummer)
);

INSERT INTO kunder VALUES
(DEFAULT, 'Skohjørnet', 'Hans Christensen', '20201919', 'hansen@sko.dk', '87654321', 'Lygten 70', '2300'),
(DEFAULT, 'Hyggekrogen', 'Miklas Narrov', '25251515', 'MI@Hyg.dk', '38959301', 'Rødovrevej', '2620'),
(DEFAULT, 'Kongburger', 'Snu Satan', '33336119', 'haha@ha.dk', '83421521', 'Vesterbrovej 60', '2450'),
(DEFAULT, 'WeAr', 'Jens Andersen', '43153919', 'JA@wear.dk', '84235121', 'Magrevej 50', '2670'),
(DEFAULT, 'qtOutfit', 'Hildur Jensen', '25151239', 'HJ@qt.dk', '63412321', 'Kagevej 125', '2300');

CREATE TABLE medarbejdere
(
medarbejder_id	INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
fornavn			VARCHAR(15) NOT NULL,
efternavn		VARCHAR(15) NOT NULL,
ansettelsesdato	DATE NOT NULL,
telefon			VARCHAR(10) NOT NULL,
email			VARCHAR(30),
cpr				VARCHAR(15) NOT NULL,
lon				DECIMAL(10.2) NOT NULL DEFAULT '20000.00',
adresse 		VARCHAR(30) NOT NULL,
postnummer 		INT	NOT NULL,

FOREIGN KEY(postnummer)references byer(postnummer)
);

INSERT INTO medarbejdere VALUES
(DEFAULT, 'Svend', 'Petersen', '2015-05-05', '50502929', 'Svend@hotmail.dk', '200590-9597', DEFAULT, 'Vejensvej 50', '2300'),
(DEFAULT, 'Jens', 'Ibsen', '2018-10-10', '45402929', 'Jeib@hotmail.dk', '100791-9595', DEFAULT, 'Bakerstreet 122', '2620'),
(DEFAULT, 'Mads', 'Direksen', '2017-07-03', '65342929', 'Direk@gmail.dk', '200590-9597', DEFAULT, 'Nalerstreet 697', '2670'),
(DEFAULT, 'Henrik', 'Vardik', '2014-01-10', '32342929', 'Vardikkeren@gmail.dk', '200790-2595', DEFAULT, 'Lavervej 10', '2450'),
(DEFAULT, 'Hildur', 'Bentsen', '2011-12-15', '25602929', 'Hidu@hotmail.dk', '200590-5596', DEFAULT, 'ajgervej 601', '2300');


CREATE TABLE vagtplan
(
vagtplan_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
starttid TIME,
sluttid TIME,
timetal DECIMAL(3.2),
dato DATE,
medarbejder_id_fk INT,
kunder_id_fk INT,

FOREIGN KEY (medarbejder_id_fk)				REFERENCES medarbejdere(medarbejder_id),
FOREIGN KEY (kunder_id_fk)						REFERENCES kunder(kunde_id)
);

INSERT INTO vagtplan VALUES
(DEFAULT, '23:00:00', '05:00:00', '6.0', '2015-05-05', '1', '2'),
(DEFAULT, '11:00:00', '19:00:00', '8.0', '2015-06-06', '2', '3'),
(DEFAULT, '12:00:00', '12:00:00', '12.0', '2016-05-05', '3', '4');

CREATE TABLE newsfeed
(
newsfeed_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
opslag VARCHAR(1000),
dato DATE,
tid TIME
);

INSERT INTO newsfeed VALUES
(DEFAULT, 'Dette er det første opslag', '2019-05-10', '10:00:00'),
(DEFAULT, 'Dette er det andet opslag', '2019-06-10', '10:00:00'),
(DEFAULT, 'Dette er det tredje opslag, Dette er det tredje opslag, Dette er det tredje opslag, Dette er det tredje opslag, Dette er det tredje opslag, Dette er det tredje opslag, Dette er det tredje opslag, Dette er det tredje opslag, Dette er det tredje opslag', '2019-07-10', '10:00:00');
