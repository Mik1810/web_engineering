<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" >
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/table.css">
</head>
<body>

<!-- Visualizzare i dettagli della richiesta presa in carico richiesta, con il pulsante per vedere le caratteristiche
    e il pulsante per creare una proposta
-->
<!-- Visualizzare il popup delle caratteristiche -->
<!-- Visualizzare il popup del menù per inserire i campire della proposta-->

<#include "modules/header.ftl">

<div class="proposte-container">
    <h1>Proposte</h1>

    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="left">Codice Prodotto</th>
                <th scope="col" class="left">Nome Prodotto</th>
                <th scope="col" class="left">Produttore</th>
                <th scope="col">Note</th>
                <th scope="col" class="left">Prezzo</th>
                <th scope="col" class="left">URL</th>
                <th scope="col" class="left">Stato</th>
                <th scope="col">Motivazione</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <#list proposte as proposta>
                <tr>
                    <td class="left">${proposta.codiceProdotto!""}</td>
                    <td class="left">${proposta.nomeProdotto!""}</td>
                    <td class="left">${proposta.produttore!""}</td>
                    <td class="left">${proposta.note!""}</td>
                    <td class="left">${proposta.prezzo!""}</td>
                    <td class="left"><a href="${proposta.URL!""}">Vai al sito.</a></td>
                    <td class="left">${proposta.statoProposta!""}</td>
                    <td class="left">${proposta.motivazione!""}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <#if page?? >
        <div class="paginazione">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <#if page == 0>
                        <li class="page-item"><a class="btn btn-secondary disabled">Pagina Precedente</a></li>
                    <#else>
                        <li class="page-item"><a class="btn btn-primary" href="proposte_tecnico_preventivi?page=${page-1}">Pagina precedente</a>
                        </li>
                    </#if>
                    <li class="page-item"><a class="btn btn-primary" href="proposte_tecnico_preventivi?page=${page+1}">Pagina successiva</a>
                    </li>
                </ul>
            </nav>
        </div>
    </#if>
</div>

<#include "modules/footer.ftl">

</body>
</html>