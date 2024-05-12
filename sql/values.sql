/* Populate the init.sql tables */
USE webmarket;

-- Popolamento StatoConsegna
INSERT INTO StatoConsegna (nome) VALUES ('Presa in carico'), ('In consegna'), ('Consegnato');

-- Popolamento Feedback
INSERT INTO Feedback (nome) VALUES ('Accettato'), ('Respinto perché non conforme'), ('Respinto perché non funzionante');

-- Popolamento StatoProposta
INSERT INTO StatoProposta (nome) VALUES ('In Attesa'), ('Accettata'), ('Rifiutata');

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
INSERT INTO Ufficio (sede, numero, piano, telefono) VALUES ('Via Roma', 12, 1, '0746229912');
INSERT INTO Ufficio (sede, numero, piano, telefono) VALUES ('Via Roma', 23, 2, '0746758396');
INSERT INTO Ufficio (sede, numero, piano, telefono) VALUES ('Via Castello', 11, 1, '0861997534');

-- Popolamento Ordinante
INSERT INTO Ordinante (email, password, ID_ufficio) VALUES ('michaelpiccirilli3@gmail.com', 'supersafepassword', 3);
INSERT INTO Ordinante (email, password, ID_ufficio) VALUES ('paoloccigiacomo@gmail.com', 'miksoccombi', 1);

-- Popolamento RichiestaAcquisto
INSERT INTO Richiesta (codice_richiesta, note, ID_ordinante) VALUES ('7592783840', 'Voglio un PC Desktop per poter giocare a WarThunder in ufficio', 2);
INSERT INTO Richiesta (codice_richiesta, note, ID_ordinante) VALUES ('7683845728', 'Voglio una scrivania più larga ', 1);

-- Popolamento composta
INSERT INTO composta (valore, ID_richiesta, ID_caratteristica) VALUES ('32', 1, 1);
INSERT INTO composta (valore, ID_richiesta, ID_caratteristica) VALUES ('100', 2, 5);

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