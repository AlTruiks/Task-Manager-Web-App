function openPopUp(element) {
    const taskid = element.getAttribute("data-taskid");
    const taskname = element.getAttribute("data-taskname");
    const taskstatus = element.getAttribute("data-taskstatus");
    const userid = element.getAttribute("data-userid");
    const popupContainer = document.querySelector('.popup-container');

    popupContainer.classList.add('active');

    const popupStatusContent = document.querySelector('.popup-status-section');
    const popupCommentsContent = document.querySelector('.popup-comments-section');

    popupStatusContent.innerHTML = "<h2>" + taskname + "</h2>" +
        "<form action=\"/updatetask/" + taskid + "\" method=\"post\">" +
            "<span>Status zadania: " + taskstatus + "</span><br>" +
            "<select name='taskstatus'>" +
                "<option value='1'>To Do</option>" +
                "<option value='2'>In Progress</option>" +
                "<option value='3'>Completed</option>" +
                "<option value='4'>Deferred</option>" +
            "</select>" +
            "<button type='submit'>Zapisz</button>" +
        "</form>" +
        "<button class=\"popup-button-close\" onclick=\"closePopUp()\">Close</button>";

    popupCommentsContent.innerHTML = "<h3>Dodaj komentarz</h3>" +
        "<form action=\"/addcomment/" + userid + "/" + taskid + "\" method='post'>" +
        "<input type='text' name='commentcontent' placeholder='Wpisz komentarz'>" +
        "<button type='submit'>Dodaj komentarz</button> " +
        "</form>";

    fetch("/getcomments/" + taskid)
        .then(response => response.json())
        .then(comments => {
            popupCommentsContent.innerHTML += "<h3>Komentarze</h3>";
            comments.forEach(comment => {
                popupCommentsContent.innerHTML += "<div>" +
                    "<span>" + comment[1] + "</span><br>" + // zawartość komentarza
                    "<span>" + comment[3] + "</span><br>" + // data dodania komentarza
                    "<span>" + comment[2] + "</span>" + // autor komentarza
                    "</div>";
            });
        })
        .catch(error => {
            console.error("Błąd pobierania komentarzy:", error);
        });
}
function closePopUp() {
    const popupContainer = document.querySelector('.popup-container');
    popupContainer.classList.remove('active');
}
