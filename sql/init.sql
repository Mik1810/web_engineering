CREATE DATABASE IF NOT EXISTS webmarket;

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
    DROP TABLE IF EXISTS chiude;
    DROP TABLE IF EXISTS OrdineAcquisto;
    DROP TABLE IF EXISTS Feedback;
    DROP TABLE IF EXISTS TecnicoOrdini;
    DROP TABLE IF EXISTS Amministratore;
    DROP TABLE IF EXISTS Proposta;
    DROP TABLE IF EXISTS RichiestaPresaInCarico;
    DROP TABLE IF EXISTS TecnicoPreventivi;
    DROP TABLE IF EXISTS composta;
    DROP TABLE IF EXISTS RichiestaAcquisto;
    DROP TABLE IF EXISTS Ordinante;
    DROP TABLE IF EXISTS Ufficio;
    DROP TABLE IF EXISTS Caratteristica;
    DROP TABLE IF EXISTS CategoriaNipote;
    DROP TABLE IF EXISTS CategoriaFiglio;
    DROP TABLE IF EXISTS CategoriaPadre;

    DROP USER IF EXISTS 'admin'@'localhost';

    CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
    GRANT ALL PRIVILEGES ON webmarket.* TO 'admin'@'localhost';

    CREATE TABLE CategoriaPadre (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        nome VARCHAR(255) NOT NULL
    );

    CREATE TABLE CategoriaFiglio (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        nome VARCHAR(255) NOT NULL,
        ID_categoria_padre INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_categoria_padre) REFERENCES CategoriaPadre(ID)
        ON DELETE CASCADE ON UPDATE CASCADE
    );

    CREATE TABLE CategoriaNipote (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        nome VARCHAR(255) NOT NULL,
        ID_categoria_figlio INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_categoria_figlio) REFERENCES CategoriaFiglio(ID)
        ON DELETE CASCADE ON UPDATE CASCADE

    );

    CREATE TABLE Caratteristica (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        nome VARCHAR(255) NOT NULL,
        unita_di_misura VARCHAR(255) NOT NULL,
        ID_categoria_nipote INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_categoria_nipote) REFERENCES CategoriaNipote(ID)
        ON DELETE CASCADE ON UPDATE CASCADE
        /* Per RAM, dato che può essere associato sia a PC Desktop che a Notebook, queste costituiranno due
           entry in caratteristica del tipo:
           (1, RAM, GB, ID_notebook)
           (2, RAM, GB, ID_pc_desktop)
        */
    );

    CREATE TABLE Ufficio (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        sede VARCHAR(255) NOT NULL,
        numero INT NOT NULL,
        piano INT NOT NULL,
        telefono VARCHAR(255) NOT NULL
    );

    CREATE TABLE Ordinante (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        ID_ufficio INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_ufficio) REFERENCES Ufficio(ID)
        ON DELETE CASCADE ON UPDATE CASCADE
    );

     CREATE TABLE RichiestaAcquisto (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        codice_richiesta VARCHAR(10) NOT NULL UNIQUE,
        note TEXT NULL,
        data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        ID_ordinante INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_ordinante) REFERENCES Ordinante(ID)
        ON DELETE CASCADE ON UPDATE CASCADE
    );

    CREATE TABLE composta (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        valore VARCHAR(255) NOT NULL,
        /* non per forza il valore è un intero, magari sto ordinando
           qualcosa il cui valore è una stringa */
        ID_richiesta_acquisto INT UNSIGNED NOT NULL,
        ID_caratteristica INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_richiesta_acquisto) REFERENCES RichiestaAcquisto(ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (ID_caratteristica) REFERENCES Caratteristica(ID)
        ON DELETE CASCADE ON UPDATE CASCADE
    );

    CREATE TABLE TecnicoPreventivi (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL
    );

    CREATE TABLE RichiestaPresaInCarico (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        ID_richiesta_acquisto INT UNSIGNED NOT NULL,
        ID_tecnico_preventivi INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_richiesta_acquisto) REFERENCES RichiestaAcquisto(ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (ID_tecnico_preventivi) REFERENCES TecnicoPreventivi(ID)
        ON DELETE RESTRICT ON UPDATE CASCADE
    );

    CREATE TABLE Proposta (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        codice_prodotto VARCHAR(255) NOT NULL UNIQUE,
        produttore VARCHAR(255) NOT NULL,
        note TEXT NULL,
        prezzo DECIMAL(10,2) NOT NULL,
        nome_prodotto VARCHAR(255) NOT NULL,
        URL VARCHAR(2048) NOT NULL,
        stato ENUM('In Attesa', 'Accettata', 'Rifiutata') NOT NULL,
        motivazione TEXT NULL,
        ID_tecnico_preventivi INT UNSIGNED NOT NULL,
        ID_richiesta_presa_in_carico INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_richiesta_presa_in_carico) REFERENCES RichiestaPresaInCarico(ID)
        ON DELETE CASCADE ON UPDATE CASCADE
    );

    CREATE TABLE Amministratore (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL
    );

    CREATE TABLE TecnicoOrdini (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL
    );

    CREATE TABLE Feedback (
        /* Default value: 1 => Feedback non disponibile */
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        nome VARCHAR(255) NOT NULL
    );

    CREATE TABLE OrdineAcquisto (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        stato_consegna ENUM('Presa in carico', 'In consegna', 'Consegnato') NOT NULL,
        ID_feedback INT UNSIGNED NOT NULL DEFAULT 1,
        /* Default value: 1 => Feedback non disponibile */
        ID_tecnico_ordini INT UNSIGNED NULL,
        ID_proposta INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_tecnico_ordini) REFERENCES TecnicoOrdini(ID)
        ON DELETE SET NULL ON UPDATE CASCADE,
        FOREIGN KEY (ID_proposta) REFERENCES Proposta(ID)
        ON DELETE RESTRICT ON UPDATE CASCADE,
        FOREIGN KEY (ID_feedback) REFERENCES Feedback(ID)
        ON DELETE SET DEFAULT ON UPDATE CASCADE
    );

    CREATE TABLE chiude (
        ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        ID_ordine INT UNSIGNED NOT NULL,
        ID_ordinante INT UNSIGNED NOT NULL,
        FOREIGN KEY (ID_ordine) REFERENCES OrdineAcquisto(ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (ID_ordinante) REFERENCES Ordinante(ID)
        ON DELETE CASCADE ON UPDATE CASCADE
    );