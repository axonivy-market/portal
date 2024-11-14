var sessionCounterUpdatedOn = new Date(),
isEndSession = false;

var PortalSessionWarning = function() {
  var warningDialogShow = false,
  isInteractedInIframeTaskTemplate = false,
  isInteractedTaskTemplate = false,
  intervalCheckSessionTimeout;

  init = function(serverSideTimeout) {
    timeout = serverSideTimeout;
    window.onload = resetCounterAndTimeout;
    document.onkeydown = updateInteractedTaskTemplate;
    document.onclick = updateInteractedTaskTemplate;
    document.onmousedown = updateInteractedTaskTemplate;
    document.ontouchstart = updateInteractedTaskTemplate;

    $(window).bind("visibilitychange", function() {
      if (!document.hidden) {
        backToTab();
      }
    });

    if ($("#iFrame").length > 0) {

      $("#iFrame").on("load", function () {
        var iframeContent = $("#iFrame").get(0).contentDocument || $("#iFrame").get(0).contentWindow.document;

        iframeContent.addEventListener("keypress", function (event) {
          updateInteractionStatusInIFrame();
        });

        iframeContent.addEventListener("mousedown", function (event) {
          updateInteractionStatusInIFrame();
        });

        iframeContent.addEventListener("touchstart", function (event) {
          updateInteractionStatusInIFrame();
        });

        iframeContent.addEventListener("click", function (event) {
          updateInteractionStatusInIFrame();
        });

      });
    }
  },

  timerDecrement = function() {
    if (getRemainingTimeInSeccond() < 0 && isEndSession == false) {
      isEndSession = true;
      return;
    }

    // perform check interaction when timeout less than 60 seconds and the warning dialog is hiding
    if (getRemainingTimeInSeccond() < 60 && warningDialogShow == false) {
      // If have interaction, send a request to server to keep session
      if (isInteractedTaskTemplate == true || ($("#iFrame").length > 0 && isInteractedInIframeTaskTemplate == true)) {
        keepPortalAlive();
        return;
      }

      // If don't have interaction, show the warning dialog
      warningDialogShow = true;
      PF('timeout-warning-dialog').show();
      tryClearChartInterval();
    }
  },

  resetCounterAndTimeout = function() {
    clearInterval(intervalCheckSessionTimeout);
    hideWarningDialog();
    isInteractedTaskTemplate = false;
    isInteractedInIframeTaskTemplate = false;
    isEndSession = false;
    resetDateTimeout();
    intervalCheckSessionTimeout = setInterval(function() {
      timerDecrement();
      if (isEndSession) {
        clearInterval(intervalCheckSessionTimeout);
      }
    }, 1000); // Call every second, stop when user is logged out
  },

  keepPortalAlive = function() {
    keepSession().then(responseData => {
      if (!responseData.document){
        showTimeoutDialog();
        return;
      }
      tryInitRefreshChart();
    }).catch(function (error) {
      showTimeoutDialog();
    });
  },

  resetDateTimeout = function () {
    sessionCounterUpdatedOn = new Date();
    sessionCounterUpdatedOn.setSeconds(new Date().getSeconds() + (timeout / 1000) + 60);
  },

  updateInteractedTaskTemplate = function() {
    isInteractedTaskTemplate = true;
  },

  updateInteractionStatusInIFrame = function() {
    isInteractedInIframeTaskTemplate = true;
  },

  hideWarningDialog = function() {
    warningDialogShow = false;
    PF('timeout-warning-dialog').hide();
  },

  backToTab = function() {
    keepPortalAlive();
  },

  showTimeoutDialog = function () {
    PF('timeout-warning-dialog').hide();
    PF('portal-view-expired-exception-dialog').show();
    isEndSession = true;
    tryClearChartInterval();
  },
  
  getRemainingTimeInSeccond  = function () {
    return (sessionCounterUpdatedOn.getTime() - new Date().getTime()) / 1000;
  },

  tryClearChartInterval = function() {
    try {
      clearChartInterval();
    } catch (ignore) {}
  },

  tryInitRefreshChart = function() {
    try {
      initRefresh();
    } catch (ignore) {}
  }

  return {
    init: init,
    resetCounterAndTimeout: resetCounterAndTimeout,
    hideWarningDialog: hideWarningDialog,
    keepPortalAlive: keepPortalAlive,
    showTimeoutDialog: showTimeoutDialog
  };
}();