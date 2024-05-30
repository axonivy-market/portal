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
        hideWarningDialog();
        logoutAndShowDialog();
        isLogOut = true;
        stopAvailableChartPolling();
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
      warningDialogShow = true;
      showTimeoutDialog([
        {name:'tabs', value:null},
        {name:'isShowWarningForCurrentTab', value:true}
      ]);
    }
  },

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
    keepSession([{name:'tabId', value:tabId}, {name : 'title', value:document.title}]);
  }

  callKeepSessionInIFrameCmd = function() {
    keepSessionInIFrame([{name:'tabId', value:tabId}, {name : 'title', value:document.title}]);
  }

  callKeepSessionWithoutCheckTimeoutCmd = function() {
    keepSessionWithoutCheckTimeout([{name:'tabId', value:tabId}, {name : 'title', value:document.title}]);
  }

  getTabInteractionsAsJsonCmd = function(sessionInfos) {
    if (sessionInfos == '') {
      logoutAndShowDialog();
    }

    var tabsTimedOut = [];
    console.log(sessionInfos);
    sessionInfos = JSON.parse(sessionInfos);

    var isShowWarningForCurrentTab = false;
    var isCurrentTabExpired = true;

    // Check sessionInfos for interactions in all open tabs
    for (var i = 0; i < sessionInfos.length; i++) {
      var info = sessionInfos[i];
      var timeOutSeconds = info.millisecondsToTimeout / 1000;

      // If an session info will be expire within one minute
      if (timeOutSeconds <= 60) {
        warningDialogShow = true;
        if (info.tabId == tabId) {
          isShowWarningForCurrentTab = true;
        }
        tabsTimedOut.push(info.title);
      } else if (info.tabId == tabId) {
        // If session of this tab still in the list of session info, it mean the session of it don't expired yet.
        isCurrentTabExpired = false;
      }
    }

    // If the current tab is expired, show the view expired dialog
    if (isCurrentTabExpired == true) {
      warningDialogShow = true;
      logoutAndShowDialog();
      return;
    }

    // If only the current tab has session expire in 1 minute, clear the time out list
    if (tabsTimedOut.length == 1 && isShowWarningForCurrentTab) {
      tabsTimedOut = [];
    }

    if (warningDialogShow) {
      showTimeoutDialog([
        {name:'tabs', value:JSON.stringify(tabsTimedOut)},
        {name:'isShowWarningForCurrentTab', isShowWarningForCurrentTab}
      ]);
    }
  }

  unloadTabSessionCmd = function() {
    unloadTabSession([{name:'tabId', value:tabId}]);
  }

  return {
    init: init,
    resetCounterAndTimeout: resetCounterAndTimeout,
    hideWarningDialog: hideWarningDialog,
    callKeepSessionCmd : callKeepSessionCmd,
    callKeepSessionInIFrameCmd : callKeepSessionInIFrameCmd,
    callKeepSessionWithoutCheckTimeoutCmd : callKeepSessionWithoutCheckTimeoutCmd,
    getTabInteractionsAsJsonCmd : getTabInteractionsAsJsonCmd,
    unloadTabSessionCmd : unloadTabSessionCmd
  };
}();

// Wire up the events as soon as the DOM tree is ready
$(document).ready(function() {
  $(window).bind("beforeunload", function() {
    PortalSessionWarning.unloadTabSessionCmd();
  });
});