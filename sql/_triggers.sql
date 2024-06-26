USE webmarket;

DROP TRIGGER IF EXISTS controlla_inserimento_feedback;
DROP TRIGGER IF EXISTS aggiorna_data_consegna;
DROP TRIGGER IF EXISTS inserisci_ordine_chiuso;
DROP TRIGGER IF EXISTS rimuovi_richiesta_senza_composta;

DELIMITER $$

CREATE TRIGGER aggiorna_data_consegna
    BEFORE UPDATE
    ON Ordine
    FOR EACH ROW
BEGIN
    IF NEW.stato_consegna = 'Consegnato' AND NEW.data_di_consegna IS NULL THEN
        SET NEW.data_di_consegna = CURRENT_TIMESTAMP;
    END IF;
END;


CREATE TRIGGER controlla_inserimento_feedback
    BEFORE UPDATE
    ON Ordine
    FOR EACH ROW
BEGIN
    IF NEW.stato_consegna != 'Consegnato' AND NEW.feedback IS NOT NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Inserimento feedback non consentito per questo stato di consegna.';
    END IF;
END;


CREATE TRIGGER inserisci_ordine_chiuso
    AFTER UPDATE
    ON Ordine
    FOR EACH ROW
BEGIN
    DECLARE ID_ordinante INT UNSIGNED;

    SET ID_ordinante = (SELECT o.ID
                        FROM ordinante o
                                 JOIN richiesta r ON o.ID = r.ID_ordinante
                                 JOIN richiestapresaincarico r2 ON r.ID = r2.ID_richiesta
                                 JOIN proposta p on r2.ID = p.ID_richiesta_presa_in_carico
                                 JOIN ordine o2 ON p.ID = o2.ID_proposta
                        WHERE o2.ID = NEW.ID);
    IF NEW.stato_consegna = 'Consegnato' THEN
        INSERT INTO chiude(ID_ordine, ID_ordinante)
        VALUES (NEW.ID, ID_ordinante);
    END IF;

END;

CREATE TRIGGER rimuovi_richiesta_senza_composta
    AFTER DELETE
    ON composta
    FOR EACH ROW
BEGIN
    DECLARE richiesta_count INT;

    SELECT COUNT(*)
    INTO richiesta_count
    FROM composta
    WHERE ID_richiesta = OLD.ID_richiesta;

    IF richiesta_count = 0 THEN
        DELETE FROM richiesta
        WHERE ID = OLD.ID_richiesta;
    END IF;
END;

$$
DELIMITER ;