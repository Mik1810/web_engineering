import {sweetAlert} from './sweetAlert.js';

document.addEventListener("DOMContentLoaded", () => {
    let span = document.getElementById('success');
    if (span.innerText === "1") {
        sweetAlert("OK", "CategoriaPadre modificata con successo.", "success", () => {
            // This is done to avoid the resubmission of the form
            window.location.href = window.location.pathname + window.location.search
        });

        /*
        Swal.fire({
            icon: "success",
            title: "OK",
            text: "CategoriaPadre modificata con successo.",
        }).then((result) => {
            if (result.isConfirmed) {
                // This is done to avoid the resubmission of the form
                window.location.href = window.location.pathname + window.location.search
            }
        });*/


    } else if (span.innerText === "2") {
        sweetAlert("OK", "CategoriaPadre aggiunta con successo.", "success", () => {
            // This is done to avoid the resubmission of the form
            window.location.href = window.location.pathname + window.location.search
        });


        /*
        Swal.fire({
            icon: "success",
            title: "OK",
            text: "CategoriaPadre aggiunta con successo.",
        }).then((result) => {
            if (result.isConfirmed) {
                // This is done to avoid the resubmission of the form
                window.location.href = window.location.pathname + window.location.search
            }
        });
         */
    }
});

