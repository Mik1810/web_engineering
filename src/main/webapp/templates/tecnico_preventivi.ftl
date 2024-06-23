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
    <link rel="stylesheet" href="/style/card.css">
    <link rel="stylesheet" href="/style/user.css">

</head>
<body>

<#include "modules/header.ftl">

<h1 class="d-flex justify-content-center bacheca">Bacheca</h1>

<div class="container-postit">
    <div class="quote-container">
        <i class="pin"></i>
        <blockquote class="note yellow">
            <h2 class="justify-content-center">Richieste non gestite</h2>
            <div>
                <#list richieste as richiesta>
                    <a class="a-postit" href="prendi_in_consegna?id=${richiesta.key}">Richiesta
                        <span class="numeric">${richiesta.codiceRichiesta}</span>
                    </a>
                </#list>

            </div>
        </blockquote>
    </div>

    <div class="quote-container">
        <i class="pin"></i>
        <blockquote class="note pink">
            <h2>Richieste prese in carico</h2>
            <div>
                <#list richiestePreseInCarico as richiestaPresaIncarico>
                    <a class="a-postit" href="richieste_prese_in_carico?id=${richiestaPresaIncarico.key}">Richiesta presa in carico:
                        <span class="numeric">${richiestaPresaIncarico.richiesta.codiceRichiesta}</span>
                    </a>
                </#list>
            </div>
        </blockquote>
    </div>

    <div class="quote-container">
        <i class="pin"></i>
        <blockquote class="note green">
            <h2>Proposte</h2>
            <div>
                <#--
                <#list ordini as ordine>
                    <a class="a-postit" href="ordini?id=${ordine.key}">Ordine per richiesta
                        <span class="numeric">${ordine.proposta.richiestaPresaInCarico.richiesta.codiceRichiesta}</span>
                    </a>
                </#list>
                -->
            </div>
        </blockquote>
    </div>

</div>

<h1 class="d-flex justify-content-center menu">Menu Tecnico Preventivi</h1>

<div class="container">

    <a class="card" href="prendi_in_consegna?page=0">
        <div class="icon">✅</div>
        <div class="separator"></div>
        <div class="title">Prendi in consegna</div>
        <div class="text">Prendi in consegna una <b>richiesta</b></div>
    </a>
    <a class="card" href="richieste_prese_in_carico?page=0">
        <div class="icon">📋</div>
        <div class="separator"></div>
        <div class="title">Richieste prese in carico</div>
        <div class="text">Visualizza le tue <b>richieste prese in carico</b> e crea una <b>proposta</b></div>
    </a>

    <a class="card" href="proposte_tecnico?page=0">
        <div class="icon">📩</div>
        <div class="separator"></div>
        <div class="title">Proposte</div>
        <div class="text">Visualizza lo stato delle tue <b>proposte</b></div>
    </a>

</div>

<#include "modules/footer.ftl">

</body>
</html>