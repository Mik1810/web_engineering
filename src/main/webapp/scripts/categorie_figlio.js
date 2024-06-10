document.addEventListener("DOMContentLoaded", () => {
    console.log("DOM fully loaded and parsed")
    let span = document.getElementById('success');
    if (span.innerText === "1") {
        Swal.fire({
            icon: "success",
            title: "OK",
            text: "CategoriaFiglio modificata con successo.",
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = window.location.pathname
            }
        });
    } else if (span.innerText === "2") {
        Swal.fire({
            icon: "success",
            title: "OK",
            text: "CategoriaFiglio aggiunta con successo.",
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = window.location.pathname
            }
        });
    } else if (span.innerText === "-1") {
        Swal.fire({
            icon: "error",
            title: "Attenzione",
            text: "Inserisci un nome valido",
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = window.location.pathname
            }
        });
    } else if (span.innerText === "-2") {
        Swal.fire({
            icon: "error",
            title: "Attenzione",
            text: "Scegli una categoria padre",
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = window.location.pathname
            }
        });
    }
});