<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/table.css">
</head>
<body>

<#include "modules/header.ftl">

<div class="ordine-container">
    <h1>Richieste</h1>

    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="left">Codice</th>
                <th scope="col" class="left fixed-width">Note</th>
                <th scope="col" class="data-column left">Data</th>
                <th scope="col">Caratteristiche</th>
                <th scope="col">Prendi in consegna</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <#list richieste as richiesta>
                <tr>
                    <td class="left">${richiesta.codiceRichiesta}</td>
                    <td class="left fixed-width">${richiesta.note}</td>
                    <td class="data-column left">${richiesta.data}</td>
                    <td>
                        <#if id??>
                            <form method="POST" action="prendi_in_consegna?id=${id}">
                                <input class="btn btn-primary" type="submit" id="render" name="render"
                                       value="Caratteristiche">
                            </form>
                        <#else>
                            <form method="POST" action="prendi_in_consegna?page=${page!"0"}">
                                <input type="hidden" id="id" name="id" value="${richiesta.key}">
                                <input class="btn btn-primary" type="submit" id="render" name="render"
                                       value="Caratteristiche">
                            </form>
                        </#if>

                    </td>
                    <td>
                        <#if id??>
                            <form method="POST" action="prendi_in_consegna?id=${id}">
                                <input class="btn btn-success" type="submit" id="action" name="action"
                                       value="Prendi in consegna">
                            </form>
                        <#else>
                            <form method="POST" action="prendi_in_consegna?page=${page!"0"}">
                                <input type="hidden" id="id" name="id" value="${richiesta.key}">
                                <input class="btn btn-success" type="submit" id="action" name="action"
                                       value="Prendi in consegna">
                            </form>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <#if page??>
        <div class="paginazione">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <#if page == 0>
                        <li class="page-item"><a class="btn btn-secondary disabled">Pagina Precedente</a></li>
                    <#else>
                        <li class="page-item">
                            <a class="btn btn-primary" href="prendi_in_consegna?page=${page-1}">Pagina precedente</a>
                        </li>
                    </#if>
                    <li class="page-item">
                        <a class="btn btn-primary" href="prendi_in_consegna?page=${page+1}">Pagina successiva</a>
                    </li>
                </ul>
            </nav>
        </div>
    </#if>
</div>

<div class="popup-container" id="add" style="display: ${visibilityModify!"none"}">
    <div class="popup">

        <div class="update-screen">
            <div class="titolo-popup">
                <h4>Caratteristiche</h4>
            </div>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" class="left">Nome</th>
                    <th scope="col" class="left">Valore</th>
                    <th scope="col" class="data-column left">Unità di Misura</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <#if caratteristiche??>
                    <#list caratteristiche as caratteristicaConValore>
                        <tr>
                            <td class="left">${caratteristicaConValore.caratteristica.nome!""}</td>
                            <td class="left">${caratteristicaConValore.valore!""}</td>
                            <td>${caratteristicaConValore.caratteristica.unitaMisura!""}</td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>

            <#if id??>
                <a class="btn btn-danger proposta-buttons" href="prendi_in_consegna?id=${id!""}">Esci</a>
            <#else>
                <a class="btn btn-danger proposta-buttons" href="prendi_in_consegna?page=${page!"0"}">Esci</a>
            </#if>
        </div>
    </div>
</div>

<span class="invisible" id="success">${success!"0"}</span>

<#include "modules/footer.ftl">

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/prendi_in_consegna.js"></script>

</body>
</html>