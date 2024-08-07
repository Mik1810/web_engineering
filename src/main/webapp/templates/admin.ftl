<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/card.css">
</head>
<body>
<#include "modules/header.ftl">

<div class="container">

    <a class="card" href="categoria_padre?page=0">
        <div class="icon">📗
            <!--<img class="image-class" src="/images/icons/list-solid-1.png" alt="icona categoria padre">--></div>
        <div class="separator"></div>
        <div class="title">Gestione Categoria Padre</div>
        <div class="text">Inserisci, modifica o cancella una categoria <b>padre</b></div>
    </a>
    <a class="card" href="categoria_figlio?page=0">
        <div class="icon">📘
            <!--<img class="image-class" src="/images/icons/list-solid-2.png" alt="icona categoria figlio">--></div>
        <div class="separator"></div>
        <div class="title">Gestione Categoria Figlio</div>
        <div class="text">Inserisci, modifica o cancella una categoria <b>figlio</b></div>
    </a>

    <a class="card" href="categoria_nipote?page=0">
        <div class="icon">📙<!--<i class="fa-solid fa-list"></i>--></div>
        <div class="separator"></div>
        <div class="title">Gestione Categoria Nipote</div>
        <div class="text">Inserisci, modifica o cancella una categoria <b>nipote</b></div>
    </a>

    <a class="card" href="gestione_caratteristiche">
        <div class="icon">🔍</div>
        <div class="separator"></div>
        <div class="title">Gestisci le caratteristiche</div>
        <div class="text">Inserisci, modifica o cancella le <b>caratteristiche</b></div>
    </a>

    <a class="card" href="gestione_uffici">
        <div class="icon">🏢</div>
        <div class="separator"></div>
        <div class="title">Gestisci uffici</div>
        <div class="text">Inserisci, modifica o cancella gli <b>uffici</b></div>
    </a>

    <a class="card" href="gestione_ordinanti?page=0">
        <div class="icon">🧑🏻‍💼</div>
        <div class="separator"></div>
        <div class="title">Gestisci ordinanti</div>
        <div class="text">Inserisci, modifica o cancella gli <b>ordinanti</b></div>
    </a>

    <a class="card" href="gestione_tecnici_preventivi">
        <div class="icon">🧑🏻‍💻</div>
        <div class="separator"></div>
        <div class="title">Gestisci tecnici dei preventivi</div>
        <div class="text">Inserisci, modifica o cancella i <b>tecnici dei preventivi</b></div>
    </a>

    <a class="card" href="gestione_tecnici_ordini">
        <div class="icon">👷🏻‍♂️</div>
        <div class="separator"></div>
        <div class="title">Gestisci tecnici degli ordini</div>
        <div class="text">Inserisci, modifica o cancella i <b>tecnici degli ordini</b></div>
    </a>

</div>

<#include "modules/footer.ftl">
</body>
</html>