loadIframe();
function loadIframe() {
  var iframe = document.getElementById('iFrame');
  var window = iframe.contentWindow;
  $(iframe).on('load', function() {
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
    }]);
  });
}

function checkUrl(iFrame) {
  document.title = iFrame.contentDocument.title;
  var path = iFrame.contentWindow.location.pathname;
  if (path.match("/default/end.xhtml$") || path.match("/default/end.jsp$")) {
    var href = iFrame.contentWindow.location.href;
    var taskId = href.substring(href.lastIndexOf("=") + 1);
    iFrame.src = "about:blank";
    redirectToEndPageCommand([{
      name : 'taskId',
      value : taskId
    }]);
  }
}

window.addEventListener("resize", resizeIFrame, false);
function resizeIFrame() {
  Portal.updateLayoutContent();
  var taskHeaderContainerHeight = ($('.js-task-header-container').outerHeight(true)||0);
  var announcementMessageContainerHeight = ($('.js-annoucement-in-frame-template').outerHeight(true)||0);
  var error = 25;

  var mainScreenHeight = $('.js-layout-content').outerHeight(true);
  var availableHeight = mainScreenHeight - taskHeaderContainerHeight - announcementMessageContainerHeight - error;

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