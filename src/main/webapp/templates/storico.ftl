<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
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
                <th scope="col" class="left">Stato Consegna</th>
                <th scope="col" class="left">Feedback</th>
                <th scope="col" class="left">Data di Consegna</th>
                <th scope="col" class="left">Prodotto</th>
                <th scope="col">Aggiungi Feedback</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <#list ordini as ordine>
                <tr>
                    <td class="left">📦 ${ordine.statoConsegna!""}</td>
                    <td class="left">${ordine.feedback!""}</td>
                    <td class="left">${ordine.dataConsegna!""}</td>
                    <td class="left">${ordine.proposta.nomeProdotto!""}</td>
                    <td>
                        <!-- La seconda condizione non serve ma è giusto per stare sicuri -->
                        <#if ordine.feedback?? || ordine.statoConsegna != "Consegnato">
                        <a class="btn btn-secondary disabled">Aggiungi</a>
                        <#else>
                        <#if id??>
                        <form method="POST" action="storico?id=${id}">
                            <#else>
                            <form method="POST" action="storico?page=${page!"0"}">
                                <input type="hidden" id="id" name="id" value="${ordine.key}">
                                </#if>
                                <input class="btn btn-primary" type="submit" id="render" name="render" value="Aggiungi">
                            </form>
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
                        <li class="page-item"><a class="btn btn-primary" href="storico?page=${page-1}">Pagina
                                precedente</a>
                        </li>
                    </#if>
                    <li class="page-item"><a class="btn btn-primary" href="storico?page=${page+1}">Pagina
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
                <h4>Aggiungi feedback</h4>
            </div>
            <#if id??>
            <form method="POST" accept-charset="UTF-8" action="storico?id=${id}" class="contenuto-form-popup">
                <#else>
                <form method="POST" accept-charset="UTF-8" action="storico?page=${page!"0"}"
                      class="contenuto-form-popup">
                    <input type="hidden" id="id" name="id" value="${(ordineDaFeedbackare.key)!"0"}">
                    </#if>
                    <label for="feedback">Scegli Feedback: </label>
                    <select id="feedback" name="feedback" class="form-select mb-2">
                        <#list feedbacks as feedback>
                            <option value="${feedback}">${feedback}</option>
                        </#list>
                    </select>
                    <div class="buttons-choose">
                        <input class="btn btn-primary" type="submit" id="action" name="action" value="Aggiungi">
                        <a class="btn btn-danger" href="storico?page=${page!0}">Annulla</a>
                    </div>
                </form>
        </div>
    </div>
</div>

<span class="invisible" id="success">${success!"0"}</span>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/storico.js"></script>

<#include "modules/footer.ftl">

</body>
</html>