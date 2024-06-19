import {sweetAlert} from './sweetAlert.js';

document.addEventListener("DOMContentLoaded", () => {
    let span = document.getElementById('success');
    if (span.innerText === "1") {
        sweetAlert("OK", "Tecnico modificato con successo.", "success", () => {
            // This is done to avoid the resubmission of the form
            window.location.href = window.location.pathname + window.location.search
        });
    } else if (span.innerText === "2") {
        sweetAlert("OK", "Tecnico aggiunto con successo.", "success", () => {
            // This is done to avoid the resubmission of the form
            window.location.href = window.location.pathname + window.location.search
        });
    } else if (span.innerText === "-1") {
        sweetAlert("Compila tutti i campi", "Inserisci email e/o password", "error", () => {
            // This is done to avoid the resubmission of the form
            window.location.href = window.location.pathname + window.location.search
        });
    } else if (span.innerText === "-2") {
        sweetAlert("Email già utilizzata", "Non è permesso l'utilizzo della stessa mail più volte", "error", () => {
            // This is done to avoid the resubmission of the form
            window.location.href = window.location.pathname + window.location.search
        });
    }
});