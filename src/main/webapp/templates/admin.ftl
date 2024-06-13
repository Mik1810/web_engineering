<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="/style/admin-style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
</head>
<body>
<#include "modules/header.ftl">

<div class="container">
    <a class="card" href="categoria_padre">
        <div class="icon">🏠</div>
        <div class="separator"></div>
        <div class="title">Categoria Padre</div>
        <div class="text">Inserisci, modifica o cancella una categoria <b>padre</b></div>
    </a>
    <a class="card" href="categoria_figlio">
        <div class="icon">🌟</div>
        <div class="separator"></div>
        <div class="title">Categoria Figlio</div>
        <div class="text">Inserisci, modifica o cancella una categoria <b>figlio</b></div>
    </a>

    <a class="card" href="#3">
        <div class="icon">🚀</div>
        <div class="separator"></div>
        <div class="title">Titolo 3</div>
        <div class="text">Questo è il testo della terza card.</div>
    </a>

    <a class="card" href="#4">
        <div class="icon">💡</div>
        <div class="separator"></div>
        <div class="title">Titolo 4</div>
        <div class="text">Questo è il testo della quarta card.</div>
    </a>

    <a class="card" href="#5">
        <div class="icon">🎓</div>
        <div class="separator"></div>
        <div class="title">Titolo 5</div>
        <div class="text">Questo è il testo della quinta card.</div>
    </a>

    <a class="card" href="#6">
        <div class="icon">📦</div>
        <div class="separator"></div>
        <div class="title">Titolo 6</div>
        <div class="text">Questo è il testo della sesta card.</div>
    </a>

    <a class="card" href="#7">
        <div class="icon">🎨</div>
        <div class="separator"></div>
        <div class="title">Titolo 7</div>
        <div class="text">Questo è il testo della settima card.</div>
    </a>

    <a class="card" href="#8">
        <div class="icon">⚙️</div>
        <div class="separator"></div>
        <div class="title">Titolo 8</div>
        <div class="text">Questo è il testo dell'ottava card.</div>
    </a>

</div>
<#include "modules/footer.ftl">
</body>
</html>