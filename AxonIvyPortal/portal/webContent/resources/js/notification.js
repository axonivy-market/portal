var isFirstLoad = false;
function openNav() {
    $('#notification-panel').addClass('active')
    let right = document.getElementById("notification-panel").style.right;
    if (right !== '-400px') {
        document.getElementById("notification-panel").style.right = "-400px";
    } else {
        document.getElementById("notification-panel").style.right = "0";
    }
}

function closeNav() {
    document.getElementById("notification-panel").style.right = "-400px";
}

$(document).ready(function() {
    //document.getElementById("notification-panel").style.width = "400px";
    document.getElementById("notification-panel").style.right = "-400px";

    setInterval(() => updateNotificationBadge(), 10 * 1000);
});
const pathName = window.location.pathname;
const baseURL = pathName.substring(0, pathName.indexOf('/faces/'));


const updateNotificationBadge = () => {
    countUnreadNotifications().then(response => {
        $('#unreadNotifications').text(response);
    });
}
const countUnreadNotifications = async () => {
    const jsonResponse = await fetch(baseURL + "/api/notification/unread",
        {
            method: "GET",
            mode: 'cors',
            credentials: "include",
            headers: {"X-Requested-By": "ivy", "Content-Type": "application/json", "Accept": "application/json"}
        });

    if (jsonResponse.status === 200) {
        let countUnRead = await jsonResponse.json();
        return countUnRead > 99 ? '99+': countUnRead;
    }
    return 0;
}

updateNotificationBadge();
