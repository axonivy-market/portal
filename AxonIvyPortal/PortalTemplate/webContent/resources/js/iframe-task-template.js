$(document).ready(function() {
  $.removeCookie('serenity_expandeditems', {path: '/'});
});

loadIframe(false);
var recheckFrameTimer;
function loadIframe(recheckIndicator) {
  var iframe = document.getElementById('iFrame');

  if (!recheckIndicator) {
    $(iframe).on('load', function() {
    processIFrameData(iframe);
    clearTimeout(recheckFrameTimer);
    return;
    });
  }
  else {
    iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
    if (iframeDoc.readyState == 'complete') {
    processIFrameData(iframe);
    clearTimeout(recheckFrameTimer);
    return;
    }
  }

  recheckFrameTimer = setTimeout(() => {
    loadIframe(true);
  }, 500);
}

function processIFrameData(iframe) {
    var window = iframe.contentWindow;
    checkUrl(iframe);
    getDataFromIFrame([{
      name : 'currentProcessStep',
      value : window.currentProcessStep
    }, {
      name : 'processSteps',
      value : window.processSteps
    }, {
      name : 'isShowAllSteps',
      value : window.isShowAllSteps
    }, {
      name : 'isHideCaseInfo',
      value : window.isHideCaseInfo
    }, {
      name : 'isHideTaskName',
      value : window.isHideTaskName
    }, {
      name : 'isHideTaskAction',
      value : window.isHideTaskAction
    }, {
      name : 'isWorkingOnATask',
      value : window.isWorkingOnATask
    }, {
      name : 'processChainDirection',
      value : window.processChainDirection
    }, {
      name : 'processChainShape',
      value : window.processChainShape
    }, {
      name : 'announcementInvisible',
      value : window.announcementInvisible
    }, {
      name : 'viewName',
      value : window.viewName
    }]);
}

function checkUrl(iFrame) {
  document.title = iFrame.contentDocument.title;
  var loc = iFrame.contentWindow.location;
  if (loc.pathname.match("/default/redirect.xhtml$")) {
    var redirectUrl = new URLSearchParams(loc.search).get("redirectPage");
    iFrame.src = "about:blank";
    redirectToUrlCommand([{
      name: 'url',
      value: redirectUrl
    }]);
  } else {
    useTaskInIFrame([{
      name: 'url',
      value: loc.pathname
    }]);
  }
}

window.addEventListener("resize", resizeIFrame, false);
function resizeIFrame() {
  Portal.updateLayoutContent();
  var taskHeaderContainerHeight = ($('.js-task-header-container').outerHeight(true)||0);
  var announcementMessageContainerHeight = ($('.js-annoucement-in-frame-template').outerHeight(true)||0);

  var mainScreenHeight = $('.js-layout-content').outerHeight(true);
  var availableHeight = mainScreenHeight - taskHeaderContainerHeight - announcementMessageContainerHeight;

  if (!!availableHeight) {
    $('iframe[id="iFrame"]').height(availableHeight);
  }
}

function updateContentContainerClass() {
  if ($('.js-task-name-vertical-process-chain').length > 0) {
    $('.js-task-frame-container').addClass('vertical-process-chain');
    if ($('.vertical-chain-shape-line').length > 0) {
      $('.js-task-frame-container').addClass('vertical-chain-shape-line');
    }
  }
  if (!window.announcementInvisible) {
    $('#announcement').removeClass('u-invisibility');
  }
  $('.task-template-container').removeClass('u-invisibility');
}