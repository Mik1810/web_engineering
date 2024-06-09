<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
            text-align: left;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e9e9e9;
        }

        .container {
            max-width: 800px;
            margin: auto;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .invisible {
            display: none;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Categorie</h1>
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
</body>
</html>