console.log("dio cane")
const urlParams = new URLSearchParams(window.location.search);
const page = urlParams.get('page');
const previous_page_link = document.getElementById('previous-page');
const next_page_link = document.getElementById('next-page');

if (page === "0") {
    previous_page_link.style.display = "none";
    next_page_link.href = window.location.pathname + "?page=" + (parseInt(page) + 1);
} else {
    previous_page_link.style.display = "block";
    previous_page_link.href = window.location.pathname + "?page=" + (parseInt(page) - 1);
    next_page_link.href = window.location.pathname + "?page=" + (parseInt(page) + 1);

}