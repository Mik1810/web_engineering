<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" >
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/table.css">
</head>
<body>

<#include "modules/header.ftl">

<div class="ordine-container">
    <h1>Ordini</h1>

    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Stato Consegna</th>
                <th scope="col">Prodotto</th>
                <th scope="col">Note</th>
                <th scope="col">Caratteristiche</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <#list ordini as ordine>
                <tr>
                    <#if ordine.statoConsegna == "Presa in carico">
                        <td class="left">📥 ${ordine.statoConsegna!""}</td>
                    <#elseif ordine.statoConsegna == "Presa in carico">
                        <td class="left">🚚 ${ordine.statoConsegna!""}</td>
                    <#else>
                        <td class="left">📦 ${ordine.statoConsegna!""}</td>
                    </#if>
                    <td class="left">${ordine.proposta.nomeProdotto!""}</td>
                    <td class="left">${ordine.proposta.richiestaPresaInCarico.richiesta.note!""}</td>
                    <td><a class="btn btn-primary" href="caratteristiche?id_richiesta=${ordine.proposta.richiestaPresaInCarico.richiesta.key}">Caratteristiche</a></td>
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
                        <li class="page-item"><a class="btn btn-primary" href="ordini?page=${page-1}">Pagina
                                precedente</a>
                        </li>
                    </#if>
                    <li class="page-item"><a class="btn btn-primary" href="ordini?page=${page+1}">Pagina
                            successiva</a>
                    </li>
                </ul>
            </nav>
        </div>
    </#if>
</div>

<#include "modules/footer.ftl">

</body>
</html>