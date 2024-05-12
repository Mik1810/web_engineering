DROP VIEW IF EXISTS storico;

CREATE VIEW storico as

SELECT
    o.id AS ID_ordine,
    c.id AS ID_ordinante,
    f.nome AS feedback,
    p.codice_prodotto AS prodotto,
    o.data_di_consegna AS data_di_consegna

FROM chiude AS c
JOIN ordine AS o ON o.id=c.ID_ordine
JOIN feedback as f ON o.feedback=f.ID
JOIN statoconsegna AS sc ON sc.ID=o.stato_consegna
JOIN proposta AS p ON p.id=o.ID_proposta
WHERE sc.ID=3; -- "Chiuso"