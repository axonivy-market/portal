function ResponsiveButton() {
	function hideComponent($hiddenComponent, fadeTime) {
	    $hiddenComponent.attr("style", "display: none; opacity: 1").animate({
	      'opacity' : '0'
	    }, fadeTime);
	  }

	  function displayComponent($displayedComponent, fadeTime) {
	    $displayedComponent.attr("style", "display: inline-block; opacity: 0").animate({
	      'opacity' : '1'
	    }, fadeTime);
	  }
	 
	  return {
	    apply : function(data, onStartCallback, onCompleteCallback) {
	      var displayedClass = 'replaced';
	      var selectedClass = 'is-selected is-clicked';
	      var $clickedLink = $(data.clickedLink);
	      
	      if ($clickedLink.hasClass('is-selected')) {
	        return;
	      }
	      
	      
	      if (typeof onStartCallback === 'function') {
	        onStartCallback();
	      }

	      if (viewPort.isLargeScreen()) {
	        data.hiddenSelectorsInLargeScreen.forEach(function(hiddenSelector) {
	          var $hiddenComponent = $(hiddenSelector);
	          hideComponent($hiddenComponent, data.fadeTime);
	          $hiddenComponent.removeClass(displayedClass);
	        });
	      } else if (viewPort.isMediumScreen()) {
	        data.hiddenSelectorsInMediumScreen.forEach(function(hiddenSelector) {
	          var $hiddenComponent = $(hiddenSelector);
	          hideComponent($hiddenComponent, data.fadeTime);
	          $hiddenComponent.removeClass(displayedClass);
	        });
	      } else if (viewPort.isSmallScreen()) {
	        data.hiddenSelectorsInSmallScreen.forEach(function(hiddenSelector) {
	          var $hiddenComponent = $(hiddenSelector);
	          hideComponent($hiddenComponent, data.fadeTime);
	          $hiddenComponent.removeClass(displayedClass);
	        });
	      }

	      data.displayedSelectors.forEach(function(displayedSelector) {
	        var $displayedComponent = $(displayedSelector);
	        displayComponent($displayedComponent, data.fadeTime);
	        $displayedComponent.addClass(displayedClass);
	      });
	      $clickedLink.addClass(selectedClass).siblings().removeClass(selectedClass);

	      if (typeof onCompleteCallback === 'function') {
	        onCompleteCallback();
	      }
	    }
	  };
};
var responsiveButton = ResponsiveButton();