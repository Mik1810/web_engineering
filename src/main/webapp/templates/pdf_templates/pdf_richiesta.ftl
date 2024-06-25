<!DOCTYPE html>
<html lang="it">
<head>
    <title>Template PDF</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        p {
            line-height: 1.6;
            font-size: 14px;
        }

        .caratteristiche {
            margin-top: 1rem;
        }

        header {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 2rem;
        }
        hr {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>

<div class="container">
    <header>
        <div>
            <h1>MyWebmarket</h1>
        </div>
        <hr></hr>
    </header>
    <h3><b>Riepilogo Richiesta</b></h3>
    <div>Richiesta: <b>${richiesta.codiceRichiesta}</b></div>
    <!--
     Data: 2024-06-24
    Ordinante: michaelpiccirilli3@gmail.com
    Note: Voglio che il cavo sia anche rosso e in nylon
    Caratteristiche:
    Peso: 0.3
    Lunghezza cavo: 3.5
     -->
    <div>Data: ${richiesta.data}</div>
    <div>Email: ${richiesta.ordinante.email}</div>
    <div>Note: ${richiesta.note}</div>
    <div class="caratteristiche">Caratteristiche:
        <ul>
        <#list richiesta.caratteristicheConValore as caratteristicaConValore>
            <li>${caratteristicaConValore.caratteristica.nome}: ${caratteristicaConValore.valore}</li>
        </#list>
        </ul>
    </div>

</div>

</body>
</html>
