function openNotificationPanel() {
    $('#notifications-panel').addClass('active')
    let right = document.getElementById("notifications-panel").style.right;
    if (right !== '-420px') {
        document.getElementById("notifications-panel").style.right = "0";
    }
}

function closeNotificationPanel() {
    document.getElementById("notifications-panel").style.right = "-420px";
}

function markAsRead(notiId) {
    $('i#' + notiId).each(function(index) {
      if ($(this) !== undefined) {
      $(this).removeClass('fa-circle');
      $(this).parents('.notifications-container-top').removeClass('font-bold');
    }
  });
}

$(document).ready(function () {
    closeNotificationPanel();
    let notificationPanel = document.getElementById("notifications-panel");
    let bellIcon = document.getElementById('open-notifications-panel');
    let isClickOnBell = false;
    document.addEventListener('click', event => {
        const isClickInside = notificationPanel.contains(event.target);
        if (bellIcon !== null && bellIcon !== undefined){
            isClickOnBell = bellIcon.contains(event.target);
        } else {
            isClickOnBell = false;
        }
        if (!isClickInside && notificationPanel.style.right === '0px') {
            notificationPanel.style.right = "-420px";
        } else if (isClickOnBell) {
            notificationPanel.style.right = "0";
        }
    })
});
var notificationId;
function setNotificationId(id) {
    notificationId = id;
}
var notiTaskId;
function setNotiTaskId(id) {
    notiTaskId = id;
}
var redirectType;
function setRedirectType(type) {
    redirectType = type;
}
function checkWarningLogForTaskDetail(notificationId, taskId) {
    setNotificationId(notificationId);
    setNotiTaskId(taskId);
    setRedirectType('TASK_DETAIL');
    showNotificationConfirmationDialog();
}
function checkWarningLogForTaskStart(notificationId, taskId) {
    setNotificationId(notificationId);
    setNotiTaskId(taskId);
    setRedirectType('TASK_START');
    showNotificationConfirmationDialog();
}
function showNotificationConfirmationDialog() {
    if (PrimeFaces.widgets['notification-task-losing-confirmation-dialog']) {
        PF('notification-task-losing-confirmation-dialog').show();
        return false;
    }
    return true;
}
function fireEventClick() {
    // do nothing
}
