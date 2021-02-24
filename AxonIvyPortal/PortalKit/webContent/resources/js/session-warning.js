var sessionCounter = 0;
var sessionCounterUpdatedOn = new Date();
var isLogOut = false;

var PortalSessionWarning = function() {
  var warningDialogShow = false,
  isInteractedInIframeTaskTemplate = false,
  intervalCheckSessionTimeout,

  init = function(clientSideTimeOut) {
    timeout = clientSideTimeOut,
    timeOutSeconds = timeout / 1000,
    sessionCounter = timeOutSeconds,
    isLogOut = false,
    intervalCheckSessionTimeout = setInterval(timerDecrement, 1000); // Call Every Second
    window.onload = resetCounterAndTimeout;
    document.onkeypress = resetCounterAndTimeout;
    document.onclick = resetCounterAndTimeout;
    document.onmousedown = resetCounterAndTimeout;
    document.ontouchstart = resetCounterAndTimeout;
    document.onscroll = resetCounterAndTimeout;

    // Using IFrame Task template
    if ($("#iFrame").length > 0) {
      $("#iFrame").get(0).contentDocument.onload = updateInteractionStatusInIFrame;
      $("#iFrame").get(0).contentDocument.onkeypress = updateInteractionStatusInIFrame;
      $("#iFrame").get(0).contentDocument.onclick = updateInteractionStatusInIFrame;
      $("#iFrame").get(0).contentDocument.onmousedown = updateInteractionStatusInIFrame;
      $("#iFrame").get(0).contentDocument.ontouchstart = updateInteractionStatusInIFrame;
      $("#iFrame").get(0).contentDocument.onscroll = updateInteractionStatusInIFrame;
    }
  },

  timerDecrement = function() {
    var lastUpdated = sessionCounterUpdatedOn, now = new Date(), shouldCheck = false;
    if (lastUpdated == 'null') {
      sessionCounterUpdatedOn = now;
    } else if (now.getTime() - new Date(lastUpdated).getTime() >= 1000) {
      sessionCounterUpdatedOn = now;
      shouldCheck = true;
    }

    if (shouldCheck) {
      if (timeOutSeconds > 0) {
        if (parseInt(sessionCounter, 10) > 0) {
          timeOutSeconds = parseInt(sessionCounter, 10);
        }
        timeOutSeconds--;
        sessionCounter = timeOutSeconds;

      } else {
        if (isLogOut == false) {
          isLogOut = true;
          logoutAndShowDialog();
        } else {
          PF('timeout-warning-dialog').hide();
          PF('timeout-dialog').show();
          clearInterval(intervalCheckSessionTimeout);
        }
      }
    } else {
      timeOutSeconds = parseInt(sessionCounter, 10);
    }

    if (timeOutSeconds < 60) {
      if ($("#iFrame").length > 0 && isInteractedInIframeTaskTemplate == true) {
        warningDialogShow = false;
        isInteractedInIframeTaskTemplate = false;
        keepSessionInIFrame();
      } else if (warningDialogShow == false) {
        warningDialogShow = true;
        PF('timeout-warning-dialog').show();
      }
    } else {
      hideWarningDialog();
    }
  },

  resetCounterAndTimeout = function() {
    if (warningDialogShow == false) {
      sessionCounterUpdatedOn = null;
      timeOutSeconds = timeout / 1000;
      sessionCounter = timeOutSeconds;
    }
  },

  updateInteractionStatusInIFrame = function() {
    isInteractedInIframeTaskTemplate = true;
  },

  hideWarningDialog = function() {
    warningDialogShow = false;
    PF('timeout-warning-dialog').hide();
  }
  
  return {
    init: init,
    resetCounterAndTimeout: resetCounterAndTimeout,
    hideWarningDialog: hideWarningDialog
  };
}();