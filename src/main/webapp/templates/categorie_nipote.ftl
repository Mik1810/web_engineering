<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Categorie Nipote</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/categoria.css">


</head>
<body>
<#include "modules/header.ftl">
<div class="container">
    <h1>Categorie Nipote</h1>

    <div id="aggiungi" class="aggiungi">
        <form method="POST" action="categoria_nipote">
            <input class="btn btn-success" type="submit" name="render" id="render" value="Aggiungi"/>
        </form>
    </div>

    <div class="row">

        <div class="list">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Categoria Genitore</th>
                    <th scope="col">Modifica</th>
                    <th scope="col">Elimina</th>
                </tr>
                </thead>
                <tbody>
                <#list categorie as categoria>
                    <tr>
                        <td>${categoria.nome}</td>
                        <td>
                            <a href="categoria_figlio">
                                ${categoria.categoriaGenitore.nome}
                        </td>
                        <td>
                            <form method="POST" action="categoria_nipote">
                                <input type="hidden" name="id" value="${categoria.key}">
                                <input type="hidden" name="id_categoria_genitore"
                                       value="${id_categoria_genitore!"null"}">
                                <input class="btn btn-primary" type="submit" id="render" name="render"
                                       value="Modifica">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action="categoria_nipote">
                                <input type="hidden" name="id" value="${categoria.key}">
                                <input type="hidden" name="id_categoria_genitore"
                                       value="${id_categoria_genitore!"null"}">
                                <input class="btn btn-danger" type="submit" id="action" name="action"
                                       value="Elimina">
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>


        <div style="display: ${visibilityUpdate!"none"}" class="modify-screen">
            <form method="POST" action="categoria_nipote">
                <input type="hidden" name="id_categoria_genitore"
                       value="${id_categoria_genitore!"null"}">
                <label for="nome">Inserisci nuovo nome:</label>
                <label for="id"></label>
                <input type="text" id="id" name="id" style="display: none"
                       value="${(categoriaModifica.key)!"0"}">
                <input class="form-control modifica-input" id="nome" name="nome" type="text"
                       value="${(categoriaModifica.nome)!""}">

                <div class="dropdownContainer">
                    <select name="sceltaCategoriaFiglio" id="sceltaCategoriaFiglio" class="scelta-dropdown" required>
                        <option class="dropdown-scelta-item" value="${(categoriaGenitoreEsistente.key)!"0"}" selected
                                disabled
                                hidden>${(categoriaGenitoreEsistente.nome)!"Categoria Padre"}</option>
                        <#if (categorieFiglio)??>
                            <#list categorieFiglio as categoriaFiglio>
                                <option class="dropdown-scelta-item"
                                        value="${categoriaFiglio.key}">${categoriaFiglio.nome}</option>
                            </#list>
                        </#if>
                    </select>
                </div>


                <div class="buttons-choose">
                    <input class="btn btn-primary" type="submit" id="action" name="action"
                           value="Modifica">
                    <#if (id_categoria_genitore)??>
                        <a class="btn btn-danger"
                           href="categoria_nipote?id_categoria_genitore=${id_categoria_genitore}">Annulla</a>
                    <#else>
                        <a class="btn btn-danger" href="categoria_nipote">Annulla</a>
                    </#if>
                </div>

            </form>
        </div>

        <div style="display: ${visibilityInsert!"none"}" class="update-screen">
            <form method="POST" action="categoria_nipote">
                <input type="hidden" name="id_categoria_genitore"
                       value="${id_categoria_genitore!"null"}">
                <label for="nome">Inserisci nome:</label>
                <input class="form-control modifica-input" id="nome" name="nome" type="text" required>
                <div class="dropdownContainer">
                    <select name="sceltaCategoriaFiglio" id="sceltaCategoriaFiglio" class="scelta-dropdown" required>
                        <#if (categorieFiglio)??>
                            <#list categorieFiglio as categoriaFiglio>
                                <option class="dropdown-scelta-item"
                                        value="${categoriaFiglio.key}">${categoriaFiglio.nome}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
                <div class="buttons-choose">
                    <input class="btn btn-primary" type="submit" id="action" name="action" value="Aggiungi">
                    <#if (id_categoria_genitore)??>
                        <a class="btn btn-danger"
                           href="categoria_nipote?id_categoria_genitore=${id_categoria_genitore}">Annulla</a>
                    <#else>
                        <a class="btn btn-danger" href="categoria_nipote">Annulla</a>
                    </#if>
                </div>
            </form>
        </div>
    </div>
</div>


<span class="invisible" id="success">${success!"0"}</span>

<#include "modules/footer.ftl">


<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/categorie_nipote.js"></script>

</body>
</html>