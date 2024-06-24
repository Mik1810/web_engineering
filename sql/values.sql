/* Populate the init.sql tables */
USE webmarket;

-- Popolamento CategoriaPadre
INSERT INTO CategoriaPadre (nome) VALUES
('Modulistica'),
('Informatica'),
('Mobilio');

-- Popolamento CategoriaFiglio
INSERT INTO CategoriaFiglio (nome, ID_categoria_padre) VALUES
('Computer', 2),
('Accessori', 2),
('Mobili', 3);

-- Popolamento CategoriaNipote
INSERT INTO CategoriaNipote (nome, ID_categoria_figlio) VALUES
('PC Desktop', 1),
('Notebook', 1),
('Scrivania', 3),
('Cuffie', 2),
('Mouse', 2);

-- Popolamento Caratteristica
INSERT INTO caratteristica (nome, unita_di_misura, ID_categoria_nipote) VALUES
('RAM', 'GB', 1),
('RAM', 'GB', 2),
('CPU', 'GHz', 1),
('CPU', 'GHz', 2),
('Lunghezza', 'cm', 3),
('Peso', 'kg', 4),
('Lunghezza cavo', 'm', 4),
('Peso', 'kg', 5),
('Lunghezza cavo', 'm', 5);

-- Popolamento Ufficio
INSERT INTO Ufficio (sede, numero, piano, telefono, citta) VALUES
('Via Roma', 12, 1, '0746229912', 'Milano'),
('Via Roma', 23, 2, '0746758396', 'Roma'),
('Via Castello', 11, 1, '0861997534', 'L\'aquila');

-- Popolamento Ordinante
INSERT INTO Ordinante (email, password, ID_ufficio) VALUES
('michaelpiccirilli3@gmail.com', '78bcd4a8fdf004fbc369061a84b5cd31312da76008934816ebeb74ea3afd23f66375dffde3b91b8872ca20ed36a7b071', 3), #supersafepassword
('paoloccigiacomo@gmail.com', 'c93af3c4d15d0614f28aa3c0a3f929b01f0bbafc0dcf39608f5934e200b0094b1712e362953461c3b39fba751aae358b', 1); #giacomopass

-- Popolamento RichiestaAcquisto
INSERT INTO Richiesta (codice_richiesta, note, ID_ordinante) VALUES
('7592783840', 'Voglio un Nootbook per poter programmare in ufficio', 1),
('7683845728', 'Voglio una scrivania più larga ', 1),
('7834723777', 'Voglio un paio di cuffie da usare per le videochiamate', 1);

-- Popolamento composta
INSERT INTO composta (valore, ID_richiesta, ID_caratteristica) VALUES
('32', 1, 2),
('100', 2, 5),
('5', 1, 4),
('3.5',3, 7),
('0.4', 3, 6);

-- Popolamento TecnicoPreventivi
INSERT INTO TecnicoPreventivi (email, password) VALUES
('techprev1@gmail.com', '19bc1dfbdcdf7d8ddbb8f6b7c12112192b7cc3569eaed68dab0720fd3a93fd7211951aa804aa499d33163944eec1312b'), #techprevpass1
('techprev2@gmail.com', 'e942d4bea001c6c035cf6783f17d8d8b0ab0c5dffee23ff48bd6d157b325079c7a7f3a003fefb42b04cfe5b89123f7ae'); #techprevpass2

-- Popolamento RichiestaPresaInCarico
INSERT INTO RichiestaPresaInCarico (ID_richiesta, ID_tecnico_preventivi) VALUES
(1, 1),
(2, 2),
(3, 1);

-- Popolamento Amministratore
INSERT INTO Amministratore (email, password) VALUES
 ('admin@gmail.com', '282db4a4425f50237e7df29d56988825f15dd8b34fa74af54e650ce0fd8897a82dff0b952017a3a88a62f5f1b0e0e467'); #admin

-- Popolamento TecnicoOrdini
INSERT INTO TecnicoOrdini (email, password) VALUES
('techord1@gmail.com', '86dfa250e8da2a2add194605ef10dd450692f9706c01e7b352d5f13f410ccf179252694deeecff8e71a416b668abb288'), #techordpass1
('techord2@gmail.com', 'e1333ca4aa5ee5963c9b8a38bc62024fb9c9636e42d01e4f0fb85f59b94d31256a8d2dd9c3f2aab686345bbebf637f6c'); #techordpass2

-- Popolamento Proposta
INSERT INTO Proposta(codice_prodotto, produttore, note, prezzo, nome_prodotto, URL, stato_proposta, motivazione, ID_richiesta_presa_in_carico) VALUES
('8fu9eurw9e8ur8u38hc3m', 'HP', 'Pc economico ma plasticoso',  273.50, 'Pixel Intel N4500', 'https://www.amazon.it/HP-255-G8-portatile-DDR4-SDRAM/', 'Rifiutato', 'Non ha abbastanza RAM, non mi piace il materiale', 1),
('229f8ej382j8je9sua0s9', 'Honor', 'Pc molto leggero e di un colore blu elettrico',  699.99,'Honor MagicBook 14', 'https://www.honor.com/it/laptops/honor-magicbook-14/', 'In attesa', NULL, 1),
('34209348nc2034823048c', 'MALM', 'Scrivania con 2 cassetti molto ampii',  159.99,'Scrivania Bianca MALM', 'https://www.ikea.com/it/it/p/malm-scrivania-bianco-60214159/', 'Accettato', NULL, 2),
('2387fsn9d87fsd8f7379n', 'ASUS', 'Cuffie da gaming ASUS',  109.99, 'ASUS Delta S Core', 'https://rog.asus.com/it/headsets-audio/headsets/3-5mm-headsets/rog-delta-s-core-model/', 'Accettato', NULL, 3);

INSERT INTO Ordine(stato_consegna, feedback, data_di_consegna, ID_tecnico_ordini, ID_proposta) VALUES
('Presa in carico', NULL, NULL, 1, 3),
('Consegnato', 'Accettato', '2021-06-12', 1, 4);

INSERT INTO chiude(ID_ordine, ID_ordinante) VALUES
(2, 1);
