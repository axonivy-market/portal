var invalidIFrameSrcPath = false;

var recheckFrameTimer;
function loadIframe(recheckIndicator) {
  var iframe = getPortalIframe();

  if (!recheckIndicator) {
    $(iframe).on('load', function () {
      if (!document.documentURI.endsWith('?taskUrl=blank')) {
        iframe.style.visibility = 'hidden';
      }
      processIFrameData(iframe);
      clearTimeout(recheckFrameTimer);
      setTimeout(function() {
        if ($(iframe).attr('src') != 'about:blank') {
          iframe.style.visibility = 'visible';
          }
        }, 500);
      return;
    });
  }
  else {
    const iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
    iframeDoc.onbeforeunload = function() {
      $(iframe).addClass('u-hidden');
    }
    if (iframeDoc.readyState == 'complete') {
      processIFrameData(iframe);
      clearTimeout(recheckFrameTimer);
      iframe.style.visibility = 'visible';
      return;
    }
  }

  resizeIFrame();
  recheckFrameTimer = setTimeout(function () { loadIframe(true); }, 500);
}

function getPortalIframe() {
  return document.getElementById('iFrame');
}

function processIFrameData(iframe) {
  var window = iframe.contentWindow;
  var appName = $('#application-name-for-title').get(0).value;
  checkUrl(iframe, appName);
  if (invalidIFrameSrcPath) {
    invalidIFrameSrcPath = false;
    return;
  }
  streamliningPortalFrameStyle(window);
  getDataFromIFrame([{
    name: 'currentProcessStep',
    value: window.currentProcessStep
  }, {
    name: 'processSteps',
    value: convertProcessSteps(window.processSteps)
  }, {
    name: 'isShowAllSteps',
    value: window.isShowAllSteps
  }, {
    name: 'isHideCaseInfo',
    value: window.isHideCaseInfo
  }, {
    name: 'isHideTaskName',
    value: window.isHideTaskName
  }, {
    name: 'isHideTaskAction',
    value: window.isHideTaskAction
  }, {
    name: 'isWorkingOnATask',
    value: window.isWorkingOnATask
  }, {
    name: 'processChainDirection',
    value: window.processChainDirection
  }, {
    name: 'processChainShape',
    value: window.processChainShape
  }, {
    name: 'announcementInvisible',
    value: window.announcementInvisible
  }, {
    name: 'viewName',
    value: window.viewName
  }, {
    name: 'caseId',
    value: window.caseId
  }, {
    name: 'taskName',
    value: window.taskName
  }, {
    name : 'taskIcon',
    value : window.taskIcon
    }]);
}

function streamliningPortalFrameStyle(window) {
  var iframeElement = getPortalIframe();
  if (window.isCardFrame) {
    $(iframeElement).addClass("card");
  } else {
    $(iframeElement).removeClass("card");
    updateTaskHeaderContainerStyle(true);
  }
}

function checkUrl(iFrame, appName) {
  const iframeDoc = iFrame.contentDocument;
  if (iframeDoc === undefined || iframeDoc === null) {
    console.log("The iframe content docment is undefined");
    invalidIFrameSrcPath = true;
    return;
  }

  // Update title
  const title = iframeDoc.title ? iframeDoc.title.concat(" - ", appName) : appName;
  document.title = title;

  var path = getPortalIframePath(iFrame);
  if (path === '' || invalidIFrameSrcPath) {
    return;
  }
  invalidIFrameSrcPath = false;

  if (path.match("/default/redirect.xhtml$")) {
    var redirectUrl = new URLSearchParams(iFrame.contentWindow.location.search).get("redirectPage");
    iFrame.src = "about:blank";
    redirectToUrlCommand([{
      name: 'url',
      value: redirectUrl
    }]);
  } else {
    useTaskInIFrame([{
      name: 'url',
      value: path
    }]);
    updateHistory(iFrame.contentWindow.location.href);
  }
}

window.addEventListener("resize", resizeIFrame, false);
function resizeIFrame() {
  Portal.updateLayoutContent();
  Portal.updateBreadcrumb();
  const taskHeaderContainerHeight = (getTaskHeaderContainer().outerHeight(true) || 0);
  const announcementMessageContainerHeight = ($('.js-annoucement-in-frame-template').outerHeight(true) || 0);
  const $frameCard = $("iframe[id='iFrame'].task-frame.card");
  const frameCardPadding = parseInt($frameCard.css("padding-top") || 0) + parseInt($frameCard.css("padding-bottom") || 0);
  const mainScreenHeight = PortalLayout.getAvailableHeight() - PortalLayout.getYPaddingLayoutContent();
  let availableHeight = mainScreenHeight - taskHeaderContainerHeight - announcementMessageContainerHeight - frameCardPadding;
  if (!!availableHeight) {
    $(getPortalIframe()).height(availableHeight);
  }
  updateTaskHeaderContainerStyle($frameCard.length == 0);
}

function updateTaskHeaderContainerStyle(noFrameCard) {
  let frameFullWidthDocument = undefined;
  try {
    // Find the body of frame-10-full-width template
    frameFullWidthDocument = getPortalIframe().contentWindow.document;
  } catch (error) {
    console.log("Cannot access to iframe location data: " + error);
  }
  if ($(frameFullWidthDocument).length == 0) {
    getTaskHeaderContainer().removeAttr('style');
    return;
  }
  const bodyHD = frameFullWidthDocument.querySelectorAll("body.body-hd");
  const frameContent = frameFullWidthDocument.querySelectorAll("#content.container.frame.full-width");
  if (noFrameCard && $(bodyHD).length > 0 && $(frameContent).length > 0) {
    let leftSpacer = parseInt($(bodyHD).css("margin-left") || 0) + parseInt($(bodyHD).css("padding-left") || 0);
    leftSpacer = leftSpacer + parseInt($(frameContent).css("margin-left") || 0) + parseInt($(frameContent).css("padding-left") || 0);
    let rightSpacer = parseInt($(bodyHD).css("margin-right") || 0) + parseInt($(bodyHD).css("padding-right") || 0);
    rightSpacer = rightSpacer + parseInt($(frameContent).css("margin-right") || 0) + parseInt($(frameContent).css("padding-right") || 0);
    getTaskHeaderContainer().css({ 'margin-left': leftSpacer + 'px', 'margin-right': rightSpacer + 'px' });
  }
}

function getTaskHeaderContainer() {
  return $(".js-task-header-container");
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

function getPortalIframePath(iFrame) {
  invalidIFrameSrcPath = false;
  const iframeLocation = iFrame.contentWindow.location;
  let path = '';
  try {
    path = iframeLocation.pathname;
  } catch (error) {
    invalidIFrameSrcPath = true;
    console.log("Cannot access to iframe location data: " + error);
  }
  return path;
}

let updateHistory = (newHref) => {
  let newHrefUrl = new URL(newHref);
  let historyUrl = new URL(window.location);
  historyUrl.searchParams.set('taskUrl', newHrefUrl.pathname + newHrefUrl.search);
  history.replaceState({}, "", historyUrl);
}

const convertProcessSteps = processSteps => {
  // If process steps are not defined, don't do anything
  if (processSteps === undefined) {
    return '';
  }

  // If the process steps is a valid array, convert to JSON
  if (Array.isArray(processSteps)) {
    return JSON.stringify(processSteps);
  } else { // Compatible with Portal 8, process steps could be String
    let stepsCompatibleWithPortal8 = processSteps.split(',');
    return JSON.stringify(stepsCompatibleWithPortal8);
  }
}