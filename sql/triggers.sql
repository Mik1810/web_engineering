USE webmarket;

DROP TRIGGER IF EXISTS controlla_inserimento_feedback;
DROP TRIGGER IF EXISTS aggiorna_data_consegna;

DELIMITER $$

CREATE TRIGGER aggiorna_data_consegna
    AFTER UPDATE ON Ordine
    FOR EACH ROW
BEGIN
    IF NEW.stato_consegna = 3 AND NEW.data_di_consegna IS NULL THEN
        UPDATE Ordine
        SET data_di_consegna = CURRENT_TIMESTAMP
        WHERE ID = NEW.ID;
    END IF;
END;

CREATE TRIGGER controlla_inserimento_feedback
    BEFORE UPDATE ON Ordine
    FOR EACH ROW
BEGIN
    IF NEW.stato_consegna != 3 AND NEW.feedback IS NOT NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Inserimento feedback non consentito per questo stato di consegna.';
    END IF;
END;


CREATE TRIGGER inserisci_ordine_chiuso
    AFTER UPDATE ON Ordine
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
    IF NEW.stato_consegna = 3 THEN
        INSERT INTO chiude(ID_ordine,ID_ordinante)
        VALUES (NEW.ID,ID_ordinante);
    END IF;

END;


$$
DELIMITER ;