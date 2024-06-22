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
    <link rel="stylesheet" href="/style/table.css">
</head>
<body>

<#include "modules/header.ftl">

<div class="container">
    <h1>Caratteristiche della richiesta: ${richiesta.codiceRichiesta}</h1>

    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="left">Nome</th>
                <th scope="col" class="left">Valore</th>
                <th scope="col" class="data-column left">Unità di Misura</th>
                <th scope="col">Modifica</th>
                <th scope="col">Rimuovi</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <#list caratteristiche as caratteristicaConValore>
                <tr>
                    <td class="left">${caratteristicaConValore.caratteristica.nome}</td>
                    <td class="left">${caratteristicaConValore.valore}</td>
                    <td class="data-column left">${caratteristicaConValore.caratteristica.unitaMisura}</td>
                    <td>
                        <form method="POST" action="caratteristiche?id_richiesta=${richiesta.key}">
                            <input type="hidden" id="id" name="id" value="${caratteristicaConValore.key}">
                            <input class="btn btn-primary" type="submit" id="render" name="render" value="Modifica">
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="caratteristiche?id_richiesta=${richiesta.key}">
                            <input type="hidden" name="id" value="${caratteristicaConValore.key}">
                            <input class="btn btn-danger" type="submit" id="action" name="action" value="Elimina">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</div>

<div class="popup-container" id="add" style="display: ${visibilityModify!"none"}">
    <div class="popup">

        <div class="update-screen">
            <h4 class="d-flex align-items-center">Modifica Caratteristica</h4>
            <p class="p-3"></p>
            <form method="POST" action="caratteristiche?id_richiesta=${(richiesta.key)!"0"}">

                <input type="hidden" name="id" value="${(caratteristicaConValore.key)!"0"}">
                <label for="valore">Inserisci nuovo valore: </label>
                <input class="form-control" name="valore" id="valore" value="${(caratteristicaConValore.valore)!"0"}">
                <p class="p-1"></p>
                <div class="buttons-choose">
                    <input class="btn btn-primary" type="submit" id="action" name="action" value="Modifica">
                    <a class="btn btn-danger" href="caratteristiche?id_richiesta=${(richiesta.key)!"0"}">Annulla</a>
                </div>

            </form>
        </div>

    </div>
</div>

<span class="invisible" id="success">${success!"0"}</span>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/caratteristiche_richiesta.js"></script>

<#include "modules/footer.ftl">

</body>
</html>