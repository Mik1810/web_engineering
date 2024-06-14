<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="it" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>MyWebmarket</title>

    <link rel="stylesheet" href="/style/admin-style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/style/default.css">
    <link rel="stylesheet" href="/style/ordinante.css">
</head>
<body>

    <#include "modules/header.ftl">

    <div class="container">
        <div class="quote-container">
            <i class="pin"></i>
            <blockquote class="note yellow">
                <div>
                    <#list ordini as ordine>
                        <span><a href="richieste?id=${richiesta.key}">Ordine ${ordine.key} - ${ordine.statoConsegna}</a></span>
                    </#list>
                </div>
              <cite class="author">
                  <a href="richieste">Vedi tutti</a>
              </cite>
            </blockquote>
          </div>

          <div class="quote-container">
            <i class="pin"></i>
            <blockquote class="note pink">
                <div>
                    <a href="#">Proposta per: ordine 1</a>
                </div>
                <div>
                    <a href="#">Proposta per: ordine 2</a>
                </div>
                <div>
                    <a href="#">Proposta per: ordine 3</a>
                </div>

              <cite class="author">Vedi tutti</cite>
            </blockquote>
          </div>

          <div class="quote-container">
            <i class="pin"></i>
            <blockquote class="note green">
                <div>
                    <a href="#">Opzione 1</a>
                </div>
                <div>
                    <a href="#">Opzione 2</a>
                </div>
                <div>
                    <a href="#">Opzione 3</a>
                </div>
              <cite class="author">Vedi tutti</cite>
            </blockquote>
          </div>

    </div>

    <#include "modules/footer.ftl">

</body>
</html>