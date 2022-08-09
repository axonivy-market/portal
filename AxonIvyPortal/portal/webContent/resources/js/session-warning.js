var sessionCounter = 0,
sessionCounterUpdatedOn = new Date(),
isLogOut = false;

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
    let periodOfTime = 0;
    if (lastUpdated == null) {
      sessionCounterUpdatedOn = now;
    } else {
      periodOfTime = now.getTime() - new Date(lastUpdated).getTime();
      if (periodOfTime >= 1000) {
        sessionCounterUpdatedOn = now;
        shouldCheck = true;
      }
    }

    if (shouldCheck) {
      if (timeOutSeconds > 0) {
        if (sessionCounter > 0) {
          timeOutSeconds = sessionCounter;
        }
        timeOutSeconds = timeOutSeconds - (periodOfTime / 1000);
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
      timeOutSeconds = sessionCounter;
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