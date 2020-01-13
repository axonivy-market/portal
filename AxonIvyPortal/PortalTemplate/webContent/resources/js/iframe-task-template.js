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
      name : 'processChainDirection',
      value : window.processChainDirection
    }, {
      name : 'processChainShape',
      value : window.processChainShape
    }, {
      name : 'announcementInvisible',
      value : window.announcementInvisible
    }]);
    
    if (!window.announcementInvisible) {
      $('#announcement-container').removeClass('u-hidden');
      $('.task-template-container').height($('.task-template-container').outerHeight() - 10);
    }
    $('.task-template-container').removeClass('u-hidden');
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