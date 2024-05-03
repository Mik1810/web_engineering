/* Populate the init.sql tables */
USE webmarket;

/*
Categoria Padre: (ID, Nome)
Categoria Figlio: (ID, Nome, ID categoria padre)
Categoria Nipote: (ID, Nome, ID categoria figlio)
Caratteristica: (ID, Nome, Unit`a di misura, ID categoria nipote)
Ufficio: (ID, sede, numero, piano, telefono)
Ordinante: (ID, email, password, ID ufficio)
Richiesta di Acquisto: (ID, Codice richiesta, Note, Data, Ora, ID ordinante)
composta: (ID, Valore, ID richiesta di acquisto, ID caratteristica)
Tecnico dei Preventivi: (ID, email, password)
Richiesta Presa in Carico: (ID, ID richiesta di acquisto, ID tecnico dei preventivi)
Proposta: (ID, Codice prodotto, Produttore, Note, Prezzo, Nome prodotto, URL, Stato*, Motiva-
zione, ID tecnico dei preventivi)
Amministratore: (ID, email, password)
Tecnico degli ordini: (ID, email, password)
Feedback: (ID, nome)
Ordine di Acquisto: (ID, Stato consegna**, ID feedback ID tecnico degli ordini, ID proposta)
chiude: (ID, ID ordine, ID ordinante
* Stato `e una ENUM(”In Attesa”, ”Accettata”, ”Rifiutata”)
** Stato consegna `e una ENUM(”Presa in carico”,”In consegna”, ”Consegnato”)
*/

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
INSERT INTO RichiestaAcquisto (codice_richiesta, note, ID_ordinante) VALUES ('7592783840', 'Voglio un PC Desktop per poter giocare a WarThunder in ufficio', 2);
INSERT INTO RichiestaAcquisto (codice_richiesta, note, ID_ordinante) VALUES ('7683845728', 'Voglio una scrivania più larga ', 1);

-- Popolamento composta
INSERT INTO composta (valore, ID_richiesta_acquisto, ID_caratteristica) VALUES ('32', 1, 1);
INSERT INTO composta (valore, ID_richiesta_acquisto, ID_caratteristica) VALUES ('100', 2, 5);

-- Popolamento TecnicoPreventivi
INSERT INTO TecnicoPreventivi (email, password) VALUES ('techprev1@gmail.com', 'password1');
INSERT INTO TecnicoPreventivi (email, password) VALUES ('techprev2@gmail.com', 'password2');

-- Popolamento RichiestaPresaInCarico
INSERT INTO RichiestaPresaInCarico (ID_richiesta_acquisto, ID_tecnico_preventivi) VALUES (1, 1);
INSERT INTO RichiestaPresaInCarico (ID_richiesta_acquisto, ID_tecnico_preventivi) VALUES (2, 2);

-- Popolamento Amministratore
INSERT INTO Amministratore (email, password) VALUES ('admin1@gmail.com', 'adminpassword1');
INSERT INTO Amministratore (email, password) VALUES ('admin2@gmail.com', 'adminpassword2');

-- Popolamento TecnicoOrdini
INSERT INTO TecnicoOrdini (email, password) VALUES ('techord1@gmail.com', 'techpassword1');
INSERT INTO TecnicoOrdini (email, password) VALUES ('techord2@gmail.com', 'techpassword2');