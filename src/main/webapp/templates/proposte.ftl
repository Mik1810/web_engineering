<html lang="it">
<head>
    <meta charset="UTF-8">
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
                <th scope="col">Accetta</th>
                <th scope="col">Rifiuta</th>
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
                    <td>
                        <#if proposta.statoProposta == "In attesa">
                            <#if id??>
                                <form method="POST" action="proposte?id=${id}">
                            <#else>
                                <form method="POST" action="proposte?page=${page!"0"}">
                                <input type="hidden" id="id" name="id" value="${proposta.key}">
                            </#if>
                                <input class="btn btn-primary" type="submit" id="action" name="action" value="Accetta">
                            </form>
                        <#else>
                            <a class="btn btn-secondary disabled">Accetta</a>
                        </#if>

                    </td>
                    <td>
                        <#if proposta.statoProposta == "In attesa">
                            <#if id??>
                                <form method="POST" action="proposte?id=${id}">
                            <#else>
                                <form method="POST" action="proposte?page=${page!"0"}">
                                <input type="hidden" id="id" name="id" value="${proposta.key}">
                            </#if>
                                <input class="btn btn-danger" type="submit" id="render" name="render" value="Rifiuta">
                            </form>
                        <#else>
                            <a class="btn btn-secondary disabled">Rifiuta</a>
                        </#if>
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
                        <li class="page-item"><a class="btn btn-primary" href="proposte?page=${page-1}">Pagina
                                precedente</a>
                        </li>
                    </#if>
                    <li class="page-item"><a class="btn btn-primary" href="proposte?page=${page+1}">Pagina
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
            <div class="titolo-popup">
                <h4>Rifiuta Proposta</h4>
            </div>
            <#if id??>
                <form method="POST" accept-charset="UTF-8" action="proposte?id=${id}" class="contenuto-form-popup">
            <#else>
                <form method="POST" accept-charset="UTF-8" action="proposte?page=${page!"0"}" class="contenuto-form-popup">
                <input type="hidden" id="id" name="id" value="${(propostaDaRifiutare.key)!"0"}">
            </#if>
                <label for="motivazione">Inserisci motivazione: </label>
                <textarea class="textarea-note form-control" id="motivazione" name="motivazione" rows="4" cols="50"></textarea>
                <p class="p-1"></p>
                <div class="buttons-choose">
                    <input class="btn btn-primary" type="submit" id="action" name="action" value="Rifiuta">
                    <a class="btn btn-danger" href="proposte?page=${page!0}">Annulla</a>
                </div>

            </form>
        </div>
    </div>
</div>

<span class="invisible" id="success">${success!"0"}</span>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/proposte.js"></script>

<#include "modules/footer.ftl">

</body>
</html>