/**
 * Displays a sweet alert with the given parameters.
 *
 * @param {string} title - The title of the alert.
 * @param {string} text - The text content of the alert.
 * @param {("success"|"error"|"warning"|"info"|"question")} type - The type of the alert.
 * @param {function|null} [thenConfirm] - The function to be executed after the alert is closed by a confirm button.
 */
export function sweetAlert(title, text, type, thenConfirm) {
    if (thenConfirm) {
        Swal.fire({
            title: title,
            text: text,
            icon: type
        }).then((result) => {
            if (result.isConfirmed) {
                thenConfirm();
            }
        });
        return;
    } else {
        Swal.fire({
            title: title,
            text: text,
            icon: type
        });
    }
}