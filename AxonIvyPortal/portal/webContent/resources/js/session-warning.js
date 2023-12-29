var sessionCounter = 0,
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
          handleGlobalSearchInIFrame();
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
        keepSession();
        return;
      }

      // If have interaction inside an iframe, send a request to server to keep session
      if ($("#iFrame").length > 0 && isInteractedInIframeTaskTemplate == true) {
        keepSessionInIFrame();
        return;
      }

      // If don't have interaction, show the warning dialog
      warningDialogShow = true;
      PF('timeout-warning-dialog').show();
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

  handleGlobalSearchInIFrame = function() {
    if ( $("[id='quick-global-search-component:global-search-form']").is(':visible')) {
      $("[id='quick-global-search-component:global-search-data']").removeClass('global-large-search-bar').addClass('global-small-search-bar');
      $("[id='quick-global-search-component:global-search-form']").hide();
    }
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

  return {
    init: init,
    resetCounterAndTimeout: resetCounterAndTimeout,
    hideWarningDialog: hideWarningDialog
  };
}();
