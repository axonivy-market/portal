function openNotificationPanel() {
    $('#notification-panel').addClass('active')
    let right = document.getElementById("notification-panel").style.right;
    if (right !== '-420px') {
        document.getElementById("notification-panel").style.right = "0";
    }
}

function closeNotificationPanel() {
    document.getElementById("notification-panel").style.right = "-420px";
}

$(document).ready(function () {
    closeNotificationPanel();
    let notificationPanel = document.getElementById("notification-panel");
    let bellIcon = document.getElementById('open-notifications-panel');
    document.addEventListener('click', event => {
        const isClickInside = notificationPanel.contains(event.target);
        const isClickOnBell = bellIcon.contains(event.target);

        if (!isClickInside && notificationPanel.style.right === '0px') {
            notificationPanel.style.right = "-420px";
        } else if (isClickOnBell) {
            notificationPanel.style.right = "0";
        }
    })
});

