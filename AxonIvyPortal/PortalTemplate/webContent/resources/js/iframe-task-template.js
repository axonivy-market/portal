var invalidIFrameSrcPath = false;
loadIframe();
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
    const iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
    if (iframeDoc.readyState == 'complete') {
      processIFrameData(iframe);
      clearTimeout(recheckFrameTimer);
      return;
    }
  }

  recheckFrameTimer = setTimeout(function(){ loadIframe(true); }, 500);
}

function processIFrameData(iframe) {
  var window = iframe.contentWindow;
  checkUrl(iframe);
  if (invalidIFrameSrcPath) {
    invalidIFrameSrcPath = false;
    return;
  }

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
    name : 'caseId',
    value : window.caseId
  }]);
}

function checkUrl(iFrame) {
  const iframeDoc = iFrame.contentDocument;
  if (iframeDoc === undefined || iframeDoc === null) {
    console.log("The iframe content docment is undefined");
    invalidIFrameSrcPath = true;
    return;
  }
  document.title = iframeDoc.title;
  var path = getPortalIframePath(iFrame);
  if (path === '' || invalidIFrameSrcPath) {
    return;
  }
  invalidIFrameSrcPath = false;
  if (path.match("/default/end.xhtml$") || path.match("/default/end.jsp$")) {
    var href = iFrame.contentWindow.location.href;
    var taskId = href.substring(href.lastIndexOf("=") + 1);
    iFrame.src = "about:blank";
    redirectToEndPageCommand([{
      name : 'taskId',
      value : taskId
    }]);
  } else {
    useTaskInIFrame([{
      name: 'url',
      value: path
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
  if ($('.task-name-vertical-process-chain').length > 0) {
    $('.task-frame-container').addClass('vertical-process-chain');
    if ($('.vertical-chain-shape-line').length > 0) {
      $('.task-frame-container').addClass('vertical-chain-shape-line');
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