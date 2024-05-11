-- da fare

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

$$
DELIMITER ;