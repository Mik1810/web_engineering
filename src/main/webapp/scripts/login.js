//import Swal from 'sweetalert2'

document.addEventListener("DOMContentLoaded", () => {
    const queryString = window.location.search
    const urlParams = new URLSearchParams(queryString);
    const errorURL = urlParams.get("error")

    switch (errorURL) {
        case "1":
            error("Errore", "Sessione scaduta, effettuare nuovamente il login!")
            break
        case "2":
            error("Errore", "Email o password errate!")
            break
        case "3":
            error("Errore", "Inserire email e password!")
            break
    }
});

function error(title, text) {
    Swal.fire({
        title: title,
        text: text,
        icon: "error"
    });
}


