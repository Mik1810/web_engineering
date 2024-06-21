<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/admin-style.css">
    <link rel="stylesheet" href="/style/ordinante.css">

</head>
<body>

    <#include "modules/header.ftl">

    <h1 class="d-flex justify-content-center bacheca">Bacheca</h1>

    <div class="container-postit">
        <div class="quote-container">
            <i class="pin"></i>
            <blockquote class="note yellow">
                <h2>Richieste</h2>
                <div>
                    <#list richieste as richiesta>
                        <a class="a-postit" href="richieste?id=${richiesta.key}">Richiesta
                            <span class="numeric">${richiesta.codiceRichiesta}</span>
                        </a>
                    </#list>
                </div>
            </blockquote>
          </div>

          <div class="quote-container">
            <i class="pin"></i>
            <blockquote class="note pink">
                <h2>Proposte</h2>
                <div>
                    <#list proposte as proposta>
                        <a class="a-postit" href="proposte?id=${proposta.key}">Proposta per richiesta
                                <span class="numeric">${proposta.richiestaPresaInCarico.richiesta.codiceRichiesta}</span>
                        </a>
                    </#list>
                </div>
            </blockquote>
          </div>

          <div class="quote-container">
            <i class="pin"></i>
            <blockquote class="note green">
                <h2>Ordini</h2>
                <div>
                    <#list ordini as ordine>
                        <a class="a-postit" href="ordini?id=${ordine.key}">Ordine per richiesta
                                <span class="numeric">${ordine.proposta.richiestaPresaInCarico.richiesta.codiceRichiesta}</span>
                        </a>
                    </#list>
                </div>
            </blockquote>
          </div>

    </div>

    <h1 class="d-flex justify-content-center menu">Menu Ordinante</h1>

    <div class="container">

        <a class="card" href="crea_richiesta">
            <div class="icon">✍🏻</div>
            <div class="separator"></div>
            <div class="title">Nuova Richiesta</div>
            <div class="text">Crea una <b>nuova richiesta</b>    selezionando la categoria e le caratteristiche</div>
        </a>
        <a class="card" href="richieste?page=0">
            <div class="icon">📋</div>
            <div class="separator"></div>
            <div class="title">Richieste</div>
            <div class="text">Visualizza, modifica o elimina le tue <b>richieste</b> attive</div>
        </a>

        <a class="card" href="proposte?page=0">
            <div class="icon">📩</div>
            <div class="separator"></div>
            <div class="title">Proposte</div>
            <div class="text">Visualizza, accetta o rifiuta le <b>proposte</b> attive</div>
        </a>

        <a class="card" href="ordini?page=0">
            <div class="icon">📦</div>
            <div class="separator"></div>
            <div class="title">Ordini</div>
            <div class="text">Visualizza gli <b>ordini</b> e il loro stato di consegna</div>
        </a>

        <a class="card" href="storico?page=0">
            <div class="icon">📑</div>
            <div class="separator"></div>
            <div class="title">Storico</div>
            <div class="text">Visualizza lo storico dei tuoi <b>ordini</b></div>
        </a>

    </div>

    <#include "modules/footer.ftl">

</body>
</html>