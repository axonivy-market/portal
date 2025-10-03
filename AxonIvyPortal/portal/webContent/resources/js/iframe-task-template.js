/*
When iframe loads in dark mode, iframe shows white page. Portal handles by register iframe load, unload events.
It makes iframe invisible when on unload, and visible when on load.

If iframe reaches /default/redirect.xhtml$, Portal stops processing inside iframe.
Portal navigates in main page, also not execute unnecessary JS.

On Chrome, iframe is loaded with about:blank, then onload is registered, then loaded with the URL.
On Firefox (like GUI test), iframe is loaded with the URL (no about:blank) before onload is registered.
Therefore we need to check if iframe document is ready and not about:blank, execute logic of onload to not miss any onload event.

Consider to test: multi-browsers, skip task list, back to home, session timeout.
Check no white page in dark mode, try removing visibility = "hidden" to check handling inside iframe, log to see any unexpected behaviors.
*/
var invalidIFrameSrcPath = false;
var isMainPageNavigating = false;
function loadIframe() {
  var iframe = getPortalIframe();

  const onIframeLoad = function () {
    var appName = $('#application-name-for-title').get(0).value;
    checkUrl(iframe, appName);
    if (isMainPageNavigating) {
      return;
    }
    processIFrameData(iframe);
    iframe.style.visibility = 'visible';

    const unloadHandler = () => {
      iframe.style.visibility = "hidden";
    };

    // Remove the unloadHandler in case it was already attached, could happen with skip task list.
    iframe.contentWindow.removeEventListener("unload", unloadHandler);
    iframe.contentWindow.addEventListener("unload", unloadHandler);
    return;
  };

  $(iframe).on('load', onIframeLoad);
  // Handle for Firefox vs Chrome, see the comment on the top
  if (iframe.contentDocument && iframe.contentDocument.readyState === 'complete' &&
    iframe.contentWindow.location.href !== 'about:blank') {
    onIframeLoad(iframe);
  }

  resizeIFrame();
}

function getPortalIframe() {
  return document.getElementById('iFrame');
}

function processIFrameData(iframe) {
  var window = iframe.contentWindow;
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
    name: 'taskIcon',
    value: window.taskIcon
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
  if (appName) {
    const title = iframeDoc.title ? iframeDoc.title.concat(" - ", appName) : appName;
    document.title = title;
  } else {
    document.title = iframeDoc.title;
  }

  var path = getPortalIframePath(iFrame);
  if (path === '' || invalidIFrameSrcPath) {
    return;
  }
  invalidIFrameSrcPath = false;

  if (path.endsWith('/go/end') || path.endsWith('/go/home') || path.endsWith('/go/tasks') || path.endsWith('/go/cases')) {
    let redirectUrl = getMetaRefreshUrl(iFrame.contentDocument);
    if (redirectUrl) {
      iFrame.contentWindow.stop();
      redirectToUrlCommand([{ name: 'url', value: redirectUrl }]);
      isMainPageNavigating = true;
      return;
    }
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

function getMetaRefreshUrl(doc) {
  if (!doc) {
    return undefined;
  }
  const metas = Array.from(doc.getElementsByTagName('meta'));
  const refreshMeta = metas.find(m => m.httpEquiv.toLowerCase() === 'refresh');
  if (!refreshMeta) {
    return undefined;
  }

  const content = refreshMeta.getAttribute('content') || '';
  const urlPart = content.split(';').find(part => part.trim().toLowerCase().startsWith('url='));

  if (!urlPart) {
    return undefined;
  }

  let url = urlPart.substring(urlPart.indexOf('=') + 1).trim();
  // trim quotes
  url = url.replace(/^['"]|['"]$/g, '');
  return url;
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