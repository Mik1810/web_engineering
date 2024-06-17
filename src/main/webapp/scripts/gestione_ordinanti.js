import {sweetAlert} from './sweetAlert.js';

document.addEventListener("DOMContentLoaded", () => {
    let span = document.getElementById('success');
    if (span.innerText === "1") {
        sweetAlert("OK", "Ordinante modificato con successo.", "success", () => {
            // This is done to avoid the resubmission of the form
            window.location.href = window.location.pathname + window.location.search
        });
    } else if (span.innerText === "2") {
        sweetAlert("OK", "Ordinante aggiunto con successo.", "success", () => {
            // This is done to avoid the resubmission of the form
            window.location.href = window.location.pathname + window.location.search
        });
    } else if (span.innerText === "-1") {
        sweetAlert("Compila tutti i campi", "Inserisci email e/o password", "error", () => {
            // This is done to avoid the resubmission of the form
            window.location.href = window.location.pathname + window.location.search
        });
    }
});

