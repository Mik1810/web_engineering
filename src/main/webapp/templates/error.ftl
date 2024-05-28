<!DOCTYPE html>
<html  lang="it">
<head>
    <title>Error ${error_code}</title>
</head>
<body>
<div class="body">

    <h1>Error ${error_code}</h1>
    <p>The system encountered the following error while handling your request:</p>
    <p>${error_message!"Unknown error"}</p>
    <div><a href="${referrer!login}">Return to the previous page.</a></div>
</div>
</body>
</html>