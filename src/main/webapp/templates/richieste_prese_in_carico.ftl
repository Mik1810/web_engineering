<!DOCTYPE html >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/table.css">
</head>
<body>

<!-- Visualizzare i dettagli della richiesta presa in carico richiesta, con il pulsante per vedere le caratteristiche
    e il pulsante per creare una proposta
-->
<!-- Visualizzare il popup delle caratteristiche -->
<!-- Visualizzare il popup del menù per inserire i campire della proposta-->

<#include "modules/header.ftl">

<div class="ordine-container">
    <h1>Richieste Prese In Carico</h1>

    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="left">Codice</th>
                <th scope="col" class="left fixed-width">Note</th>
                <th scope="col" class="data-column left">Data</th>
                <th scope="col">Caratteristiche</th>
                <th scope="col">Proposte</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <#list richiestePreseInCarico as richiestaPresaInCarico>
                <tr>
                    <td class="left">${richiestaPresaInCarico.richiesta.codiceRichiesta}</td>
                    <td class="left fixed-width">${richiestaPresaInCarico.richiesta.note}</td>
                    <td class="data-column left">${richiestaPresaInCarico.richiesta.data}</td>
                    <td>
                        <#if id??>
                            <form method="POST" action="richieste_prese_in_carico?id=${id}">
                                <input class="btn btn-primary" type="submit" id="render" name="render"
                                       value="Caratteristiche">
                            </form>
                        <#else>
                            <form method="POST" action="richieste_prese_in_carico?page=${page!"0"}">
                                <input type="hidden" id="id" name="id" value="${richiestaPresaInCarico.key}">
                                <input class="btn btn-primary" type="submit" id="render" name="render"
                                       value="Caratteristiche">
                            </form>
                        </#if>

                    </td>
                    <td>
                        <#if id??>
                            <form method="POST" action="richieste_prese_in_carico?id=${id}">
                                <input class="btn btn-success" type="submit" id="render" name="render"
                                       value="Crea Proposta">
                            </form>
                        <#else>
                            <form method="POST" action="richieste_prese_in_carico?page=${page!"0"}">
                                <input type="hidden" id="id" name="id" value="${richiestaPresaInCarico.key}">
                                <input class="btn btn-success" type="submit" id="render" name="render"
                                       value="Crea Proposta">
                            </form>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <#if page??>
        <div class="paginazione">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <#if page == 0>
                        <li class="page-item"><a class="btn btn-secondary disabled">Pagina Precedente</a></li>
                    <#else>
                        <li class="page-item">
                            <a class="btn btn-primary" href="richieste_prese_in_carico?page=${page-1}">Pagina
                                precedente</a>
                        </li>
                    </#if>
                    <li class="page-item">
                        <a class="btn btn-primary" href="richieste_prese_in_carico?page=${page+1}">Pagina successiva</a>
                    </li>
                </ul>
            </nav>
        </div>
    </#if>
</div>

<div class="popup-container" id="caratteristiche" style="display: ${visibilityCaratteristiche!"none"}">
    <div class="popup">

        <div class="update-screen">
            <div class="titolo-popup">
                <div><h4>Caratteristiche</h4></div>
                <#if caratteristiche??>
                    <p>Categoria: ${caratteristiche[0].caratteristica.categoriaNipote.categoriaGenitore.categoriaGenitore.nome} > ${caratteristiche[0].caratteristica.categoriaNipote.categoriaGenitore.nome} > ${caratteristiche[0].caratteristica.categoriaNipote.nome}</p>
                </#if>
            </div>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" class="left">Nome</th>
                    <th scope="col">Valore</th>
                    <th scope="col">Unità di Misura</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <#if caratteristiche??>
                    <#list caratteristiche as caratteristicaConValore>
                        <tr>
                            <td class="left">${caratteristicaConValore.caratteristica.nome!""}</td>
                            <td>${caratteristicaConValore.valore!""}</td>
                            <td>${caratteristicaConValore.caratteristica.unitaMisura!""}</td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>

            <#if id??>
                <a class="btn btn-danger proposta-buttons" href="richieste_prese_in_carico?id=${id!""}">Esci</a>
            <#else>
                <a class="btn btn-danger proposta-buttons" href="richieste_prese_in_carico?page=${page!"0"}">Esci</a>
            </#if>
        </div>
    </div>
</div>

<div class="popup-container" id="compila-proposta" style="display: ${visibilityProposta!"none"}">
    <div class="popup">

        <div class="update-screen">
            <div class="titolo-popup">
                <h4>Crea Proposta</h4>
            </div>
            <!-- Qui va  la form per compilare la proposta-->
            <!-- Cose da inserire:
                1. Produttore
                2. note
                3. prezzo
                4. nome prodotto
                5. URL
                6. stato proposta - "In attesa"
             -->
            <#if id??>
                <form method="POST" action="richieste_prese_in_carico?id=${id}">

                    <label for="produttore">Produttore</label>
                    <input class="form-control" type="text" id="produttore" name="produttore" required/>

                    <label for="prezzo">Prezzo</label>
                    <input class="form-control" type="text" id="prezzo" name="prezzo" required/>

                    <label for="nome_prodotto">Nome prodotto</label>
                    <input class="form-control" type="text" id="nome_prodotto" name="nome_prodotto" required/>

                    <label for="URL">URL</label>
                    <input class="form-control" type="text" name="URL" id="URL" pattern="https://.*" required/>

                    <label for="note"></label>
                    <textarea class="textarea-note form-control" id="note" name="note" rows="4" cols="50"
                              placeholder="Inserisci note..."></textarea>

                    <div class="proposta-container-buttons">
                        <input class="btn btn-primary proposta-buttons" type="submit" id="action" name="action"
                               value="Crea"/>
                        <a class="btn btn-danger proposta-buttons" href="richieste_prese_in_carico?id=${id!""}">Esci</a>
                    </div>
                </form>

            <#else>

                <form method="POST" action="richieste_prese_in_carico?page=${page!"0"}">

                    <input class="form-control" type="hidden" id="id" name="id"
                           value="${(richiestaPresaInCarico.key)!""}">

                    <label for="produttore">Produttore</label>
                    <input class="form-control" type="text" id="produttore" name="produttore" required/>

                    <label for="prezzo">Prezzo</label>
                    <input class="form-control" type="text" id="prezzo" name="prezzo" required/>

                    <label for="nome_prodotto">Nome prodotto</label>
                    <input class="form-control" type="text" id="nome_prodotto" name="nome_prodotto" required/>

                    <label for="URL">URL</label>
                    <input class="form-control" type="text" name="URL" id="URL" pattern="https://.*" required/>

                    <label for="note"></label>
                    <textarea class="textarea-note form-control" id="note" name="note" rows="4" cols="50"
                              placeholder="Inserisci note..."></textarea>

                    <div class="proposta-container-buttons">
                        <input class="btn btn-success proposta-buttons" type="submit" id="action" name="action"
                               value="Crea">
                        <a class="btn btn-danger proposta-buttons" href="richieste_prese_in_carico?page=${page!"0"}">Esci</a>
                    </div>
                </form>

            </#if>
        </div>
    </div>
</div>

<span class="invisible" id="success">${success!"0"}</span>

<#include "modules/footer.ftl">

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="module" src="/scripts/richieste_prese_in_carico.js"></script>

</body>
</html>