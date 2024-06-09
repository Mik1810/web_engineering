<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Categoria Padre</title>

    <link rel="stylesheet" href="/style/categoria-padre.css">
    <link rel="stylesheet" href="/style/default.css">

</head>
<body>
<#include "modules/header.ftl">
<div class="container">
    <h1>Categorie</h1>

    <div class="row">

        <div class="list">
            <table>
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Modifica</th>
                    <th>Elimina</th>
                </tr>
                </thead>
                <tbody>
                <#list categorie as categoria>
                    <tr>
                        <td><a href="categoria_figlio">${categoria.nome}</a></td>
                        <td>
                            <form method="POST" action="categoria_padre">
                                <input type="hidden" name="id" value="${categoria.key}">
                                <input type="submit" id="action" name="action" value="Modifica">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action="categoria_padre">
                                <input type="hidden" name="id" value="${categoria.key}">
                                <input type="submit" id="action" name="action" value="Elimina">
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>


        <div id="modify-screen" style="display: ${visibility!"none"}">
            <form method="POST" action="categoria_padre" class="modify_screen">
                <label for="nome">Inserisci nuovo nome:</label>
                <label for="id"></label><input type="text" id="id" name="id" style="display: none"
                                               value="${(categoriaModifica.key)!"0"}">
                <input id="nome" name="nome" type="text" value="${(categoriaModifica.nome)!""}">
                <input type="submit" id="actionModify" name="actionModify" value="Modifica">

            </form>
        </div>
    </div>
</div>


<span class="invisible" id="success">${success!"false"}</span>

<#include "modules/footer.ftl">


<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="/scripts/categorie_padre.js"></script>

</body>
</html>