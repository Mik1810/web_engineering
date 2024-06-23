<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/card.css">
    <link rel="stylesheet" href="/style/tecnico-ordini-dashboard.css">
</head>
<body>
<#include "modules/header.ftl">

<div class="container">

    <a class="card" href="tecnico_ordini_gestione_proposte_accettate?page=0">
        <div class="icon">✅</div>
        <div class="separator"></div>
        <div class="title">Gestisci Proposte Accettate e crea Ordini</div>
        <div class="text">Visualizza e gestisci le <b>proposte accettate</b> e crea nuovi <b>ordini</b></div>

    </a>
    <a class="card" href="tecnico_ordini_gestione_ordini?page=0">
        <div class="icon">📦</div>
        <div class="separator"></div>
        <div class="title">Gestisci gli Ordini</div>
        <div class="text">Visualizza e gestisci gli <b>Ordini</b></div>

    </a>


</div>

<#include "modules/footer.ftl">
</body>
</html>