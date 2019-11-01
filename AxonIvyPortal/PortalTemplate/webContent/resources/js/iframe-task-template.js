loadIframe();
function loadIframe() {
  var iframe = document.getElementById('iFrame');
  var window = iframe.contentWindow;
  $(iframe).on('load', function() {
    buildHeader([{
      name : 'taskId',
      value : window.taskId
    }, {
      name : 'currentProcessStep',
      value : window.currentProcessStep
    }, {
      name : 'processSteps',
      value : window.processSteps
    }, {
      name : 'processChainDirection',
      value : window.processChainDirection
    }, {
      name : 'processChainShape',
      value : window.processChainShape
    }]);
  });
}

function checkUrl(iFrame) {
  var path = iFrame.contentWindow.location.pathname;
  if (path.match("/default/end.xhtml$") || path.match("/default/end.jsp$")) {
    var href = iFrame.contentWindow.location.href;
    var taskId = href.substring(href.lastIndexOf("=") + 1);
    redirectToEndPageCommand([{
      name : 'taskId',
      value : taskId
    }]);
  }
}

window.addEventListener("resize", resizeIFrame, false);
resizeIFrame();
function resizeIFrame() {
  var mainPanelHeight = $('.task-frame-container').get(0).offsetHeight;
  $('iframe[id="iFrame"]').height(mainPanelHeight);
}

function updateContentContainerClass() {
  if ($('.task-name-vertical-process-chain').length > 0) {
    $('.task-frame-container').addClass('vertical-process-chain');
    if ($('.vertical-chain-shape-line').length > 0) {
      $('.task-frame-container').addClass('vertical-chain-shape-line');
    }
  }
}