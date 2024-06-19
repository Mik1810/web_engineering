import {sweetAlert} from './sweetAlert.js';

document.addEventListener("DOMContentLoaded", () => {

    console.log("DOM fully loaded and parsed")
    const richiestaID = document.getElementById('id').innerText;
    const span = document.getElementById('success');
    console.log(span.innerText);
    if (span.innerText === "1") {

        sweetAlert("OK", "Richiesta modificata con successo.", "success", () => {
        });

    } else if(span.innerText === "-1") {
        sweetAlert("Errore", "Richiesta non modificata, la nuova nota è identica alla precedente.", "error", () => {
        });
    } else if(span.innerText === "2") {
        sweetAlert("OK", "Richiesta eliminata con successo.", "success", () => {
        });
    }
});