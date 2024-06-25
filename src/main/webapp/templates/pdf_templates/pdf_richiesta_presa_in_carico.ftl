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
    <h3>Richiesta presa in carico</h3>
    <div>Richiesta: <b>${richiestaPresaInCarico.richiesta.codiceRichiesta}</b></div>
    <div>Tecnico dei Preventivi: ${richiestaPresaInCarico.tecnicoPreventivi.email}</div>

</div>

</body>
</html>
