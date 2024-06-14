USE webmarket;

DROP VIEW IF EXISTS storico;

CREATE VIEW storico as

SELECT o.id               AS ID_ordine,
       c.id               AS ID_ordinante,
       f.nome             AS feedback,
       p.nome_prodotto    AS prodotto,
       o.data_di_consegna AS data_di_consegna

FROM chiude AS c
         JOIN ordine AS o ON o.id = c.ID_ordine
         JOIN feedback as f ON o.feedback = f.ID
         JOIN statoconsegna AS sc ON sc.ID = o.stato_consegna
         JOIN proposta AS p ON p.id = o.ID_proposta
WHERE sc.ID = 3; -- "Chiuso"

DROP VIEW IF EXISTS richiesta_con_caratteristiche;

CREATE VIEW richiesta_con_caratteristiche AS
SELECT r.codice_richiesta AS codice_richiesta,
       r.data AS data,
       r.ID AS ID_richiesta,
       r.ID_ordinante AS ID_ordinante,
       r.note AS note,
       r.version AS richiesta_version,
       ca.ID AS ID_caratteristica,
       ca.ID_categoria_nipote AS ID_categoria_nipote,
       ca.nome AS nome_caratteristica,
       ca.unita_di_misura AS unita_di_misura,
       ca.version AS caratteristica_version,
       c.valore AS valore
FROM richiesta AS r
JOIN composta AS c ON r.ID = c.ID_richiesta
JOIN caratteristica AS ca ON ca.id = c.ID_caratteristica;