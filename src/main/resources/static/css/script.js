function openPopUp(element) {
    const taskid = element.getAttribute("data-taskid");
    const taskname = element.getAttribute("data-taskname");
    const taskstatus = element.getAttribute("data-taskstatus");
    const popupContainer = document.querySelector('.popup-container');

    popupContainer.classList.add('active');

    const popupContent = document.querySelector('.popup-content');

    popupContent.innerHTML = "<h2>" + taskname + "</h2>" +
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
}
function closePopUp() {
    const popupContainer = document.querySelector('.popup-container');
    popupContainer.classList.remove('active');
}
