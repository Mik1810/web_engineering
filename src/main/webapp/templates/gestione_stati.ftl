<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyWebmarket</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/table.css">
</head>
<body>
<#include "modules/header.ftl">


<div class="container">
    <h1>Stati e Feedback</h1>

    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="fixed-width left">Nome</th>
                <th scope="col">Modifica</th>
                <th scope="col">Elimina</th>
            </tr>
            </thead>
            <tbody id="tbody">
                <#list statiConsegna as statoConsegna>
                    <tr>
                        <td class="left">${statoConsegna.nome}</td>
                        <td>
                            <form method="POST" action="gestione_stati?stato_consegna">
                                <input type="hidden" name="id" value="${statoConsegna.key}">
                                <input class="btn btn-primary" type="submit" id="render" name="render" value="Modifica">
                            </form>
                        </td>
                        <td>
                            <form method="POST" action="gestione_stati?stato_consegna">
                                <input type="hidden" name="id" value="${statoConsegna.key}">
                                <input class="btn btn-danger" type="submit" id="action" name="action" value="Elimina">
                            </form>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>

    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="fixed-width left">Nome</th>
                <th scope="col">Modifica</th>
                <th scope="col">Elimina</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <#list statiProposta as statoProposta>
                <tr>
                    <td class="left">${statoProposta.nome}</td>
                    <td>
                        <form method="POST" action="gestione_stati?stato_proposta">
                            <input type="hidden" name="id" value="${statoProposta.key}">
                            <input class="btn btn-primary" type="submit" id="render" name="render" value="Modifica">
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="gestione_stati?stato_proposta">
                            <input type="hidden" name="id" value="${statoProposta.key}">
                            <input class="btn btn-danger" type="submit" id="action" name="action" value="Elimina">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="fixed-width left">Nome</th>
                <th scope="col">Modifica</th>
                <th scope="col">Elimina</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <#list feedbacks as feedback>
                <tr>
                    <td class="left">${feedback.nome}</td>
                    <td>
                        <form method="POST" action="gestione_stati?feedback">
                            <input type="hidden" name="id" value="${feedback.key}">
                            <input class="btn btn-primary" type="submit" id="render" name="render" value="Modifica">
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="gestione_stati?feedback">
                            <input type="hidden" name="id" value="${feedback.key}">
                            <input class="btn btn-danger" type="submit" id="action" name="action" value="Elimina">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div


<#include "modules/footer.ftl">
</body>
</html>