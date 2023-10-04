sessionCounterUpdatedOn = new Date(),
isLogOut = false;

var PortalSessionWarning = function() {
  var warningDialogShow = false,
  isInteractedInIframeTaskTemplate = false,
  isInteractedTaskTemplate = false,
  intervalCheckSessionTimeout,

  init = function(clientSideTimeOut) {
    timeout = clientSideTimeOut,
    timeOutSeconds = timeout / 1000,
    isLogOut = false,
    intervalCheckSessionTimeout = setInterval(timerDecrement, 1000); // Call Every Second
    window.onload = resetCounterAndTimeout;
    document.onkeypress = updateInteractedTaskTemplate;
    document.onclick = updateInteractedTaskTemplate;
    document.onmousedown = updateInteractedTaskTemplate;
    document.ontouchstart = updateInteractedTaskTemplate;
    document.onscroll = updateInteractedTaskTemplate;

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
    timeOutSeconds = timeOutSeconds - 1;

    // when timed out, close the warning dialog and make a request to server to show session timeout dialog
    if (timeOutSeconds < 0) {
        hideWarningDialog();
        keepSession();
        return;
      }

    // perform check interaction when timeout less than 60 seconds and the warning dialog is hiding
    if (timeOutSeconds < 60 && warningDialogShow == false) {

      // If have interaction, send a request to server to keep session
      if (isInteractedTaskTemplate == true) {
        keepSession();
        return;
      }

      // If have interaction inside an iframe, send a request to server to keep session
      if ($("#iFrame").length > 0 && isInteractedInIframeTaskTemplate == true) {
        warningDialogShow = false;
        isInteractedInIframeTaskTemplate = false;
        keepSessionInIFrame();
        return;
      }

      // If don't have interaction, show the warning dialog
      warningDialogShow = true;
      PF('timeout-warning-dialog').show();
    }
  }

  resetCounterAndTimeout = function() {
    hideWarningDialog();
    isInteractedTaskTemplate = false;
    isInteractedInIframeTaskTemplate = false;
    timeOutSeconds = timeout / 1000;
  },

  resetInteractedTaskTemplate = function() {
    isInteractedTaskTemplate = false;
  }

  updateInteractedTaskTemplate = function() {
    isInteractedTaskTemplate = true;
  }

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
    resetInteractedTaskTemplate : resetInteractedTaskTemplate,
    updateInteractedTaskTemplate : updateInteractedTaskTemplate,
    hideWarningDialog: hideWarningDialog
  };
}();