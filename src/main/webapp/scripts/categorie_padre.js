document.addEventListener("DOMContentLoaded", () => {
    let span = document.getElementById('success');
    if (span.innerText === "1") {
        Swal.fire({
            icon: "success",
            title: "OK",
            text: "CategoriaPadre modificata con successo.",
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = window.location.pathname
            }
        });
    } else if(span.innerText === "2") {
        Swal.fire({
            icon: "success",
            title: "OK",
            text: "CategoriaPadre aggiunta con successo.",
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = window.location.pathname
            }
        });
    }
});