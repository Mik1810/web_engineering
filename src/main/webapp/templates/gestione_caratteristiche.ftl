<?xml version="1.0" encoding="ISO-8859-1"?>
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
    <link rel="stylesheet" href="/style/gestione-caratteristiche.css">
    <link rel="stylesheet" href="/style/default.css">

</head>
<body>
<#include "modules/header.ftl">
<div class="container">
    <h1>Gestisci Caratteristiche</h1>

    <div id="aggiungi" class="aggiungi">
        <form method="POST" action="gestione_caratteristiche?page=${page}">
            <input class="btn btn-success" type="submit" name="render" id="render" value="Aggiungi"/>
        </form>
    </div>


    <div class="row">

        <div class="list">
            <span class="invisible" id="page">${page!"0"}</span>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Unità di misura</th>
                    <th scope="col">Categoria Nipote</th>
                    <th scope="col">Modifica</th>
                    <th scope="col">Elimina</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <#list caratteristiche as caratteristica>
                    <tr>
                        <td>${caratteristica.nome}</td>
                        <td>${caratteristica.unitaMisura}</td>
                        <td>${caratteristica.categoriaNipote.nome}</td>
                        <td>
                            <form method="POST" action="gestione_caratteristiche?page=${page}">
                                <input type="hidden" name="id" value="${caratteristica.key}">
                                <input class="btn btn-primary" type="submit" id="render" name="render"
                                       value="Modifica">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action="gestione_caratteristiche?page=${page}">
                                <input type="hidden" name="id"
                                       value="${caratteristica.key}">
                                <input class="btn btn-danger" type="submit" id="action" name="action"
                                       value="Elimina">
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
                <li class="page-item"><a class="btn btn-primary" href="gestione_caratteristiche?page=${page-1}">Pagina
                        precedente</a>
                </li>
            </#if>
            <li class="page-item"><a class="btn btn-primary" href="gestione_caratteristiche?page=${page+1}">Pagina
                    successiva</a>
            </li>
        </ul>
    </nav>
</div>


<!--inizio div popup-->
<div class="popup-container" id="add" style="display: ${visibilityInsert!"none"}">
    <div class="popup">
        <!--fine div popup-->
        <div class="update-screen">
            <div class="titolo-popup">
                <h4>Aggiungi Caratteristica</h4>
            </div>
            <form method="POST" action="gestione_caratteristiche?page=${page}" class="contenuto-form-popup">
                <label for="nome">Inserisci Nome:</label>
                <input class="form-control modifica-input" id="nome" name="nome" type="text" required>
                <label for="nome">Inserisci Unità di misura:</label>
                <input class="form-control modifica-input" id="misura" name="misura" type="text" required>
                <div class="dropdownContainer">
                    <select name="sceltaNipote" id="sceltaNipote" class="scelta-dropdown" required>
                        <option class="dropdown-scelta-item" selected
                                disabled
                                hidden>Seleziona Categoria Nipote
                        </option>
                        <#if (nipoti)??>
                            <#list nipoti as nipote>
                                <option class="dropdown-scelta-item"
                                        value="${nipote.key}">${nipote.nome}</option>
                            </#list>
                        </#if>
                    </select>
                </div>

                <div class="buttons-choose">
                    <input class="btn btn-primary" type="submit" id="action" name="action" value="Aggiungi">
                    <a class="btn btn-danger" href="gestione_caratteristiche?page=${page}">Annulla</a>
                </div>
            </form>
        </div>
    </div>
</div>


<!--inizio div popup-->
<div class="popup-container" id="modify" style="display: ${visibilityUpdate!"none"}">
    <div class="popup">
        <!--fine div popup-->
        <div class="modify-screen">
            <div class="titolo-popup">
                <h4>Modifica Caratteristica</h4>
            </div>
            <form method="POST" action="gestione_caratteristiche?page=${page}" class="contenuto-form-popup">
                <label for="nome">Inserisci nuovo Nome:</label>
                <label for="id"></label>
                <input type="text" id="id" name="id" style="display: none" value="${(caratteristicaModifica.key)!"0"}">
                <input class="form-control modifica-input" id="nome" name="nome" type="text"
                       value="${(caratteristicaModifica.nome)!""}" required>
                <label for="nome">Inserisci nuova Unità di misura:</label>
                <input class="form-control modifica-input" id="misura" name="misura" type="text"
                       value="${(caratteristicaModifica.unitaMisura)!""}" required>
                <div class="dropdownContainer">
                    <select name="sceltaNipote" id="sceltaNipote" class="scelta-dropdown" required>
                        <option class="dropdown-scelta-item" value="${(caratteristicaModifica.categoriaNipote.key)!"0"}"
                                selected
                                disabled
                                hidden>${(caratteristicaModifica.categoriaNipote.nome)!"Seleziona Ufficio"}</option>
                        <#if (nipoti)??>
                            <#list nipoti as nipote>
                                <option class="dropdown-scelta-item"
                                        value="${nipote.key}">${nipote.nome}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
                <div class="buttons-choose">
                    <input class="btn btn-primary" type="submit" id="action" name="action" value="Modifica">
                    <a class="btn btn-danger" href="gestione_caratteristiche?page=${page}">Annulla</a>
                </div>

            </form>
        </div>
    </div>
</div>


<span class="invisible" id="success">${success!"0"}</span>
<#include "modules/footer.ftl">


<script type="application/javascript" src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/gestione_caratteristiche.js"></script>
<!--
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="application/javascript" src="/scripts/pager.js"></script>
-->
</body>
</html>