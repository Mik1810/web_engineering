<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina di Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/style/login-style.css">
</head>
<body>
<div class="container">
    <h1>Accedi</h1>
    <form class="item" action="login" method="POST">
        <#if (referrer??)>
            <input name="referrer" type="hidden" value="${referrer}"/>
        </#if>
        <p class="p-4"></p>
        <div class="form-floating mb-3">
            <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com">
            <label for="email">Email address</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            <label for="password">Password</label>
        </div>
        <p id="access-as-string">Ruolo:</p>
        <div class="radio-container">
            <div>
                <input class="form-check-input" type="radio" name="role" id="radio-ordinante" value="${ORDINANTE}"
                       checked>
                <label class="form-check-label" for="radio-ordinante">Ordinante</label>
            </div>
            <div>
                <input class="form-check-input" type="radio" name="role" id="radio-tech-prev"
                       value="${TECNICO_PREVENTIVI}">
                <label class="form-check-label" for="radio-tech-prev">Tecnico Preventivi</label>
            </div>
            <div>
                <input class="form-check-input" type="radio" name="role" id="radio-tech-ord" value="${TECNICO_ORDINI}">
                <label class="form-check-label" for="radio-tech-ord">Tecnico Ordini</label>
            </div>
            <div>
                <input class="form-check-input" type="radio" name="role" id="radio-admin" value="${AMMINISTRATORE}">
                <label class="form-check-label" for="radio-admin">Amministratore</label>
            </div>
        </div>
        <p class="p-3"></p>
        <div class="d-flex justify-content-center">
            <button id="login" name="login" type="submit" class="btn btn-primary w-50 mt-2 submit-button">Entra</button>
        </div>

    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="/scripts/login.js"></script>

</body>

</html>