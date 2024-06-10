<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Categoria Padre</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/categoria.css">
    <link rel="stylesheet" href="/style/default.css">

</head>
<body>
<#include "modules/header.ftl">
<div class="container">
    <h1>Categorie</h1>
    <div id="aggiungi">
        <form method="POST" action="categoria_padre">
            <input type="submit" name="render" id="render" value="Aggiungi"/>
        </form>
    </div>

    <div class="row">

        <div class="list">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Modifica</th>
                    <th scope="col">Elimina</th>
                </tr>
                </thead>
                <tbody>
                <#list categorie as categoria>
                    <tr>
                        <td><a href="categoria_figlio?id=${categoria.key}">${categoria.nome}</a></td>
                        <td>
                            <form method="POST" action="categoria_padre">
                                <input type="hidden" name="id" value="${categoria.key}">
                                <input class="btn btn-primary" type="submit" id="render" name="render"
                                       value="Modifica">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action="categoria_padre">
                                <input type="hidden" name="id"
                                       value="${categoria.key}">
                                <input class="btn btn-danger" type="submit" id="action" name="action"
                                       value="Elimina">


                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>


        <div id="modify-screen" style="display: ${visibilityUpdate!"none"}">
            <form method="POST" action="categoria_padre" class="modify_screen">
                <label for="nome">Inserisci nuovo nome:</label>
                <label for="id"></label><input type="text" id="id" name="id" style="display: none"
                                               value="${(categoriaModifica.key)!"0"}">
                <input class="form-control modifica-input" id="nome" name="nome" type="text"
                       value="${(categoriaModifica.nome)!""}">
                <input class="btn btn-primary" type="submit" id="action" name="action"
                       value="Modifica">

            </form>
        </div>

        <div id="update-screen" style="display: ${visibilityInsert!"none"}">
            <form method="POST" action="categoria_padre" class="update_screen">
                <label for="nome">Inserisci nome:</label>
                <input class="form-control modifica-input" id="nome" name="nome" type="text">
                <input class="btn btn-primary" type="submit" id="action" name="action" value="Aggiungi">
            </form>
        </div>
    </div>
</div>


<span class="invisible" id="success">${success!"0"}</span>

<#include "modules/footer.ftl">


<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="/scripts/categorie_padre.js"></script>

</body>
</html>