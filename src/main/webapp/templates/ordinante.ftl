<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>MyWebmarket</title>

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
                        <div><a class="a-postit" href="richieste_ordinante?id=${richiesta.key}">Richiesta <span class="numeric">${richiesta.codiceRichiesta}</span></a></div>
                    </#list>
                </div>
            </blockquote>
          </div>

          <div class="quote-container">
            <i class="pin"></i>
            <blockquote class="note pink">
                <div>
                    <a href="#">Proposta per: ordine 1</a>
                </div>
                <div>
                    <a href="#">Proposta per: ordine 2</a>
                </div>
                <div>
                    <a href="#">Proposta per: ordine 3</a>
                </div>

              <cite class="author">Vedi tutti</cite>
            </blockquote>
          </div>

          <div class="quote-container">
            <i class="pin"></i>
            <blockquote class="note green">
                <div>
                    <a href="#">Opzione 1</a>
                </div>
                <div>
                    <a href="#">Opzione 2</a>
                </div>
                <div>
                    <a href="#">Opzione 3</a>
                </div>
              <cite class="author">Vedi tutti</cite>
            </blockquote>
          </div>

    </div>

    <h1 class="d-flex justify-content-center menu">Menu Ordinante</h1>

    <div class="container">

        <a class="card" href="crea_richiesta">
            <div class="icon">✍🏻</div>
            <div class="separator"></div>
            <div class="title">Nuova Richiesta</div>
            <div class="text">Crea una nuova richiesta selezionando la categoria e le caratteristiche</div>
        </a>
        <a class="card" href="richieste_ordinante?page=0">
            <div class="icon">📋</div>
            <div class="separator"></div>
            <div class="title">Richieste</div>
            <div class="text">Visualizza, modifica o elimina le tue <b>richieste</b> attive</div>
        </a>

        <a class="card" href="#">
            <div class="icon">📙<!--<i class="fa-solid fa-list"></i>--></div>
            <div class="separator"></div>
            <div class="title">Categoria Nipote</div>
            <div class="text">Inserisci, modifica o cancella una categoria <b>nipote</b></div>
        </a>

        <!--
        <a class="card" href="#">
            <div class="icon">🏢</div>
            <div class="separator"></div>
            <div class="title">Gestisci uffici</div>
            <div class="text">Inserisci, modifica o cancella gli <b>uffici</b></div>
        </a>-->

    </div>

    <#include "modules/footer.ftl">

</body>
</html>