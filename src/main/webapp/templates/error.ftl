<!DOCTYPE html >
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Error ${error_code}</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
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