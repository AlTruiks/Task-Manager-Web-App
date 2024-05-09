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
            "<p>Task status: " + taskstatus + "</p>" +
            "<select name='taskstatus'>" +
                "<option value='1'>To Do</option>" +
                "<option value='2'>In Progress</option>" +
                "<option value='3'>Completed</option>" +
                "<option value='4'>Deferred</option>" +
            "</select>" +
            "<button type='submit'>Save</button>" +
        "</form>";

    popupCommentsContent.innerHTML = "<h3>Add comment</h3>" +
        "<form action=\"/addcomment/" + projectid + "/" + userid + "/" + taskid + "\" method='post'>" +
        "<input type='text' name='commentcontent' placeholder='Leave a comment'>" +
        "<button type='submit' class='popup-button-add-comment'>Add commment</button> " +
        "</form>";

    fetch("/getcomments/" + taskid)
        .then(response => response.json())
        .then(comments => {
            popupCommentsContent.innerHTML += "<h3>Comments</h3>";
            comments.forEach(comment => {
                const comment_userid = comment[2].toString();
                let commentHTML = "<div class='comment'>";
                if(comment_userid === userid || userid === "1") {
                    commentHTML +=
                        "<span class='comment-delete'>" +
                        "<form action=\"/deletecomment/" + projectid + "/" + comment[0] + "\" method='post' class='abcdef'>" +
                        "<button type='submit' class=\"popup-button-delete-comment\">X</button>" +
                        "</form>" +
                        "</span><br>";
                }
                commentHTML += "<span class='comment-content'>" + comment[1] + "</span>"; // zawartość komentarza
                commentHTML += "<span class='comment-duedate'>" + comment[4] + "</span>" + // data dodania komentarza
                    "<span class='comment-author'>" + comment[3] + "</span>"; // autor komentarza
                commentHTML += "</div>";
                popupCommentsContent.innerHTML += commentHTML;
            });
        })
        .catch(error => {
            console.error("Error retrieving comments:", error);
        });
}
function closePopUp() {
    const popupContainer = document.querySelector('.popup-container');
    popupContainer.classList.remove('active');
}
