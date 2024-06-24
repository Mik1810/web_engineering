<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/tecnico-ordini-gestione-proposte-accettate.css">
    <link rel="stylesheet" href="/style/default.css">

</head>
<body>
<#include "modules/header.ftl">
<div class="container">
    <h1>Gestisci Proposte accettate</h1>

    <div class="row">

        <div class="list">
            <span class="invisible" id="page">${page!"0"}</span>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Codice Proposta</th>
                    <th scope="col">Caratteristiche Proposta</th>
                    <th scope="col">Link</th>
                    <th scope="col">Crea Ordine</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <#list proposte as proposta>
                    <tr>
                        <td>${proposta.codiceProdotto}</td>
                        <td>${proposta.nomeProdotto + ", "+proposta.produttore} <br> ${proposta.prezzo + "€"} </td>
                        <td>
                            <a href="${proposta.URL}" target="_blank">Link</a>
                        </td>
                        <td>
                            <form method="POST" action="tecnico_ordini_gestione_proposte_accettate?page=${page}">
                                <input type="hidden" name="id" value="${proposta.key}">
                                <input class="btn btn-primary" type="submit" id="ordine" name="ordine"
                                       value="Crea Ordine">
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="paginazione">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <#if page == 0>
                <li class="page-item"><a class="btn btn-secondary disabled">Pagina Precedente</a></li>
            <#else>
                <li class="page-item"><a class="btn btn-primary"
                                         href="tecnico_ordini_gestione_proposte_accettate?page=${page-1}">Pagina
                        precedente</a>
                </li>
            </#if>
            <li class="page-item"><a class="btn btn-primary"
                                     href="tecnico_ordini_gestione_proposte_accettate?page=${page+1}">Pagina
                    successiva</a>
            </li>
        </ul>
    </nav>
</div>


<span class="invisible" id="success">${success!"0"}</span>
<#include "modules/footer.ftl">


<script type="application/javascript" src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/tecnico_ordini_gestione_proposte_accettate.js"></script>
<!--
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="application/javascript" src="/scripts/pager.js"></script>
-->
</body>
</html>