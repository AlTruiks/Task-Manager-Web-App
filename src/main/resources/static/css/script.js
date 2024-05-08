function openPopUp(element) {
    const taskid = element.getAttribute("data-taskid");
    const taskname = element.getAttribute("data-taskname");
    const taskstatus = element.getAttribute("data-taskstatus");
    const userid = element.getAttribute("data-userid");
    const projectid = element.getAttribute("data-projectid");
    const popupContainer = document.querySelector('.popup-container');

    popupContainer.classList.add('active');

    const popupStatusContent = document.querySelector('.popup-status-section');
    const popupCommentsContent = document.querySelector('.popup-comments-section');

    popupStatusContent.innerHTML = "<h2>" + taskname + "</h2>" +
        "<button class=\"popup-button-close\" onclick=\"closePopUp()\">X</button>" +
        "<form action=\"/updatetask/" + projectid + "/" + taskid + "\" method=\"post\">" +
            "<p>Status zadania: " + taskstatus + "</p>" +
            "<select name='taskstatus'>" +
                "<option value='1'>To Do</option>" +
                "<option value='2'>In Progress</option>" +
                "<option value='3'>Completed</option>" +
                "<option value='4'>Deferred</option>" +
            "</select>" +
            "<button type='submit'>Zapisz</button>" +
        "</form>";

    popupCommentsContent.innerHTML = "<h3>Dodaj komentarz</h3>" +
        "<form action=\"/addcomment/" + projectid + "/" + userid + "/" + taskid + "\" method='post'>" +
        "<input type='text' name='commentcontent' placeholder='Wpisz komentarz'>" +
        "<button type='submit'>Dodaj komentarz</button> " +
        "</form>";

    fetch("/getcomments/" + taskid)
        .then(response => response.json())
        .then(comments => {
            popupCommentsContent.innerHTML += "<h3>Komentarze</h3>";
            comments.forEach(comment => {
                const comment_userid = comment[2].toString();
                let commentHTML = "<div class='comment'>";
                if(comment_userid === userid) {
                    commentHTML += "<button class=\"popup-button-delete-comment\" data-commentid=\"${comment[0]}\">X</button>";
                }
                commentHTML +=
                    "<span>" + comment[1] + "</span><br>" + // zawartość komentarza
                    "<span>" + comment[4] + "</span>" + // data dodania komentarza
                    "<span>" + comment[3] + "</span>"; // autor komentarza
                commentHTML += "</div>";
                popupCommentsContent.innerHTML += commentHTML;
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
