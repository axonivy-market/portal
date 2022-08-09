function GuidePanel(targetCssSelector, originMy, at, targetPosition, arrowPosition) {
  var $target = $(targetCssSelector);
  var originTargetPosition = $target.css('position');
  var originTargetBackgroundColor = $target.css('background-color')
  var originTargetZIndex = $target.css('z-index');
  
  this.show = function() {
    var $guidePanel = $('.guide-panel:visible');
    var my = updateMy($guidePanel);
    positionArrow();
    var zIndex = $guidePanel.css('z-index');
    $guidePanel.position({
      my : my,
      at : at,
      of : $target
    });
    highlightTarget(zIndex);
  }
  
  this.hide = function() {
    removeHighlightFromTarget();
  }
  
  var updateMy = function($guidePanel) {
    var my = originMy;
    if (originMy.indexOf('center') !== -1) {
      var left;
      if (originMy.indexOf("center") == 0) {
        var centerAlign = ($target.outerWidth() - $guidePanel.outerWidth()) / 2;
        left = 'left+' + centerAlign;
      } else {
        left = originMy.substr(0, originMy.indexOf(' '));
      }
      var top;
      if (originMy.indexOf("center", originMy.length - "center".length) !== -1) {
        var centerAlign = ($target.outerHeight() - $guidePanel.outerHeight()) / 2;
        top = ' top+' + centerAlign;
      } else {
        top = originMy.substr(originMy.lastIndexOf(' '));
      }
      my = left + top;
    }
    return my;
  }
  
  var highlightTarget = function(zIndex) {
    positionTarget(targetPosition, 'white', zIndex);
    setGuideModalZIndex(zIndex);
  }
  
  var removeHighlightFromTarget = function() {
    positionTarget(originTargetPosition, originTargetBackgroundColor, originTargetZIndex);
    setGuideModalZIndex(-1);
  }
  
  var positionTarget = function(position, backgroundColor, zIndex) {
    $target.css({
      'position' : position,
      'background-color' : backgroundColor,
      'z-index' : zIndex
    });
  }
  
  var setGuideModalZIndex = function(zIndex) {
    $('#guide-modal').css('z-index', zIndex);
  }
  
  var positionArrow = function() {
    var $arrow = $('.guide-panel:visible .guide-panel__arrow');
    var side = arrowPosition.substr(0, arrowPosition.indexOf(' '));
    var pos = arrowPosition.substr(arrowPosition.lastIndexOf(' ') + 1);
    var posValue = '15px';
    if (pos === 'center') {
      pos = 'left';
      posValue = 'calc(50% - 15px)';
    }
    
    var mainAngle = 'border-bottom';
    var angle1 = 'border-left';
    var angle2 = 'border-right';
    if (side === 'bottom') {
      mainAngle = 'border-top';
    } else if (side === 'left') {
      mainAngle = 'border-right';
      angle1 = 'border-top';
      angle2 = 'border-bottom';
    } else if (side === 'right') {
      mainAngle = 'border-left';
      angle1 = 'border-top';
      angle2 = 'border-bottom';
    }
    
    $arrow.css(side, '-15px')
      .css(pos, posValue)
      .css(mainAngle, '15px solid white')
      .css(angle1, '15px solid transparent')
      .css(angle2, '15px solid transparent');
  }
}