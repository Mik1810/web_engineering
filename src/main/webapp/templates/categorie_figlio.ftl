<!DOCTYPE html >
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
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/table.css">


</head>
<body>
<#include "modules/header.ftl">
<div class="container">
    <h1>Gestione Categorie Figlio</h1>

    <div id="aggiungi" class="aggiungi">
        <form method="POST" action="categoria_figlio">
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
                        <td><a href="categoria_nipote?id_categoria_genitore=${categoria.key}">${categoria.nome}</a></td>
                        <td>
                            <a href="categoria_padre?id=${categoria.categoriaGenitore.key}">${categoria.categoriaGenitore.nome}</a>
                        </td>
                        <td>
                            <form method="POST" action="categoria_figlio">
                                <input type="hidden" name="id" value="${categoria.key}">
                                <input type="hidden" name="id_categoria_genitore"
                                       value="${id_categoria_genitore!"null"}">
                                <input class="btn btn-primary" type="submit" id="render" name="render"
                                       value="Modifica">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action="categoria_figlio">
                                <input type="hidden" name="id"
                                       value="${categoria.key}">
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
    </div>
</div>


<!--inizio div popup-->
<div class="popup-container" id="add" style="display: ${visibilityInsert!"none"}">
    <div class="popup">
        <!--fine div popup-->
        <div class="update-screen">
            <div class="titolo-popup">
                <h4>Aggiunta Categoria Figlio</h4>
            </div>
            <form method="POST" action="categoria_figlio" class="contenuto-form-popup">
                <input type="hidden" name="id_categoria_genitore" value="${id_categoria_genitore!"null"}">
                <label for="nome">Inserisci nome:</label>
                <input class="form-control modifica-input" id="nome" name="nome" type="text" required>
                <div class="dropdownContainer">
                    <select name="sceltaCategoriaPadre" id="sceltaCategoriaPadre" class="scelta-dropdown" required>
                        <#if (categoriePadre)??>
                            <#list categoriePadre as categoriaPadre>
                                <option class="dropdown-scelta-item"
                                        value="${categoriaPadre.key}">${categoriaPadre.nome}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
                <div class="buttons-choose">
                    <input class="btn btn-primary" type="submit" id="action" name="action" value="Aggiungi">
                    <#if (id_categoria_genitore)??>
                        <a class="btn btn-danger"
                           href="categoria_figlio?id_categoria_genitore=${id_categoria_genitore}">Annulla</a>
                    <#else>
                        <a class="btn btn-danger" href="categoria_figlio">Annulla</a>
                    </#if>
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
                <h4>Modifica Categoria Figlio</h4>
            </div>
            <form method="POST" action="categoria_figlio" class="contenuto-form-popup">
                <input type="hidden" name="id_categoria_genitore"
                       value="${id_categoria_genitore!"null"}">
                <label for="nome">Inserisci nuovo nome:</label>
                <label for="id"></label><input type="text" id="id" name="id" style="display: none"
                                               value="${(categoriaModifica.key)!"0"}">
                <input class="form-control modifica-input" id="nome" name="nome" type="text"
                       value="${(categoriaModifica.nome)!""}" required>

                <div class="dropdownContainer">
                    <select name="sceltaCategoriaPadre" id="sceltaCategoriaPadre" class="scelta-dropdown" required>
                        <option class="dropdown-scelta-item" value="${(categoriaGenitoreEsistente.key)!"0"}" selected
                                disabled
                                hidden>${(categoriaGenitoreEsistente.nome)!"Categoria Padre"}</option>
                        <#if (categoriePadre)??>
                            <#list categoriePadre as categoriaPadre>
                                <option class="dropdown-scelta-item"
                                        value="${categoriaPadre.key}">${categoriaPadre.nome}</option>
                            </#list>
                        </#if>
                    </select>
                </div>


                <div class="buttons-choose">
                    <input class="btn btn-primary" type="submit" id="action" name="action"
                           value="Modifica">
                    <#if (id_categoria_genitore)??>
                        <a class="btn btn-danger"
                           href="categoria_figlio?id_categoria_genitore=${id_categoria_genitore}">Annulla</a>
                    <#else>
                        <a class="btn btn-danger" href="categoria_figlio">Annulla</a>
                    </#if>
                </div>

            </form>
        </div>
    </div>
</div>


<span class="invisible" id="success">${success!"0"}</span>

<#include "modules/footer.ftl">

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/categorie_figlio.js"></script>

</body>
</html>