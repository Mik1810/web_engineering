import {sweetAlert} from './sweetAlert.js';

document.addEventListener("DOMContentLoaded", () => {
    console.log("DOM fully loaded and parsed")
    let span = document.getElementById('success');
    console.log(span.innerText);
    if (span.innerText === "1") {

        sweetAlert("OK", "Categoria Figlio modificata con successo.", "success", () => {
        });

        /*
        Swal.fire({
            icon: "success",
            title: "OK",
            text: "CategoriaFiglio modificata con successo.",
        }).then((result) => {
            if (result.isConfirmed) {
                //window.location.href = window.location.pathname
            }
        });
         */
    } else if (span.innerText === "2") {
        sweetAlert("OK", "Categoria Figlio aggiunta con successo.", "success", () => {
        });


        /*
        Swal.fire({
            icon: "success",
            title: "OK",
            text: "CategoriaFiglio aggiunta con successo.",
        }).then((result) => {
            if (result.isConfirmed) {
                //window.location.href = window.location.pathname
            }
        });
         */
    } else if (span.innerText === "-2") {
        sweetAlert("Errore", "Categoria Figlio non aggiunta, nome già esistente.", "error", () => {
        });

        /*
        Swal.fire({
            icon: "error",
            title: "Attenzione",
            text: "Inserisci un nome valido",
        }).then((result) => {
            if (result.isConfirmed) {
                //window.location.href = window.location.pathname
            }
        });
        */

    } else if (span.innerText === "-1") {

        sweetAlert("Errore", "Categoria Figlio non aggiunta, scegli una categoria padre.", "error", () => {
        });

        /*
        Swal.fire({
            icon: "error",
            title: "Attenzione",
            text: "Scegli una categoria padre",
        }).then((result) => {
            if (result.isConfirmed) {
                //window.location.href = window.location.pathname
            }
        });
        */
    }
});