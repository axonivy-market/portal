var sessionCounter = 0,
sessionCounterUpdatedOn = new Date(),
isLogOut = false;
var tabInteractions = [];
const tabId = 'tab_' + (Date.now()).toString();

var PortalSessionWarning = function() {
  var warningDialogShow = false,
  isInteractedInIframeTaskTemplate = false,
  isInteractedTaskTemplate = false,
  intervalCheckSessionTimeout,

  init = function(clientSideTimeOut) {

    callKeepSessionCmd();

    timeout = clientSideTimeOut,
    timeOutSeconds = timeout / 1000,
    sessionCounter = timeOutSeconds,
    isLogOut = false,
    intervalCheckSessionTimeout = setInterval(function() {
        timerDecrement();
        if (isLogOut) {
          clearInterval(intervalCheckSessionTimeout);
        }
      }, 1000); // Call every second, stop when user is logged out
    window.onload = resetCounterAndTimeout;
    document.onkeypress = updateInteractedTaskTemplate;
    document.onclick = updateInteractedTaskTemplate;
    document.onmousedown = updateInteractedTaskTemplate;
    document.ontouchstart = updateInteractedTaskTemplate;
    document.onscroll = updateInteractedTaskTemplate;
    // Using IFrame Task template
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

        iframeContent.addEventListener("scroll", function (event) {
          updateInteractionStatusInIFrame();
        });

        iframeContent.addEventListener("click", function (event) {
          updateInteractionStatusInIFrame();
        });
      });
    }

  },

  timerDecrement = function() {
    timeOutSeconds = timeOutSeconds - 1;

    // when timed out, close the warning dialog and make a request to server to show session timeout dialog
    if (timeOutSeconds < 0 && isLogOut == false) {
        handleLogout();
        return;
      }

    // perform check interaction when timeout less than 60 seconds and the warning dialog is hiding
    if (timeOutSeconds < 60 && warningDialogShow == false && isLogOut == false) {

      // If have interaction, send a request to server to keep session
      if (isInteractedTaskTemplate == true) {
        callKeepSessionCmd();
        warningDialogShow = false;
        return;
      }

      // If have interaction inside an iframe, send a request to server to keep session
      if ($("#iFrame").length > 0 && isInteractedInIframeTaskTemplate == true) {
        callKeepSessionCmd();
        warningDialogShow = false;
        return;
      }

      // If there is no interaction, show timeout dialog
      showWarningDialog();
    }
  },

  handleLogout = function() {
    hideWarningDialog();
    logoutAndShowDialog();
    isLogOut = true;
    stopAvailableChartPolling();
  }

  resetCounterAndTimeout = function() {
    hideWarningDialog();
    isInteractedTaskTemplate = false;
    isInteractedInIframeTaskTemplate = false;
    timeOutSeconds = timeout / 1000;
  },

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
  
  showWarningDialog = function() {
    warningDialogShow = true;
    PF('timeout-warning-dialog').show();
  }
  
  stopAvailableChartPolling = function () {
    let polls = $("div[id*=':chart_model_dashboard_poll-']");
    stopChartPolling(polls);
    polls = $("div[id$=':task-polling']");
    stopChartPolling(polls);
    polls = $("div[id$=':chart_model_poll']");
    stopChartPolling(polls);
  }
  
  stopChartPolling = function (polls) {
    for (const poll of polls) {
      let widgetVar = getWidgetVarById(poll.id);
      if(widgetVar) {
        widgetVar.stop();
      }
    }
  }

  callKeepSessionCmd = function() {
    keepSession([{name:'tabId', value:tabId}]);
  }

  callKeepSessionWithoutCheckTimeoutCmd = function() {
    keepSessionWithoutCheckTimeout([{name:'tabId', value:tabId}]);
  }

  getSessionInfoCmd = function(sessionInfo) {
    // If server does not send session info
    // show log out dialog
    if (sessionInfo == '') {
      handleLogout();
    }

    sessionInfo = JSON.parse(sessionInfo);
    tabId = sessionInfo.tabId;
    this.init(sessionInfo.millisecondsToTimeout);
  }

  unloadTabSessionCmd = function() {
    unloadTabSession([{name:'tabId', value:tabId}]);
  }

  return {
    init: init,
    resetCounterAndTimeout: resetCounterAndTimeout,
    hideWarningDialog: hideWarningDialog,
    callKeepSessionCmd : callKeepSessionCmd,
    callKeepSessionWithoutCheckTimeoutCmd : callKeepSessionWithoutCheckTimeoutCmd,
    getSessionInfoCmd : getSessionInfoCmd,
    unloadTabSessionCmd : unloadTabSessionCmd
  };
}();


$(document).ready(function() {
  // add event listener to call remote command to delete
  // session info of this tab on the server when the webpage is unload.
  $(window).bind("beforeunload", function() {
    PortalSessionWarning.unloadTabSessionCmd();
  });
  $(window).bind("visibilitychange", function() {
    if (!document.hidden) {
      PortalSessionWarning.callKeepSessionCmd();
    }
  });
});