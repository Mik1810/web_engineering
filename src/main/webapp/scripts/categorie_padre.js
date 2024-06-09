document.addEventListener("DOMContentLoaded", () => {
    let span = document.getElementById('success');
    if (span.innerText === "true") {
        Swal.fire({
            icon: "success",
            title: "OK",
            text: "Categoria padre modificata con successo",
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = window.location.pathname
            }
        });
    }
});