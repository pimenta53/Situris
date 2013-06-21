
SET CHARACTER SET utf8;

#CREATE DATABASE situris;
DROP DATABASE IF EXISTS situris;
CREATE DATABASE situris CHARACTER SET = utf8;
USE situris;

--
-- Base de Dados: 'situris'

--
-- Estrutura da tabela 'CoordenadasGPS'
--

CREATE TABLE CoordenadasGPS (
  idCoordenadasGPS INT UNSIGNED NOT NULL AUTO_INCREMENT,
  latitude varchar(50) DEFAULT NULL,
  longitude varchar(50) DEFAULT NULL,
  altitude varchar(50) DEFAULT NULL,
  PRIMARY KEY (idCoordenadasGPS)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Perfil'
--

CREATE TABLE Perfil (
  idPerfil INT UNSIGNED NOT NULL AUTO_INCREMENT,
  descricao varchar(500) DEFAULT NULL,
  PRIMARY KEY (idPerfil)
) ENGINE=InnoDB;

-- Estrutura da tabela 'Pais'

CREATE TABLE pais (
  idPais INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nomePais varchar(30) UNIQUE NOT NULL,
  PRIMARY KEY (idPais)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Categoria'
--

CREATE TABLE Categoria (
  idCategoria INT UNSIGNED NOT NULL AUTO_INCREMENT,
  descricao varchar(500) DEFAULT NULL,
  PRIMARY KEY (idCategoria)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'TipoInteresse'
--

CREATE TABLE TipoInteresse (
  idTipoInteresse INT unsigned NOT NULL AUTO_INCREMENT,
  descricao varchar(500) DEFAULT NULL,
  PRIMARY KEY (idTipoInteresse)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Users'
--

CREATE TABLE Users (
  idUser INT unsigned NOT NULL AUTO_INCREMENT,
  username varchar(50) UNIQUE NOT NULL,
  name varchar(50),
  password varchar(50) BINARY NOT NULL,
  email varchar(100) UNIQUE NOT NULL,
  formSourceId varchar(100) BINARY,
  passwordNonce varchar(40),
  passwordNonceCreatedOn datetime,
  PRIMARY KEY (idUser)
) Engine=InnoDB;

--
-- Estrutura da tabela 'UserRole'
--

CREATE TABLE UserRole (
  username varchar(50) NOT NULL,
  role varchar(50) NOT NULL DEFAULT 'user',
  INDEX (username),
  FOREIGN KEY (username) REFERENCES Users(username),
  PRIMARY KEY (username,role)
) Engine=InnoDB;

--
-- Estrutura da tabela 'PontoReferencia'
--

CREATE TABLE PontoReferencia (
  idPontoRef INT unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(50) UNIQUE DEFAULT NULL,
  descricao varchar(500) DEFAULT NULL,
  area float(8,2) DEFAULT NULL,
  privadoPontoRef binary(1) DEFAULT 0,
  idCoordenadasGPS INT unsigned DEFAULT NULL,
  idUser INT unsigned DEFAULT NULL,
  idPais INT unsigned DEFAULT NULL,
  FOREIGN KEY (idCoordenadasGPS) REFERENCES CoordenadasGPS(idCoordenadasGPS),
  FOREIGN KEY (idUser) REFERENCES Users(idUser),
  FOREIGN KEY (idPais) REFERENCES pais(idPais),
  PRIMARY KEY (idPontoRef)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'Visita'
--

CREATE TABLE Visita (
  idVisita INT unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(50) UNIQUE DEFAULT NULL,
  descricao varchar(500) DEFAULT NULL,
  link varchar(100) DEFAULT NULL,
  imagem varchar(500) DEFAULT NULL,
  privadoVisita binary(1) DEFAULT 0,
  idCoordenadasGPS INT unsigned DEFAULT NULL,
  idTipoInteresse INT unsigned DEFAULT NULL,
  idUser INT unsigned DEFAULT NULL,
  FOREIGN KEY (idCoordenadasGPS) REFERENCES CoordenadasGPS(idCoordenadasGPS),
  FOREIGN KEY (idTipoInteresse) REFERENCES TipoInteresse(idTipoInteresse),
  FOREIGN KEY (idUser) REFERENCES Users(idUser),
  PRIMARY KEY (idVisita)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'Evento'
--

CREATE TABLE Evento (
  idEvento INT unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(50) UNIQUE DEFAULT NULL,
  descricao varchar(500) DEFAULT NULL,
  link varchar(100) DEFAULT NULL,
  imagem varchar(150) DEFAULT NULL,
  dataInicio date DEFAULT NULL,
  dataFim date DEFAULT NULL,
  privadoEvento binary(1) DEFAULT 0,
  idCoordenadasGPS INT unsigned DEFAULT NULL,
  idTipoInteresse INT unsigned DEFAULT NULL,
  idUser INT unsigned DEFAULT NULL,
  FOREIGN KEY (idCoordenadasGPS) REFERENCES CoordenadasGPS(idCoordenadasGPS),
  FOREIGN KEY (idTipoInteresse) REFERENCES TipoInteresse(idTipoInteresse),
  FOREIGN KEY (idUser) REFERENCES Users(idUser),
  PRIMARY KEY (idEvento)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'PontoReferencia_Evento'
--

CREATE TABLE PontoReferencia_Evento (
  id INT unsigned NOT NULL AUTO_INCREMENT,
  idPontoRef INT unsigned NOT NULL,
  idEvento INT unsigned NOT NULL,
  FOREIGN KEY (idEvento) REFERENCES Evento(idEvento),
  FOREIGN KEY (idPontoRef) REFERENCES PontoReferencia(idPontoRef),
  UNIQUE INDEX (idPontoRef, idEvento),
  PRIMARY KEY (id)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'PontoReferencia_Visita'
--

CREATE TABLE PontoReferencia_Visita (
  id INT unsigned NOT NULL AUTO_INCREMENT,
  idPontoRef INT unsigned NOT NULL,
  idVisita INT unsigned NOT NULL,
  FOREIGN KEY (idVisita) REFERENCES Visita(idVisita),
  FOREIGN KEY (idPontoRef) REFERENCES PontoReferencia(idPontoRef),
  UNIQUE INDEX (idPontoRef, idVisita),
  PRIMARY KEY (id)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'Horario'
--

CREATE TABLE Horario (
  idHorario INT unsigned NOT NULL AUTO_INCREMENT,
  descricao varchar(50) DEFAULT NULL,
  horaInicio Time NOT NULL,
  horaEvento Time NOT NULL,
  idVisita INT unsigned DEFAULT NULL,
  idEvento INT unsigned DEFAULT NULL,
  FOREIGN KEY (idVisita) REFERENCES Visita(idVisita),
  FOREIGN KEY (idEvento) REFERENCES Evento(idEvento),
  PRIMARY KEY (idHorario)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'HorarioTransporte'
--

CREATE TABLE HorarioTransporte (
  idHorarioTransporte INT unsigned NOT NULL AUTO_INCREMENT,
  descricao varchar(50) DEFAULT NULL,
  link varchar(100) DEFAULT NULL,
  idPontoRef INT unsigned DEFAULT NULL,
  FOREIGN KEY (idPontoRef) REFERENCES PontoReferencia(idPontoRef),
  PRIMARY KEY (idHorarioTransporte)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Roteiro'
--

CREATE TABLE Roteiro (
  idRoteiro INT unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(50) UNIQUE DEFAULT NULL,
  descricao varchar(500) DEFAULT NULL,
  privadoRoteiro binary(1) DEFAULT 0,
  idTipoInteresse INT unsigned DEFAULT NULL,
  idUser INT unsigned DEFAULT NULL,
  FOREIGN KEY (idTipoInteresse) REFERENCES TipoInteresse(idTipoInteresse),
  PRIMARY KEY (idRoteiro),
  FOREIGN KEY (idUser) REFERENCES Users(idUser)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Roteiro_PontoReferencia'
--

CREATE TABLE Roteiro_PontoReferencia (
  id INT unsigned NOT NULL AUTO_INCREMENT,
  idRoteiro INT unsigned NOT NULL,
  idPontoRef INT unsigned NOT NULL,
  posicao int(11) NOT NULL,
  FOREIGN KEY (idRoteiro) REFERENCES Roteiro(idRoteiro),
  FOREIGN KEY (idPontoRef) REFERENCES PontoReferencia(idPontoRef),
  UNIQUE INDEX (idRoteiro, idPontoRef),
  PRIMARY KEY (id)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'Avaliacao'
--

CREATE TABLE Avaliacao (
  idAvaliacao INT unsigned NOT NULL AUTO_INCREMENT,
  estrelas int(1) DEFAULT NULL,
  comentario varchar(500) DEFAULT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  idUser INT unsigned DEFAULT NULL,
  idPontoRef INT unsigned DEFAULT NULL,
  idRoteiro INT unsigned DEFAULT NULL,
  idVisita INT unsigned DEFAULT NULL,
  idEvento INT unsigned DEFAULT NULL,
  FOREIGN KEY (idVisita) REFERENCES Visita(idVisita),
  FOREIGN KEY (idEvento) REFERENCES Evento(idEvento),
  FOREIGN KEY (idPontoRef) REFERENCES PontoReferencia(idPontoRef),
  FOREIGN KEY (idRoteiro) REFERENCES Roteiro(idRoteiro),
  FOREIGN KEY (idUser) REFERENCES Users(idUser),
  PRIMARY KEY (idAvaliacao)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Reclamacao'
--

CREATE TABLE Reclamacao (
  idReclamacao INT unsigned NOT NULL AUTO_INCREMENT,
  texto varchar(500) DEFAULT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  idUser INT unsigned DEFAULT NULL,
  idPontoRef INT unsigned DEFAULT NULL,
  idRoteiro INT unsigned DEFAULT NULL,
  idVisita INT unsigned DEFAULT NULL,
  idEvento INT unsigned DEFAULT NULL,
  idHorarioTransporte INT unsigned DEFAULT NULL,
  FOREIGN KEY (idVisita) REFERENCES Visita(idVisita),
  FOREIGN KEY (idEvento) REFERENCES Evento(idEvento),
  FOREIGN KEY (idPontoRef) REFERENCES PontoReferencia(idPontoRef),
  FOREIGN KEY (idRoteiro) REFERENCES Roteiro(idRoteiro),
  FOREIGN KEY (idHorarioTransporte) REFERENCES HorarioTransporte(idHorarioTransporte),
  FOREIGN KEY (idUser) REFERENCES Users(idUser),
  PRIMARY KEY (idReclamacao)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'PropostaPatrocinio'
--

CREATE TABLE PropostaPatrocinio (
  idProposta INT unsigned NOT NULL AUTO_INCREMENT,
  valor float(8,2) DEFAULT NULL,
  descricao varchar(500) DEFAULT NULL,
  estado binary(1) DEFAULT 0,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  idUser INT unsigned DEFAULT NULL,
  FOREIGN KEY (idUser) REFERENCES Users(idUser),
  PRIMARY KEY (idProposta)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'TabelaPrecos'
--

CREATE TABLE TabelaPrecos (
  idTabelaPrecos INT unsigned NOT NULL AUTO_INCREMENT,
  tipoTabela INT unsigned DEFAULT NULL,
  idCategoria INT unsigned DEFAULT NULL,
  idPerfil INT unsigned DEFAULT NULL,
  valor float(8,2) DEFAULT NULL,
  FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria),
  FOREIGN KEY (idPerfil) REFERENCES Perfil(idPerfil),
  PRIMARY KEY (idTabelaPrecos)
) ENGINE=InnoDB;


--
-- Estrutura da tabela 'Patrocinio'
--

CREATE TABLE Patrocinio (
  idPatrocinio INT unsigned NOT NULL AUTO_INCREMENT,
  dataInicio date DEFAULT NULL,
  dataFm date DEFAULT NULL,
  nCliques int(11) DEFAULT 0,
  estadoPagamento binary(1) DEFAULT 0,
  descricao varchar(500) DEFAULT NULL,
  idUser INT unsigned DEFAULT NULL,
  idProposta INT unsigned DEFAULT NULL,
  idCategoria INT unsigned DEFAULT NULL,
  idPerfil INT unsigned DEFAULT NULL,
  idEvento INT unsigned DEFAULT NULL,
  idVisita INT unsigned DEFAULT NULL,
  FOREIGN KEY (idUser) REFERENCES Users(idUser),
  FOREIGN KEY (idProposta) REFERENCES PropostaPatrocinio(idProposta),
  FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria),
  FOREIGN KEY (idPerfil) REFERENCES Perfil(idPerfil),
  FOREIGN KEY (idEvento) REFERENCES Evento(idEvento),
  FOREIGN KEY (idVisita) REFERENCES Visita(idVisita),
  PRIMARY KEY (idPatrocinio)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Pagamento'
--

CREATE TABLE Pagamento (
  idPagamento INT unsigned NOT NULL AUTO_INCREMENT,
  dataPagamento timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  valorPagamento float(8,2) DEFAULT NULL,
  idPatrocinio INT unsigned DEFAULT NULL,
  idTabelaPrecos INT unsigned DEFAULT NULL,
  FOREIGN KEY (idPatrocinio) REFERENCES Patrocinio(idPatrocinio),
  FOREIGN KEY (idTabelaPrecos) REFERENCES TabelaPrecos(idTabelaPrecos),
  PRIMARY KEY (idPagamento)
) ENGINE=InnoDB;



-- INSERTS --------
-- pass e igual ao username
INSERT INTO Users (idUser,username,password,name,email) VALUES 
(1,'admino','d5410c0c3d67362aba0b4c60fd7fdfea68b66610','Admino','admino@grupo5.com'),
(2,'anasampaio','b082972f8e0345a0214fa1ce4a6b31158db8678c','Ana Sampaio','ana@grupo5.com'),
(3,'andrepimenta','6ab9a0c5390f8d676aa4de1190d5aeb9104933bd','Andre Pimenta','andre@grupo5.com'),
(4,'andreiasilva','950afdce77f522bf15e8dd70302f79d2573c0814','Andreia','andreia@grupo5.com'),
(5,'cedricpimenta','347ad70a23ce3873e1119d937679a4b433e8e0cf','Cedric','cedric@grupo5.com'),
(6,'rafaelabreu','23fc6e6c72ae76a26fd843953ab4ee9bebffa6db','Rafael','rafael@grupo5.com'),
(7,'cliente','d94019fd760a71edf11844bb5c601a4de95aacaf','Cliente','cliente@grupo5.com');


INSERT INTO UserRole VALUES 
('admino', 'admin'),
('anasampaio', 'user'),
('andrepimenta', 'user'),
('andreiasilva', 'user'),
('cedricpimenta', 'user'),
('rafaelabreu', 'user'),
('cliente', 'client');



INSERT INTO pais (idPais, nomePais) VALUES 
(1,'Antarctica'),
(2,'USA'),
(3,'Panama'),
(4,'Costa Rica'),
(5,'Canada'),
(6,'Nicagarua'),
(7,'Honduras'),
(8,'El Salvador'),
(9,'Belize'),
(10,'Guatemala'),
(11,'Mexico'),
(12,'Haiti'),
(13,'Dominican Republic'),
(14,'Puerto Rico'),
(15,'Jamaica'),
(16,'Cuba'),
(17,'Suriname'),
(18,'French Guiana'),
(19,'Guyana'),
(20,'Trinidad and Tobago'),
(21,'Venezuela'),
(22,'Colombia'),
(23,'Equador'),
(24,'Peru'),
(25,'Bolivia'),
(26,'Chile'),
(27,'Paraguay'),
(28,'Uruguay'),
(29,'Argentina'),
(30,'Brazil'),
(31,'Greenland'),
(32,'Russia'),
(33,'Cyprus'),
(34,'Montenegro'),
(35,'Albania'),
(36,'Macedonia'),
(37,'Greece'),
(38,'Bulgaria'),
(39,'Bosnia Herzegovina'),
(40,'India'),
(41,'Serbia'),
(42,'Croatia'),
(43,'Estonia'),
(44,'Latvia'),
(45,'Lithuania'),
(46,'Romania'),
(47,'Moldova'),
(48,'Ukraine'),
(49,'Belarus'),
(50,'Hungary'),
(51,'Slovakia'),
(52,'Slovenia'),
(53,'Czech Republic'),
(54,'Switzerland'),
(55,'Austria'),
(56,'Italy'),
(57,'Portugal'),
(58,'Spain'),
(59,'France'),
(60,'Luxembourg'),
(61,'Belgium'),
(62,'Netherland'),
(63,'Poland'),
(64,'Ireland'),
(65,'Britain'),
(66,'Denmark'),
(67,'Iceland'),
(68,'Norway'),
(69,'Germany'),
(70,'Finland'),
(71,'Sweden'),
(72,'Gambia'),
(73,'Guinea'),
(74,'Guinea-Bissau'),
(75,'Sierra Leone'),
(76,'Liberia'),
(77,'Ivory Coast'),
(78,'Burkina Faso'),
(79,'Ghana'),
(80,'Togo'),
(81,'Benin'),
(82,'Nigeria'),
(83,'Equatorial Guinea'),
(84,'Gabon'),
(85,'Central African Republic'),
(86,'Cameroon'),
(87,'Congo'),
(88,'Zaire'),
(89,'Uganda'),
(90,'Rwanda'),
(91,'Burundi'),
(92,'Angola'),
(93,'Zambia'),
(94,'Malawi'),
(95,'Namibia'),
(96,'Botswana'),
(97,'Zimbabwe'),
(98,'Swaziland'),
(99,'Kenya'),
(100,'Tanzania'),
(101,'Mozambique'),
(102,'Lesotho'),
(103,'South Africa'),
(104,'Western Sahara'),
(105,'Mauritania'),
(106,'Senegal'),
(107,'Mali'),
(108,'Niger'),
(109,'Chad'),
(110,'Madagascar'),
(111,'Djibouti'),
(112,'Eritrea'),
(113,'Somalia'),
(114,'Ethiopia'),
(115,'Sudan'),
(116,'Egypt'),
(117,'Libya'),
(118,'Tunisia'),
(119,'Morocco'),
(120,'Algeria'),
(121,'Tajikistan'),
(122,'Turkmenistan'),
(123,'Afghanistan'),
(124,'Pakistan'),
(125,'Gorgia'),
(126,'Azerbaijan'),
(127,'Armenia'),
(128,'Iran'),
(129,'Turkey'),
(130,'Jordan'),
(131,'Israel'),
(132,'Lebanon'),
(133,'Syria'),
(134,'Iraq'),
(135,'Kuwait'),
(136,'Qatar'),
(137,'United Arab Emirates'),
(138,'Oman'),
(139,'Yemen'),
(140,'Saudi Arabia'),
(141,'Papua New Guinea'),
(142,'New Zealand'),
(143,'Australia'),
(144,'Kyrgyzstan'),
(145,'Uzbekistan'),
(146,'Kazakhstan'),
(147,'Mongolia'),
(148,'Brunai'),
(149,'Taiwan'),
(150,'Philippines'),
(151,'Indonesia'),
(152,'Malaysia'),
(153,'Thailand'),
(154,'Cambodia'),
(155,'Laos'),
(156,'Vietnam'),
(157,'Burma'),
(158,'Sri Lanka'),
(159,'Bangladesh'),
(160,'Buthan'),
(161,'Nepal'),
(162,'South Korea'),
(163,'North Korea'),
(164,'Japan'),
(165,'China');


INSERT INTO TipoInteresse (idTipoInteresse, descricao) VALUES
(1, 'Gastronomic'),
(2, 'Cultural'),
(3, 'Religious'),
(4, 'Museums'),
(5, 'Sports'),
(6, 'Leisure');


INSERT INTO CoordenadasGPS (idCoordenadasGPS, latitude, longitude, altitude) VALUES
(1, '41.563059','-8.420334', NULL),
(2, '38.717662','-9.133072', NULL), 
(3, '41.155652','-8.609505', NULL),
(4, '48.861553','2.353477', NULL),
(5, '51.499179','-0.133724', NULL),
(6, '41.398445','2.164307', NULL),
(7, '40.450082','-3.696899', NULL),
(8, '50.461001','30.524826', NULL),
(9, '48.025295','37.806015', NULL),
(10, '50.474113','30.454788', NULL), 
(11, '37.247821','-119.509277', NULL), 
(12, '32.361403','-99.755859', NULL), 
(13, '25.7968','-80.226631', NULL), 
(14, '48.005314','7.844238', NULL), 
(15, '45.474577','9.192123', NULL), 
(16, '38.912808','1.432686', NULL), 
(17, '-22.881236','-43.213348', NULL), 
(18, '60.166963','24.943643', NULL),  
(19, '41.905343','12.494202', NULL), 
(20, '32.115149','-6.575317', NULL),
-- coords for visits
(21, '41.555481','-8.377719', NULL), 
(22, '41.554261','-8.377848', NULL),
(23, '38.702525','-9.206371', NULL),
(24, '39.659727','-8.825197', NULL), 
(25, '38.711969','-9.127536', NULL),
(26, '53.479861','-2.232971', NULL),
(27, '48.858927','2.294168', NULL),
(28, '40.414444','-3.692393', NULL),
(29, '41.414555','2.151861', NULL),
(30, '55.474113','25.454788', NULL),
-- coords for events
(31, '41.5466003','-8.4183598', NULL),
(32, '48.025754','37.781982', NULL),
(33, '50.453788','30.534782', NULL),
(34, '25.814727','-80.124664', NULL),
(35, '32.275232','-6.724319', NULL),
(36, '45.472651','9.173541', NULL),
(37, '-22.966928','-43.186569', NULL),
(38, '-21.779905','-42.84668', NULL),
(39, '60.16692','24.943728', NULL),
(40, '48.272454','7.721672', NULL);

INSERT INTO Roteiro (idRoteiro, nome, descricao, privadoRoteiro, idTipoInteresse, idUser) VALUES
(1, 'The best of Cultural Spain', 'Visit great places from Madrid to Barcelona', '0', 2, 1),
(2, 'Euro 2012','Soccer games at Euro 2012 Poland-Ukraine','0',5, 1),
(3, 'Best places in Portugal', 'Visit beautiful cathedrals and monasteries','0',3, 1),
(4, 'Fiesta','Join the best parties in Spain','0', 2, 1),
(5, 'Universitary Futsal', 'Do not miss the oportunity to see the best players','0',5,1),
(6, 'The Pizza Challenge', 'Go to the best pizza places in Italy','0', 1,1),
(7, 'NBA Championship','Assist the most important sport in USA','0',5,1),
(8, 'Around the Europe','Backpack and go explore the world!','0',2,1);

INSERT INTO PontoReferencia (idPontoRef, nome, descricao, area, privadoPontoRef, idCoordenadasGPS, idUser, idPais) VALUES
(1, 'Braga', 'Braga is a Portuguese city, founded by the Romans as Bracara Augusta, with more than 2000 years of history in constant development, growth and expansion.', '0', '0', 1, 1, 57),
(2, 'Porto', 'The city of Porto is known as the City Invicta. It is the city that gave its name to Portugal - very early (c. 200 BC), when designated for Portus Cale, coming later to become the capital of Portucalense. It is also a city known worldwide for its wine, its bridges and ancient and contemporary architecture, its historical center, classified as World Heritage by UNESCO, and one of its football clubs, the FC Porto.', '0', '0', 3, 1, 57),
(3, 'Lisbon', 'Lisbon is the capital city and largest city of Portugal. Is recognised as a global city because of its importance in finance, commerce, media, entertainment, arts, international trade, education, and tourism', '0', '0', 2, 1, 57),
(4, 'London', 'London is the capital city of England and the United Kingdom, the largest metropolitan area in the United Kingdom, and the largest urban zone in the European Union by most measures', '0', '0', 5, 1, 65),
(5, 'Paris', 'Paris is the capital and largest city of France. It is situated on the river Seine, in northern France', '0', '0', 4, 1, 59),
(6, 'Madrid', 'Madrid is the capital and largest city of Spain. The population of the city is roughly 3.3 million and the entire population of the Madrid metropolitan area is calculated to be 6.571 million!', '0', '0', 7, 1, 58),
(7, 'Barcelona', 'Barcelone is the capital of Catalonia and the second largest city in Spain, after Madrid. Nowaday is one of the worlds leading tourist, economic, trade fair/exhibitions and cultural-sports centres, and its influence in commerce, education, entertainment, media, fashion, science, and the arts all contribute to its status as one of the worlds major global cities!', '0', '0', 6, 1, 58),
(8, 'Donetsk', 'City in Ukraine', '0', '0', 9, 1, 48),
(9, 'Kiev','Kiev is the capital and the largest city of Ukraine, located in the north central part of the country on the Dnieper River', '0', '0', 8, 1, 48),
(10, 'New Delhi', 'Capital of India and a metropolitan city!', 0.5, '0', 10, 1, 40),
(11, 'Miami', 'Miami is a major center and a leader in finance, commerce, culture and media!', 5.0, '0', 13, 1, 2),
(12, 'Morocco', 'Morocco is a constitutional monarchy with an elected parliament.', 0.0, '0', 20, 1, 119),
(13, 'Milan', 'Mamma Mia', 0.0, '0', 15, 1, 56),
(14, 'Ibiza', 'Party and crazy people', 3.0, '0', 16, 1, 58),
(15, 'Rio de Janeiro', 'Beaches, sun and a beautiful landscape', 1.0, '0', 17, 1, 30),
(16, 'Helsinki', 'Helsinki is Finland major political, educational, financial center', 0.0, '0', 18, 1, 70),
(17, 'Rome', 'Romes history spans two and a half thousand years!', 2.0, '0', 19, 1, 56),
(18, 'Freiburg', 'The city is situated in the heart of a major wine-growing region', 0.0, '0', 14, 1, 69),
(19, 'Texas', 'Texas is nicknamed the Lone Star State to signify Texas as a former independent republic.', 0.0, '0', 12, 1, 2),
(20, 'California', 'The name California once referred to a large area of North America claimed by Spain.', 0.0, '0', 11, 1, 2);


INSERT INTO Visita (idVisita, nome, descricao, link, imagem, privadoVisita, idCoordenadasGPS, idTipoInteresse, idUser) VALUES
(1, 'Bom Jesus', 'Mandatory Reference to Baroque Europe', 'http://www.guiadacidade.pt/pt/poi-santuario-do-bom-jesus-13785', NULL, '0', 21, 3, 1),
(2, 'Sameiro', 'The Shrine of Sameiro is the largest center of Marian devotion in Portugal, after Fátima.', 'http://www.guiadacidade.pt/pt/poi-santuario-do-sameiro-14847', NULL, '0', 22, 3, 1),
(3, 'Mosteiro dos Jerónimos', 'It was commissioned by King Manuel I, shortly after Vasco da Gama returned from his trip to India. The decorative elements are filled with symbols of the art of navigation and sculptures of exotic plants and animal.', 'http://www.guiadacidade.pt/pt/poi-mosteiro-dos-jeronimos-14000', NULL, '0', 23, 2, 1),
(4, 'Mosteiro da Batalha', 'Is the great monument of the Portuguese late Gothic and the first where he debuted the "Manueline art."', 'http://www.guiadacidade.pt/pt/poi-mosteiro-da-batalha-13761', NULL, '0', 24, 2, 1),
(5, 'Museu do Fado', 'The exhibition of the Museum of Fado Fado is a tribute to their farmers and, spreading its history from the nineteenth-century Lisbon.','http://www.museudofado.pt/', NULL, '0', 25, 4, 1),
(6, 'Picaddily Circus', 'Piccadilly Circus is a road junction and public space of London West End in the City of Westminster, built in 1819 to connect Regent Street with the major shopping street of Piccadilly', NULL, NULL, '0',26, 4, 1),
(7, 'Eiffel Tower', 'Eiffel Tower is a puddled iron lattice tower located on the Champ de Mars in Paris',NULL, NULL, '0',27, 2, 1),
(8, 'Prado Museum', 'The Museo del Prado is a museum and art gallery that features one of the worlds finest collections of European art, from the 12th century to the early 19th century', NULL, NULL, '0', 28, 4,1),
(9, 'Park Güell', 'Park Güell is a garden complex with architectural elements situated on the hill of El Carmel in Barcelona. It was designed by the Catalan architect Antoni Gaudí and built in the years 1900 to 1914. It is part of the UNESCO World Heritage Site "Works of Antoni Gaudí.','http://www.parkguell.es/', NULL, '0',29, 2, 1),
(10, 'Restaurant Tai-Chi','Best food in India', NULL, NULL, '0', 30, 1,1);

INSERT INTO Evento (idEvento, nome, descricao, link, imagem, dataInicio, dataFim, privadoEvento, idCoordenadasGPS, idTipoInteresse, idUser) VALUES
(1, 'TED Talk', 'The TEDxYouth @ Braga has as its main theme: Inspire yourself. This theme aims to motivate and challenge all young people of the city of Braga to fight for your dreams!', 'http://www.tedxyouthbraga.com/', NULL, '2012-06-29', '2012-06-30', '0', 31, 2, 1),
(2, 'Portugal vs Spain', 'Soccer Games at Euro 2012', 'http://www.uefa.com/', NULL, '2012-06-27', '2012-06-08', '0', 32, 5, 1),
(3, 'Portugal vs ?', 'Will be known the winner of Euro 2012 (will be Portugal)', 'http://www.uefa.com/', NULL, '2012-07-1', '2012-07-1', '0', 33, 5, 1),
(4, 'Mercedes-Benz Fashion Week Miami', 'Come see all the great cars from Mercedes-Benz', NULL, NULL, '2012-10-10', '2012-10-20', '0', 34, 6, 1),
(5, '2015 Africa Cup of Nations', 'Who will win the tournament?', NULL, NULL, '2015-06-12', '2015-07-05', '0', 35, 5,1),
(6, 'La Triennale di Milano', 'Design museum and events venue located inside the Palace of Art building', NULL, NULL, '2012-06-25', '2012-08-25', '0', 36, 4,1),
(7, 'New Years Eve', 'The best way to leave the old year and welcome the new one', NULL, NULL, '2012-12-31', '2013-01-01', '0', 37, 6,1),
(8, 'Carnival', 'Carnaval, is an annual celebration in the Roman Catholic tradition that allows merry-making and red meat consumption', NULL, NULL, '2013-03-08', '2013-03-09', '0', 38, 2,1),
(9, 'Helsinki Festival', 'Largest multi-arts festival in Finland.', NULL, NULL, '2012-08-01', '2012-08-31', '0', 39, 2,1),
(10, 'Europa-Park', 'Europa-Park is the largest theme park in Germany and a third most popular theme park resort in Europe.', NULL, NULL, '2012-04-04', '2012-11-26', '0', 40, 6,1);


-- Transports --
INSERT INTO Horario (idHorario, descricao, horaInicio, horaEvento, idVisita, idEvento) VALUES
(1, 'Open from Monday to Friday', '10:00:00', '16:30:00', 2, NULL),
(2, 'Open at Wednesday and Thursday', '08:00:00', '18:00:00', 6, NULL),
(3, 'Open all days', '9:00:00', '22:00:00', 10, NULL),
(4, 'Only closes at Sundays', '13:00:00', '24:00:00', 9, NULL),
(5, 'One time event', '10:00:00', '12:30:00', NULL, 1),
(6, NULL, '19:45:00', '22:00:00', NULL, 3),
(7, 'Once a year', '00:00:00', '23:59:59', NULL, 8),
(8, 'Everyday', '08:00:00', '21:00:00', NULL, 10);

INSERT INTO HorarioTransporte(idHorarioTransporte,descricao,link,idPontoRef) VALUES
(1,'Bus','www.tub.pt',1),
(2,'Metro','http://www.metrodoporto.pt/',2),
(3,'Lisbon Carris','www.carris.pt/',3),
(4,'London Transport','http://www.tfl.gov.uk/',4),
(5,'Transport Metropolitans de Barcelona','www.tmb.cat/en/home',7),
(6,'Miami Public Transport','www.miamidade.gov/transit/long-term-delays-detours.asp',11),
(7,'Buses and Trams','www.atac.roma.it/index.asp?lingua=ENG',17),
(8,'Pulic Transports','www.ratp.fr/en/ratp/c_21879/tourists/',5),
(9,'Texas Public Transports','www.txdot.gov/',19);


-- Roteiro_Pontos
INSERT INTO Roteiro_PontoReferencia (id, idRoteiro, idPontoRef, posicao) VALUES
(1, 1, 6, 1),
(2, 1, 7, 2),
(3, 2, 8, 1),
(4, 2, 9, 2),
(5, 3, 1, 1),
(6, 3, 2, 2),
(7, 3, 3, 3),
(8, 7, 11, 1),
(9, 7, 19, 3),
(10, 7, 20, 2),
(11, 4, 6, 2),
(12, 4, 14, 1),
(13, 6, 13, 1),
(14, 6, 17, 2),
(15, 5, 1, 2),
(16, 5, 2, 1),
(17, 8, 3, 1),
(18, 8, 6, 2),
(19, 8, 5, 3),
(20, 8, 13, 4),
(21, 8, 18, 5),
(22, 8, 16, 6);

-- Ponto_visitas
INSERT INTO PontoReferencia_Visita (idPontoRef, idVisita) VALUES
(1, 1),
(1, 2),
(3, 3),
(3, 4),
(3, 5),
(4, 6),
(5, 7),
(6, 8),
(7, 9),
(10, 10);

-- Ponto_eventos
INSERT INTO PontoReferencia_Evento (idPontoRef, idEvento) VALUES
(1, 1),
(8, 2),
(9, 3),
(11, 4),
(12, 5),
(13, 6),
(15, 7),
(15, 8),
(16, 9),
(18, 10);


-- Modelo de negocio --
INSERT INTO Categoria VALUES
(1,'Large businesses'),
(2,'Medium businesses'),
(3,'Small businesses');

INSERT INTO Perfil VALUES
(1,'Hotels'),
(2,'Restaurants'),
(3,'Commerce');

INSERT INTO TabelaPrecos VALUES
(1,1,1,1,'0.10'),
(2,1,1,2,'0.06'),
(3,1,1,3,'0.18'),
(4,1,2,1,'0.05'),
(5,1,2,2,'0.03'),
(6,1,2,3,'0.03'),
(7,1,3,1,'0.02'),
(8,1,3,2,'0.01'),
(9,1,3,3,'0.01'),

(10,2,1,1,'0.12'),
(11,2,1,2,'0.08'),
(12,2,1,3,'0.10'),
(13,2,2,1,'0.07'),
(14,2,2,2,'0.05'),
(15,2,2,3,'0.05'),
(16,2,3,1,'0.04'),
(17,2,3,2,'0.03'),
(19,2,3,3,'0.03'),

(20,3,1,1,'0.15'),
(21,3,1,2,'0.11'),
(22,3,1,3,'0.15'),
(23,3,2,1,'0.10'),
(24,3,2,2,'0.08'),
(25,3,2,3,'0.08'),
(26,3,3,1,'0.09'),
(27,3,3,2,'0.06'),
(28,3,3,3,'0.06');


INSERT INTO PropostaPatrocinio VALUES
(1,200, 'McDonals wants to be sponsor of EURO 2012 games','0','2012-06-05',3),
(2,10, 'I want to be a sponsor of Park Guell','0','2012-06-10',4),
(3,150, 'I want to sponsor a new event called Barraquinhas de S. Joao','0','2012-07-01',2),
(4,300, 'I want to sponsor the visit Restaurant Tai-Chi','1','2012-04-01',7),
(5,400, 'Nike wants to sponsor the 2015 Africa Cup of Nations event','0','2012-05-12',6),
(6,100, 'McDonals wants to be sponsor of Helsinki Festival event','1','2012-03-02',7),
(7,650, 'I want to sponsor the Europa-Park event','0','2011-06-01',2),
(8,1000, 'I want to sponsor the New Years Eve event','0','2010-06-01',4),
(9,900, 'I want to sponsor the Museu do Fado','0','2012-02-25',5),
(10,1500, 'I want to sponsor the La Triennale di Milano','0','2012-02-08',5),
(11,5000, 'I want to sponsor the Mercedes-Benz Fashion Week Miami','0','2012-01-10',6);


INSERT INTO Patrocinio(idPatrocinio,dataInicio,dataFm,nCliques,estadoPagamento,
            descricao,idUser,idProposta,idCategoria,idPerfil,idEvento,idVisita) VALUES
(1,'2012-04-01','2012-08-31',20,'0','Cliente sponsors Helsinki Festival', 7,6,1,3,9,NULL),
(2,'2012-05-01','2012-08-25',10,'0','Cliente sponsors Tai-Chi Restaurant', 7,4,3,2,NULL,10);

-- comentarios e avaliacao
INSERT INTO Avaliacao(idAvaliacao,estrelas,comentario,data,idUser,idPontoRef,
            idRoteiro,idVisita,idEvento) VALUES
(1,4,'Expectations for the game?','2012-06-24',2,NULL,NULL,NULL,2),
(2,5,'Beautiful Park, great architecture!','2012-06-22',3,NULL,NULL,NULL,9),
(3,5,'Everyone should try this!','2012-02-22',5,NULL,NULL,NULL,10),
(4,2,'Not that great','2012-06-26',4,NULL,NULL,NULL,6),
(5,5,'Beautiful place!','2012-06-19',6,NULL,NULL,2,NULL),
(6,4,'Great food and low prices','2012-05-30',6,NULL,NULL,10,NULL),
(7,3,'I was a bit disappointed','2012-04-24',3,NULL,NULL,8,NULL),
(8,4,'Just what I was expecting','2012-06-01',5,NULL,NULL,3,NULL),
(9,4,'Approved','2012-06-12',5,3,NULL,NULL,NULL),
(10,5,'I recommend this for everyone','2012-05-29',7,1,NULL,NULL,NULL),
(11,5,'Everyone should visit this spetacular city','2012-04-03',2,20,NULL,NULL,NULL),
(12,2,'Not a great city to stay in vacations','2012-01-22',4,12,NULL,NULL,NULL),
(13,5,'Recommended!','2012-06-22',6,NULL,3,NULL,NULL),
(14,4,'Approved!','2012-06-23',6,NULL,6,NULL,NULL),
(15,5,'Best time of my life','2012-07-01',2,NULL,7,NULL,NULL),
(16,2,'Did not like it very much','2012-05-05',3,NULL,5,NULL,NULL),
(17,1,'Worst place ever','2012-04-04',5,NULL,2,NULL,NULL),
(18,4,'A time well spent','2012-03-08',4,NULL,8,NULL,NULL),
(19,3,'You should look to some alternative places','2011-08-08',7,NULL,4,NULL,NULL),
(20,4,'Followed it step by step and enjoyed the travel very much','2011-11-22',6,NULL,8,NULL,NULL),
(21,4,'A bit expensive but money well spent','2012-03-30',2,NULL,8,NULL,NULL),
(22,1,'Hated it!','2012-07-03',5,NULL,1,NULL,NULL);