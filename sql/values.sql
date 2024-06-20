/* Populate the init.sql tables */
USE webmarket;

-- Popolamento CategoriaPadre
INSERT INTO CategoriaPadre (nome) VALUES ('Modulistica');
INSERT INTO CategoriaPadre (nome) VALUES ('Informatica');
INSERT INTO CategoriaPadre (nome) VALUES ('Mobilio');

-- Popolamento CategoriaFiglio
INSERT INTO CategoriaFiglio (nome, ID_categoria_padre) VALUES ('Computer', 2);
INSERT INTO CategoriaFiglio (nome, ID_categoria_padre) VALUES ('Accessori', 2);
INSERT INTO CategoriaFiglio (nome, ID_categoria_padre) VALUES ('Mobili', 3);

-- Popolamento CategoriaNipote
INSERT INTO CategoriaNipote (nome, ID_categoria_figlio) VALUES ('PC Desktop', 1);
INSERT INTO CategoriaNipote (nome, ID_categoria_figlio) VALUES ('Notebook', 1);
INSERT INTO CategoriaNipote (nome, ID_categoria_figlio) VALUES ('Scrivania', 3);

-- Popolamento Caratteristica
INSERT INTO caratteristica (nome, unita_di_misura, ID_categoria_nipote) VALUES ('RAM', 'GB', 1);
INSERT INTO caratteristica (nome, unita_di_misura, ID_categoria_nipote) VALUES ('RAM', 'GB', 2);
INSERT INTO caratteristica (nome, unita_di_misura, ID_categoria_nipote) VALUES ('CPU', 'GHz', 1);
INSERT INTO caratteristica (nome, unita_di_misura, ID_categoria_nipote) VALUES ('CPU', 'GHz', 2);
INSERT INTO caratteristica (nome, unita_di_misura, ID_categoria_nipote) VALUES ('Lunghezza', 'cm', 3);

-- Popolamento Ufficio
INSERT INTO Ufficio (sede, numero, piano, telefono, citta) VALUES ('Via Roma', 12, 1, '0746229912', 'Milano');
INSERT INTO Ufficio (sede, numero, piano, telefono, citta) VALUES ('Via Roma', 23, 2, '0746758396', 'Roma');
INSERT INTO Ufficio (sede, numero, piano, telefono, citta) VALUES ('Via Castello', 11, 1, '0861997534', 'L\'aquila');

-- Popolamento Ordinante
INSERT INTO Ordinante (email, password, ID_ufficio) VALUES ('michaelpiccirilli3@gmail.com', '78bcd4a8fdf004fbc369061a84b5cd31312da76008934816ebeb74ea3afd23f66375dffde3b91b8872ca20ed36a7b071', 3);
INSERT INTO Ordinante (email, password, ID_ufficio) VALUES ('paoloccigiacomo@gmail.com', 'miksoccombi', 1);

-- Popolamento RichiestaAcquisto
INSERT INTO Richiesta (codice_richiesta, note, ID_ordinante) VALUES ('7592783840', 'Voglio un Nootbook per poter programmare in ufficio', 1);
INSERT INTO Richiesta (codice_richiesta, note, ID_ordinante) VALUES ('7683845728', 'Voglio una scrivania più larga ', 1);

-- Popolamento composta
INSERT INTO composta (valore, ID_richiesta, ID_caratteristica) VALUES ('32', 1, 2);
INSERT INTO composta (valore, ID_richiesta, ID_caratteristica) VALUES ('100', 2, 5);
INSERT INTO composta(valore, ID_richiesta, ID_caratteristica) VALUES('5', 1, 4);

-- Popolamento TecnicoPreventivi
INSERT INTO TecnicoPreventivi (email, password) VALUES ('techprev1@gmail.com', 'password1');
INSERT INTO TecnicoPreventivi (email, password) VALUES ('techprev2@gmail.com', 'password2');

-- Popolamento RichiestaPresaInCarico
INSERT INTO RichiestaPresaInCarico (ID_richiesta, ID_tecnico_preventivi) VALUES (1, 1);
INSERT INTO RichiestaPresaInCarico (ID_richiesta, ID_tecnico_preventivi) VALUES (2, 2);

-- Popolamento Amministratore
INSERT INTO Amministratore (email, password) VALUES ('admin@gmail.com', '282db4a4425f50237e7df29d56988825f15dd8b34fa74af54e650ce0fd8897a82dff0b952017a3a88a62f5f1b0e0e467');

-- Popolamento TecnicoOrdini
INSERT INTO TecnicoOrdini (email, password) VALUES ('techord1@gmail.com', 'techpassword1');
INSERT INTO TecnicoOrdini (email, password) VALUES ('techord2@gmail.com', 'techpassword2');

-- Popolamento Proposta
INSERT INTO Proposta(codice_prodotto, produttore, note, prezzo, nome_prodotto, URL, stato_proposta, motivazione, ID_richiesta_presa_in_carico) VALUES
('229f8ej382j8je9sua0s9', 'Honor', 'Pc molto leggero e di un colore blu elettrico',  699.99,'Honor MagicBook 14', 'https://www.honor.com/it/laptops/honor-magicbook-14/', 'Accettato', NULL, 1),
('8fu9eurw9e8ur8u38hc3m', 'HP', 'Pc economico ma plasticoso',  273.50, 'Pixel Intel N4500', 'https://www.amazon.it/HP-255-G8-portatile-DDR4-SDRAM/', 'Rifiutato', 'Non ha abbastanza RAM, non mi piace il materiale', 1);
