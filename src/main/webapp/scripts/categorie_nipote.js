import {sweetAlert} from './sweetAlert.js';

document.addEventListener("DOMContentLoaded", () => {
    console.log("DOM fully loaded and parsed")
    let span = document.getElementById('success');
    console.log(span.innerText);
    if (span.innerText === "1") {

        sweetAlert("OK", "Categoria Nipote modificata con successo.", "success", () => {
        });

    } else if (span.innerText === "2") {
        sweetAlert("OK", "Categoria Nipote aggiunta con successo.", "success", () => {
        });

    } else if (span.innerText === "-2") {
        sweetAlert("Errore", "Categoria Nipote non aggiunta, nome già esistente.", "error", () => {
        });

    } else if (span.innerText === "-1") {

        sweetAlert("Errore", "Categoria Nipote non aggiunta, scegli una categoria figlio.", "error", () => {
        });

    }
});