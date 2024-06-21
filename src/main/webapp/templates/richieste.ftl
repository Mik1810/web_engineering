<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/categoria.css">
</head>
<body>

<#include "modules/header.ftl">

<div class="container">
    <h1>Richieste</h1>

    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="left">Codice</th>
                <th scope="col" class="left">Note</th>
                <th scope="col" class="data-column left">Data</th>
                <th scope="col">Caratteristiche</th>
                <th scope="col">Modifica</th>
                <th scope="col">Elimina</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <#list richieste as richiesta>
                <tr>
                    <td class="left">${richiesta.codiceRichiesta}</td>
                    <td class="left">${richiesta.note}</td>
                    <td class="data-column left">${richiesta.data}</td>
                    <td>
                        <form method="GET" action="caratteristiche">
                            <input type="hidden" name="id_richiesta" value="${richiesta.key}">
                            <input id="ModificaButton" class="btn btn-primary" type="submit" value="Caratteristiche">
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="richieste?page=${page!"0"}">
                            <input type="hidden" id="id" name="id" value="${richiesta.key}">
                            <input class="btn btn-primary" type="submit" id="render" name="render" value="Modifica">
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="richieste?page=${page!"0"}">
                            <input type="hidden" name="id" value="${richiesta.key}">
                            <input class="btn btn-danger" type="submit" id="action" name="action" value="Elimina">
                        </form>
                    </td>
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
                    <li class="page-item"><a class="btn btn-primary" href="richieste?page=${page-1}">Pagina
                            precedente</a>
                    </li>
                </#if>
                <li class="page-item"><a class="btn btn-primary" href="richieste?page=${page+1}">Pagina
                        successiva</a>
                </li>
            </ul>
        </nav>
    </div>
    </#if>
</div>

<div class="popup-container" id="add" style="display: ${visibilityModify!"none"}">
    <div class="popup">

        <div class="update-screen">
            <h4 class="d-flex align-items-center">Modifica Richiesta</h4>
            <p class="p-3"></p>
            <form method="POST" action="richieste?page=${page!"0"}">

                <input type="hidden" name="id" value="${(richiestaDaModificare.key)!"0"}">
                <label for="note">Inserisci nuove note: </label>
                <textarea class="textarea-note form-control" name="note" rows="4" cols="50" id="note">${(richiestaDaModificare.note)!"0"}</textarea>
                <p class="p-1"></p>
                <div class="buttons-choose">
                    <input class="btn btn-primary" type="submit" id="action" name="action" value="Modifica">
                    <a class="btn btn-danger" href="richieste?page=${page!0}">Annulla</a>
                </div>

            </form>
        </div>

    </div>
</div>

<span class="invisible" id="success">${success!"0"}</span>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/richiesta.js"></script>

<#include "modules/footer.ftl">

</body>
</html>