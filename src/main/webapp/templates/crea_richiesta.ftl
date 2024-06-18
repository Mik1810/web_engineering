<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8" content="">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MyWebmarket</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" >
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/categoria.css">
    <link rel="stylesheet" href="/style/crea-caratteristica.css">

</head>
<body>

<#include "modules/header.ftl">
<!--Le note le deve mettere alla fine-->
<!--Il codice richiesta è generato dal sistemq-->
<!--La data lo aggiunge comunque il sistema-->
<!--L'ordinante sta salvato in sessione-->
<!--Bisogna solo scegliere le caratteristiche con valore  e poi le note
-->
<div class="container">
    <div class="left-content">
        <div id="categoriePadreSelect" class="d-flex flex-column mb-2">
            <label for="sceltaCategoriaPadre">Categoria Padre:</label>
            <select name="sceltaCategoriaPadre" id="sceltaCategoriaPadre" class="form-select select-size" aria-label="Select per categoria padre">
                <option value="0" selected>Seleziona Categoria Padre</option>
                <#list categoriePadre as categoriaPadre>
                    <option value="${(categoriaPadre.key)}">
                        ${(categoriaPadre.nome)}
                    </option>
                </#list>
            </select>
        </div>

        <div id="categorieFiglioSelect" class="d-flex flex-column invisible mb-2">
            <label for="sceltaCategoriaFiglio">Categoria Figlio:</label>
            <select name="sceltaCategoriaFiglio" id="sceltaCategoriaFiglio" class="form-select select-size" aria-label="Select per categoria figlio">
            </select>
        </div>

        <div id="categorieNipoteSelect" class="d-flex flex-column invisible mb-2">
            <label for="sceltaCategoriaNipote">Categoria Nipote:</label>
            <select name="sceltaCategoriaNipote" id="sceltaCategoriaNipote"  class="form-select select-size" aria-label="Select per categoria nipote">
            </select>
        </div>
    </div>

    <div class="right-content">
        <form id="caratteristiche" action="crea_richiesta" method="POST" class="invisible">

            <div class="d-flex align-items-center flex-column">
                <label>
                    <textarea class="textarea-note form-control" name="note" rows="4" cols="50" placeholder="Inserisci note..."></textarea>
                </label>
                <input class="btn btn-primary button-submit" type="submit" value="Crea Richiesta">
            </div>
        </form>
    </div>
</div>

<#include "modules/footer.ftl">

<script type="application/javascript" src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="module"  src="/scripts/crea_richiesta.js"></script>
</body>
</html>